import Control.Avancer;
import Control.Control;
import Control.Game;

import Control.Temp;
import Model.*;
import Vue.Affichage;

import javax.swing.*;
import java.awt.*;


public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vole, vole, moto!");
        //Définir le mode d'arrêt.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ajouter l'affichage à la frame.
        //JPanel panelPrincipe = new JPanel();

//        Position position = new Position(LARG/2-WIDTH/2,HAUT-HEIGHT);

        Nuages nuages=new Nuages("nuage");

        Lune lune = new Lune("lune");
        Soleil soleil=new Soleil("soleil");
        Piste piste=new Piste();
        Arbre arbre=new Arbre(piste);

        Moto moto = new Moto(piste,nuages, lune, soleil,arbre);
        Sablier sablier=new Sablier(piste,moto);
        Affichage affichage = new Affichage(moto,piste,lune,soleil,nuages,arbre,sablier);
        moto.setAffichage(affichage);

        Thread game=new Thread(new Game(sablier,affichage,moto, piste, frame));
        Thread avancer=new Thread(new Avancer(piste,affichage,moto,arbre));
        Thread temp=new Thread(new Temp(sablier,affichage,moto));
        temp.start();
        avancer.start();
        game.start();


        frame.addKeyListener(new Control(moto));
        frame.add(affichage);
        //Ajouter un moniteur de souris.
        //Laisser le cadre ajuster automatiquement sa taille.
        frame.pack();
        //Réglez l'écran pour qu'il soit visible.
        frame.setVisible(true);


    }
}