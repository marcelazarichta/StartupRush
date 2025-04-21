package businessLogic;

import utilities.Utilities;

public class Startup {

    private String name;
    private String slogan;
    private int foundingYear;
    private int score;
    private boolean[] events;
    private int[] eventsOcurrences;
    private String[] eventName;


    public Startup(String name, String slogan, int foundingYear) {
        this.name = name;
        this.slogan = slogan;
        this.foundingYear = foundingYear;
        this.score = 70;
        this.events = new boolean[5];
        this.eventsOcurrences = new int[5];
        this.eventName = new String[] {
                "Convincing pitch",
                "Buggy product",
                "Good user traction",
                "Annoyed investor",
                "Pitch with fake news"
        };
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getEventOcurrences(int index) {
        return eventsOcurrences[index];
    }

    public String[] getEventName() {
        return eventName;
    }

    public void resetEvents(){
        this.events = new boolean[5];
    }

    public int selectAvailableEvents() {
        int event = 0;
        while(true) {
            System.out.println("\nSelect an available event: ");

            printAvailableEvents();
            event = Utilities.readInt(1, 6);

            if (event == 6){
                break;
            }

            if (events[event - 1]){
                Utilities.printRed("You must choose an available event");
                continue;
            }
            break;
        }

        return event;
    }

    public void printAvailableEvents() {
        if (!events[0]) {
            System.out.println("[1] " + eventName[0] + " +6");
        }
        if (!events[1]) {
            System.out.println("[2] "+ eventName[1] + " -4");
        }
        if (!events[2]) {
            System.out.println("[3] " + eventName[2] + " +3");
        }
        if (!events[3]) {
            System.out.println("[4] " + eventName[3] + " -6");
        }
        if (!events[4]) {
            System.out.println("[5] " + eventName[4] + " -8");
        }
        System.out.println("[6] Exit");
    }

    public void makeEvent(int event) {
        switch (event) {
            case 0:
                score += 6;
                break;
            case 1:
                score -= 4;
                break;
            case 2:
                score += 3;
                break;
            case 3:
                score -= 6;
                break;
            case 4:
                score -= 8;
                break;
        }


        events[event] = true;
        eventsOcurrences[event]++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + "\n");
        sb.append("Slogan: " + slogan + "\n");
        sb.append("Founding Year: " + foundingYear + "\n");
        sb.append("Score: " + score + "\n");
        return sb.toString();
    }

}
