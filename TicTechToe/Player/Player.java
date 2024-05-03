package Player;

public class Player {
    private String name = "";
    private char symbol = '-';

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    // Getters
    public char getPlayerSymbol() {
        return this.symbol;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void printPlayerDetails() {
        if (!name.equals(""))
            System.out.println("Player Name : " + name);

        if (symbol != '-')
            System.out.println("Player Symbol : " + symbol);
    }

}
