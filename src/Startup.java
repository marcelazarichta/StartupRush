public class Startup {

    private int teamNumber;
    private String name;
    private String slogan;
    private int foundingYear;
    private int pontuacao;

    public Startup(int teamNumber, String name, String slogan, int foundingYear) {
        this.teamNumber = teamNumber;
        this.name = name;
        this.slogan = slogan;
        this.foundingYear = foundingYear;
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

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team Number: " + teamNumber + "\n");
        sb.append("Name: " + name + "\n");
        sb.append("Slogan: " + slogan + "\n");
        sb.append("Founding Year: " + foundingYear + "\n");
        sb.append("Pontuacao: " + pontuacao + "\n");
        return sb.toString();
    }


}
