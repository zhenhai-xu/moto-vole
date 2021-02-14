import Control.Control;
import Control.Avancer;
import Model.*;
import Vue.Affichage;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vole, vole, moto!");
        //Définir le mode d'arrêt.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ajouter l'affichage à la frame.
        //JPanel panelPrincipe = new JPanel();

//        Position position = new Position(LARG/2-WIDTH/2,HAUT-HEIGHT);

        Nuages nuages=new Nuages();
        Piste piste=new Piste();
        Moto moto = new Moto(piste,nuages);
        Lune lune = new Lune();
        Soleil soleil=new Soleil();
        Affichage affichage = new Affichage(moto,piste,lune,soleil,nuages);
        Thread avancer=new Thread(new Avancer(piste,affichage));
        avancer.start();
        moto.setAffichage(affichage);
        frame.addKeyListener(new Control(moto));
        frame.add(affichage);
        //Ajouter un moniteur de souris.
        //Laisser le cadre ajuster automatiquement sa taille.
        frame.pack();
        //Réglez l'écran pour qu'il soit visible.
        frame.setVisible(true);
    }
}