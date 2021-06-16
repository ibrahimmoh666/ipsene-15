package models;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ResourcePlace extends Place{
    protected String namn;
    protected int kostnad;

    public ResourcePlace(int n, int x, int y, String a, String name, int kost){
        super(n, x, y, a);
        namn = name;
        kostnad=kost;
        setToolTipText(namn);
    }

    public String getNamn(){
        return namn;
    }

    public int getKost(){
        return kostnad;
    }

    public int getResurs(){
        double kost = kostnad*0.1;
        int iKost = (int)kost;
        return iKost;
    }

    public Color getCol(){
        return Color.white;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setPaint(borderColor);
        if(align.equals("up") || align.equals("down"))
            rect = new Rectangle2D.Double(0, 0, x2, y2);
        else if(align.equals("left") || align.equals("right"))
            rect = new Rectangle2D.Double(0, 0, x2, y2);
        g2d.draw(rect);
        g2d.setPaint(Color.black);
        setFont(new Font("Goudy Handtooled BT", Font.PLAIN, 8));
        if(align.equals("right")){
            g2d.drawString(namn, 10, 25);
            g2d.drawString(String.valueOf(kostnad), 10, 35);
        }
        else if(align.equals("down")){
            g2d.drawString(namn, 10, 30);
            g2d.drawString(String.valueOf(kostnad), 10, 40);
        }
        else{
            g2d.drawString(namn, 4, 25);
            g2d.drawString(String.valueOf(kostnad), 4, 35);
        }
        if(!players.isEmpty()){
            int px = 10;
            int py = 5;
            for(Player p : players){
                add(p);
                p.setPosition(px, py);
                if(align.equals("up") || align.equals("down"))
                    py+=20;
                else if(align.equals("left") || align.equals("right"))
                    px+=20;
            }
        }

    }
    public String toString(){
        return namn;
    }
}


