package ru.akirakozov.sd.refactoring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.akirakozov.sd.refactoring.dto.ProductDto;
import ru.akirakozov.sd.refactoring.pojo.Product;

/**
 * @author ilyakirpichev
 */
public class ProductsDao {
    public static void insert(ProductDto product) throws RuntimeException {
        String sql = "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + product.name + "\"," + product.price + ")";
        executeSqlUpdate(sql);
    }

    public static List<Product> getAllProducts() throws RuntimeException {
        String sql = "SELECT * FROM PRODUCT";
        ResultSet sqlResult = executeSqlQuery(sql);
        final List<Product> products = new ArrayList<>();
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    products.add(new Product(name, price));
                }
                sqlResult.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return products;
    }

    private static ResultSet executeSqlQuery(String sql) throws RuntimeException {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet result = stmt.executeQuery(sql);
                stmt.close();
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void executeSqlUpdate(String sql) throws RuntimeException {
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
