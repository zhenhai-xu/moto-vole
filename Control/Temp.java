package Control;

import Model.Sablier;
import Vue.Affichage;

import javax.swing.*;
/**
 * creer un thread pour le temps dimminue de temps en temps.
 */
public class Temp implements Runnable{
    private Sablier sablier;
    private Affichage affichage;
    public Temp(Sablier sablier,Affichage affichage){
        this.sablier=sablier;
        this.affichage=affichage;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                sablier.Soustraire_min();
                sablier.Soustraire_sec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
