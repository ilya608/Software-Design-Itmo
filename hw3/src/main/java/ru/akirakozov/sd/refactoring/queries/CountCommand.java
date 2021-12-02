package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.utils.HtmlResultProvider;

/**
 * @author ilyakirpichev
 */
public class CountCommand extends Command {
    @Override
    public String execute() {
        long productsCount = productsService.getProductsCount();
        return HtmlResultProvider.prepare("Number of products: ", String.valueOf(productsCount));
    }
}
