package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ru.akirakozov.sd.refactoring.pojo.Product;
import ru.akirakozov.sd.refactoring.service.ProductsService;
import ru.akirakozov.sd.refactoring.utils.HtmlResultProvider;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    private final ProductsService productsService;

    public QueryServlet(ProductsService productsService) {
        super();
        this.productsService = productsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            try {
                Product maxPriceProduct = productsService.getMaxPriceProduct();
                response.getWriter().println(HtmlResultProvider.prepare("<h1>Product with max price: </h1>",
                        maxPriceProduct.getName() + "\t" + maxPriceProduct.getPrice() + "</br>"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("min".equals(command)) {
            try {
                Product minPriceProduct = productsService.getMinPriceProduct();
                response.getWriter().println(HtmlResultProvider.prepare("<h1>Product with min price: </h1>",
                        minPriceProduct.getName() + "\t" + minPriceProduct.getPrice() + "</br>"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("sum".equals(command)) {
            try {
                long productsSum = productsService.getProductsSum();
                response.getWriter().println(HtmlResultProvider
                        .prepare("Summary price: ", String.valueOf(productsSum)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("count".equals(command)) {
            try {
                long productsCount = productsService.getProductsCount();
                response.getWriter()
                        .println(HtmlResultProvider.prepare("Number of products: ", String.valueOf(productsCount)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
