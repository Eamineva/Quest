package com.quest.servlets;

import com.quest.model.GameService;
import com.quest.model.GameState;
import com.quest.model.Step;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GameServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        gameService = new GameService(); // ОДИН раз при инициализации
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");

        System.out.println("=== GAME SERVLET doGet() ===");
        System.out.println("GameState: " + gameState);

        if (gameState == null) {
            System.out.println("GameState is NULL! Redirecting to /start");
            response.sendRedirect(request.getContextPath() + "/start");
            return;
        }

        String stepId = gameState.getCurrentStepId();
        System.out.println("Current stepId: " + stepId);
        System.out.println("GameService instance: " + this.gameService); // ДОБАВЬТЕ

        // НЕ СОЗДАВАЙТЕ НОВЫЙ GameService! Используйте существующий:
        Step step = this.gameService.getStep(stepId); // ИСПРАВЬТЕ ЭТУ СТРОКУ
        System.out.println("Step object: " + step);

        if (step == null) {
            System.out.println("ERROR: Step is NULL for id: " + stepId);
            // Получите шаг "start" из ТОГО ЖЕ gameService
            step = this.gameService.getStep("start");
            System.out.println("Fallback step: " + step);
        }

        // Передайте атрибуты
        request.setAttribute("step", step);
        request.setAttribute("gameState", gameState);

        if (step != null) {
            System.out.println("Forwarding to game.jsp with step: " + step.getTitle());
        } else {
            System.out.println("ERROR: Step is still NULL!");
        }

        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");

        if (gameState == null) {
            response.sendRedirect(request.getContextPath() + "/start");
            return;
        }

        String choice = request.getParameter("choice");

        if (choice != null) {
            Step nextStep = this.gameService.processChoice(gameState, choice);

            if (nextStep != null && nextStep.isFinal()) {
                // Сохраняем в сессии
                session.setAttribute("finalStep", nextStep);

                // ПЕРЕДАЁМ АТРИБУТЫ И ПОКАЗЫВАЕМ result.jsp
                request.setAttribute("finalStep", nextStep);
                request.setAttribute("gameState", gameState);
                request.getRequestDispatcher("/result.jsp").forward(request, response);
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/game");
    }
}