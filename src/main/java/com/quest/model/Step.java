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

    // Геттеры и сеттеры для стандартных свойств

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Choice> getChoices() {
        return choices;
    }

    public void setChoices(Map<String, Choice> choices) {
        this.choices = choices;
    }

    // ВАЖНО: Геттеры и сеттеры для boolean свойств

    // Для свойства isFinal - стандартный геттер
    public boolean isFinal() {
        return isFinal;
    }

    // Для EL (Expression Language) - альтернативный геттер
    // Используйте в JSP: ${step.finalStep}
    public boolean getFinalStep() {
        return isFinal;
    }

    // Сеттер для isFinal
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    // Для свойства isWin - стандартный геттер
    public boolean isWin() {
        return isWin;
    }

    // Для EL - этот геттер будет использоваться при ${step.win}
    public boolean getWin() {
        return isWin;
    }

    // Сеттер для isWin
    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }

    // Внутренний класс Choice
    public static class Choice implements Serializable {
        private String text;
        private String nextStepId;

        public Choice(String text, String nextStepId) {
            this.text = text;
            this.nextStepId = nextStepId;
        }

        // Геттеры
        public String getText() {
            return text;
        }

        public String getNextStepId() {
            return nextStepId;
        }

        // Сеттеры (опционально, но полезно)
        public void setText(String text) {
            this.text = text;
        }

        public void setNextStepId(String nextStepId) {
            this.nextStepId = nextStepId;
        }
    }

    // Дополнительные полезные методы

    public boolean hasChoices() {
        return choices != null && !choices.isEmpty();
    }

    public int getChoiceCount() {
        return choices != null ? choices.size() : 0;
    }

    @Override
    public String toString() {
        return "Step{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", isFinal=" + isFinal +
                ", isWin=" + isWin +
                ", choices=" + choices.size() +
                '}';
    }
}