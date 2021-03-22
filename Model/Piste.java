package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * creer la class Piste pour la moto peut deplacer dans la piste, en fait, on cree 2 ligne pour construire la piste
 * mais, on fait just creer une ligne, la deuxieme ligne est construit par la premiere ligne
 */
public class Piste{
    private int x1=Affichage.LARG/2;//initiation de la piste de premier point dans la premiere ligne
    private int y1=Affichage.HAUT;
    public   boolean Terminal=false;
    public static final double Avancer= 0.5 ;
    public  double vitesse= 0;
    private double position=0;
    private int calculPoints=0; //pour calculer combien points dans toute la piste;
    private ArrayList<Point> points1=new ArrayList<>();//pour construire la piste
    private ArrayList<Point> points2=new ArrayList<>();//pour  construire le terminue
    public boolean AvoirPremierPoint2 =false;


    public Piste(){

        points1.add(new Point(x1,y1));

        int z1=new Random().nextInt(100)+Affichage.LARG/2 - 50;//la deuxiere point
        int u1=y1-100;

        points1.add(new Point(z1,u1));
        calculPoints++;
        do{
            z1 = new Random().nextInt(100)+Affichage.LARG/2 - 50;
            u1 = points1.get(points1.size()-1).y - 100 ;
            points1.add(new Point(z1,u1));
            calculPoints++;
        }while ( u1 > Affichage.HAUT/4);

    }

    public double getter(){
        return this.vitesse;
    }

    /**
     * pour construire A generer des points
     * @return Points
     */


    public ArrayList<Point> getPiste1(){
        this.points1.forEach(p->p.y+=vitesse);
        this.points2.forEach(p->p.y+=vitesse);
        if(calculPoints%15<=1){
            Terminal=true;
        }
        if(points1.get(points1.size()-1).y>=Affichage.HAUT/4){
            int x=new Random().nextInt(100)+Affichage.LARG/2 - 50;
            int y= points1.get(points1.size()-1).y-150;
            this.points1.add(new Point(x,y));
            calculPoints++;
                if(Terminal) {
                    int tour_x = points1.get(points1.size() - 1).x;
                    int tour_y = points1.get(points1.size() - 1).y;
                    this.points2.add(new Point(tour_x, tour_y));
                    AvoirPremierPoint2=true;
                    Terminal=false;

                }
        }
        if(points1.get(1).y>= Affichage.HAUT){
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
    public ArrayList<Point> getPiste(){
        return points1;
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
    public double x_actuel(double y){
        ArrayList<Point> pointlist = this.points1;
        double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0, x;
        double k, b;
        for(int i =0;i<pointlist.size() - 1;i++){
            //System.out.println(pointlist.get(i).y );
            if(pointlist.get(i).y >= y && pointlist.get(i+1).y <= y){
                //System.out.println("ieme point"+ i+" "+pointlist.get(i).y );
                //(x1, y1),(x2, y2) sont 2 points sur le côté gauche de la route, y est entre ces deux points
                x1 = pointlist.get(i).x;
                x2 = pointlist.get(i+1).x;
                y1 = pointlist.get(i).y;
                y2 = pointlist.get(i+1).y;
                //System.out.println("ieme point"+ i+" x1 "+x1+"   x2 "+ x2+"   y1 "+y1+"   y2 "+y2  );
            }
        }
        k = (y2 - y1) / (x2 -x1);
        b = (x2*y1 - x1*y2)/(x2 - x1);
        x = (y - b)/k;
        return x;
    }
    public void removePiste(){
        points1.remove(0);
    }



    public void changeVitesse(){vitesse -= 2*Avancer; }
    public void setterPlus(){this.vitesse += Avancer; }
    public void setterMoins(){this.vitesse -= Avancer; }

    public void setterPosition(){
        this.position+=Math.round(vitesse/5);
    }
    public int getPosition() {
        return (int) position;
    }
}
