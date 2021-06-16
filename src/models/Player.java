package models;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends JComponent {
    private String namn;
    private int money;
    private int plan = 1;
    private ArrayList<Place> owns = new ArrayList<Place>();
    private TexturePaint bild;
    private Rectangle rect;
    private boolean okRounded = false;
    boolean haveJC = false;
    boolean isinJail = false;

    public Player(String na, int m, BufferedImage bi){
        namn = na;
        money = m;
        rect = new Rectangle(0, 0, 30, 30);
        bild = new TexturePaint(bi, rect);
        setToolTipText(namn);
    }

    public void setPlan(int i){
        int antal = 0;
        plan+=i;

        if(plan>28){
            antal = plan - 28;
            plan = antal;
            okRounded = true;
        }
        else
            okRounded = false;
    }

    public void setPosition(int x, int y){
        setBounds(x,y,30,30);
    }

    public void setDirectPosition(int i){
        plan = i;
    }

    public boolean hasRounded(){
        return okRounded;
    }

    public TexturePaint getBild(){
        return bild;
    }

    public int getPlan(){
        return plan;
    }

    public String getNamn(){
        return namn;
    }

    public int getMoney(){
        return money;
    }

    public int getIncome(){
        int cash = 0;
        for(Place p : owns)
            if(p instanceof ResourcePlace){
                ResourcePlace rp = (ResourcePlace) p;
                cash += rp.getResurs();
            }
        return cash;
    }

    public boolean buyProp(Place p){
        if(owns.contains(p))
            return false;
        owns.add(p);
        return true;
    }

    public boolean sellProp(Place p){
        if(!owns.contains(p))
            return false;
        owns.remove(p);
        return true;
    }

    public boolean propExists(Place p){
        if(owns.contains(p))
            return true;
        else
            return false;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(bild);
        g2d.fill(rect);
    }

    public ArrayList getProps(){
        return new ArrayList(owns);
    }

    public void setMoney(int i){
        money+= i;
    }
}
