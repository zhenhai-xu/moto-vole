package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * creer class Lune, pour decoler la ferme.
 * la clasee Montagne, Nuages, PresqueSoir, Soir, Soleil sont meme chose avec la class Lune
 */
public class Soir extends Paysage{
    public int W = 800;//weight de la Soir
    public int H = 205;// height de la Soir

    public Soir(String a ){
        super(a);
        this.x= 0;
        this.y=0;
    }

}
