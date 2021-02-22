package Control;

import Model.Moto;
import Model.Piste;
import Vue.Affichage;

import javax.swing.*;

/**
 * creer un thread pour la piste change toujours.
 */
public class Avancer implements Runnable{
    Piste piste;//un objet de piste
    Affichage affichage;// objet de affichage
    Moto moto;

    public Avancer(Piste piste, Affichage affichage,Moto moto){
        this.piste=piste;
        this.affichage=affichage;
        this.moto=moto;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(50);
                //Modifier les coordonnées de piste
                piste.setter();
                moto.move();
                affichage.getArbre();//creer un arbre
                affichage.arbreMove();//laisser les arbres qui descendre;
                affichage.revalidate();
                affichage.repaint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
