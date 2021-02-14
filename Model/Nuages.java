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
public class Nuages {
    int x ;
    int y ;
    int w=100;
    int h=50;
    private BufferedImage pictrue;
    public Nuages(){
        this.x= Affichage.LARG/3;
        this.y=Affichage.HAUT/4;

        try {
            this.pictrue = ImageIO.read(new File("src/png/nuage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Nuages en mouvement
     */
    public void moveL(){
        this.x -= 3;
    }

    public void moveR(){
        this.x += 3;
    }

    public BufferedImage getPictrue() {
        return pictrue;
    }
    public int getAbsc() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

