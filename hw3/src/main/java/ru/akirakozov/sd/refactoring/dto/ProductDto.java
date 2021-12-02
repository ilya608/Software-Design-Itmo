package ru.akirakozov.sd.refactoring.dto;

/**
 * @author ilyakirpichev
 */
public class ProductDto {
    public final String name;
    public final int price;


    public ProductDto(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
