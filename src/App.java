import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    public static void main (String[] args) {

        ArrayList<Startup> startups = new ArrayList<>();

        System.out.println("\nWelcome to STARTUP RUSH!");
        System.out.println("First, let's register 4, 6 or 8 teams to join the competition\n");


        for (int i = 0; i < 8; i++) {
            int teamNumber = i + 1;

            int option = 0;
            if (teamNumber == 5 || teamNumber == 7) {
                System.out.println("\nWould you like to register another two teams?");
                System.out.println("[1] yes  [2] no");
                option = Utilities.readInt( 1, 2);
            }

            if (option == 2){
                break;
            }

            Startup startup = createStartup(teamNumber);
            startups.add(startup);

            System.out.println();
        }

        System.out.println("\nGreat! Now that the startups are all set, we've reached the battle stage.");

        double rounds = startups.size();
        startups = Utilities.ramdomDraw(startups, startups.size());

        int currentRound = 1;

        while (rounds > 1){
            System.out.println("Current round: " + currentRound);

            ArrayList<Battle> battles = new ArrayList<>();

            int battleNumber = 1;
            for (int i = 0; i < rounds; i += 2) {
                Battle battle = new Battle(battleNumber, startups.get(i), startups.get(i + 1));
                battles.add(battle);

                battleNumber++;
            }

            while (!battles.isEmpty()) {
                System.out.println("\nChoose which battle you would like to start:");

                int option = 1;
                for (Battle b : battles) {
                    System.out.println("[" + option + "] " + b.getStartup1().getName()
                            + "  X  " + b.getStartup2().getName());
                    option ++;
                }

                int aux = Utilities.readInt(1, battles.size()) - 1;

                Battle chosenBattle = battles.get(aux);
                chosenBattle.startBattle();

                if (chosenBattle.getStartup1().getScore() == chosenBattle.getStartup2().getScore()) {
                    chosenBattle.sharkfight();
                }

                //winner gets 30 points
                chosenBattle.getHighestScore().setScore(30);
                chosenBattle.resetStartups();

                battles.remove(chosenBattle);
            }


            startups.sort(Comparator.comparingInt(Startup::getScore).reversed());
            startups = Utilities.ramdomDraw(startups, startups.size()/2);

            currentRound++;
            rounds /= 2;
        }

        startups.sort(Comparator.comparingInt(Startup::getScore).reversed());

        Startup winner = startups.get(0);
        printTable(startups);

        System.out.println("\n" + winner.getSlogan());

    }

    public static Startup createStartup (int teamNumber) {
        Scanner in = new Scanner(System.in);

        Startup startup;
        String name;
        String slogan;
        int foundingYear = 0;

        System.out.println("\nTeam " + teamNumber);

        System.out.println("\nName: ");
        name = in.nextLine();

        System.out.println("Slogan: ");
        slogan = in.nextLine();

        System.out.println("Founding year: ");
        foundingYear = Utilities.readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

        startup = new Startup(teamNumber, name, slogan, foundingYear);

        return startup;
    }

    public static void printTable(ArrayList<Startup> startups) {
        System.out.printf("%-15s %-10s", "Startup", "Score");

        if (!startups.isEmpty()) {
            Startup firstStartup = startups.get(0);
            String[] eventNames = firstStartup.getEventName();

            for (String event : eventNames) {
                System.out.printf("  %-12s", event);
            }
        }
        System.out.println();

        for (Startup s : startups) {
            System.out.printf("%-15s %-10d", s.getName(), s.getScore());

            for (int i = 0; i < 5; i++) {
                int occurrences = s.getEventOcurrences(i);
                System.out.printf("   %-16d", occurrences);
            }

            System.out.println();
        }
    }
}
