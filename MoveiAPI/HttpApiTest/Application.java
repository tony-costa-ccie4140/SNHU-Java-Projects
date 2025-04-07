package com.kenzie.app;

import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Application {
    // Initialize the required constant variable here
    static String GET_URL = "http://www.boredapi.com/api/activity"; // Gets a random activity

    //Formats URL query string with one property
    public static String formatURL(String URLString, String parameter, String value){
        //TODO: Fill out method and update return value

        String completeURL = URLString + "?" + parameter + "=" + value;

        return completeURL;

    }

    //Overload method: Formats URL query string with two properties
    public static String formatURL(String URLString, String parameter1, String value1,String parameter2, String value2){
        //TODO: Fill out method and update return value

        String completeURL = URLString + "?" + parameter1 + "=" + value1 + "&" + parameter2 + "=" + value2;
        System.out.println(completeURL);
        return completeURL;
    }

    public static String getURLResponse(String URLString) {
        //TODO: Fill out method and update return value

        HttpClient getIt = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);

        HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> httpResponse;

        try {
            httpResponse = getIt.send(apiRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }

        int statusCode = httpResponse.statusCode();

        if (statusCode == 200) {
            return httpResponse.body();
        } else {
            return String.format("Get request failed: %d status code received", statusCode);

        }
    }

    public static String formatActivityOutput(String jsonString) throws JsonProcessingException {

        if (jsonString.contains("No Activity Found") || jsonString.contains("error")) {
            return "No Activity Found";
        }
        else {
            ObjectMapper objectMapper = new ObjectMapper();
            ActivityDTO activityDTO = objectMapper.readValue(jsonString, ActivityDTO.class);

            return (activityDTO.toString());
        }

    }

    public static String getActivityRandom(String URL) {
        //TODO: Fill out method and update return value
        String jsonReturned = getURLResponse(URL);

       return jsonReturned;

    }

    public static String getActivityType(String URL, String type) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        //TODO: Fill out method and update return value

        String formattedURL = formatURL(URL, "type", type);

        return getURLResponse(formattedURL);
    }

    public static String getActivityWithPrice(String URL, double price) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
        //TODO: Fill out method and update return value

        String priceString = Double.toString(price);
        String formattedURL = formatURL(URL, "price", priceString);

        return getURLResponse(formattedURL);

    }

    public static String getActivityTypeForGroup(String URL, String type, int numParticipants) {
        //TODO: Fill out method and update return value

        String numParticipantsString = Integer.toString(numParticipants);
        String formattedURL = formatURL(URL, "type", type, "participants", numParticipantsString);

        return getURLResponse(formattedURL);
    }

    /** Do not modify main method **/
    public static void main(String[] args) throws IOException {
        String response;
        try {
            System.out.println("Are you feeling bored? Try these activities: ");

            //parse JSON string into formatted output
            System.out.println(formatActivityOutput(getActivityRandom(GET_URL)));
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "education")));
            System.out.println(formatActivityOutput(getActivityWithPrice(GET_URL, 0)));
            System.out.println(formatActivityOutput(getActivityTypeForGroup(GET_URL, "recreational",4)));

            //Test for error checking: this last one does not have a return. Should print "No activity found"
            System.out.println(formatActivityOutput(getActivityType(GET_URL, "mayhem")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}