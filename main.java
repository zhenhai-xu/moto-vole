import Control.Avancer;
import Control.Control;
import Control.Game;
import Control.MusicStuff;


import Control.Temp;
import Model.*;
import Vue.Affichage;
import Vue.EndGame;
import Vue.StartGame;

import javax.swing.*;

/**
 * Entrée de programme, à partir de là, vous pouvez démarrer le jeu.
 */


public class main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Vole, vole, moto!");
        //Définir le mode d'arrêt.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ajouter l'affichage à la frame.
        //JPanel panelPrincipe = new JPanel();

        StartGame gameStart=new StartGame();
        frame.add(gameStart);

        //Réglez l'écran pour qu'il soit visible.
        frame.pack();
        frame.setVisible(true);

        //jouer de la musique
        MusicStuff musicStuff=new MusicStuff();
        musicStuff.playMusic();

        //Utilisez le flag pour déterminer l'état de début du jeu
        boolean flag = true;
        while (flag){
            flag= !gameStart.getGameStart();
            System.out.println(" cliquez start ");
        }

        //Supprimer le contenu.
        frame.remove(gameStart);
        //refocus la souris.
        frame.requestFocus();

        //Initialiser l'objet
        Nuages nuages=new Nuages("nuage");
        Lune lune = new Lune("lune");
        Soleil soleil=new Soleil("soleil");
        Piste piste=new Piste();
        Arbre arbre=new Arbre(piste);
        Concurrent concurrent=new Concurrent(piste);
        Moto moto = new Moto(piste,nuages, lune, soleil,arbre,concurrent);
        Sablier sablier=new Sablier(piste,moto);
        Ombre ombre=new Ombre("ombre",moto);
        Affichage affichage = new Affichage(moto,piste,lune,soleil,nuages,arbre,sablier,ombre ,concurrent);
        moto.setAffichage(affichage);
        moto.setOmbre(ombre);

        //Initialisez le thread et terminez l'interface.
        EndGame endGame = new EndGame(piste,frame);
        Thread game=new Thread(new Game(sablier,affichage,moto, piste, frame,endGame));
        Thread avancer=new Thread(new Avancer(piste,affichage,moto,arbre,sablier,concurrent));
        Thread temp=new Thread(new Temp(sablier,affichage,moto));

        //Démarrer le Thread
        temp.start();
        avancer.start();
        game.start();
        frame.addKeyListener(new Control(moto));
        frame.add(affichage);
        //Ajouter un moniteur de souris.
        //Laisser le cadre ajuster automatiquement sa taille.
        frame.pack();


    }
}