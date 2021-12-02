package ru.akirakozov.sd.refactoring.service;

import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.dto.ProductDto;

/**
 * @author ilyakirpichev
 */
public class ProductsService {
    private final ProductsDao productsDao;

    public ProductsService(ProductsDao dao) {
        this.productsDao = dao;
    }

    public void insert(String name, int price) {
        ProductDto newProductDto = new ProductDto(name, price);
        ProductsDao.insert(newProductDto);
    }
}
