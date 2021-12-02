package ru.itmo.kirpichev.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.kirpichev.http.UrlReader;
import ru.itmo.kirpichev.rules.HostReachableRule;

/**
 * @author ilyakirpichev
 */
@HostReachableRule.HostReachable("api.vk.com/method/newsfeed.get")
class UrlReaderTest {
    @Test
    public void pingTest() {
        String url = "https://api.vk.com/method/newsfeed.get?&access_token=523e8fed16c237b878bd08a001c36b9a276990365f85051cb05885fdcf8cbf03d5612a5ac0b9e9c7965bd&v=5.131";
        String result = new UrlReader()
                .readAsText(url);
        Assertions.assertTrue(result.length() > 0);
    }
}