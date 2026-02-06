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
                        "Но сначала нужно подготовиться к путешествию!",
                false, false);
        start.addChoice("buy_ticket", "Купить билет на самолет", "suitcase_step");  // сразу на suitcase_step
        start.addChoice("skip_ticket", "Решить купить билет позже", "lost_no_ticket");
        steps.put("start", start);

        // Шаг с чемоданом (сразу после покупки билета)
        Step suitcaseStep = new Step("suitcase_step", "Сбор чемодана",
                "Вы купили билет! Теперь нужно собрать чемодан. ",
                false, false);
        suitcaseStep.addChoice("pack", "Собрать все необходимое", "passport_step");
        suitcaseStep.addChoice("forget", "Собрать в последний момент", "lost_no_suitcase");
        steps.put("suitcase_step", suitcaseStep);

        // Шаг с паспортом
        Step passportStep = new Step("passport_step", "Проверка документов",
                "Чемодан собран. Осталось проверить самый важный документ для полета за границу.",
                false, false);
        passportStep.addChoice("check", "Проверить паспорт и положить в сумку", "airport_step");
        passportStep.addChoice("ignore", "Не проверять", "lost_no_passport");
        steps.put("passport_step", passportStep);

        // Шаг аэропорта
        Step airportStep = new Step("airport_step", "Аэропорт",
                "Вы приехали в аэропорт. Впереди контроль безопасности и паспортный контроль.",
                false, false);
        airportStep.addChoice("security", "Пройти контроль безопасности", "boarding_step");  // сразу на boarding_step
        airportStep.addChoice("skip_security", "Попробовать пройти без контроля", "lost_security");
        steps.put("airport_step", airportStep);

        // Шаг посадки (без отдельного шага рейса)
        Step boardingStep = new Step("boarding_step", "Посадка в самолет",
                "Вы прошли контроль безопасности! Начинается посадка на рейс.",
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
                        "Путешествие отменяется.",
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
                "Вы не успели на посадку! Самолет улетел без вас. " ,
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
            case "start":
                // Если купили билет в первом шаге
                if ("buy_ticket".equals(choiceId)) {
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
            case "boarding_step":
                if ("board".equals(choiceId)) {
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