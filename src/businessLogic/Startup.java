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
    private String[] randomEvents;


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
        this.randomEvents = new String[] {
                "Economic recession -6",
                "Impressed investor +3",
                "Representative stuttered during the presentation -2",
                "Product is trending topics on Twitter +2",
                "Pitch was a little too long -1",
                "Products are in perfect condition +4",
                "Data leak -4",
                "Celebrity made free Instagram story about the Startup +6"
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

    public boolean getEvent(int index) {
        return events[index];
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
        int event;
        while(true) {
            System.out.println("\nSelect an available event: ");

            printAvailableEvents();
            event = Utilities.readInt(1, 7);

            if (event == 6){
                makeRandomEvent();
                continue;
            }

            if (event == 7){
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
        System.out.println("[6] Random surprise event");
        System.out.println("[7] Exit");
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

    public void makeRandomEvent() {
        int randomEvent = Utilities.randomNumber(randomEvents.length);

        System.out.println("\n" + randomEvents[randomEvent]);

        switch (randomEvent) {
            case 0:
                score -= 6;
                break;
            case 1:
                score += 3;
                break;
            case 2:
                score -= 2;
                break;
            case 3:
                score += 2;
                break;
            case 4:
                score -= 1;
                break;
            case 5:
                score += 4;
                break;
            case 6:
                score -= 4;
                break;
            default:
                score += 6;
                break;
        }
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
