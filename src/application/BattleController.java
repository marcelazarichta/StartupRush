package application;

import businessLogic.Battle;
import businessLogic.Startup;
import persistence.DatabaseManager;

import java.util.ArrayList;

public class BattleController {

    private ArrayList<Battle> battles;
    private DatabaseManager databaseManager;

    public BattleController() {
        battles = new ArrayList<>();
        databaseManager = new DatabaseManager();
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
        Startup startup = battle.startBattle();


        startup.setScore(30);

        battle.resetStartups();
        saveBattle(battle);

        battles.remove(battle);
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
