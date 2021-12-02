package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.pojo.Product;
import ru.akirakozov.sd.refactoring.utils.HtmlResultProvider;

/**
 * @author ilyakirpichev
 */
public class MinCommand extends Command{
    @Override
    public String execute() {
        Product minPriceProduct = productsService.getMinPriceProduct();
        return HtmlResultProvider.prepare("<h1>Product with min price: </h1>",
                minPriceProduct.getName() + "\t" + minPriceProduct.getPrice() + "</br>");
    }
}
