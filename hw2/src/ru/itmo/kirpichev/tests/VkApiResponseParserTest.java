package ru.itmo.kirpichev.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.itmo.kirpichev.vk.VkApiResponseParser;
import ru.itmo.kirpichev.vk.dto.HashtagDto;

/**
 * @author ilyakirpichev
 */
class VkApiResponseParserTest {
    @Test
    public void test() throws IOException {
        String inputJson = Files.readString(Path.of("src/ru/itmo/kirpichev/hw2/main.dto.tests/files/result.json"));
        VkApiResponseParser parser = new VkApiResponseParser();
        HashtagDto parsed = parser.parseResponse(inputJson);
        Assertions.assertEquals(parsed.response.items.stream().map(x -> x.date).sorted().collect(Collectors.toList()),
                List.of(1635185273L, 1635185294L, 1635185307L));

    }
}