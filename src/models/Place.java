package models;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Place extends JPanel{
    protected int nr;
    protected Rectangle2D.Double rect;
    protected int xP, yP;
    protected String align;
    protected int x2;
    protected int y2;
    protected ArrayList<Player> players = new ArrayList<Player>();
    protected boolean ownd = false;
    protected Color borderColor = Color.black;

    public Place(int n, int x, int y, String a){
        nr=n;
        xP=x;
        yP=y;
        align = a;
        if(align.equals("up") || align.equals("down")){
            x2 = 40;
            y2 = 60;
        }
        else if(align.equals("left") || align.equals("right")){
            x2 = 60;
            y2 = 40;
        }
        setBounds(x, y, x2, y2);
        setOpaque(true);
        setBackground(new Color(159, 214, 141));
    }

    public int getNr(){
        return nr;
    }

    public void setBordColor(Color c){
        borderColor = c;
    }

    public boolean insert(Player p){
        if(players.contains(p))
            return false;
        players.add(p);
        add(p);
        return true;
    }
    public String getNamn(){
        return "Place";
    }

    public boolean taBort(Player p){
        remove(p);
        players.remove(p);
        return true;
    }

    public boolean finns(Player p){
        if(players.contains(p))
            return true;
        else
            return false;
    }

    public int getKost(){
        return 0;
    }

    public Color getCol(){
        return new Color(0,0,0);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(Color.black);
        g2d.setStroke(new BasicStroke(3));

        repaint();
    }
    public String toString(){
        return "A Place";
    }
}
