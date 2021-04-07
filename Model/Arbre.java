package Model;

import Vue.Affichage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *Pour la décoration des deux côtés de l'allée,
 *  nous avons généré au hasard différents arbres (images différentes)
 */
public class Arbre {
    public int W=50;//weight de l'arbre
    public int H =70;// height de l'arbre
    public int x;
    public  int y;
    private int index=0;
    private BufferedImage arbres;
    private List<Arbre> arbresList=new ArrayList<Arbre>();
    private Piste piste;
    private double ratio;
    public Arbre(Piste piste) {
        this.piste=piste;
        this.x= Affichage.LARG-200;
        this.y=Affichage.HAUT/4- H;
        Random rd=new Random();
        int index=rd.nextInt(8)+1;
        int n=rd.nextInt(2);//pour afficher les arbres dans la piste A droite ou A  gauche;
        //generer les arbres dans la piste droite
        int xchoixDroit = rd.nextInt(200) + Affichage.LARG / 2 + 150;//x est A droite;
        //generer les arbres dans la piste gauche
        int xchoixGauche = rd.nextInt(Affichage.LARG / 2 - 200);//x est A gauche;
        String RamdomArbre="arbre"+index;
        if(n==0){
            this.x= xchoixDroit;
        }else{
            this.x= xchoixGauche;
        }
            try {
                arbres = ImageIO.read(new File("src/png/" + RamdomArbre + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        this.y=200;
    }

    /**
     * Obtenez la coordonnée x de l'arbre
     * @return x
     */
    public int getX() {
        return this.x;
    }
    /**
     * Obtenez la coordonnée y de l'arbre
     * @return y
     */
    public int getY() { return this.y;
    }
    /**
     * Obtenez l'image des arbres
     * @return this.arbres
     */
    public BufferedImage getPicture() {
        return  this.arbres;
    }

    /**
     * pour les arbres deplacent de haut A bas;
     */
    public void getArbre(){
        this.index++;//pour prendre note de la nombre de la fonction appliquEe, Toutes les n fois, on ajoue un arbre dans la liste;
        if(index>=5){
            Arbre arbre=new Arbre(piste);
            arbresList.add(arbre);
            index=0;
        }
        removeArbre();//Supprimer les arbres
    }

    /**
     * changer les coordonnees des arbres
     */
    public void arbreMove(){
        arbresList.forEach(p->p.y+= piste.vitesse);
        arbresList.forEach(p->p.W=(int) (W*0.003*p.y) );
        arbresList.forEach(p->p.H=(int) (H*0.003*p.y) );
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

    /**
     * obtenir Arraylist
     * @return arbresList
     */
    public List<Arbre> getArbresList() {

        return arbresList;
    }
    public  void removeArbre(){
        if(arbresList.size()>1)
        if (arbresList.get(1).y>=550)
            arbresList.remove(0);
    }
}
