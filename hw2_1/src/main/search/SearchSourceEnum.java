package main.search;

/**
 * @author ilyakirpichev
 */
public enum SearchSourceEnum {
    YANDEX("yandex"),
    YAHOO("yahoo"),
    GOOGLE("google");

    private final String source;

    SearchSourceEnum(String source) {
        this.source = source;
    }

    public String getSourceHost() {
        return source;
    }

}
