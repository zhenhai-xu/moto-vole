package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * creer class Lune, pour decoler la ferme.
 * la clasee Montagne, Nuages, PresqueSoir, Soir, Soleil sont meme chose avec la class Lune
 */
public class PresqueSoir{
    private BufferedImage pictrue;
    int x;
    int y;
    int w=800;
    int h=200;
    public PresqueSoir(){
        this.x= 0;
        this.y=0;

        try {
            this.pictrue = ImageIO.read(new File("src/png/PresqueSoir.png"));
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
