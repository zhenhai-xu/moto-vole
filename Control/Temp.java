package Control;

import Model.Moto;
import Model.Sablier;
import Vue.Affichage;

import javax.swing.*;
import java.awt.*;

/**
 * creer un thread pour le temps dimminue de temps en temps.
 */
public class Temp implements Runnable{
    private Sablier sablier;
    private Affichage affichage;
    private Moto moto;

    public Temp(Sablier sablier, Affichage affichage, Moto moto){
        this.sablier=sablier;
        this.affichage=affichage;
        this.moto=moto;
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                sablier.Soustraire_min();
                sablier.Soustraire_sec();
                sablier.getBonus();

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
