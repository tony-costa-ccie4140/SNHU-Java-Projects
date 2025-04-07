package com.kenzie.app;

// import necessary libraries
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Scanner;



public class Application {
    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!
     */


    public static void main(String[] args) throws JsonProcessingException {
        //Write main execution code here

        Scanner scanner = new Scanner(System.in);
        String playerAnswer;
        int playerScore = 0;

// Refactoring to use an alternate URL and API due to Kenzie jService cert failure
//        String singleClueURL = "https://jservice.kenzie.academy/api/random-clue";
        String singleClueURL = "http://jservice.io/api/random?count=1";
        String singleClueJSON;
        SingleClueDTO clueDTO = new SingleClueDTO();

        System.out.println("This is Something Like Jeopardy \n");

        //Loop 10 times
        for(int i = 0; i < 10; i++) {

            // Get a single clue into a DTO

            singleClueJSON = CustomHttpClient.sendGET(singleClueURL);

// Since the alternate API returns an array of one clue the next lines are a hack to remove the brackets from the JSON String
            singleClueJSON = singleClueJSON.replace("[", "");
            singleClueJSON = singleClueJSON.replace("]", "");

            if(singleClueJSON.contains("Error getting clue")) {
                System.out.println("Sorry I don't have a clue");
                break;
            }
            else {
                clueDTO = convertJsonToClue(singleClueJSON);
            }


            // Print Category.title
            System.out.println("Your Catgory is: " + clueDTO.getCategory().getTitle());
            // Print question
            System.out.println("And the Clue is: "+ clueDTO.getQuestion());

            // Read playerAnswer
            playerAnswer = scanner.nextLine();

            // Compare playerAnswer to getAnswer();
            //   if answers match
            if(clueDTO.getAnswer().toLowerCase().contains(playerAnswer.toLowerCase())) {
                //      playerScore++
                //      print getAnswer() + playerScore
                playerScore++;
                System.out.println("Correct! " + clueDTO.getAnswer() + "\nYour score is: " + playerScore + "\n");
            }
            //   else
            //      print sorry looking for getAnswer() + playerScore
            else {
                System.out.println("Sorry we were looking for " + clueDTO.getAnswer() + "\nYour score is: " + playerScore + "\n");
            }
        }
        // Thanks for playing! Your Score is: playerScore
        System.out.println("Thanks for playing! Your Score was: " + playerScore + " Way to go!!!");
    }

    private static SingleClueDTO convertJsonToClue(String singleClueJSON) throws JsonProcessingException {

        ObjectMapper singleClueMapper = new ObjectMapper();
        SingleClueDTO myDTO = singleClueMapper.readValue(singleClueJSON, SingleClueDTO.class);

        return myDTO;
    }
}