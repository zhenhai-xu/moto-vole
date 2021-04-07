package Control;

import Model.Moto;
import Model.Sablier;
import Vue.Affichage;

/**
 * creer un thread pour former une montre à compte à rebours
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

            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
