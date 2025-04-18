import java.util.Random;

public class Battle {

    private Startup startup1, startup2;

    public Battle(int battleNumber, Startup startup1, Startup startup2) {
        this.startup1 = startup1;
        this.startup2 = startup2;
    }

    public Startup getStartup1() {
        return startup1;
    }

    public Startup getStartup2() {
        return startup2;
    }

    public Startup getHighestScore(){
        if (startup1.getScore() > startup2.getScore()){
            return startup1;
        } else {
            return startup2;
        }
    }

    public void printStartups(){
        System.out.println(getStartup1().getName() + "  [Score: " + getStartup1().getScore() + "]");
        System.out.println(getStartup2().getName() + "  [Score: " + getStartup2().getScore() + "]");
    }

    public void resetStartups(){
        startup1.resetEvents();
        startup2.resetEvents();
    }

    public void startBattle(){
        int endBattle = 0;

        while (endBattle != 1){
            System.out.println("\nParticipating Startups:");
            printStartups();

            System.out.println("\nWhich startup would you like to register events for?");
            System.out.println("[1] " + startup1.getName() + "  [2] " + startup2.getName() + "  [3] End battle");
            int aux = Utilities.readInt(1, 3);

            int event;
            if (aux == 1){
                event = getStartup1().selectAvailableEvents();
                if (event == 6){
                    continue;
                }
                startup1.makeEvent(event - 1);
            } else if (aux == 2){
                event = getStartup2().selectAvailableEvents();
                if (event == 6){
                    continue;
                }
                startup2.makeEvent(event - 1);
            } else {
                endBattle++;
            }
        }
    }

    public void sharkfight (){
        System.out.println("\nWe have a tie between the two startups!");
        printStartups();
        System.out.println("\n  \uD83E\uDD88  It's SHARK FIGHT time  \uD83E\uDD88\n");
        System.out.println("where a random team gets 2 points");
        System.out.println("And the winner is...\n");

        Random rand = new Random();


        int winner = rand.nextInt(2);
        if (winner == 0){
            startup1.setScore(2);
        } else {
            startup2.setScore(2);
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(getHighestScore().getName() + "  [Score: " + getHighestScore().getScore() + "]\n");
    }
}
