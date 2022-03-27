import dao.DaoUtils;
import http.RxNettyHttpController;
import io.reactivex.netty.protocol.http.server.HttpServer;
import rx.Observable;

public class Main {
    public static void main(String[] args) {
        RxNettyHttpController controller = new RxNettyHttpController(DaoUtils.createReactiveDao());

        HttpServer.newServer(8080);

        HttpServer
                .newServer(8080)
                .start((req, resp) -> {
                    Observable<String> response = controller.getResponse(req);
                    return resp.writeString(response);
                })
                .awaitShutdown();
    }
}
