package ru.itmo.kirpichev.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.kirpichev.vk.VkApiHashtagClient;
import ru.itmo.kirpichev.vk.VkApiParams;

/**
 * @author ilyakirpich
 */
class VkApiHashtagClientTest {
    @Test
    void createUrlTest() {
        String actual = "https://api.vk.com/method/newsfeed.get?access_token=token&v=5.131&start_time=0";
        VkApiHashtagClient client = new VkApiHashtagClient("api.vk.com");
        Assertions.assertEquals(actual, client.createUrl(new VkApiParams("token", "far", "5.131", 0L)));
    }
}