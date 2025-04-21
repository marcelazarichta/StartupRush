package utilities;

import businessLogic.Startup;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Utilities {

    private static String red = "\u001B[31m";
    private static String normal = "\u001B[0m";

    public static void printRed (String message) {
        System.out.println(red + message + normal);
    }

    public static int readInt(int bottomLimit, int topLimit) {
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                int val = in.nextInt();
                in.nextLine();

                if(val < bottomLimit || val > topLimit) {
                    printRed("You must type a valid option");
                    continue;
                }
                return val;
            } catch (Exception e) {
                printRed("You must type a valid option in number format");
                in.nextLine();
            }
        }
    }

    public static ArrayList<Startup> ramdomDraw (ArrayList<Startup> startups, int limit) {
        Random rand = new Random();
        ArrayList<Startup> newArray = new ArrayList<>();
        ArrayList<Startup> temp = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            temp.add(startups.get(i));
        }

        int tempSize = temp.size();

        for (int i = 0; i < tempSize; i++) {
            int randomPosition = rand.nextInt(temp.size());
            Startup randomStartup = temp.get(randomPosition);
            newArray.add(randomStartup);
            temp.remove(randomPosition);
        }

        for (int i = limit; i < startups.size(); i++) {
            newArray.add(startups.get(i));
        }

        return newArray;
    }
}
