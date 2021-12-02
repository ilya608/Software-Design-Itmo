package ru.akirakozov.sd.refactoring.queries;

import ru.akirakozov.sd.refactoring.utils.HtmlResultProvider;

/**
 * @author ilyakirpichev
 */
public class SumCommand extends Command {
    @Override
    public String execute() {
        long productsSum = productsService.getProductsSum();
        return HtmlResultProvider
                .prepare("Summary price: ", String.valueOf(productsSum));
    }
}
