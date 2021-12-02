package ru.akirakozov.sd.refactoring.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.akirakozov.sd.refactoring.pojo.Product;
import ru.akirakozov.sd.refactoring.service.ProductsService;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private final ProductsService productsService;

    public GetProductsServlet(ProductsService productsService) {
        super();
        this.productsService = productsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = productsService.getAllProducts();
            response.getWriter().println("<html><body>");
            for (Product p : products) {
                response.getWriter().println(p.getName() + "\t" + p.getPrice() + "</br>");
            }
            response.getWriter().println("</body></html>");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
