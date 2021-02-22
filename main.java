import Control.Avancer;
import Control.Control;
import Model.*;
import Vue.Affichage;

import javax.swing.*;
import java.util.ArrayList;

import java.util.List;


public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vole, vole, moto!");
        //Définir le mode d'arrêt.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ajouter l'affichage à la frame.
        //JPanel panelPrincipe = new JPanel();

//        Position position = new Position(LARG/2-WIDTH/2,HAUT-HEIGHT);

        Nuages nuages=new Nuages("nuage");
        Piste piste=new Piste();
        Lune lune = new Lune("lune");
        Soleil soleil=new Soleil("soleil");
        Moto moto = new Moto(piste,nuages, lune, soleil);


        Arbre arbre=new Arbre();
        Affichage affichage = new Affichage(moto,piste,lune,soleil,nuages);
        //affichage.action();
        Thread avancer=new Thread(new Avancer(piste,affichage,moto));
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