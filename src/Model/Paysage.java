package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * paysage est le mere de la class Lune，Montagne，Nuages，PresqueSoir，Soir，et Soleil
 */
public class Paysage {

    private BufferedImage pictrue;
    int x;//abscisse de la Paysage
    int y;//ordonnee de la Paysage

    int speedMove = 1;
    public Paysage(String a){
        try {
            this.pictrue = ImageIO.read(new File("src/png/" + a + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void run( ) {
        // chaque instance que la lune augmente la distance 10
        this.y -= speedMove;
    }
    public void retourne(){
        //refaire les coordonnee de la lune losque le soleil arrive.
        this.y=Affichage.HAUT/4-50;
    }
    public int getAbsc() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setXL(){this.x -=2;}
    public void setXR(){this.x +=2;}
    public BufferedImage getPicture(){
        return this.pictrue;
    }

    public void moveL(){
        this.x -= Moto.ABSC_MOVE;
    }

    public void moveR(){
        this.x += Moto.ABSC_MOVE;
    }
}

