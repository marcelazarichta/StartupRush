package application;

import businessLogic.Startup;
import persistence.DatabaseManager;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Comparator;

public class StartupController {

    private ArrayList<Startup> startups;
    private DatabaseManager databaseManager;

    public StartupController () {
        this.startups = new ArrayList<>();
        this.databaseManager = new DatabaseManager();
    }

    public ArrayList<Startup> getStartups () {
        return startups;
    }

    public Startup getStartup (int id) {
        return startups.get(id);
    }

    public int startupsSize(){
        return startups.size();
    }

    public void createStartup (String name, String slogan, int foundingYear) {
        Startup startup = new Startup(name, slogan, foundingYear);
        startups.add(startup);
        databaseManager.registerStartup(name, slogan, foundingYear);
    }

    public void drawStartups (int limit) {
        Utilities.ramdomDraw(startups, limit);
    }

    public void sortStartups () {
        startups.sort(Comparator.comparingInt(Startup::getScore).reversed());
    }
}
