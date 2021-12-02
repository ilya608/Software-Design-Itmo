package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.dao.ProductsDao;
import ru.akirakozov.sd.refactoring.service.ProductsService;

/**
 * @author ilyakirpichev
 */
public abstract class Command {
    protected final ProductsService productsService = new ProductsService(new ProductsDao());
    public abstract String execute();
}
