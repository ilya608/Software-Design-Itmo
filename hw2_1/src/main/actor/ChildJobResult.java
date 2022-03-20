package main.actor;

import java.util.List;

import main.pojo.SearchItem;
import main.search.SearchSourceEnum;

/**
 * @author ilyakiripchev
 */
public class ChildJobResult {
    public final SearchSourceEnum source;
    public final List<SearchItem> results;

    public ChildJobResult(SearchSourceEnum source, List<SearchItem> results) {
        this.source = source;
        this.results = results;
    }
}