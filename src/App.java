import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class App {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Startup> startups = new ArrayList<>();

        System.out.println("\nWelcome to STARTUP RUSH!");
        System.out.println("First, let's register 4, 6 or 8 teams to join the competition\n");


        for (int i = 0; i < 8; i++) {
            int teamNumber = i + 1;

            int option = 0;
            if (teamNumber == 5 || teamNumber == 7) {
                System.out.println("\nWould you like to register another two teams?");
                System.out.println("[1] yes  [2] no");
                option = readInt( 1, 2);
            }

            if (option == 2){
                break;
            }

            Startup startup = createStartup(teamNumber);
            startups.add(startup);

            System.out.println();
        }


        startups = ramdomDraw(startups);

        System.out.println("\nGreat! Now that the startups are all set, we've reached the battle stage.");


        ArrayList<Battle> battles = new ArrayList<>();

        int battleNumber = 1;
        for (int i = 0; i < startups.size(); i += 2) {
            Battle battle = new Battle(battleNumber, startups.get(i), startups.get(i + 1));
            battles.add(battle);

            battleNumber++;
        }


        while (!battles.isEmpty()) {
            System.out.println("Choose which battle you would like to start:");

            int option = 1;
            for (Battle b : battles) {
                System.out.println("[" + option + "] " + b.getStartup1().getName()
                        + "  X  " + b.getStartup2().getName());
                option ++;
            }

            int aux = readInt(1, battles.size()) - 1;

            Battle chosenBattle = battles.get(aux);

            chosenBattle.startBattle();




            battles.remove(chosenBattle);
        }
    }








    public static int readInt(int bottomLimit, int topLimit) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int val = in.nextInt();
                in.nextLine();

                if(val < bottomLimit || val > topLimit) {
                    Color.printRed("You must type a valid option");
                    continue;
                }
                return val;
            } catch (Exception e) {
                Color.printRed("You must type a valid option in number format");
                in.nextLine();
            }
        }
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
        foundingYear = readInt(Integer.MIN_VALUE, Integer.MAX_VALUE);

        startup = new Startup(teamNumber, name, slogan, foundingYear);

        return startup;
    }

    public static ArrayList<Startup> ramdomDraw (ArrayList<Startup> startups) {
        Random rand = new Random();
        ArrayList<Startup> newArray = new ArrayList<>();
        int startupsSize = startups.size();

        for (int i = 0; i < startupsSize; i++) {
            int randomPosition = rand.nextInt(startups.size());
            Startup randomStartup = startups.get(randomPosition);
            newArray.add(randomStartup);
            startups.remove(randomPosition);
        }

        return newArray;
    }
}

class Color {
    public static String red = "\u001B[31m";
    public static String normal = "\u001B[0m";

    public static void printRed (String message) {
        System.out.println(red + message + normal);
    }

}
