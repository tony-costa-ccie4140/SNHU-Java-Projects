package com.kenzie.supportingmaterials;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MakeGetRequest {
    // Method to make a GET request
    public String makeGETRequest(String url) throws IOException {
        // This returns the string response from the request
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            // We will return the response body shortly
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }

    }

}
