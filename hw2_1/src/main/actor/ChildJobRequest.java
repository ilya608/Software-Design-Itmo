package main.actor;

import main.search.SearchSourceEnum;

/**
 * @author ilyakirpichev
 */
public class ChildJobRequest {
    public final SearchSourceEnum source;
    public final String query;

    public ChildJobRequest(SearchSourceEnum source, String query) {
        this.source = source;
        this.query = query;
    }
}