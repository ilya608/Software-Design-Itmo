package ru.itmo.kirpichev.vk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author ilyakirpichev
 */
public class ItemDto {
    @SerializedName("source_id")
    public String sourceId;

    @SerializedName("date")
    public Long date;

    public ItemDto(String sourceId, Long date) {
        this.sourceId = sourceId;
        this.date = date;
    }
}