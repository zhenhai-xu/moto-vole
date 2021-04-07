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
 * Les adversaires, les adversaires dépassés seront supprimés et leurs scores seront augmentés.
 * L'adversaire tentera de s'approcher du véhicule du joueur et de ralentir le véhicule du joueur.
 */
public class Concurrent{

    public int W=Affichage.WIDTH;//weight de Concurrent
    public int H =Affichage.HEIGHT;// hauteur de Concurrent
    public int x;
    public int y;

    private int index=0;
    private BufferedImage concurents;
    private List<Concurrent> concurentList= new ArrayList<>();
    private Piste piste;
    private int concurrentVitesse=16;//initialisation de la vitesse du concurrent
    public boolean fix=true;//pour laisser le concurrent qui peut se deplacer vers en bas
    Random rd=new Random();
    int Randomindex=rd.nextInt(4)+1;//pour laisser le concurrent des images différentes au hasard

    /**
     * generer l'image du concurrent
     * @param piste
     */
    public Concurrent(Piste piste) {
        this.piste=piste;
        this.x= Affichage.LARG/2;//initialisation de l'abscisse du concurrent
        this.y=Affichage.HAUT/4- H;//initialisation de l'axe Y du concurrent
        String RamdomConcurrent="concurent"+Randomindex;
        try {
            concurents = ImageIO.read(new File("src/png/" + RamdomConcurrent + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pour obtenir l'abscisse du concurrent
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * pour obtenir l'axe Y du concurrent
     * @return y
     */
    public int getY() {
        return this.y;
    }
    public BufferedImage getPicture() {
        return  this.concurents;
    }

    /**
     * pour ajouter l'objet concurrent dans la concurrentList，et verifier si le concurrent est depassé par la moto,
     * on obtient les score et supprime ce concurrent
     */
    public void getConccurent(){
        if(concurentList.size()<1){
            Concurrent concurrent=new Concurrent(piste);
           concurentList.add(concurrent);
        }
        if(concurentList.get(0).getY()>=800){
            piste.getBonusores();
            removeConcurrent();
        }

    }

    /**
     *Faire bouger l'ennemi par rapport à la piste
     */
    public void concurrentMove(){
        if(fix) {
            concurentList.forEach(p -> p.y += (piste.vitesse - concurrentVitesse));
        }
    }

    /**
     * pour deplacer le concurrent A droite;
     */
    public void getConcurrentlisteR(){

        this.concurentList.forEach(p->p.x+=4);
    }

    /**
     * pour deplacer le concurrent A gauche
     */
    public void getConcurrentlisteL(){
        this.concurentList.forEach(p->p.x-=4);
    }

    public List<Concurrent> getConcurrentList() {
        return concurentList;
    }

    /**
     * lorsque  la moto s'approche  au concurrent, il a but de empecher le moto qui le depasse,
     * alors il fait une parcours sin();
     */
    public void empecheMotodepasse(){
            if(concurentList.get(0).y>400 && fix) {
                int x1 = (int) (Math.sin(Math.PI / 6 * index) * 50);
                this.x = 400 + x1;
                index++;
                this.concurentList.forEach(p -> p.x = x);
            }
    }

    public void removeConcurrent() {
           concurentList.remove(0);
    }

}
