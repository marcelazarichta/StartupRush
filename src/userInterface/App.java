package userInterface;

import application.StartupController;
import businessLogic.*;
import persistence.DatabaseManager;
import utilities.Utilities;



import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    static StartupController startupController = new StartupController();
    static DatabaseManager databaseManager = new DatabaseManager();

    public static void main (String[] args) {

        ArrayList<Startup> startups = new ArrayList<Startup>();

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

        double rounds = startupController.getStartupsSize();


        startupController.drawStartups(startupController.getStartupsSize());

        int currentRound = 1;

        while (rounds > 1){
            System.out.println("Current round: " + currentRound);

            ArrayList<Battle> battles = new ArrayList<Battle>();

            int battleNumber = 1;
            for (int i = 0; i < rounds; i += 2) {
                Battle battle = new Battle(battleNumber, startupController.getStartup(i), startupController.getStartup(i + 1));
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

                databaseManager.saveRoundResult(
                        chosenBattle.getStartup1().getName(),
                        chosenBattle.getStartup2().getName(),
                        chosenBattle.getHighestScore().getName(),
                        chosenBattle.getStartup1().getScore(),
                        chosenBattle.getStartup2().getScore()
                );

                chosenBattle.resetStartups();

                battles.remove(chosenBattle);
            }


            startupController.sortStartups();
            startupController.drawStartups(startupController.getStartupsSize() / 2);

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

//public class App {
//
//    static DatabaseManager databaseManager = new DatabaseManager();
//    static StartupController startupController = new StartupController();
//
//    public static void main (String[] args) {
//
//        //ArrayList<Startup> startups = new ArrayList<Startup>();
//
//        System.out.println("\nWelcome to STARTUP RUSH!");
//        System.out.println("First, let's register 4, 6 or 8 teams to join the competition\n");
//
//
//        for (int i = 0; i < 8; i++) {
//            int teamNumber = i + 1;
//
//            int option = 0;
//            if (teamNumber == 5 || teamNumber == 7) {
//                System.out.println("\nWould you like to register another two teams?");
//                System.out.println("[1] yes  [2] no");
//                option = Utilities.readInt( 1, 2);
//            }
//
//            if (option == 2){
//                break;
//            }
//
//            createStartup(teamNumber);
//            //startups.add(startup);
//
//            System.out.println();
//        }
//
//        System.out.println("\nGreat! Now that the startups are all set, we've reached the battle stage.");
//
//        //double rounds = startups.size();
//
//        double rounds = startupController.getStartupsSize();
//        startupController.drawStartups(startupController.getStartupsSize());
//        //startups = Utilities.ramdomDraw(startups, startupController.getStartupsSize());
//
//        int currentRound = 1;
//
//        while (rounds > 1){
//            System.out.println("Current round: " + currentRound);
//
//            ArrayList<Battle> battles = new ArrayList<Battle>();
//
//            int battleNumber = 1;
//            for (int i = 0; i < rounds; i += 2) {
//                Battle battle = new Battle(battleNumber, startups.get(i), startups.get(i + 1));
//                battles.add(battle);
//
//                battleNumber++;
//            }
//
//            while (!battles.isEmpty()) {
//                System.out.println("\nChoose which battle you would like to start:");
//
//                int option = 1;
//                for (Battle b : battles) {
//                    System.out.println("[" + option + "] " + b.getStartup1().getName()
//                            + "  X  " + b.getStartup2().getName());
//                    option ++;
//                }
//
//                int aux = Utilities.readInt(1, battles.size()) - 1;
//
//                Battle chosenBattle = battles.get(aux);
//                chosenBattle.startBattle();
//
//                if (chosenBattle.getStartup1().getScore() == chosenBattle.getStartup2().getScore()) {
//                    chosenBattle.sharkfight();
//                }
//
//                //winner gets 30 points
//                chosenBattle.getHighestScore().setScore(30);
//
//                databaseManager.saveRoundResult(
//                        chosenBattle.getStartup1().getName(),
//                        chosenBattle.getStartup2().getName(),
//                        chosenBattle.getHighestScore().getName(),
//                        chosenBattle.getStartup1().getScore(),
//                        chosenBattle.getStartup2().getScore()
//                );
//
//                chosenBattle.resetStartups();
//
//                battles.remove(chosenBattle);
//            }
//
//
//            startupController.sortStartups();;
//            startupController.drawStartups(startupController.getStartupsSize() / 2);
//
//            currentRound++;
//            rounds /= 2;
//        }
//
//        startups.sort(Comparator.comparingInt(Startup::getScore).reversed());
//
//        Startup winner = startups.get(0);
//        printTable(startups);
//
//        System.out.println("\n" + winner.getSlogan());
//
//    }
//
////    public static Startup createStartup (int teamNumber) {
////        Scanner in = new Scanner(System.in);
////
////        Startup startup;
////        String name;
////        String slogan;
////        int foundingYear = 0;
////
////        System.out.println("\nTeam " + teamNumber);
////
////        System.out.println("\nName: ");
////        name = in.nextLine();
////
////        System.out.println("Slogan: ");
////        slogan = in.nextLine();
////
////        System.out.println("Founding year: ");
////        foundingYear = Utilities.readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
////
////        startup = new Startup(name, slogan, foundingYear);
////
////        databaseManager.registerStartup(name, slogan, foundingYear);
////
////        return startup;
////    }
//
//    public static void createStartup (int teamNumber) {
//        Scanner in = new Scanner(System.in);
//
//        Startup startup;
//        String name;
//        String slogan;
//        int foundingYear = 0;
//
//        System.out.println("\nTeam " + teamNumber);
//
//        System.out.println("\nName: ");
//        name = in.nextLine();
//
//        System.out.println("Slogan: ");
//        slogan = in.nextLine();
//
//        System.out.println("Founding year: ");
//        foundingYear = Utilities.readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
//
//        startupController.createStartup(name, slogan, foundingYear);
//    }
//
//    public static void printTable(ArrayList<Startup> startups) {
//        System.out.printf("%-15s %-10s", "model.Startup", "Score");
//
//        if (!startups.isEmpty()) {
//            Startup firstStartup = startups.get(0);
//            String[] eventNames = firstStartup.getEventName();
//
//            for (String event : eventNames) {
//                System.out.printf("  %-12s", event);
//            }
//        }
//        System.out.println();
//
//        for (Startup s : startups) {
//            System.out.printf("%-15s %-10d", s.getName(), s.getScore());
//
//            for (int i = 0; i < 5; i++) {
//                int occurrences = s.getEventOcurrences(i);
//                System.out.printf("   %-16d", occurrences);
//            }
//
//            System.out.println();
//        }
//    }
//}
