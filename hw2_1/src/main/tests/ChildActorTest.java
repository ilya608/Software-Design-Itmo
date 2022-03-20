package main.tests;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.util.Timeout;
import main.actor.ChildActor;
import main.actor.ChildJobRequest;
import main.actor.ChildJobResult;
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
public class ChildActorTest {
    private ActorSystem system;

    @Before
    public void setUp() {
        system = ActorSystem.create("ChildActorTest");
    }

    @After
    public void tearDown() {
        system.terminate();
    }

    @Test
    public void testChildActor() {
        ActorRef childActor = system.actorOf(Props.create(ChildActor.class, new SearchStubClient(Map.of())));

        ChildJobResult response = (ChildJobResult) ask(
                childActor,
                new ChildJobRequest(SearchSourceEnum.YANDEX, "query"),
                Timeout.apply(10, TimeUnit.SECONDS)
        ).toCompletableFuture().join();

        Assertions.assertEquals(response.source, SearchSourceEnum.YANDEX);
        Assertions.assertFalse(response.results.isEmpty());
    }
}
