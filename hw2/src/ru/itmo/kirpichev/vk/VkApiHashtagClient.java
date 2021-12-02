package ru.itmo.kirpichev.vk;

import org.apache.http.client.utils.URIBuilder;
import ru.itmo.kirpichev.http.UrlReader;
import ru.itmo.kirpichev.vk.dto.HashtagDto;

/**
 * @author ilyakirpichev
 */
public class VkApiHashtagClient {
    private final String host;
    private final VkApiResponseParser parser;
    private final UrlReader reader;

    public VkApiHashtagClient(String host) {
        this.host = host;
        this.parser = new VkApiResponseParser();
        this.reader = new UrlReader();
    }

    public HashtagDto getResponseDto(VkApiParams params) {
        String url = createUrl(params);
        String response = reader.readAsText(url);
        return parser.parseResponse(response);
    }

    public String createUrl(VkApiParams params) {
        return new URIBuilder()
                .setScheme("https")
                .setHost(host + "/method/newsfeed.get")
                .addParameter("access_token", params.getAccessToken())
                .addParameter("v", params.getVersion())
                .addParameter("start_time", params.getStartTime().toString())
                .toString();

    }
}
