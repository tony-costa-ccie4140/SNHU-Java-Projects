package com.kenzie.app;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CustomHttpClient {

    //TODO: Write sendGET method that takes URL and returns response
    public static String sendGET(String URLString) {
        //** Start of GET request algorithm

        String safeURL = URLString.replaceAll(" ", "+");

        HttpClient getAlexTrebek = HttpClient.newHttpClient();
        URI uri = URI.create(safeURL);

        HttpRequest singleClueRequest = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse;

        try {
            httpResponse = getAlexTrebek.send(singleClueRequest, HttpResponse.BodyHandlers.ofString());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int statusCode = httpResponse.statusCode();

        if(statusCode == 200) {
            return httpResponse.body();
        }
        else {
            return "Error getting clue for " + safeURL;
        }
    }
}
