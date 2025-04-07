package com.kenzie.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

// The following are refactored to use an alternate URL and API due to Kenzie cert failure

public class SingleClueDTO {
    @JsonProperty("id")
    private int id;
    @JsonProperty("category")
    private Category category;
    @JsonProperty("game_id")
    private int gameId;
    @JsonProperty("category_id")
    private int categoryId;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("airdate")
    private String airdate;
    @JsonProperty("question")
    private String question;
    @JsonProperty("answer")
    private String answer;

    public static class Category {
        @JsonProperty("clues_count")
        private int cluesCount;
        @JsonProperty("updated_at")
        private String updatedAt;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("title")
        private String title;
        @JsonProperty("id")
        private int id;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // Commenting the original DTO properties and methods due to Kenzie Cert Issue
//    @JsonProperty("canon")
//    private boolean canon;
//    @JsonProperty("game")
//    private Game game;
//    @JsonProperty("category")
//    private static Category category;
//    @JsonProperty("invalidCount")
//    private int invalidcount;
//    @JsonProperty("gameId")
//    private int gameid;
//    @JsonProperty("categoryId")
//    private int categoryid;
//    @JsonProperty("value")
//    private int value;
//    @JsonProperty("question")
//    private String question;
//    @JsonProperty("answer")
//    private String answer;
//    @JsonProperty("id")
//    private int id;
//
//    public static class Game {
//        @JsonProperty("canon")
//        private boolean canon;
//        @JsonProperty("aired")
//        private String aired;
//    }
//
//    public static class Category {
//        @JsonProperty("canon")
//        private boolean canon;
//        @JsonProperty("title")
//        private String title;
//        @JsonProperty("id")
//        private int id;
//
//        public String getTitle() {
//            return title;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//    }
//
//    public static Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public void setQuestion(String question) {
//        this.question = question;
//    }
//
//    public String getAnswer() {
//        return answer;
//    }
//
//    public void setAnswer(String answer) {
//        this.answer = answer;
//    }
}
