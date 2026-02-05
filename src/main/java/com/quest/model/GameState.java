package com.quest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Serializable {
    private String playerName;
    private String currentStepId;
    private boolean hasTicket;
    private boolean hasSuitcase;
    private boolean hasPassport;
    private boolean passedSecurity;
    private boolean onTime;
    private boolean atSea;
    private int gamesPlayed;
    private List<String> visitedSteps = new ArrayList<>();

    public GameState() {}

    public GameState(String playerName) {
        this.playerName = playerName;
        this.currentStepId = "start";
        this.hasTicket = false;
        this.hasSuitcase = false;
        this.hasPassport = false;
        this.passedSecurity = false;
        this.onTime = false;
        this.atSea = false;
        this.gamesPlayed = 0;
        this.visitedSteps.add("start");
    }

    // Getters and setters
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public String getCurrentStepId() { return currentStepId; }
    public void setCurrentStepId(String currentStepId) {
        this.currentStepId = currentStepId;
        if (!visitedSteps.contains(currentStepId)) {
            visitedSteps.add(currentStepId);
        }
    }

    public boolean isHasTicket() { return hasTicket; }
    public void setHasTicket(boolean hasTicket) { this.hasTicket = hasTicket; }

    public boolean isHasSuitcase() { return hasSuitcase; }
    public void setHasSuitcase(boolean hasSuitcase) { this.hasSuitcase = hasSuitcase; }

    public boolean isHasPassport() { return hasPassport; }
    public void setHasPassport(boolean hasPassport) { this.hasPassport = hasPassport; }

    public boolean isPassedSecurity() { return passedSecurity; }
    public void setPassedSecurity(boolean passedSecurity) { this.passedSecurity = passedSecurity; }

    public boolean isOnTime() { return onTime; }
    public void setOnTime(boolean onTime) { this.onTime = onTime; }

    public boolean isAtSea() { return atSea; }
    public void setAtSea(boolean atSea) { this.atSea = atSea; }

    public int getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(int gamesPlayed) { this.gamesPlayed = gamesPlayed; }
    public void incrementGamesPlayed() { this.gamesPlayed++; }

    public List<String> getVisitedSteps() { return visitedSteps; }
    public void setVisitedSteps(List<String> visitedSteps) { this.visitedSteps = visitedSteps; }

    public boolean isGameWon() {
        return hasTicket && hasSuitcase && hasPassport && passedSecurity && onTime && atSea;
    }

    public boolean isGameLost() {
        return currentStepId.equals("lost");
    }

    public void reset() {
        this.currentStepId = "start";
        this.hasTicket = false;
        this.hasSuitcase = false;
        this.hasPassport = false;
        this.passedSecurity = false;
        this.onTime = false;
        this.atSea = false;
        this.visitedSteps.clear();
        this.visitedSteps.add("start");
    }
}