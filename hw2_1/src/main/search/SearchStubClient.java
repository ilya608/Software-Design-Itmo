package main.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.pojo.SearchItem;
import main.pojo.SearchRequest;

/**
 * @author ilyakirpichev
 */
public class SearchStubClient implements SearchClient {
    private final int RESULTS_FROM_HOST = 5;
    private final Map<SearchSourceEnum, Long> hostToDelay;

    public SearchStubClient(Map<SearchSourceEnum, Long> hostToDelay) {
        this.hostToDelay = hostToDelay;
    }

    @Override
    public List<SearchItem> search(SearchRequest request) {
        List<SearchItem> results = new ArrayList<>();
        for (int i = 0; i < RESULTS_FROM_HOST; ++i) {
            results.add(new SearchItem(
                    request.getSource(),
                    generateTitle(request.getSource().getSourceHost(), request.getQuery(), i)));
        }
        try {
            Thread.sleep(hostToDelay.getOrDefault(request.getSource(), 0L));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return results;
        }
        return results;
    }

    private String generateTitle(String host, String query, int pos) {
        return String.format("%s: Search result for query:%s with pos: %s", host, query, pos);
    }
}
