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
    private Piste piste;
    private Nuages nuages;
    private Soleil soleil;
    private Lune lune;
    private Arbre arbre;

    public  boolean up = false;
    public  boolean down = false;
    public  boolean left = false;
    public  boolean right = false;
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
/*
    /**
     * Effectuer différentes actions en tapant les touches du clavier
     * @param key
     */
/*
    public void move(Direction key) {
        if(key == Direction.up ) {
            //this.absc =
            System.out.println("accelerer");
        }else if(key == Direction.down ) {
            //this.absc
            System.out.println("lentement");
        }else if(key == Direction.left && this.absc > 0) {
            this.absc -= ABSC_MOVE;
            this.piste.setPositVoieR();
            this.nuages.moveR();
        }else if(key == Direction.right && this.absc < affichage.LARG-affichage.WIDTH) {
            this.absc += ABSC_MOVE;
            this.piste.setPositVoieL();
            this.nuages.moveL();
        }else {
            System.out.println("la grenouille atteigne le bord ");
        }
        affichage.repaint();
    }*/
    public void moveUp() {
        if(up){
            System.out.println("acclerer");
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
        if (down) {
            System.out.println("lencement");
        }
    }
public void move(){
    moveUp();
    moveDown();
    moveLeft();
    moveRight();
    affichage.repaint();
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
     * @return
     */
    public int getAbsc() {
        return this.absc;
    }
    public BufferedImage getPitrue(){
        return this.pitrue;
    }
}
