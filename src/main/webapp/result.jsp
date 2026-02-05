<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Полет на море - Результат</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      margin: 0;
      padding: 20px;
      min-height: 100vh;
      display: flex;
      justify-content: center;
      align-items: center;
    }

    .result-container {
      max-width: 800px;
      width: 100%;
      background-color: white;
      border-radius: 20px;
      padding: 40px;
      box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
      text-align: center;
      border: 5px solid;
    }

    .win {
      border-color: #4CAF50;
      background: linear-gradient(to bottom, #E8F5E9, #C8E6C9);
    }

    .lose {
      border-color: #F44336;
      background: linear-gradient(to bottom, #FFEBEE, #FFCDD2);
    }

    .result-icon {
      font-size: 5em;
      margin-bottom: 20px;
    }

    .result-title {
      font-size: 2.5em;
      margin-bottom: 20px;
      color: #333;
    }

    .result-description {
      font-size: 1.3em;
      line-height: 1.6;
      margin-bottom: 30px;
      padding: 20px;
      border-radius: 10px;
      background-color: rgba(255, 255, 255, 0.7);
    }

    .stats-summary {
      background-color: #F5F5F5;
      padding: 25px;
      border-radius: 15px;
      margin: 30px 0;
      text-align: left;
      display: inline-block;
    }

    .stat-item {
      margin-bottom: 15px;
      font-size: 1.1em;
    }

    .buttons-container {
      margin-top: 30px;
      display: flex;
      justify-content: center;
      gap: 20px;
      flex-wrap: wrap;
    }

    .result-button {
      padding: 15px 35px;
      font-size: 1.1em;
      border: none;
      border-radius: 10px;
      cursor: pointer;
      font-weight: bold;
      transition: all 0.3s;
      text-decoration: none;
      display: inline-block;
    }

    .play-again {
      background: linear-gradient(to right, #2196F3, #1976D2);
      color: white;
    }

    .play-again:hover {
      background: linear-gradient(to right, #1976D2, #1565C0);
      transform: scale(1.05);
    }

    .change-name {
      background: linear-gradient(to right, #4CAF50, #45a049);
      color: white;
    }

    .change-name:hover {
      background: linear-gradient(to right, #45a049, #4CAF50);
      transform: scale(1.05);
    }

    .main-menu {
      background: linear-gradient(to right, #FF9800, #F57C00);
      color: white;
    }

    .main-menu:hover {
      background: linear-gradient(to right, #F57C00, #FF9800);
      transform: scale(1.05);
    }

    .progress-summary {
      font-size: 1.2em;
      margin: 20px 0;
      padding: 15px;
      border-radius: 10px;
      background-color: #E3F2FD;
    }

    .win-message {
      color: #2E7D32;
      font-weight: bold;
      animation: pulse 2s infinite;
    }

    .lose-message {
      color: #C62828;
      font-weight: bold;
    }

    @keyframes pulse {
      0% { opacity: 0.8; }
      50% { opacity: 1; }
      100% { opacity: 0.8; }
    }
  </style>
</head>
<body class="${finalStep.win ? 'win-background' : 'lose-background'}">
<div class="result-container ${finalStep.win ? 'win' : 'lose'}">
  <div class="result-icon">
    <c:choose>
      <c:when test="${finalStep.win}">🏆🌊🎉</c:when>
      <c:otherwise>💥😢🚫</c:otherwise>
    </c:choose>
  </div>

  <h1 class="result-title">
    <c:choose>
      <c:when test="${finalStep.win}">🎉 ПОБЕДА! 🎉</c:when>
      <c:otherwise>💀 ПОРАЖЕНИЕ 💀</c:otherwise>
    </c:choose>
  </h1>

  <div class="result-description">
    ${finalStep.description}
  </div>

  <div class="stats-summary">
    <h3>📊 Ваши результаты:</h3>
    <div class="stat-item">👤 Игрок: ${gameState.playerName}</div>
    <div class="stat-item">🎮 Игр сыграно: ${gameState.gamesPlayed}</div>
    <div class="stat-item">📍 Пройдено шагов: ${gameState.visitedSteps.size()}</div>
    <div class="progress-summary">
      <c:choose>
        <c:when test="${finalStep.win}">
          <p class="win-message">Вы успешно добрались до моря! Отличная работа! 🏖️</p>
        </c:when>
        <c:otherwise>
          <p class="lose-message">В следующий раз будьте внимательнее! 😊</p>
        </c:otherwise>
      </c:choose>
    </div>
  </div>

  <div class="buttons-container">
    <form action="${pageContext.request.contextPath}/restart" method="post">
      <input type="hidden" name="playerName" value="${gameState.playerName}">
      <button type="submit" class="result-button play-again">
        🎮 Играть еще раз
      </button>
    </form>

    <a href="${pageContext.request.contextPath}/start" class="result-button main-menu">
      🏠 Главное меню
    </a>
  </div>
</div>
</body>
</html>