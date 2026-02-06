<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Полет на море - Добро пожаловать!</title>
  <style>
    body {
      font-family: 'Arial', sans-serif;
      background: linear-gradient(to bottom, #87CEEB, #E0F7FF);
      color: #333;
      margin: 0;
      padding: 20px;
      min-height: 100vh;
    }

    .container {
      max-width: 800px;
      margin: 0 auto;
      background-color: white;
      border-radius: 15px;
      padding: 30px;
      box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
      border: 3px solid #4CAF50;
    }

    .header {
      text-align: center;
      margin-bottom: 30px;
    }

    h1 {
      color: #2196F3;
      font-size: 2.5em;
      margin-bottom: 10px;
    }

    .subtitle {
      color: #FF9800;
      font-size: 1.2em;
      font-weight: bold;
    }

    .story {
      background-color: #E3F2FD;
      padding: 20px;
      border-radius: 10px;
      margin-bottom: 30px;
      border-left: 5px solid #2196F3;
    }

    .story h2 {
      color: #2196F3;
      margin-top: 0;
    }

    .story p {
      line-height: 1.6;
      margin-bottom: 15px;
    }

    .story ul {
      list-style: none;
      padding-left: 20px;
    }

    .story li {
      margin-bottom: 10px;
      position: relative;
      padding-left: 25px;
    }

    .story li::before {
      content: '✅';
      position: absolute;
      left: 0;
    }

    .warning {
      color: #F44336;
      font-weight: bold;
      text-align: center;
      margin-top: 15px;
      padding: 10px;
      background-color: #FFEBEE;
      border-radius: 5px;
      border: 1px solid #F44336;
    }

    .form-container {
      background-color: #F1F8E9;
      padding: 25px;
      border-radius: 10px;
      margin-top: 20px;
      text-align: center;
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin-bottom: 8px;
      color: #388E3C;
      font-weight: bold;
      font-size: 1.1em;
    }

    input[type="text"] {
      width: 100%;
      max-width: 300px;
      padding: 12px;
      border: 2px solid #4CAF50;
      border-radius: 8px;
      font-size: 1.1em;
      text-align: center;
    }

    .start-button {
      background: linear-gradient(to right, #4CAF50, #45a049);
      color: white;
      border: none;
      padding: 15px 40px;
      font-size: 1.2em;
      border-radius: 8px;
      cursor: pointer;
      font-weight: bold;
      transition: all 0.3s;
    }

    .start-button:hover {
      background: linear-gradient(to right, #45a049, #4CAF50);
      transform: scale(1.05);
    }

    .stats {
      margin-top: 20px;
      padding: 15px;
      background-color: #E8F5E9;
      border-radius: 8px;
      text-align: center;
      font-size: 1.1em;
    }

    .icon {
      font-size: 1.5em;
      margin-right: 10px;
    }

    .beach-image {
      text-align: center;
      margin: 20px 0;
      font-size: 3em;
    }
  </style>
</head>
<body>
<div class="container">
  <div class="header">
    <div class="beach-image">🏖️✈️🌊</div>
    <h1>✈️ Полет на море ✈️</h1>
    <p class="subtitle">Текстовый квест о путешествии к теплому морю</p>
  </div>

  <div class="story">
    <h2>🌅 Ваша мечта о море!</h2>
    <p>Вы устали от городской суеты, серых дней и бесконечной работы. Вам снится море: теплый песок, ласковые волны, коктейль с зонтиком и ничего не делать целый день...</p>

    <p><strong>Но чтобы добраться до моря, нужно:</strong></p>
    <ul>
      <li>Купить билет на самолет</li>
      <li>Собрать чемодан</li>
      <li>Не забыть паспорт</li>
      <li>Пройти контроль в аэропорту</li>
      <li>Не опоздать на рейс</li>
    </ul>

    <p>Каждый ваш выбор определяет, попадете ли вы на море или останетесь дома. Будьте внимательны!</p>

    <div class="warning">
      ⚠️ Внимание! Одно неверное решение - и ваша поездка может сорваться!
    </div>

    <!-- БЛОК "ЧТО ВАМ ПОНАДОБИТСЯ" УДАЛЕН ЗДЕСЬ -->

  </div>

  <div class="form-container">
    <form action="${pageContext.request.contextPath}/start" method="post">
      <div class="form-group">
        <label for="playerName">👤 Введите ваше имя:</label>
        <input type="text" id="playerName" name="playerName"
               placeholder="Как вас зовут, путешественник?"
               required maxlength="20">
      </div>
      <button type="submit" class="start-button">
        🚀 Начать путешествие!
      </button>
    </form>
  </div>

  <c:if test="${not empty gameState}">
    <div class="stats">
      <p>📊 Игр сыграно: <strong>${gameState.gamesPlayed}</strong></p>
    </div>
  </c:if>
</div>
</body>
</html>