package Control;

import Model.Arbre;
import Model.Moto;
import Model.Piste;
import Vue.Affichage;

import javax.swing.*;

/**
 * creer un thread pour la piste change toujours.
 */
public class Avancer implements Runnable{
    private Piste piste;//un objet de piste
    private Affichage affichage;// objet de affichage
    private Moto moto;
    private Arbre arbre;

    public Avancer(Piste piste, Affichage affichage, Moto moto, Arbre arbre){
        this.piste=piste;
        this.affichage=affichage;
        this.moto=moto;
        this.arbre=arbre;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(100);
                //Modifier les coordonnées de piste
                moto.setMotoV();
                moto.move();
                arbre.getArbre();
                arbre.arbreMove();//laisser les arbres qui descendre;
                moto.ralentir();//pour verifier si il peut changevitesse;
                if(moto.ralentir){
                    piste.changeVitesse();
                    moto.ralentir=false;
                }
                affichage.revalidate();
                affichage.repaint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
