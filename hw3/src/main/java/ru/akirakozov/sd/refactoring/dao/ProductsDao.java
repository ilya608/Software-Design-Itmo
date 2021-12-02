package ru.akirakozov.sd.refactoring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.akirakozov.sd.refactoring.dto.ProductDto;
import ru.akirakozov.sd.refactoring.pojo.Product;

/**
 * @author ilyakirpichev
 */
public class ProductsDao {
    public void insert(ProductDto product) throws RuntimeException {
        String sql = "INSERT INTO PRODUCT " +
                "(NAME, PRICE) VALUES (\"" + product.name + "\"," + product.price + ")";
        executeSqlUpdate(sql);
    }

    public List<Product> getProductsBySql(String sql) throws RuntimeException {
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
                rs.close();
                stmt.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public long aggregateFunctionBySql(String sql) throws RuntimeException {
        final long result;
        try {
            try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                if (rs.next()) {
                    result = rs.getLong(1);
                } else {
                    throw new RuntimeException("No result in aggregate function");
                }
                rs.close();
                stmt.close();
                return result;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void executeSqlUpdate(String sql) throws RuntimeException {
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
