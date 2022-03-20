package main.pojo;

import main.search.SearchSourceEnum;

/**
 * @author ilyakirpichev
 */
public class SearchRequest {
    private final SearchSourceEnum source;
    private final String query;

    public SearchRequest(SearchSourceEnum source, String query) {
        this.source = source;
        this.query = query;
    }

    public SearchSourceEnum getSource() {
        return source;
    }

    public String getQuery() {
        return query;
    }
}
