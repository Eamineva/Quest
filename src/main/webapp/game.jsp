<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Полет на море - Игра</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to bottom, #E3F2FD, #BBDEFB);
            color: #333;
            margin: 0;
            padding: 20px;
            min-height: 100vh;
        }

        .game-container {
            max-width: 900px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: 250px 1fr;
            gap: 20px;
        }

        @media (max-width: 768px) {
            .game-container {
                grid-template-columns: 1fr;
            }
        }

        /* Панель игрока */
        .player-panel {
            background-color: white;
            border-radius: 15px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 2px solid #2196F3;
        }

        .player-info {
            margin-bottom: 20px;
            text-align: center;
        }

        .player-name {
            color: #2196F3;
            font-size: 1.3em;
            font-weight: bold;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 2px solid #E3F2FD;
        }

        .progress-container {
            margin: 20px 0;
        }

        .progress-item {
            display: flex;
            align-items: center;
            margin-bottom: 12px;
            padding: 8px;
            border-radius: 5px;
            background-color: #F5F5F5;
        }

        .progress-icon {
            font-size: 1.2em;
            margin-right: 10px;
            width: 30px;
            text-align: center;
        }

        .progress-check {
            color: #4CAF50;
            font-weight: bold;
        }

        .progress-cross {
            color: #F44336;
            font-weight: bold;
        }

        .stats {
            margin-top: 20px;
            padding-top: 15px;
            border-top: 1px solid #E0E0E0;
            font-size: 0.9em;
            color: #666;
        }

        /* Игровая область */
        .game-area {
            background-color: white;
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border: 2px solid #4CAF50;
        }

        .step-title {
            color: #2196F3;
            font-size: 1.8em;
            margin-bottom: 15px;
            padding-bottom: 10px;
            border-bottom: 3px solid #E3F2FD;
        }

        .step-description {
            font-size: 1.2em;
            line-height: 1.6;
            margin-bottom: 30px;
            padding: 20px;
            background-color: #F9F9F9;
            border-radius: 10px;
            border-left: 4px solid #2196F3;
        }

        .choices-container {
            display: grid;
            gap: 15px;
        }

        .choice-button {
            background: linear-gradient(to right, #2196F3, #1976D2);
            color: white;
            border: none;
            padding: 18px 25px;
            font-size: 1.1em;
            border-radius: 10px;
            cursor: pointer;
            text-align: left;
            transition: all 0.3s;
            font-weight: bold;
        }

        .choice-button:hover {
            background: linear-gradient(to right, #1976D2, #1565C0);
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(33, 150, 243, 0.3);
        }

        .choice-icon {
            margin-right: 10px;
            font-size: 1.2em;
        }

        .restart-form {
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #E0E0E0;
            text-align: center;
        }

        .restart-button {
            background-color: #FF9800;
            color: white;
            border: none;
            padding: 12px 25px;
            font-size: 1em;
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
        }

        .restart-button:hover {
            background-color: #F57C00;
        }

        .status-message {
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 20px;
            text-align: center;
            font-weight: bold;
        }

        .success {
            background-color: #E8F5E9;
            color: #2E7D32;
            border: 1px solid #4CAF50;
        }

        .warning {
            background-color: #FFF3E0;
            color: #EF6C00;
            border: 1px solid #FF9800;
        }
    </style>
</head>
<body>
<div class="game-container">
    <!-- Панель игрока -->
    <div class="player-panel">
        <div class="player-info">
            <div class="player-name">👤 ${gameState.playerName}</div>
            <div class="progress-container">
                <div class="progress-item">
                    <span class="progress-icon">✈️</span>
                    <span>Билет</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.hasTicket}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
                <div class="progress-item">
                    <span class="progress-icon">🧳</span>
                    <span>Чемодан</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.hasSuitcase}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
                <div class="progress-item">
                    <span class="progress-icon">📘</span>
                    <span>Паспорт</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.hasPassport}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
                <div class="progress-item">
                    <span class="progress-icon">🏢</span>
                    <span>Контроль</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.passedSecurity}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
                <div class="progress-item">
                    <span class="progress-icon">⏰</span>
                    <span>Вовремя</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.onTime}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
                <div class="progress-item">
                    <span class="progress-icon">🌊</span>
                    <span>На море</span>
                    <span style="margin-left: auto;">
                            <c:choose>
                                <c:when test="${gameState.atSea}">
                                    <span class="progress-check">✅</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="progress-cross">❌</span>
                                </c:otherwise>
                            </c:choose>
                        </span>
                </div>
            </div>
        </div>

        <div class="stats">
            <p>🎮 Шаг: ${gameState.visitedSteps.size()}</p>
            <p>📅 Игр сыграно: ${gameState.gamesPlayed}</p>
        </div>
    </div>

    <!-- Игровая область -->
    <div class="game-area">
        <h1 class="step-title">${step.title}</h1>

        <div class="step-description">
            ${step.description}
        </div>

        <c:if test="${step.final}">
            <div class="status-message ${step.win ? 'success' : 'warning'}">
                <c:choose>
                    <c:when test="${step.win}">
                        🎉 Поздравляем! Вы достигли цели! 🎉
                    </c:when>
                    <c:otherwise>
                        💥 Игра окончена! 💥
                    </c:otherwise>
                </c:choose>
            </div>
        </c:if>

        <c:if test="${!step.final}">
            <form action="${pageContext.request.contextPath}/game" method="post">
                <div class="choices-container">
                    <c:forEach var="choice" items="${step.choices}">
                        <button type="submit" name="choice" value="${choice.key}"
                                class="choice-button">
                            <span class="choice-icon">👉</span>
                                ${choice.value.text}
                        </button>
                    </c:forEach>
                </div>
            </form>
        </c:if>

        <div class="restart-form">
            <form action="${pageContext.request.contextPath}/restart" method="post">
                <button type="submit" class="restart-button">
                    🔄 Начать заново
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>