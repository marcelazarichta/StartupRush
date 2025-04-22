package businessLogic;

import utilities.Utilities;

import java.util.Random;

public class Battle {

    private Startup startup1, startup2;

    public Battle(Startup startup1, Startup startup2) {
        this.startup1 = startup1;
        this.startup2 = startup2;
    }

    public Startup getStartup1() {
        return startup1;
    }

    public Startup getStartup2() {
        return startup2;
    }

    public Startup getStartup(int startup) {
        if (startup == 1) {
            return startup1;
        } else {
            return startup2;
        }
    }

    public Startup getHighestScore() {
        if (startup1.getScore() > startup2.getScore()) {
            return startup1;
        } else {
            return startup2;
        }
    }

    public String startupsScore() {
        String startups;

        startups = startup1.getName() + "  [Score: " + startup1.getScore() + "]\n";
        startups += startup2.getName() + "  [Score: " + startup2.getScore() + "]";

        return startups;
    }

    public void resetStartups() {
        startup1.resetEvents();
        startup2.resetEvents();
    }
}
