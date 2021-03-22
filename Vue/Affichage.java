package Vue;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class Affichage extends JPanel {
    /**La largeur de la fenêtre.*/
    public static final int LARG = 800;
    /**la hauteur de la fenêtre.*/
    public static final int HAUT = 800;
    /**La largeur de l'objet.*/
    public  static int WIDTH = 100;
    /**La hauteur de l'objet.*/
    public  static int HEIGHT = 80;


    private Moto moto;
    private Piste piste;
    private Lune lune;
    private Soleil soleil;
    private Montagne montagne=new Montagne("montagne");
    private Soir soir =new Soir("soir");
    private PresqueSoir presqueSoir=new PresqueSoir("PresqueSoir");
    private Nuages nuages;
    private Arbre arbre;
    private Sablier sablier;
    List<Arbre> arbresList;//creer la liste pour deposer les arbres;

    public  Affichage(Moto moto,Piste piste,Lune lune,Soleil soleil,Nuages nuages,Arbre arbre,Sablier sablier)  {
        this.moto = moto;
        this.piste=piste;
        this.lune=lune;
        this.soleil=soleil;
        this.nuages=nuages;
        this.arbre=arbre;
        this.sablier=sablier;
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
        ArrayList<Point> L2 = this.piste.getTerminal();
        //dessiner la piste a gauche.

        for (int i = 0; i < L1.size() - 1; i++) {
            Line2D line = new Line2D.Double(L1.get(i).x-100, L1.get(i).y, L1.get(i + 1).x-100, L1.get(i + 1).y);
            g2.draw(line);
            Line2D line2 = new Line2D.Double(L1.get(i).x + 100, L1.get(i).y, L1.get(i + 1).x + 100, L1.get(i + 1).y);
            g2.draw(line2);
        }

        for(int i = 0; i < L2.size() - 1; i++) {
            Line2D line3 = new Line2D.Double(L2.get(i).x-100, L2.get(i).y, L2.get(i).x+100,L2.get(i).y);
            g2.draw(line3);
        }



        //Dessinez un arrière-plan éloigné.
        g.drawImage(presqueSoir.getPicture(), presqueSoir.getAbsc(), presqueSoir.getY(), presqueSoir.W, presqueSoir.H, null);
        //Dessine une moto.
        g.drawImage(moto.getPitrue(), moto.getAbsc(), moto.getY(), WIDTH, HEIGHT, null);
        if (lune.getY() >= 0) {
            g.drawImage(soir.getPicture(),soir.getAbsc(), soir.getY(), soir.W, soir.H, null);
            lune.run();
            g.drawImage(lune.getPicture(), lune.getAbsc(), lune.getY(), lune.W, lune.H, null);
        } else if (soleil.getY() >= 0) {
            g.drawImage(montagne.getPicture(), montagne.getAbsc(), montagne.getY(), montagne.W, montagne.H, null);
            soleil.run();
            g.drawImage(soleil.getPicture(), soleil.getAbsc(), soleil.getY(), soleil.W, soleil.H, null);
        }else{
            lune.retourne();
            soleil.retourne();
        }
        g.drawImage(nuages.getPicture(),nuages.getAbsc(),getY(),nuages.W,nuages.H,null);
        //dessiner les arbres

        for(int i=0;i<arbre.getArbresList().size();i++) {
            Arbre arbre= this.arbre.getArbresList().get(i);
            g.drawImage(arbre.getPicture(), arbre.getX(), arbre.getY(), arbre.W, arbre.H, null);
        }
        Font f5 = new Font(null, Font.BOLD+Font.ITALIC,20);
        g.setFont(f5);
        g.drawString("vitesse : ",710,750);
        g.drawString(Math.round( piste.getter()* 8 ) + " KM/H", 700, 780);
        g.drawString("temps restant : ",20,750);
        g.drawString(+ sablier.getMin() + ":"+(sablier.getSec()<10?0:"")+sablier.getSec(), 20, 780);
    }

}
