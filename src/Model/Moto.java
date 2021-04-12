package Model;

import Vue.Affichage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.PublicKey;

/**
 *Contrôlez le mouvement du véhicule. Après avoir modifié les coordonnées du véhicule,
 * les coordonnées des autres contenus de la fenêtre doivent également être modifiées
 */


public class Moto {
    private int absc;

    private BufferedImage picture;
    private Affichage affichage;
    private int y = Affichage.HAUT-100;
    private Piste piste;
    private Nuages nuages;
    private Soleil soleil;
    private Lune lune;
    private Arbre arbre;
    private Ombre ombre;
    private Concurrent concurrent;

    public final static int ABSC_MOVE = 10;
    public  boolean up = false;
    public  boolean down = false;
    public  boolean left = false;
    public  boolean right = false;
    public  boolean ralentir=false;//pour verifier si moto est ralenti


    public Moto(Piste piste, Nuages nuages, Lune lune, Soleil soleil, Arbre arbre, Concurrent concurrent){
    this.absc = Affichage.LARG/2-Affichage.WIDTH/2;
        try {
            this.picture = ImageIO.read(new File("src/png/moto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.piste =piste;
        this.nuages =nuages;
        this.lune=lune;
        this.soleil=soleil;
        this.arbre=arbre;
        this.concurrent=concurrent;
    }

    public int getY(){
        return this.y;
    };

    /**
     * Effectuer différentes actions en tapant les touches du clavier
     * @param
     */
    public void moveUp() {
        //Au fur et à mesure que le véhicule monte, l'ombre devient plus petite.
        //Ajustez la taille de l'ombre pour qu'elle corresponde au véhicule
        if(up && this.y>=600) {
            this.y-=5;
            ombre.setW(-4);
            ombre.x -= 2;
            concurrent.fix=true;

        }
    }

    //Le véhicule se déplace vers la gauche.
    public void moveLeft() throws IOException {
        if(left && this.absc > 0){
            try{
                //Si vous appuyez le gauche sur clavier, nous chargeons l'image motoL
                if(right !=true)
                this.picture = ImageIO.read(new File("src/png/motoL.png"));}
            catch (IOException e) {
                e.printStackTrace();
            }
            //les coordonnées des autres contenus de la fenêtre doivent également être modifiées
            absc -= ABSC_MOVE;
            nuages.moveR();
            soleil.moveR();
            lune.moveR();
            arbre.getArbrelisteR();
            piste.setPositVoieR();
            concurrent.getConcurrentlisteR();;
            ombre.moveL();
        }
    }

    //Le véhicule se déplace vers la droite.
    public void moveRight() throws IOException {
        if(right && this.absc < Affichage.LARG - Affichage.WIDTH){
            try{
                //Si vous appuyez le droite sur clavier, nous chargeons l'image motoR
                if(left !=true)
                this.picture = ImageIO.read(new File("src/png/motoR.png"));}
            catch (IOException e) {
                e.printStackTrace();
            }

            ////les coordonnées des autres contenus de la fenêtre doivent également être modifiées
            absc += ABSC_MOVE;
            nuages.moveL();
            soleil.moveL();
            lune.moveL();
            arbre.getArbrelisteL();
            concurrent.getConcurrentlisteL();
            piste.setPositVoieL();
            ombre.moveR();
        }
    }

    public void moveDown() {

    }
public void move() throws IOException {

    //Détecter l'état du véhicule
    moveUp();
    moveDown();
    moveLeft();
    moveRight();
    //Si vous êtes en l'air et n'appuyez pas sur le bouton, le véhicule descendra et l'ombre deviendra plus grande
    if(y<700&& up != true){
        y+=5;
        ombre.setW(4);
        ombre.x += 2;

    }
    if( left != true && right !=true){

        //Le véhicule roule normalement et les images à l'état normal sont chargées
        try {
            this.picture = ImageIO.read(new File("src/png/moto.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

    /**
     * setting affichage a moto.
     * @param affichage
     */
    public void setAffichage(Affichage affichage) {
        this.affichage = affichage;
    }

    /**
     * get l'abscisse.
     * @return absc
     */
    public int getAbsc() {
        return this.absc;
    }
    public BufferedImage getPicture(){
        return this.picture;
    }

    /**
     * verifier si il est ralentit.
     */
    public void ralentir(){
        for(int i=0;i<arbre.getArbresList().size();i++) {
            Arbre arbre= this.arbre.getArbresList().get(i);
           if(Math.abs(absc - arbre.x) <= arbre.W && Math.abs(y - arbre.y) <= arbre.H){
               ralentir=true;
           }
        }
    }

    public void setOmbre(Ombre ombre) {
        this.ombre = ombre;
    }

    /**
     * losque le moto a  les collisions avec concurrent, alors il ne peut pas depasser le concurrent.
     * il faut avoir un distance de la securite.
     */
    public void ConditionDepasse(){
        if(Math.abs(getAbsc()-concurrent.getConcurrentList().get(0).x)<=85
                &&getY()-(concurrent.getConcurrentList().get(0).y)<=80 &&getY()-(concurrent.getConcurrentList().get(0).y)>=0){
            int x1=concurrent.getConcurrentList().get(0).x;
            concurrent.fix=false;
            int finalX = x1;
            concurrent.getConcurrentList().forEach(p->p.x= finalX);
            concurrent.getConcurrentList().forEach(p->p.y=concurrent.getConcurrentList().get(0).y);
            piste.acceleration= -1;
        }
        if(Math.abs(getAbsc()-concurrent.getConcurrentList().get(0).x)>=80){
            concurrent.fix=true;
        }
    }
}
