package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * creer class Lune, pour decoler la ferme.
 * la clasee Montagne, Nuages, PresqueSoir, Soir, Soleil sont meme chose avec la class Lune
 */
public class Nuages extends Paysage {
    public int W = 500;//weight de la Nuages
    public int H = 150;// height de la Nuages

    private BufferedImage pictrue;
    public Nuages(String a){
        super(a);
        this.x= Affichage.LARG/3;
        this.y=Affichage.HAUT/4;

    }

    /**
     * Nuages en mouvement
     */


}

