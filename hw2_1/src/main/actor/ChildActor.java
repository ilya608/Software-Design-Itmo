package main.actor;

import java.util.List;

import akka.actor.UntypedActor;
import main.pojo.SearchItem;
import main.pojo.SearchRequest;
import main.search.SearchClient;

/**
 * @author ilyakirpichev
 */
public class ChildActor extends UntypedActor {
    private final SearchClient searchClient;

    public ChildActor(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof ChildJobRequest) {
            ChildJobRequest job = (ChildJobRequest) o;
            List<SearchItem> results = searchClient.search(new SearchRequest(job.source, job.query));
            ChildJobResult jobResult = new ChildJobResult(job.source, results);
            getSender().tell(jobResult, self());
            getContext().stop(self());
        }
    }
}
