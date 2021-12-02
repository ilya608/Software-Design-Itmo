package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import ru.akirakozov.sd.refactoring.pojo.Product;
import ru.akirakozov.sd.refactoring.queries.Command;
import ru.akirakozov.sd.refactoring.queries.CommandTypeEnum;
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
        String commandStr = request.getParameter("command");
        String result = "";
        try {
            Command command = CommandTypeEnum.commandByTypeElseThrow(commandStr);
            result = command.execute();
        } catch (Exception e) {
            response.getWriter().println("Unknown command: " + commandStr);
        }
        response.getWriter().println(result);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
