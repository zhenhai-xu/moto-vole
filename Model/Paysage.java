package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paysage {

    private BufferedImage pictrue;
    int x;//abscisse de la lune
    int y;//ordonnee de la lune

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

    public BufferedImage getPicture(){
        return this.pictrue;
    }

    public void moveL(){
        this.x -= 5;
    }

    public void moveR(){
        this.x += 5;
    }
}

