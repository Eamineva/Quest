package com.quest.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameServiceTest {
    private GameService gameService;
    private GameState gameState;

    @Before
    public void setUp() {
        gameService = new GameService();
        gameState = new GameState("TestPlayer");
    }

    @Test
    public void testGetStep() {
        Step step = gameService.getStep("start");
        assertNotNull(step);
        assertEquals("start", step.getId());
        assertEquals("Начало приключения", step.getTitle());
        assertTrue(step.getChoices().size() > 0);
    }

    @Test
    public void testProcessChoiceBuyTicket() {
        gameState.setCurrentStepId("ticket_step");
        Step nextStep = gameService.processChoice(gameState, "buy");

        assertNotNull(nextStep);
        assertEquals("suitcase_step", nextStep.getId());
        assertTrue(gameState.isHasTicket());
    }

    @Test
    public void testProcessChoiceDontBuyTicket() {
        gameState.setCurrentStepId("ticket_step");
        Step nextStep = gameService.processChoice(gameState, "dont_buy");

        assertNotNull(nextStep);
        assertEquals("lost_no_ticket", nextStep.getId());
        assertFalse(gameState.isHasTicket());
        assertTrue(nextStep.isFinal());
        assertFalse(nextStep.isWin());
    }

    @Test
    public void testProcessChoicePackSuitcase() {
        gameState.setCurrentStepId("suitcase_step");
        Step nextStep = gameService.processChoice(gameState, "pack");

        assertNotNull(nextStep);
        assertEquals("passport_step", nextStep.getId());
        assertTrue(gameState.isHasSuitcase());
    }

    @Test
    public void testProcessChoiceCheckPassport() {
        gameState.setCurrentStepId("passport_step");
        Step nextStep = gameService.processChoice(gameState, "check");

        assertNotNull(nextStep);
        assertEquals("airport_step", nextStep.getId());
        assertTrue(gameState.isHasPassport());
    }

    @Test
    public void testProcessChoiceSecurity() {
        gameState.setCurrentStepId("airport_step");
        Step nextStep = gameService.processChoice(gameState, "security");

        assertNotNull(nextStep);
        assertEquals("flight_step", nextStep.getId());
        assertTrue(gameState.isPassedSecurity());
    }

    @Test
    public void testProcessChoiceOnTime() {
        gameState.setCurrentStepId("flight_step");
        Step nextStep = gameService.processChoice(gameState, "on_time");

        assertNotNull(nextStep);
        assertEquals("boarding_step", nextStep.getId());
        assertTrue(gameState.isOnTime());
    }

    @Test
    public void testProcessChoiceBoard() {
        gameState.setCurrentStepId("boarding_step");
        Step nextStep = gameService.processChoice(gameState, "board");

        assertNotNull(nextStep);
        assertEquals("sea_step", nextStep.getId());
        assertTrue(gameState.isOnTime());
        assertTrue(nextStep.isFinal());
        assertTrue(nextStep.isWin());
    }

    @Test
    public void testResetGame() {
        gameState.setHasTicket(true);
        gameState.setHasSuitcase(true);
        gameState.setCurrentStepId("sea_step");
        gameState.getVisitedSteps().add("test");

        gameService.resetGame(gameState, "NewPlayer");

        assertEquals("NewPlayer", gameState.getPlayerName());
        assertEquals("start", gameState.getCurrentStepId());
        assertFalse(gameState.isHasTicket());
        assertFalse(gameState.isHasSuitcase());
        assertFalse(gameState.isHasPassport());
        assertFalse(gameState.isPassedSecurity());
        assertFalse(gameState.isOnTime());
        assertFalse(gameState.isAtSea());
        assertEquals(1, gameState.getGamesPlayed());
        assertEquals(1, gameState.getVisitedSteps().size());
        assertTrue(gameState.getVisitedSteps().contains("start"));
    }

    @Test
    public void testGameWonCondition() {
        gameState.setHasTicket(true);
        gameState.setHasSuitcase(true);
        gameState.setHasPassport(true);
        gameState.setPassedSecurity(true);
        gameState.setOnTime(true);
        gameState.setAtSea(true);

        assertTrue(gameState.isGameWon());
    }

    @Test
    public void testGameLostCondition() {
        gameState.setCurrentStepId("lost");
        assertTrue(gameState.isGameLost());
    }
}