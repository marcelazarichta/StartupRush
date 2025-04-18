public class Startup {

    private int teamNumber;
    private String name;
    private String slogan;
    private int foundingYear;
    private int score;
    private boolean[] events;


    public Startup(int teamNumber, String name, String slogan, int foundingYear) {
        this.teamNumber = teamNumber;
        this.name = name;
        this.slogan = slogan;
        this.foundingYear = foundingYear;
        this.score = 70;
        this.events = new boolean[5];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public int getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(int foundingYear) {
        this.foundingYear = foundingYear;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void resetEvents(){
        this.events = new boolean[5];
    }

    public boolean[] getEvents() {
        return events;
    }

    public void setEvents(boolean[] events) {
        this.events = events;
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
