package main.pojo;

import main.search.SearchSourceEnum;

/**
 * @author ilyakirpichev
 */
public class SearchItem {
    private final SearchSourceEnum searchSource;
    private final String text;

    public SearchItem(SearchSourceEnum searchSource, String text) {
        this.searchSource = searchSource;
        this.text = text;
    }

    public SearchSourceEnum getSearchSource() {
        return searchSource;
    }

    public String getText() {
        return text;
    }
}
