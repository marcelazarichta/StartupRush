public class Startup {

    private int teamNumber;
    private String name;
    private String slogan;
    private int foundingYear;
    private int score;
    private boolean[] events;
    private int[] eventsOcurrences;
    private String[] eventName;


    public Startup(int teamNumber, String name, String slogan, int foundingYear) {
        this.teamNumber = teamNumber;
        this.name = name;
        this.slogan = slogan;
        this.foundingYear = foundingYear;
        this.score = 70;
        this.events = new boolean[5];
        this.eventsOcurrences = new int[5];
        this.eventName = setEventName();
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

    public String[] setEventName(){
        eventName = new String[5];
        eventName[0] = "Convincing pitch";
        eventName[1] = "Buggy product";
        eventName[2] = "Good user traction";
        eventName[3] = "Annoyed investor";
        eventName[4] = "Pitch with fake news";

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
            event = App.readInt(1, 6);

            if (event == 6){
                break;
            }

            if (events[event - 1]){
                Color.printRed("You must choose an available event");
                continue;
            }
            break;
        }

        return event;
    }

    public void printAvailableEvents() {
        if (!events[0]) {
            System.out.println("[1] Convincing pitch +6");
        }
        if (!events[1]) {
            System.out.println("[2] Buggy product -4");
        }
        if (!events[2]) {
            System.out.println("[3] Good user traction +3");
        }
        if (!events[3]) {
            System.out.println("[4] Annoyed investor -6");
        }
        if (!events[4]) {
            System.out.println("[5] Pitch with fake news -8");
        }
        System.out.println("[6] Exit");
    }

    public void makeEvent (int event){
        if (event == 0){
            score += 6;
        } else if (event == 1){
            score -= 4;
        } else if (event == 2){
            score += 3;
        } else if (event == 3){
            score -= 6;
        } else {
            score -= 8;
        }

        events[event] = true;
        eventsOcurrences[event]++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Number: " + teamNumber + "\n");
        sb.append("Name: " + name + "\n");
        sb.append("Slogan: " + slogan + "\n");
        sb.append("Founding Year: " + foundingYear + "\n");
        sb.append("Score: " + score + "\n");
        return sb.toString();
    }

}
