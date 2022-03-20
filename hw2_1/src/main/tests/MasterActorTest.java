package main.tests;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import main.actor.MasterActor;
import main.pojo.SearchResult;
import main.search.SearchSourceEnum;
import main.search.SearchStubClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static akka.pattern.PatternsCS.ask;

/**
 * @author ilyakirpichev
 */
public class MasterActorTest {
    private ActorSystem system;

    @Before
    public void setUp() {
        system = ActorSystem.create("MasterActorTest");
    }

    @After
    public void tearDown() {
        system.terminate();
    }

    @Test
    public void testMasterActor() throws Exception {
        SearchStubClient client = new SearchStubClient(Map.of());
        ActorRef masterActor = system.actorOf(Props.create(MasterActor.class, client));

        Object responseObject = ask(
                masterActor,
                "query",
                Timeout.apply(10, TimeUnit.SECONDS)
        ).toCompletableFuture().join();
        if (responseObject instanceof SearchResult) {
            SearchResult response = (SearchResult) responseObject;
            Assertions.assertFalse(response.getResults().isEmpty());
            Assertions.assertEquals(response.getSuccessHosts(), new HashSet<>(EnumSet.allOf(SearchSourceEnum.class)));
        } else {
            throw new Exception("Can not parse SearchResult");
        }
    }

    @Test
    public void testMasterActorTimeout() {
        ActorRef masterActor = system.actorOf(
                Props.create(MasterActor.class, new SearchStubClient(Map.of(SearchSourceEnum.YANDEX, 10000L))));

        SearchResult response = (SearchResult) ask(
                masterActor,
                "query",
                Timeout.apply(10, TimeUnit.SECONDS)
        ).toCompletableFuture().join();

        Set<SearchSourceEnum> expected = new HashSet<>(EnumSet.allOf(SearchSourceEnum.class));
        expected.remove(SearchSourceEnum.YANDEX);
        Assertions.assertEquals(expected, response.getSuccessHosts());

    }
}
