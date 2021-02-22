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
public class Montagne extends Paysage{
    public int W = 800;//weight de la lune
    public int H = 200;// hauteur de la lune

    public Montagne(String a ){
        super(a);
        this.x= 0;
        this.y=0;

    }

}
