package ru.akirakozov.sd.refactoring.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.akirakozov.sd.refactoring.service.ProductsService;

/**
 * @author akirakozov
 */
public class AddProductServlet extends HttpServlet {

    private final ProductsService productsService;

    public AddProductServlet(ProductsService productsService) {
        super();
        this.productsService = productsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        long price = Long.parseLong(request.getParameter("price"));

        productsService.insert(name, price);

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("OK");
    }
}
