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
public class Soleil{
    private BufferedImage pictrue;
    int x;
    int y;
    int w=50;
    int h=50;
    Boolean soir;
    public Soleil(){
        this.x= 200;
        this.y=Affichage.HAUT/4-h;
        this.soir=true;
        try {
            this.pictrue = ImageIO.read(new File("src/png/soleil.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
                    this.y-=10;
    }

    public void retourne(){
        this.y=Affichage.HAUT/4-h;
    }
    public int getAbsc() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean getSoleil(){return this.soir;}
    public BufferedImage getPitrue(){
        return this.pictrue;
    }


}
