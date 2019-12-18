import org.json.JSONException;
import org.json.JSONObject;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class StartServer {
    private ClientAndServer mockServer;

    public static void main(String[] args) throws JSONException {
        StartServer m = new StartServer();
        m.start();
    }

    public void start() throws JSONException {
        mockServer = startClientAndServer(1080);
        this.init();
    }

    public void init() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("status", "ok");
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/hello")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody("HI TO YOU!")

                );

        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/postjson")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(json("{'status': 'ok'}"))

                );
    }
}
