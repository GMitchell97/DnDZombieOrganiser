package mitchell.dnd.dndzombieorganiser.api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CallManager {

    private HttpResponse<String> httpResponse = null;

    public CallManager(String url) throws IOException, InterruptedException {
        getRequest(url);
    }

    private void getRequest(String url) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        makeRequest(httpClient, httpRequest);
    }

    private void makeRequest(HttpClient httpClient, HttpRequest httpRequest) throws IOException, InterruptedException {
        httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

    public String getJson() {
        return httpResponse.body();
    }

    public int getStatusCode() {
        return httpResponse.statusCode();
    }
}
