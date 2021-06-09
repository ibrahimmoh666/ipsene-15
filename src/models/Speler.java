package models;

import java.util.ArrayList;
import java.util.Arrays;

public class Speler {

    private String name;
    private String pionKleur;
    private int position;
    private int money;

    public String getName() {
        return name;
    }

    public Speler(String name, int position, int money) {
        this.name = name;
        this.position = position;
        this.money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPionKleur() {
        return pionKleur;
    }

    public void setPionKleur(String pionKleur) {
        this.pionKleur = pionKleur;
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
