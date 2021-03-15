package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * creer la class Piste pour la moto peut deplacer dans la piste, en fait, on cree 2 ligne pour construire la piste
 * mais, on fait just creer une ligne, la deuxieme ligne est construit par la premiere ligne
 */
public class Piste{
    int x1=Affichage.LARG/2;//initiation de la piste de premier point dans la premiere ligne
    int y1=Affichage.HAUT;
    boolean Terminal=false;
    public static final int Avancer=1;
    private int position;
    private ArrayList<Point> points1=new ArrayList<>();//pour construire la piste
    private ArrayList<Point> points2=new ArrayList<>();//pour  construire le terminus


    public Piste(){
        this.position=0;
        points1.add(new Point(x1,y1));

        int z1=new Random().nextInt(100)+Affichage.LARG/2 - 50;//la deuxiere point
        int u1=y1-100;

        points1.add(new Point(z1,u1));
        do{
            z1 = points1.get(points1.size()-1).x ;
            u1 = points1.get(points1.size()-1).y - 100 ;
            points1.add(new Point(z1,u1));
        }while ( u1 > Affichage.HAUT/4);

    }

    public int getter(){
        return this.position=position;
    }

    public void setter(){
            this.position+=Avancer;
    }

    /**
     * pour construire A generer des points
     * @return Points
     */

    public ArrayList<Point> getPiste1(){
        this.points1.forEach(p->p.y+=10);
        this.points2.forEach(p->p.y+=10);
        if(getter()%100<10&&getter()>100){
            Terminal=true;
        }
        if(points1.get(points1.size()-1).y>=Affichage.HAUT/4){
            int x=new Random().nextInt(100)+Affichage.LARG/2 - 50;
            int y= points1.get(points1.size()-1).y-150;
            this.points1.add(new Point(x,y));
                if(Terminal) {
                    int tour_x = points1.get(points1.size() - 1).x;
                    int tour_y = points1.get(points1.size() - 1).y;
                    this.points2.add(new Point(tour_x, tour_y));
                    Terminal=false;
                }
        }
        if(points1.get(0).y<=150){
            removePiste();
        }
        return this.points1;
    }

    /**
     * pour retourne les points des terminus.
     * @return points2
     */
    public ArrayList<Point>getTerminal(){

        return this.points2;
    }


    public void setPositVoieL(){
        for(int i = 0; i < points1.size()-1; i++) {
            points1.get(i).x -= 5;

        }
        for(int i = 0; i < points2.size()-1; i++) {
            points2.get(i).x -= 5;

        }
    }
    public void setPositVoieR(){
        for(int i = 0; i < points1.size()-1; i++) {
            points1.get(i).x += 5;
        }
        for(int i = 0; i < points2.size()-1; i++) {
            points2.get(i).x += 5;
        }
    }

    public void removePiste(){
        points1.remove(0);
    }

}
