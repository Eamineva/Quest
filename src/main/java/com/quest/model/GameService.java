package com.quest.model;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    private Map<String, Step> steps = new HashMap<>();

    public GameService() {
        initializeSteps();
    }

    private void initializeSteps() {
        // Начальный шаг
        Step start = new Step("start", "Начало приключения",
                "Вы мечтаете о поездке на море. Солнце, пляж, шум волн... " +
                        "Но сначала нужно подготовиться к путешествию! С чего начнем?",
                false, false);
        start.addChoice("buy_ticket", "Купить билет на самолет", "ticket_step");
        start.addChoice("skip_ticket", "Решить купить билет позже", "lost_no_ticket");
        steps.put("start", start);

        // Шаг с билетом
        Step ticketStep = new Step("ticket_step", "Покупка билета",
                "Вы зашли на сайт авиакомпании. Билеты на море стоят дорого, но мечта того стоит! " +
                        "Что выберете?",
                false, false);
        ticketStep.addChoice("buy", "Купить билет (5000 руб.)", "suitcase_step");
        ticketStep.addChoice("dont_buy", "Передумать, слишком дорого", "lost_no_ticket");
        steps.put("ticket_step", ticketStep);

        // Шаг с чемоданом
        Step suitcaseStep = new Step("suitcase_step", "Сбор чемодана",
                "Ура! Билет куплен. Теперь нужно собрать чемодан. " +
                        "Что будете упаковывать?",
                false, false);
        suitcaseStep.addChoice("pack", "Тщательно собрать все необходимое", "passport_step");
        suitcaseStep.addChoice("forget", "Решить собрать в последний момент", "lost_no_suitcase");
        steps.put("suitcase_step", suitcaseStep);

        // Шаг с паспортом
        Step passportStep = new Step("passport_step", "Проверка документов",
                "Чемодан собран. Осталось проверить самый важный документ для полета за границу.",
                false, false);
        passportStep.addChoice("check", "Проверить паспорт и положить в сумку", "airport_step");
        passportStep.addChoice("ignore", "Не проверять, наверное он где-то есть", "lost_no_passport");
        steps.put("passport_step", passportStep);

        // Шаг аэропорта
        Step airportStep = new Step("airport_step", "Аэропорт",
                "Вы приехали в аэропорт. Впереди контроль безопасности и паспортный контроль.",
                false, false);
        airportStep.addChoice("security", "Пройти контроль безопасности", "flight_step");
        airportStep.addChoice("skip_security", "Попробовать пройти без контроля", "lost_security");
        steps.put("airport_step", airportStep);

        // Шаг рейса
        Step flightStep = new Step("flight_step", "Регистрация на рейс",
                "Вы прошли контроль! Осталось зарегистрироваться на рейс и пройти к гейту.",
                false, false);
        flightStep.addChoice("on_time", "Вовремя пройти регистрацию и отправиться к гейту", "boarding_step");
        flightStep.addChoice("late", "Задержаться в дьюти-фри", "lost_late");
        steps.put("flight_step", flightStep);

        // Шаг посадки
        Step boardingStep = new Step("boarding_step", "Посадка в самолет",
                "Вы у гейта! Начинается посадка на рейс до теплого моря.",
                false, false);
        boardingStep.addChoice("board", "Сесть в самолет", "sea_step");
        boardingStep.addChoice("miss", "Опоздать на посадку", "lost_late");
        steps.put("boarding_step", boardingStep);

        // Финальный шаг - море!
        Step seaStep = new Step("sea_step", "На море!",
                "Самолет приземлился! Вы наконец-то на море! Теплый песок, ласковые волны, " +
                        "коктейль с зонтиком... Ваша мечта сбылась! Поздравляем с успешным путешествием!",
                true, true);
        steps.put("sea_step", seaStep);

        // Шаги проигрыша

        // Нет билета
        Step lostNoTicket = new Step("lost_no_ticket", "Без билета никуда",
                "Без билета на самолет вы не сможете улететь на море. " +
                        "Ваша мечта о пляжном отдыхе разбилась... Попробуйте еще раз!",
                true, false);
        steps.put("lost_no_ticket", lostNoTicket);

        // Нет чемодана
        Step lostNoSuitcase = new Step("lost_no_suitcase", "Что брать с собой?",
                "Вы приехали в аэропорт без чемодана. Нечего надеть на пляже! " +
                        "Путешествие отменяется. В следующий раз соберитесь лучше!",
                true, false);
        steps.put("lost_no_suitcase", lostNoSuitcase);

        // Нет паспорта
        Step lostNoPassport = new Step("lost_no_passport", "Забыли паспорт",
                "На паспортном контроле оказалось, что паспорт остался дома. " +
                        "Без документа вас не пустят на рейс. Придется вернуться домой...",
                true, false);
        steps.put("lost_no_passport", lostNoPassport);

        // Проблемы с контролем
        Step lostSecurity = new Step("lost_security", "Проблемы с контролем",
                "Сотрудники безопасности не позволили вам пройти без досмотра. " +
                        "Пришлось пропустить рейс. В следующий раз соблюдайте правила!",
                true, false);
        steps.put("lost_security", lostSecurity);

        // Опоздание
        Step lostLate = new Step("lost_late", "Опоздали на рейс",
                "Вы не успели на посадку! Самолет улетел без вас. " +
                        "Следующий рейс только через неделю... Каникулы испорчены.",
                true, false);
        steps.put("lost_late", lostLate);
    }

    public Step getStep(String id) {
        return steps.get(id);
    }

    public Step processChoice(GameState state, String choiceId) {
        Step currentStep = getStep(state.getCurrentStepId());

        if (currentStep == null || !currentStep.getChoices().containsKey(choiceId)) {
            return currentStep;
        }

        Step.Choice choice = currentStep.getChoices().get(choiceId);
        String nextStepId = choice.getNextStepId();

        // Обновляем состояние игры в зависимости от выбора
        updateGameState(state, choiceId, nextStepId);

        state.setCurrentStepId(nextStepId);
        Step nextStep = getStep(nextStepId);

        return nextStep;
    }

    private void updateGameState(GameState state, String choiceId, String nextStepId) {
        switch (state.getCurrentStepId()) {
            case "ticket_step":
                if ("buy".equals(choiceId)) {
                    state.setHasTicket(true);
                }
                break;
            case "suitcase_step":
                if ("pack".equals(choiceId)) {
                    state.setHasSuitcase(true);
                }
                break;
            case "passport_step":
                if ("check".equals(choiceId)) {
                    state.setHasPassport(true);
                }
                break;
            case "airport_step":
                if ("security".equals(choiceId)) {
                    state.setPassedSecurity(true);
                }
                break;
            case "flight_step":
            case "boarding_step":
                if ("on_time".equals(choiceId) || "board".equals(choiceId)) {
                    state.setOnTime(true);
                }
                break;
            case "sea_step":
                state.setAtSea(true);
                break;
        }
    }

    public void resetGame(GameState state, String playerName) {
        state.setPlayerName(playerName);
        state.reset();
        state.incrementGamesPlayed();
    }
}