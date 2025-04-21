package userInterface;

import application.BattleController;
import application.StartupController;
import businessLogic.*;
import persistence.DatabaseManager;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    static StartupController startupController = new StartupController();
    static BattleController battleController = new BattleController();

    public static void main (String[] args) {

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

            createStartup(teamNumber);

            System.out.println();
        }

        System.out.println("\nGreat! Now that the startups are all set, we've reached the battle stage.");

        double rounds = startupController.startupsSize();
        startupController.drawStartups(startupController.startupsSize());

        int currentRound = 1;

        while (rounds > 1){
            System.out.println("Current round: " + currentRound);

            for (int i = 0; i < rounds; i += 2) {
                battleController.createBattle(startupController.getStartup(i), startupController.getStartup(i + 1));
            }

            while (battleController.battlesSize() > 0){
                System.out.println("\nChoose which battle you would like to start:");

                int option = 1;
                for (Battle b : battleController.getBattles()) {
                    System.out.println("[" + option + "] " + b.getStartup1().getName()
                            + "  X  " + b.getStartup2().getName());
                    option ++;
                }

                int aux = Utilities.readInt(1, battleController.battlesSize()) - 1;

                battleController.startBattle(aux);
            }

            startupController.sortStartups();
            startupController.drawStartups(startupController.startupsSize() / 2);

            currentRound++;
            rounds /= 2;
        }

        startupController.sortStartups();
        Startup winner = startupController.getStartup(0);
        printTable(startupController.getStartups());

        System.out.println("\n" + winner.getSlogan());
    }

    public static void createStartup (int teamNumber) {
        Scanner in = new Scanner(System.in);

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

        startupController.createStartup(name, slogan, foundingYear);
    }

    public static void printTable(ArrayList<Startup> startups) {
        System.out.printf("%-15s %-10s", "model.Startup", "Score");

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
