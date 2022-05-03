package mitchell.dnd.dndzombieorganiser.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class CallManager {

    private HttpResponse<String> httpResponse = null;
    private boolean connected = false;

    public CallManager(String url) {
        getRequest(url);
    }

    private void getRequest(String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        makeRequest(httpClient, httpRequest);
    }

    private void makeRequest(HttpClient httpClient, HttpRequest httpRequest) {
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            connected = true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Optional<JsonNode> getJson() {
        if (!connected) {
            return Optional.empty();
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
             node = mapper.readTree(httpResponse.body());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(node) ;
    }

    public int getStatusCode() {
        return connected ? httpResponse.statusCode() : 500;
    }
}
