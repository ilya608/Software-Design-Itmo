package ru.akirakozov.sd.refactoring.dto;

/**
 * @author ilyakirpichev
 */
public class ProductDto {
    public final String name;
    public final long price;


    public ProductDto(String name, long price) {
        this.name = name;
        this.price = price;
    }
}
