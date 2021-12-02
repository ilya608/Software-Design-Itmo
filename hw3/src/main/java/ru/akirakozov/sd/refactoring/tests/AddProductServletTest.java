package ru.akirakozov.sd.refactoring.tests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.service.ProductsService;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

/**
 * @author ilyakirpichev
 */
class AddProductServletTest {
    private static Server server;

    @Test
    public void clearDatabaseTest() throws Exception {
        clearDatabase();
        HttpResponse<String> res = getProducts();
        Assertions.assertEquals(200, res.statusCode());
    }

    @Test
    public void getProducts_whenDatabaseIsClear_thenResponse200Expected() throws Exception {
        // given
        clearDatabase();

        String name1 = "iphone 6";
        Integer price1 = 100;
        String name2 = "samsung";
        Integer price2 = 50;

        // when
        addProduct(name1, 100);
        addProduct(name2, 50);

        // ten
        HttpResponse<String> res = getProducts();

        Assertions.assertTrue(res.body().contains(name1));
        Assertions.assertTrue(res.body().contains(name2));

        Assertions.assertTrue(res.body().contains(price1.toString()));
        Assertions.assertTrue(res.body().contains(price2.toString()));

    }

    @Test
    public void querySum_whenDatabaseFilled_thenExpectCorrectSum() throws Exception {
        clearDatabase();
        String name1 = "iphone 6";
        String name2 = "samsung";

        addProduct(name1, 100);
        addProduct(name2, 50);
        HttpResponse<String> res = getQuery("sum");
        Assertions.assertTrue(res.body().contains("150"));
    }

    @Test
    public void querySum_whenDatabaseFilled_thenExpectCorrectCount() throws Exception {
        clearDatabase();
        String name1 = "iphone 6";
        String name2 = "samsung";
        String name3 = "xiaomi";

        addProduct(name1, 100);
        addProduct(name2, 50);
        addProduct(name3, 70);

        HttpResponse<String> res = getQuery("count");
        Assertions.assertTrue(res.body().contains("3"));
    }

    @Test
    public void querySum_whenDatabaseFilled_thenExpectCorrectMin() throws Exception {
        clearDatabase();
        String name1 = "iphone 6";
        String name2 = "samsung";
        String name3 = "xiaomi";

        addProduct(name1, 100);
        addProduct(name2, 50);
        addProduct(name3, 70);

        HttpResponse<String> res = getQuery("min");
        Assertions.assertTrue(res.body().contains("50"));
    }

    @Test
    public void querySum_whenDatabaseFilled_thenExpectCorrectMax() throws Exception {
        clearDatabase();
        String name1 = "iphone 6";
        String name2 = "samsung";
        String name3 = "xiaomi";

        addProduct(name1, 100);
        addProduct(name2, 50);
        addProduct(name3, 70);

        HttpResponse<String> res = getQuery("max");
        Assertions.assertTrue(res.body().contains("100"));
    }

    @Test
    public void addOneProductTest() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> res = addProduct("iphone", 1200);
        Assertions.assertEquals(200, res.statusCode());
    }

    public HttpResponse<String> getProducts() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URIBuilder()
                        .setScheme("http")
                        .setHost("localhost")
                        .setPath("get-products")
                        .setPort(8081)
                        .build())
                .build();
        return client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> addProduct(String name, int price) throws IOException, InterruptedException,
            URISyntaxException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URIBuilder()
                        .setScheme("http")
                        .setHost("localhost")
                        .setPath("add-product")
                        .setPort(8081)
                        .addParameter("name", name)
                        .addParameter("price", String.valueOf(price))
                        .build())
                .build();
        return client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> getQuery(String command) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URIBuilder()
                        .setScheme("http")
                        .setHost("localhost")
                        .setPath("query")
                        .setPort(8081)
                        .addParameter("command", command)
                        .build())
                .build();
        return client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

    private void clearDatabase() throws Exception {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            String sql = "delete from PRODUCT";
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    @BeforeAll
    public static void startServer() throws Exception {
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            String sql = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " PRICE          INT     NOT NULL)";
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        }

        server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ProductsDao productsDao = new ProductsDao();
        ProductsService productsService = new ProductsService(productsDao);

        context.addServlet(new ServletHolder(new AddProductServlet(productsService)), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet(productsService)), "/get-products");
        context.addServlet(new ServletHolder(new QueryServlet(productsService)), "/query");

        server.start();
    }

    @AfterAll
    public static void stopServer() throws InterruptedException {
        server.join();
    }
}