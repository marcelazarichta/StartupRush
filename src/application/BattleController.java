package application;

import businessLogic.Battle;
import businessLogic.Startup;
import persistence.DatabaseManager;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;

public class BattleController {

    private ArrayList<Battle> battles;
    private DatabaseManager databaseManager;

    public BattleController() {
        this.battles = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
    }

    public ArrayList<Battle> getBattles() {
        return battles;
    }

    public Battle getBattle(int id) {
        return battles.get(id);
    }

    public void createBattle(Startup startup1, Startup startup2) {
        Battle battle = new Battle(startup1, startup2);
        battles.add(battle);
    }

    public int battlesSize() {
        return battles.size();
    }

    public String startupsScore(int index) {
        return battles.get(index).startupsScore();
    }

    public void removeBattle(int index) {
        battles.remove(index);
    }

    public void startBattle(int index) {
        Battle battle = battles.get(index);
        Startup startup = executeBattle(battle);

        startup.setScore(30);

        battle.resetStartups();
        saveBattle(battle);

        battles.remove(battle);
    }

    private Startup executeBattle(Battle battle) {
        int endBattle = 0;

        while (endBattle != 1) {
            System.out.println("\nParticipating Startups:");
            System.out.println(battle.startupsScore());

            System.out.println("\nWhich startup would you like to register events for?");
            System.out.println("[1] " + battle.getStartup1().getName() + "  [2] " + battle.getStartup2().getName() + "  [3] End battle");
            int aux = Utilities.readInt(1, 3);


            if (aux == 3){
                endBattle++;
                continue;
            }

            int event = battle.getStartup(aux).selectAvailableEvents();
            if (event == 7) {
                continue;
            }
            battle.getStartup(aux).makeEvent(event - 1);
        }

        if (battle.getStartup1().getScore() == battle.getStartup2().getScore()) {
            return sharkfight(battle);
        } else if (battle.getStartup1().getScore() > battle.getStartup2().getScore()) {
            return battle.getStartup1();
        } else {
            return battle.getStartup2();
        }
    }

    private Startup sharkfight (Battle battle) {

        System.out.println("\nWe have a tie between the two startups!");
        System.out.println(battle.startupsScore());
        System.out.println("\n  \uD83E\uDD88  It's SHARK FIGHT time  \uD83E\uDD88\n");
        System.out.println("where a random team gets 2 points");
        System.out.println("And the winner is...\n");

        Random rand = new Random();

        Startup startupAux;
        int winner = rand.nextInt(2);
        if (winner == 0) {
            battle.getStartup1().setScore(2);
            startupAux = battle.getStartup1();
        } else {
            battle.getStartup2().setScore(2);
            startupAux = battle.getStartup2();
        }

        Utilities.timeDelay(1500);

        System.out.println(battle.getHighestScore().getName() + "  [Score: " + battle.getHighestScore().getScore() + "]\n");

        return startupAux;
    }

    private void saveBattle(Battle battle) {
        databaseManager.saveRoundResult(
                battle.getStartup1().getName(),
                battle.getStartup2().getName(),
                battle.getHighestScore().getName(),
                battle.getStartup1().getScore(),
                battle.getStartup2().getScore()
        );
    }
}
