package main.pojo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import main.search.SearchSourceEnum;

/**
 * @author ilyakirpichev
 */
public class SearchResult {
    private final List<SearchItem> results;

    public SearchResult(List<SearchItem> results) {
        this.results = results;
    }

    public List<SearchItem> getResults() {
        return results;
    }

    public final Set<SearchSourceEnum> getSuccessHosts() {
        return results.stream().map(SearchItem::getSearchSource).collect(Collectors.toSet());
    }
}
