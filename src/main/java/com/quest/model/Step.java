package com.quest.model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Step implements Serializable {
    private String id;
    private String title;
    private String description;
    private Map<String, Choice> choices = new LinkedHashMap<>();
    private boolean isFinal;
    private boolean isWin;

    public Step() {}

    public Step(String id, String title, String description, boolean isFinal, boolean isWin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isFinal = isFinal;
        this.isWin = isWin;
    }

    public void addChoice(String id, String text, String nextStepId) {
        choices.put(id, new Choice(text, nextStepId));
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Map<String, Choice> getChoices() { return choices; }
    public boolean isFinal() { return isFinal; }
    public boolean isWin() { return isWin; }

    public static class Choice implements Serializable {
        private String text;
        private String nextStepId;

        public Choice(String text, String nextStepId) {
            this.text = text;
            this.nextStepId = nextStepId;
        }

        public String getText() { return text; }
        public String getNextStepId() { return nextStepId; }
    }
}