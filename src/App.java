import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


public class App {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);

        ArrayList<Startup> startups = new ArrayList<>();

        System.out.println("\nWelcome to STARTUP RUSH!\n");
        System.out.println("First, let's register 4, 6 or 8 teams to join the competition\n");


        for (int i = 0; i < 8; i++) {
            int teamNumber = i + 1;

            if (teamNumber == 5 || teamNumber == 7) {
                System.out.println("\nWould you like to register another two teams?");
                System.out.println("[1] yes  [2] no");
                int option = in.nextInt();
                System.out.println();

                if (option == 2) {
                    break;
                } else if (option != 1) {
                    System.out.println(Color.red + "You must choose between options [1] and [2]" + Color.normal);
                    i--;
                    continue;
                }
            }

            Startup startup = createStartup(teamNumber);
            startups.add(startup);

            System.out.println();
        }


        startups = ramdomDraw(startups);
        startups.forEach(System.out::println);

        ArrayList<Battle> battles = new ArrayList<>();

        for (int i = 0; i < startups.size(); i += 2) {
            Battle battle = new Battle(i, startups.get(i), startups.get(i + 1));
            battles.add(battle);
        }



        System.out.println("Great! Now that the startups are set, we've reached the battle stage.");
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

        int proceed = 0;

        while (proceed != 1) {
            System.out.println("Founding year: ");
            try {
                foundingYear = in.nextInt();
                proceed++;
            } catch (Exception e) {
                System.out.println(Color.red + "\nType the year of foundation of the startup in numbers");
                System.out.println("Example: '2015'\n" + Color.normal);
            }

            in.nextLine();
        }

        startup = new Startup(teamNumber, name, slogan, foundingYear);

        return startup;
    }
}

class Color {
    public static String red = "\u001B[31m";
    public static String normal = "\u001B[0m";

}