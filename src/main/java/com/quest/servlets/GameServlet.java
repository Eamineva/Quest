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
        gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");

        if (gameState == null) {
            response.sendRedirect(request.getContextPath() + "/start");
            return;
        }

        String stepId = gameState.getCurrentStepId();
        Step step = gameService.getStep(stepId);

        request.setAttribute("step", step);
        request.setAttribute("gameState", gameState);
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
            Step nextStep = gameService.processChoice(gameState, choice);

            if (nextStep != null && nextStep.isFinal()) {
                session.setAttribute("finalStep", nextStep);
                response.sendRedirect(request.getContextPath() + "/result");
                return;
            }
        }

        response.sendRedirect(request.getContextPath() + "/game");
    }
}
