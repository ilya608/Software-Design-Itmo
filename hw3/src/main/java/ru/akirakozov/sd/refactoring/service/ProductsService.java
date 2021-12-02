package ru.akirakozov.sd.refactoring.service;

import java.util.List;

import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.dto.ProductDto;
import ru.akirakozov.sd.refactoring.pojo.Product;

/**
 * @author ilyakirpichev
 */
public class ProductsService {
    private final ProductsDao productsDao;

    public ProductsService(ProductsDao dao) {
        this.productsDao = dao;
    }

    public void insert(String name, long price) throws RuntimeException {
        ProductDto newProductDto = new ProductDto(name, price);
        ProductsDao.insert(newProductDto);
    }

    public List<Product> getAllProducts() throws RuntimeException {
        return ProductsDao.getProductsBySql("SElECT * FROM PRODUCT");
    }

    public Product getMaxPriceProduct() throws RuntimeException {
        return ProductsDao.getProductsBySql("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1").get(0);
    }

    public Product getMinPriceProduct() throws RuntimeException {
        return ProductsDao.getProductsBySql("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1").get(0);
    }

    public long getProductsCount() throws RuntimeException {
        return ProductsDao.aggregateFunctionBySql("SELECT COUNT(*) FROM PRODUCT");
    }

    public long getProductsSum() throws RuntimeException {
        return ProductsDao.aggregateFunctionBySql("SELECT SUM(price) FROM PRODUCT");
    }
}
