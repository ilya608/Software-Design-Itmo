package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.pojo.Product;
import ru.akirakozov.sd.refactoring.utils.HtmlResultProvider;

/**
 * @author ilyakirpichev
 */
public class MaxCommand extends Command {
    @Override
    public String execute() {
        Product maxPriceProduct = productsService.getMaxPriceProduct();
        return HtmlResultProvider.prepare("<h1>Product with max price: </h1>",
                maxPriceProduct.getName() + "\t" + maxPriceProduct.getPrice() + "</br>");
    }
}
