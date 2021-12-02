package ru.itmo.kirpichev.vk;

/**
 * @author ilyakirpichev
 */

public class VkApiParams {
    private final String accessToken;
    private final String tag;
    private final String version;
    private final Long startTime;

    public VkApiParams(String accessToken, String tag, String version, Long startTime) {
        this.accessToken = accessToken;
        this.tag = tag;
        this.version = version;
        this.startTime = startTime;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getVersion() {
        return version;
    }

    public Long getStartTime() {
        return startTime;
    }
}
