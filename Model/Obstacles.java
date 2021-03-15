package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {
    public static final int W=80;//weight de l'arbre
    public static final int H =30;// hauteur de l'arbre
    public int x;
    public  int y;
    private int obstacle_x;//generer les arbres dans la piste gauche
    private BufferedImage obstacles;
    private List<Obstacles> obstaclesList=new ArrayList<Obstacles>();

    public Obstacles(Piste piste) {
        this.x= Affichage.LARG-200;
        this.y=Affichage.HAUT/4- H;
        Random rd=new Random();
        int index=rd.nextInt(8)+1;

        this.obstacle_x =rd.nextInt(piste.getPiste1().get(0).x-100)+200;//x est dans la gauche;

        String ramdomObstacle="arbre"+index;
        try {
            obstacles = ImageIO.read(new File("src/png/" + ramdomObstacle + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.y=200;
    }

    public BufferedImage getPicture() {
        return  this.obstacles;
    }
    public void getobstacleslisteL(){
        this.obstaclesList.forEach(p->p.x-=5);
    }
    /**
     * pour les arbres deplacent de haut A bas;
     */
    public void move(){
        y += 10;
    }


}
