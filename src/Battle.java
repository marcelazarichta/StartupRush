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
}
