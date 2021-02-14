package Model;

import Control.Direction;
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
    public Moto(Piste piste,Nuages nuages){
    this.absc = Affichage.LARG/2-Affichage.WIDTH/2;
        try {
            this.pitrue = ImageIO.read(new File("src/png/moto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.piste =piste;
        this.nuages =nuages;
    }

    /**
     * Effectuer différentes actions en tapant les touches du clavier
     * @param key
     */
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
