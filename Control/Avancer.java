package Control;

import Model.*;
import Vue.Affichage;

import java.io.IOException;

/**
 * creer un thread pour la piste peut etre changé toujours.
 */
public class Avancer implements Runnable{
    private final Piste piste;//un objet de piste
    private Affichage affichage;// objet de affichage
    private Moto moto;
    private Arbre arbre;
    private Sablier sablier;
    private Concurrent concurrent;

    public Avancer(Piste piste, Affichage affichage, Moto moto, Arbre arbre, Sablier sablier, Concurrent concurrent){
        this.piste=piste;
        this.affichage=affichage;
        this.moto=moto;
        this.arbre=arbre;
        this.sablier=sablier;
        this.concurrent=concurrent;
    }
    @Override
    public void run() {
        while(true){
            try {

                Thread.sleep(100);
                double t = 0.1;
                //Modifier les coordonnées de piste
                sablier.getBonus();
                moto.move();
                piste.setAcceleration(this.moto);
                arbre.getArbre();
                arbre.arbreMove();//laisser les arbres qui descendre;
                concurrent.getConccurent();
                if(concurrent.getConcurrentList().size()>=1) {
                    concurrent.concurrentMove();
                    concurrent.empecheMotodepasse();
                    moto.ConditionDepasse();
                }

                //System.out.println(piste.x_actuel);
                piste.setVitesse(t);
                moto.ralentir();//pour verifier si il peut changevitesse;
                if(moto.ralentir){
                    piste.changeVitesse();
                    moto.ralentir=false;
                }
                affichage.revalidate();
                affichage.repaint();
            }catch (InterruptedException | IOException e){
                e.printStackTrace();
            }
        }
    }
}
