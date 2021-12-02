package ru.itmo.kirpichev.vk.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * @author ilyakirpichev
 */
public class ResponseDto {
    @SerializedName("items")
    public List<ItemDto> items;

    public ResponseDto(List<ItemDto> items) {
        this.items = items;
    }
}