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
public class Soleil extends Paysage{

    public int W = 100;//weight de la lune
    public int H = 60;// hauteur de la lune
    Boolean soir;


    public Soleil( String a){
        super(a);
        this.x= 150;
        this.y=Affichage.HAUT/4-H;
        this.soir=true;
    }
}
