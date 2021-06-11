package models;

public class Speler {

    private String name;
    private String color;
    private int position;
    private int money;
    private int number;

    public Speler(String name, String color, int position, int money, int number) {
        this.name = name;
        this.color = color;
        this.position = position;
        this.money = money;
        this.number = number;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}


