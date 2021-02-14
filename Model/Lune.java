package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * creer class Lune, pour decoler la ferme.
 * la clasee Montagne, Nuages, PresqueSoir, Soir, Soleil sont meme chose avec la class Lune
 */
public class Lune {
    private BufferedImage pictrue;
    int x;//abscisse de la lune
    int y;//ordonnee de la lune
    int w=50;//weight de la lune
    int h=50;// hauteur de la lune
    public Lune(){
        this.x= Affichage.LARG-200;
        this.y=Affichage.HAUT/4-h;

        try {
            this.pictrue = ImageIO.read(new File("src/png/lune.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run() {
        // chaque instance que la lune augmente la distance 10
        this.y-=10;
    }
    public void retourne(){
        //refaire les coordonnee de la lune losque le soleil arrive.
        this.y=Affichage.HAUT/4-h;
    }
    public int getAbsc() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    public BufferedImage getPitrue(){
        return this.pictrue;
    }

}
