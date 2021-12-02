package ru.itmo.kirpichev.vk.dto;

import com.google.gson.annotations.SerializedName;

/**
 * @author ilyakirpichev
 */
public class HashtagDto {
    @SerializedName("response")
    public ResponseDto response;

    public HashtagDto(ResponseDto response) {
        this.response = response;
    }
}
