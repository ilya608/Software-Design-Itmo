package ru.itmo.kirpichev.vk;

import com.google.gson.Gson;
import ru.itmo.kirpichev.vk.dto.HashtagDto;

/**
 * @author ilyakirpichev
 */
public class VkApiResponseParser {
    private final Gson gson;

    public VkApiResponseParser() {
        this.gson = new Gson();
    }

    public HashtagDto parseResponse(String response) {
        return gson.fromJson(response, HashtagDto.class);
    }
}
