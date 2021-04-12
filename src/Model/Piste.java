package Model;

import Vue.Affichage;

import java.awt.*;
import java.awt.geom.Point2D;
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
    public  double vitesse= 10;
    private double position=0;
    private int calculPoints=0; //pour calculer combien points dans toute la piste;
    private ArrayList<Point> points1=new ArrayList<>();//pour construire la piste
    private ArrayList<Point2D> points2=new ArrayList<>();//pour  construire le terminue
    public boolean AvoirPremierPoint2 =false;//Afin de déterminer si vous pouvez ajouter un point
    public  double acceleration=10;//c'est un variable pour changer la vitesse
    public   double VITESSELIMITE=20;// la limitation de la vitesse
    public  boolean gameOver=false;
    public double x_actuel=0;
    public int n=0;//pour ralentir la incrementation de score,sinon c'est trop vite

    /**
     *
     Ajoutez deux points au début,
     puis entrez dans la boucle pour déterminer si vous pouvez continuer à ajouter de nouveaux points
     */
    public Piste(){

        points1.add(new Point(x1,y1));

        int z1=new Random().nextInt(100)+Affichage.LARG/2 - 50;//la deuxiere point
        int u1=y1-150;

        points1.add(new Point(z1,u1));
        calculPoints++;
        do{
            z1 = new Random().nextInt(100)+Affichage.LARG/2 - 50;
            u1 = points1.get(points1.size()-1).y - 150 ;
            points1.add(new Point(z1,u1));
            calculPoints++;
        }while ( u1 > Affichage.HAUT/4);

    }

    /**
     * obtenir la vitesse de la piste
     * @return vitesse
     */

    public double getter(){
        return this.vitesse;
    }


    /**
     * pour construire A génerer des points de contrôle
     * et changer les ordonnées des deux listes
     * retourner la premier liste qui est la piste
     * @return Points
     */
    public ArrayList<Point> getPiste1(){
        this.points1.forEach(p->p.y+=vitesse);
        this.points2.forEach(p->p.setLocation(p.getX(),p.getY()+vitesse));
        if(calculPoints%15<=1){
            Terminal=true;
        }
        if(points1.get(points1.size()-1).y>=Affichage.HAUT/6){
            int x=new Random().nextInt(100)+Affichage.LARG/2 - 50;
            int y= points1.get(points1.size()-1).y-150;
            this.points1.add(new Point(x,y));
            calculPoints++;
                if(Terminal) {
                    double tour_x = points1.get(points1.size() - 1).x;
                    double tour_y = points1.get(points1.size() - 1).y;
                    this.points2.add(new Point2D.Double(tour_x, tour_y));
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
     * pour retourne les points de contrôle.
     * @return points2
     */
    public ArrayList<Point2D>getTerminal(){

        return this.points2;
    }

    /**
     * Déplacer vers la gauche dans son ensemble
     */
    public void setPositVoieL(){
        for(int i = 0; i < points1.size()-1; i++) {
            points1.get(i).x -= 5;
        }
        for(int i = 0; i < points2.size()-1; i++) {
            points2.get(i).setLocation(points2.get(i).getX()-5,points2.get(i).getY());
        }
    }

    /**
     * Déplacer vers la droite dans son ensemble
     */
    public void setPositVoieR(){
        for(int i = 0; i < points1.size()-1; i++) {
            points1.get(i).x += 5;
        }
        for(int i = 0; i < points2.size()-1; i++) {
            points2.get(i).setLocation(points2.get(i).getX()+5,points2.get(i).getY());
        }
    }

    /**
     * Entrez l'ordonnée y pour calculer l'abscisse actuelle.
     * Cette valeur x est utilisée pour juger le point central au début du développement.
     * Dans les derniers stades de développement. Les coordonnées du point de contrôle utilisent cette fonction
     * à la place de x de la courbe de Bézier à cause d'un bug inconnu.
     * @param y
     * @return
     */
    public double x_actuel(double y){
        ArrayList<Point> pointlist = this.points1;
        double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0, x;
        double k, b;
        for(int i =0;i<pointlist.size() - 1;i++){
            //Déterminer dans quel segment de ligne se trouve le point actuel.
            if(pointlist.get(i).y >= y && pointlist.get(i+1).y <= y){
                //(x1, y1),(x2, y2) sont les points de départ et le point d'arrivée.
                x1 = pointlist.get(i).x;
                x2 = pointlist.get(i+1).x;
                y1 = pointlist.get(i).y;
                y2 = pointlist.get(i+1).y;
                //System.out.println("ieme point"+ i+" x1 "+x1+"   x2 "+ x2+"   y1 "+y1+"   y2 "+y2  );
            }
        }
        //Calculer le point d'intersection selon la formule du point d'intersection du segment de ligne
        //k est la pente
        //rappel la formule du point d'intersection du segment de ligne:
        // y－y1=k（x－x1).
        k = (y2 - y1) / (x2 -x1);
        b = (x2*y1 - x1*y2)/(x2 - x1);
        x = (y - b)/k;

        return x;
    }



    double getCurveX(Point2D p0,Point2D p1,Point2D p2,double t){
        return  (1-t) * (1-t) * p0.getX() + 2 * t * (1-t) * p1.getX() + t * t * p2.getX();
    }

    /**Entrez la valeur requise de l'ordonnée du point p,
    * initialisez une courbe de Bézier et commencez à
    * rechercher la valeur de l'abscisse correspondante
    */
     public double getXSurCourbe(double y){
        double debut_x = this.points1.get(0).getX() + 20;
        double debut_y = this.points1.get(0).getY() + 20;

        Point2D[] pointlist = new Point2D[points1.size() + 1];

        // les coordonnées ont été calculées en retirant la valeur de position à leur valeur d’abscisse.
        pointlist[0] = new Point2D.Double(debut_x, debut_y);
        int j = 1;
        for (Point2D p : points1) {
            pointlist[j] = new Point2D.Double(p.getX(), p.getY());
            j++;
        }
        double x1 = 0.0, y1 = 0.0, x2 = 0.0, y2 = 0.0, x3 = 0.0, y3 = 0.0, t, x_piste;

        for(int i = 0;i<pointlist.length - 2; i++){
            //Trouver le point p dont nous avons besoin est situé dans ce segment de ligne
            if((pointlist[i].getY() + pointlist[i + 1].getY()) *Affichage.T >= y
                    && (pointlist[i + 1].getY() + pointlist[i + 2].getY()) * Affichage.T <= y){
                //L'abscisse du point de départ est x1 et l'ordonnée est y1
                //L'abscisse du point final est x2 et l'ordonnée est y2
                //(x2, y2) est le point de control
                x1 = (pointlist[i].getX() + pointlist[i + 1].getX()) * Affichage.T;
                y1 = (pointlist[i].getY() + pointlist[i + 1].getY()) * Affichage.T;

                x2 = pointlist[i+1].getX();
                y2 = pointlist[i+1].getY();

                x3 = (pointlist[i+1].getX() + pointlist[i+2].getX())* Affichage.T;
                y3 = (pointlist[i+1].getY() + pointlist[i+2].getY())* Affichage.T;
                break;
            }
        }

        Point2D p0 = new Point2D.Double(x1, y1);
        Point2D p1 = new Point2D.Double(x2, y2);
        Point2D p2 = new Point2D.Double(x3, y3);

            //Calculez la valeur t de la courbe de Bézier selon la formule Bézier a l'aide valeur y
            t = calculeT(p0, p1, p2, y);
            //Calculer la valeur x de la courbe de Bézier selonla formule Bézier a l'aide valeur t
            x_piste = getCurveX(p0, p1, p2, t);
            //System.out.println("t = " + t + " x= " + x_piste);
        return x_piste;
    }

    /**
     * Déterminer quelle solution obtenue par l'équation2 est ce dont nous avons besoin
     * @param p0 le point debut
     * @param p1 le point control
     * @param p2 le poinr fin
     * @param p  le point qu'on veut calcule
     * @return
     */
    public double calculeT(Point2D p0, Point2D p1, Point2D p2, double p) {
        double interpolationx =  (p1.getX() - p0.getX()) / (p2.getX() - p0.getX());

        //Lorsque interpolationx est égal à 0, il n'y a qu'une seule solution.
        //rappel interpolationx est b dans equation2.
        if(interpolationx >= 0 && interpolationx <= 1){
            double[] ty = equation2(p0.getY(),p1.getY(),p2.getY(),p);
            return ty[0];

        }else{
            double[] tt = equation2(p0.getY(),p1.getY(),p2.getY(),p);
            //S'il existe deux solutions
            if(tt[1] != -1 ){
                double pointTest = getCurveX(p0,p1,p2,tt[0]);
                //Pour le jugement de distance, la valeur proche de P est la valeur t dont nous avons besoin
                if(Math.abs(pointTest-p) < 0.01){
                //    System.out.println("t  1 = " + tt[0] );
                    return tt[0];
                }else{
                //    System.out.println("t 2 = " + tt[1] );
                    return tt[1];
                }
            }else{
                //      System.out.println("t 0= " + tt[0] );
                return tt[0];
            }
        }
    }

    /**
     * Cette fonction permet de calculer la valeur tde la courbe de Bézier
     * z0, z1, z2 représentent la valeur de coordonnée x ou la valeur de coordonnée y de P0, P1, P2, zp
     * représente la valeur de coordonnée x ou la valeur de coordonnée y du point cible P
     * @param z0
     * @param z1
     * @param z2
     * @param zp
     * @return
     * Emprunter des idées sur Internet：
     * https://blog.csdn.net/netcy/article/details/102382428
     */
    double[] equation2(double z0,double z1,double z2,double zp){
        //La formule est dérivée de la courbe de Bézier, et les valeurs suivantes sont nécessaires pour trouver la formule,
        // qui sont expliquées en détail dans le rapport.
        double a = (z0 - (z1 * 2) + z2);
        double b = 2*(z1 - z0);
        double c = z0 - zp;
        double[] tt = new double[2];

        if(a == 0 && b != 0){
            //Une seule solution
            tt[0] =  - c / b;
            tt[1] = -1;
        //    System.out.println("equation2 t = " + tt[0] +" c = "+ c +" b = "+b  );
        } else {
            //Il s'agit d'exclure a == 0 et b == 0, sinon une erreur se produira.
            if (a == 0 && b == 0){
                tt[0] =  0.5;
                tt[1] =  0.5;
                return tt;
            }else {
                //Quand il y a deux solutions
                double sq = Math.sqrt( b * b - 4 * a * c );
                double tt1 = (sq - b)/ (2 * a);
                double  tt2 = (-sq - b) / (2 * a);
                //Les deux solutions sont dans la fourchette, nous devons continuer à juger,
                // nous utiliserons calculeT pour juger
                if((tt1 <= 1 && tt1>= 0) && (tt2 <= 1 && tt2>= 0)){
                    tt[0]=tt1;
                    tt[1]=tt2;
                 //Lorsque l’une des solutions n’est pas dans la plage de [0,1], l’autre solution est correcte
                }else if(tt1 <= 1 && tt1>= 0){
                    //t[0] enregistrer la bonne solution
                    tt[0] = tt1;
                    tt[1] = -1;
                }else {
                    tt[0] = tt2;
                    tt[1] = -1;
                }
            }
        }
         return tt;

    }

    /**
     * supprimer le premier objet de la liste de points1
     */
    public void removePiste(){
        points1.remove(0);

    }

    /**
     * collision avec arbre/obstacle，alors ralentir
     */
    public void changeVitesse(){vitesse -= 2*Avancer; }

    /**
     * calculer Score du jeu
      */
    public void setterPosition(){
        if(n>=10) {
            this.position += Math.round(vitesse / 10);
            n=0;
        }
        n++;
    }

    /**
     * si dépasser le conccurrent
     * alors incrementation le score
     */
    //depasser concurrent
    public void getBonusores(){
        this.position+=10;
    }

    /**
     * retourner le score pour l'instance
     * @return position
     */
    public int getPosition() {
        return (int) position;
    }

    /**
     * pour changer la vitesse de la piste
     * @param time
     */
    public void setVitesse(double time){
        if(this.vitesse<=0){
            gameOver=true;
        }
        if( this.acceleration <=0 ){
            this.vitesse += this.acceleration * time;
        }else if(this.vitesse <= this.VITESSELIMITE){
        this.vitesse += this.acceleration * time;}

        //System.out.println("moto vitesse = " +vitesse+"acc "+this.acceleration);
    }

    /**
     * pour changer l'acceleration de la piste selon l'emplacement de la moto
     * Si la moto est loin de la piste, l'accélération devient de plus en plus petite
     * @param moto
     */
    public void setAcceleration(Moto moto) {
            double x = x_actuel(moto.getY());
            double x2 = moto.getAbsc();
            double c =Math.sqrt(Math.pow((moto.getAbsc()+Affichage.WIDTH/2-x),2)+Math.pow((moto.getY()+Affichage.HEIGHT-y1),2));
            if (c==0) {
                this.acceleration=100;
            }
            else if (c <=100) {
                this.acceleration=100/c;
            }else{
                this.acceleration=-c/100;
        }
    }

}
