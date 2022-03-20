package main.search;


import java.util.List;

import main.pojo.SearchItem;
import main.pojo.SearchRequest;


/**
 * @author ilyakirpichev
 */
public interface SearchClient {
    public List<SearchItem> search(SearchRequest request);
}
