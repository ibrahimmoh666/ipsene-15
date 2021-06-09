package models;

public class Speler {

    private String name;
    private String color;
    private int position;
    private int money;

    public Speler(String name, String color, int position, int money) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.money = money;
    }

    public Speler() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}


