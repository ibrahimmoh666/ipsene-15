package models;

public class Player {
    private String name;
    private int balance;
    private int position;

    private boolean inJail = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addStepsToPosition(int steps) {
        // In case the method gets called with a integer higher than the maximum throw
        if (steps > 12)
            steps = 12;

        // This makes sure the Player can circle around the board
        this.position += steps;

        if (this.position > 39) {
            this.position -= 40;
        }
    }

    public boolean isJailed() {
        return this.inJail;
    }
}
