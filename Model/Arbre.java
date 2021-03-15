package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arbre {
    public int W=50;//weight de l'arbre
    public int H =70;// hauteur de l'arbre
    public int x;
    public  int y;
    private int index=0;
    private int XchoixDroit;//generer les arbres dans la piste droite
    private int XchoixGauche;//generer les arbres dans la piste gauche
    private BufferedImage arbres;
    private List<Arbre> arbresList=new ArrayList<Arbre>();

    public Arbre() {
        this.x= Affichage.LARG-200;
        this.y=Affichage.HAUT/4- H;
        Random rd=new Random();
        int index=rd.nextInt(8)+1;
        int n=rd.nextInt(2)+1;//pour afficher les arbres dans la piste droite ou la gauche;
        this.XchoixDroit=rd.nextInt(200)+Affichage.LARG/2+150;//x est dans la droite;
        this.XchoixGauche=rd.nextInt(Affichage.LARG/2-200);//x est dans la gauche;
        if(n==1){
            this.x=XchoixDroit;
        }else{
            this.x=XchoixGauche;
        }
        String RamdomArbre="arbre"+index;
        try {
            arbres = ImageIO.read(new File("src/png/" + RamdomArbre + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.y=200;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public BufferedImage getPicture() {
        return  this.arbres;
    }

    /**
     * pour les arbres deplacent de haut A bas;
     */
    public void move(){
        y += 10;
    }

    //pour prendre note de la nombre de la fonction appliquEe, Toutes les n fois, on ajoue un arbre dans la liste;
    public void getArbre(){
        this.index++;
        if(index>=10){
            Arbre arbre=new Arbre();
            arbresList.add(arbre);
            index=0;
        }
    }

    public void arbreMove(){
        for(int i=0;i<arbresList.size();i++){
            Arbre arbre=arbresList.get(i);
            arbre.move();
        }
    }

    /**
     * pour deplacer les arbres A droite;
     */
    public void getArbrelisteR(){

        this.arbresList.forEach(p->p.x+=5);
    }

    /**
     * pour deplacer les arbres A gauche
     */
    public void getArbrelisteL(){
        this.arbresList.forEach(p->p.x-=5);
    }

    public List<Arbre> getArbresList() {
        return arbresList;
    }
}
