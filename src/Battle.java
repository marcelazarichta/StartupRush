public class Battle {

    private int battleNumber;

    private Startup startup1, startup2;

    public Battle(int battleNumber, Startup startup1, Startup startup2) {
        this.battleNumber = battleNumber;
        this.startup1 = startup1;
        this.startup2 = startup2;
    }

    public Startup getStartup1() {
        return startup1;
    }

    public Startup getStartup2() {
        return startup2;
    }

    public int getBattleNumber() {
        return battleNumber;
    }

    public void setBattleNumber(int battleNumber) {
        this.battleNumber = battleNumber;
    }


    public void printStartups(){
        System.out.println("\nParticipating Startups:\n");
        System.out.println(getStartup1().getName() + "  [Score: " + getStartup1().getScore() + "]\n");
        System.out.println(getStartup2().getName() + "  [Score: " + getStartup2().getScore() + "]\n");
    }

    public void startBattle(){
        int endBattle = 0;

        while (endBattle != 1){
            printStartups();

            System.out.println("Which startup would you like to register events for?");
            System.out.println("[1] " + startup1.getName() + "  [2] " + startup2.getName() + "  [3] End battle");
            int aux = App.readInt(1, 3);

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
            }

        }

    }

}
