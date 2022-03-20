package main.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import main.pojo.SearchItem;
import main.pojo.SearchResult;
import main.search.SearchClient;
import main.search.SearchSourceEnum;
import scala.concurrent.duration.Duration;

/**
 * @author ilyakirpichev
 */
public class MasterActor extends UntypedActor {
    private final static List<SearchSourceEnum> sources =
            List.of(SearchSourceEnum.YANDEX, SearchSourceEnum.YAHOO, SearchSourceEnum.GOOGLE);

    final List<SearchItem> result = new ArrayList<>();
    private final Integer countSources = 0;
    private ActorRef requestSender;
    private final SearchClient searchClient;

    public MasterActor(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof String) {
            requestSender = getSender();
            String query = (String) o;
            System.out.println("query " + query);
            for (SearchSourceEnum search : sources) {
                ActorRef actor =
                        getContext().actorOf(Props.create(ChildActor.class, searchClient));
                actor.tell(new ChildJobRequest(search, query), self());
            }
            getContext().setReceiveTimeout(Duration.create(1, TimeUnit.SECONDS));
        } else if (o instanceof ChildJobResult) {
            ChildJobResult jobResult = (ChildJobResult) o;
            result.addAll(jobResult.results);
            if (countSources == result.size()) {
                requestSender.tell(new SearchResult(result), self());
                getContext().stop(self());
            }
        } else if (o instanceof ReceiveTimeout) {
            requestSender.tell(new SearchResult(result), self());
            getContext().stop(self());
        }
    }
}