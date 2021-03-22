package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Moto {
    private int absc;

    private BufferedImage pitrue;
    private Affichage affichage;
    private final int ABSC_MOVE = 10;
    private int y = Affichage.HAUT-100;
    private Piste piste;
    private Nuages nuages;
    private Soleil soleil;
    private Lune lune;
    private Arbre arbre;
    private  double VITESSELIMITE=15.0;

    public  boolean gameOver=false;
    public  boolean up = false;
    public  boolean down = false;
    public  boolean left = false;
    public  boolean right = false;
    public  boolean ralentir=false;//pour verifier si moto est ralenti
    public Moto(Piste piste,Nuages nuages,Lune lune,Soleil soleil,Arbre arbre){
    this.absc = Affichage.LARG/2-Affichage.WIDTH/2;
        try {
            this.pitrue = ImageIO.read(new File("src/png/moto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.piste =piste;
        this.nuages =nuages;
        this.lune=lune;
        this.soleil=soleil;
        this.arbre=arbre;
    }
    public int getY(){
        return this.y;
    };
/*
    /**
     * Effectuer différentes actions en tapant les touches du clavier
     * @param key
     */
    public void moveUp() {
        if(up && this.y>=600){
            System.out.println("up 5 ");
            this.y-=5;
            if(piste.vitesse>=10)
                this.VITESSELIMITE=5;
        }
    }
    public void moveLeft() {
        if(left && this.absc > 0){
            absc -= ABSC_MOVE;
            nuages.moveR();
            soleil.moveR();
            lune.moveR();
            arbre.getArbrelisteR();
            piste.setPositVoieR();
        }
    }
    public void moveRight() {
        if(right && this.absc < Affichage.LARG - Affichage.WIDTH){
            absc += ABSC_MOVE;
            nuages.moveL();
            soleil.moveL();
            lune.moveL();
            arbre.getArbrelisteL();
            piste.setPositVoieL();
        }
    }
    public void moveDown() {

    }
public void move(){
    moveUp();
    moveDown();
    moveLeft();
    moveRight();
    if(y<700&& up != true){
        y+=5;
    }if(y==700) VITESSELIMITE=15;
}
    /**
     * setting affichage a moto.
     * @param affichage
     */
    public void setAffichage(Affichage affichage) {
        this.affichage = affichage;
    }

    /**
     * get l'abscisse.
     * @return absc
     */
    public int getAbsc() {
        return this.absc;
    }
    public BufferedImage getPitrue(){
        return this.pitrue;
    }

    /**
     * verifier si il est ralentit.;
     */
    public void ralentir(){
        for(int i=0;i<arbre.getArbresList().size();i++) {
            Arbre arbre= this.arbre.getArbresList().get(i);
           if(Math.abs(absc - arbre.x) <= arbre.W && Math.abs(y - arbre.y) <= arbre.H){
               ralentir=true;
           }
        }
    }

    public void setMotoV(){
        double x =piste.x_actuel(Affichage.HAUT-Affichage.HEIGHT/2);
        //System.out.println(x+"  "+piste.getPiste().get(0).x);
        if( this.absc+Affichage.WIDTH/2 >= x-100 &&  this.absc+Affichage.WIDTH/2 <= x+100 && piste.vitesse<= VITESSELIMITE){
            piste.setterPlus();
        }else{
            if(piste.vitesse > 2){ piste.setterMoins();}
            else if (piste.vitesse == 0){ gameOver=true; }
        }
    }
}
