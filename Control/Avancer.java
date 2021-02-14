package Control;

import Model.Piste;
import Vue.Affichage;

import javax.swing.*;

/**
 * creer un thread pour la piste change toujours.
 */
public class Avancer implements Runnable{
    Piste piste;//un objet de piste
    Affichage affichage;// objet de affichage

    public Avancer(Piste piste, Affichage affichage){
        this.piste=piste;
        this.affichage=affichage;
    }
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(1000);
                //Modifier les coordonnées de piste
                piste.setter();
                affichage.revalidate();
                affichage.repaint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
