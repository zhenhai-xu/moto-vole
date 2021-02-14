package Vue;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Affichage extends JPanel {
    /**La largeur de la fenêtre.*/
    public static final int LARG = 800;
    /**la hauteur de la fenêtre.*/
    public static final int HAUT = 800;
    /**La largeur de l'objet.*/
    public static final int WIDTH = 100;
    /**La hauteur de l'objet.*/
    public static final int HEIGHT = 150;


    private Moto moto;
    private Piste piste;
    private Lune lune;
    private Soleil soleil;
    private Montagne montagne=new Montagne();
    private Soir soir =new Soir();
    private PresqueSoir presqueSoir=new PresqueSoir();
    private Nuages nuages;

    public  Affichage(Moto moto,Piste piste,Lune lune,Soleil soleil,Nuages nuages)  {
        this.moto = moto;
        this.piste=piste;
        this.lune=lune;
        this.soleil=soleil;
        this.nuages=nuages;
        //Définir la taille de la panel
        setPreferredSize(new Dimension(LARG, HAUT));
        repaint();
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, LARG, HAUT);

        //dessiner l'horizon
        g.drawLine(0, HAUT / 4, LARG, HAUT / 4);

        Stroke s = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(s);
        //une liste pour stocker des points aléatoires générés
        ArrayList<Point> L1 = this.piste.getPiste1();
        //dessiner la piste a gauche.
        for (int i = 0; i < L1.size() - 1; i++) {
            Line2D line = new Line2D.Double(L1.get(i).x-100, L1.get(i).y, L1.get(i + 1).x-100, L1.get(i + 1).y);
            g2.draw(line);
            g.drawString("Score" + piste.getter(), 20, 20);
        }
        //dessiner la piste a droit.
        for (int i = 0; i < L1.size() - 1; i++) {
            Line2D line = new Line2D.Double(L1.get(i).x + 100, L1.get(i).y, L1.get(i + 1).x + 100, L1.get(i + 1).y);
            g2.draw(line);
        }
        //Dessinez un arrière-plan éloigné.
        g.drawImage(presqueSoir.getPitrue(), presqueSoir.getAbsc(), presqueSoir.getY(), 800, 200, null);
        //Dessine une moto.
        g.drawImage(moto.getPitrue(), moto.getAbsc(), HAUT - HEIGHT, WIDTH, HEIGHT, null);
        if (lune.getY() >= 0) {
            g.drawImage(soir.getPitrue(),soir.getAbsc(), soir.getY(), 800, 200, null);
            lune.run();
            g.drawImage(lune.getPitrue(), lune.getAbsc(), lune.getY(), 50, 50, null);
        } else if (soleil.getY() >= 0) {
            g.drawImage(montagne.getPitrue(), montagne.getAbsc(), montagne.getY(), 800, 200, null);
            soleil.run();
            g.drawImage(soleil.getPitrue(), soleil.getAbsc(), soleil.getY(), 50, 50, null);
        }else{
            lune.retourne();
            soleil.retourne();
        }
        g.drawImage(nuages.getPictrue(),nuages.getAbsc(),getY(),300,100,null);
    }

}
