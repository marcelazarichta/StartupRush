import java.util.*;

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

        double rounds = startups.size();
        startups = ramdomDraw(startups, startups.size());

        System.out.println("\nGreat! Now that the startups are all set, we've reached the battle stage.");


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

                int aux = readInt(1, battles.size()) - 1;


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

            startups = ramdomDraw(startups, startups.size()/2);



            currentRound++;
            rounds /= 2;
        }

        startups.sort(Comparator.comparingInt(Startup::getScore).reversed());

        Startup winner = startups.get(0);

        printTable(startups);

        System.out.println("\n" + winner.getSlogan());

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

    public static void printTable(ArrayList<Startup> startups) {
        // Cabeçalho da tabela
        System.out.printf("%-15s %-10s", "Startup", "Score");

        // Imprimir os nomes dos eventos de todas as startups
        if (!startups.isEmpty()) {
            Startup firstStartup = startups.get(0);  // A primeira startup para acessar os eventos
            String[] eventNames = firstStartup.getEventName(); // Obtém os nomes dos eventos da primeira startup

            // Imprimir os nomes dos eventos
            for (String event : eventNames) {
                System.out.printf("  %-12s", event); // Alinha os eventos
            }
        }
        System.out.println(); // Pula uma linha

        // Imprimindo os dados das startups
        for (Startup s : startups) {
            System.out.printf("%-15s %-10d", s.getName(), s.getScore()); // Nome e score

            // Imprimir as ocorrências de eventos de cada startup
            for (int i = 0; i < 5; i++) {
                int occurrences = s.getEventOcurrences(i); // Obtém o número de ocorrências do evento
                System.out.printf("   %-16d", occurrences); // Exibe a quantidade
            }

            System.out.println(); // Pula uma linha
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

class Color {
    public static String red = "\u001B[31m";
    public static String normal = "\u001B[0m";

    public static void printRed (String message) {
        System.out.println(red + message + normal);
    }

}
