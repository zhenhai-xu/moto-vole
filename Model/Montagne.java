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
public class Montagne{
    private BufferedImage pictrue;
    int x;//abscisse
    int y;//ordonnee
    int w=800;//weight
    int h=200;//hauteur
    public Montagne(){
        this.x= 0;
        this.y=0;

        try {
            this.pictrue = ImageIO.read(new File("src/png/montagne.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
