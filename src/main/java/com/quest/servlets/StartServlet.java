package com.quest.servlets;

import com.quest.model.GameState;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/welcome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String playerName = request.getParameter("playerName");

        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Путешественник";
        }

        GameState gameState = (GameState) session.getAttribute("gameState");
        if (gameState == null) {
            gameState = new GameState(playerName);
            session.setAttribute("gameState", gameState);
        } else {
            gameState.setPlayerName(playerName);
        }

        response.sendRedirect(request.getContextPath() + "/game");
    }
}