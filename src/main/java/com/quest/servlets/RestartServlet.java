package com.quest.servlets;

import com.quest.model.GameService;
import com.quest.model.GameState;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RestartServlet extends HttpServlet {

    private GameService gameService;

    @Override
    public void init() throws ServletException {
        super.init();
        gameService = new GameService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        GameState gameState = (GameState) session.getAttribute("gameState");
        String playerName = request.getParameter("playerName");

        if (gameState == null) {
            gameState = new GameState(playerName != null ? playerName : "Путешественник");
            session.setAttribute("gameState", gameState);
        }

        if (playerName != null && !playerName.trim().isEmpty()) {
            gameState.setPlayerName(playerName);
        }

        gameService.resetGame(gameState, gameState.getPlayerName());
        session.removeAttribute("finalStep");

        response.sendRedirect(request.getContextPath() + "/game");
    }
}

