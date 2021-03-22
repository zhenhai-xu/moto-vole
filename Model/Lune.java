package Model;

import Vue.Affichage;

import java.awt.image.BufferedImage;

/**
 * creer class Lune, pour decoler la ferme.
 * la clasee Montagne, Nuages, PresqueSoir, Soir, Soleil sont meme chose avec la class Lune
 */
public class Lune extends Paysage{

    private BufferedImage pictrue;

    public int W=50;//weight de la lune
    public int H =50;// hauteur de la lune

    public Lune(String lune){
        super(lune);
        this.x=Affichage.LARG-200;
        this.y=Affichage.HAUT/4- H;
    }


}
