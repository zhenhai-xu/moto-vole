package Control;

import Model.Moto;
import Model.Piste;
import Model.Sablier;
import Vue.Affichage;
import Vue.EndGame;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 *Détecter l'état de fin du jeu.
 * Lorsque le jeu est terminé, entrez dans l'étape suivante.
 */
public class Game implements Runnable{
        private Sablier sablier;
        private Affichage affichage;
        private Moto moto;
        private Frame frame;
        private Piste piste;
        private boolean flag=true;
        private EndGame endGame;

        public Game(Sablier sablier, Affichage affichage, Moto moto, Piste piste, Frame frame, EndGame endGame){
            this.sablier=sablier;
            this.affichage=affichage;
            this.moto=moto;
            this.frame=frame;
            this.piste=piste;
            this.endGame=endGame;
        }
        @Override
        public void run() {


            while (flag) {
                try {
                    Thread.sleep(100);
                    piste.setterPosition();
                    //Déterminez la fin du jeu, si elle est terminée, supprimez le contenu actuel et chargez l'interface de fin
                    if(sablier.getMin()==0&&sablier.getSec()==0 || piste.gameOver== true){
                        frame.remove(affichage);
                        endGame.highScore();
                        JLabel label =   endGame.newLable();
                        frame.add(label);
                        frame.repaint();
                        flag=false;

                    }
                }
                catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
