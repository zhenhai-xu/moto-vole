package Vue;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;

/**
 * Acceptez les données et dessinez le contenu dans le jeu.
 */
public class Affichage extends JPanel {
    /**La largeur de la fenêtre.*/
    public static final int LARG = 800;
    /**la hauteur de la fenêtre.*/
    public static final int HAUT = 800;
    public static final double T = 0.5;
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
    private Ombre ombre;
    private Concurrent concurrent;

    /**
     * import tous les objets pour affichage de leurs images
     * @param moto
     * @param piste
     * @param lune
     * @param soleil
     * @param nuages
     * @param arbre
     * @param sablier
     * @param ombre
     * @param concurrent
     */
    public  Affichage(Moto moto, Piste piste, Lune lune, Soleil soleil, Nuages nuages, Arbre arbre, Sablier sablier, Ombre ombre,
                      Concurrent concurrent)  {
        this.moto = moto;
        this.piste=piste;
        this.lune=lune;
        this.soleil=soleil;
        this.nuages=nuages;
        this.arbre=arbre;
        this.sablier=sablier;
        this.ombre=ombre;
        this.concurrent=concurrent;
        //Définir la taille de la panel
        setPreferredSize(new Dimension(LARG, HAUT));
        repaint();
    }

    /**
     * Distinguer la piste du bord de la route
     * alors on doit just dessiner tous les lignes sauf les lignes qui est sur la piste
     * @param g2
     */
    public void drawbackground(Graphics2D g2) {
        g2.setColor(new Color(130, 250, 200));
        int[] L3 = new int[1000];
        int n = 50;
        for (int i = 200; i < 760; i++) {
            this.piste.x_actuel = piste.getXSurCourbe(i);
            L3[i] = (int) this.piste.x_actuel;
            if(i%5==0){
                n++;
            }
            g2.drawLine(0, i, L3[i] - n, i);
            g2.drawLine(L3[i] + n, i, 800, i);
        }
        g2.setColor(new Color(255, 255, 255));
        for (int i = 765; i < 800; i++) {//Afin de couvrir la ligne médiane
            g2.drawLine(220, i, 500, i);

        }
    }

    /**
     * dessiner les points de contrôle
     * @param g2
     */
    public void drawCheckPoint(Graphics2D g2){
        g2.setColor(new Color(255, 100, 100));
        ArrayList<Point2D> L2 = this.piste.getTerminal();
        for(int i = 0; i < L2.size() - 1; i++) {
            Line2D line3 = new Line2D.Double(L2.get(i).getX()-140, L2.get(i).getY(),L2.get(i).getX()+140,L2.get(i).getY());
            g2.draw(line3);
        }
    }

    /**
     * dessiner les arbres
     * @param g
     */
    public void drawArbre(Graphics g){
        for(int i=0;i<arbre.getArbresList().size();i++) {
            Arbre arbre= this.arbre.getArbresList().get(i);
            g.drawImage(arbre.getPicture(), arbre.getX(), arbre.getY(), arbre.W, arbre.H, null);
        }
    }

    /**
     * dessiner la ligne médiane
     * @param g2
     */
    public void drawcourbe(Graphics2D g2){

        g2.setColor(new Color(0, 0, 0));
        ArrayList<Point> L1 = this.piste.getPiste1();
        QuadCurve2D courbe = new QuadCurve2D.Double();
        //Étant donné que le premier point de la courbe de Bézier n’est pas utilisé.
        // (il manque un segment de ligne à l’écran, ajoutons d’abord un point.)
        double debut0_x = L1.get(0).getX() + 20;  // Point de départ.
        double debut0_y = L1.get(0).getY() + 20;
        double fin0_x = (L1.get(0).getX() + L1.get(1).getX()) /2; // point de fin.
        double fin0_y = (L1.get(0).getY() + L1.get(1).getY()) /2;
        courbe.setCurve(new Point2D.Double(debut0_x, debut0_y),
                new Point2D.Double(L1.get(0).getX(), L1.get(0).getY()),
                new Point2D.Double(fin0_x,fin0_y));
        g2.draw(courbe);
        //Dessinez une courbe de Bézier.
        for (int i = 0; i < L1.size() - 2; i++) {
            double debut_x = (L1.get(i).getX() + L1.get(i + 1).getX()) * T;
            double debut_y = (L1.get(i).getY() + L1.get(i + 1).getY()) * T;
            double fin_x = (L1.get(i + 1).getX() + L1.get(i + 2).getX()) * T;
            double fin_y = (L1.get(i + 1).getY() + L1.get(i + 2).getY()) * T;
            Point2D.Double debut = new Point2D.Double(debut_x, debut_y);
            Point2D.Double ctrl = new Point2D.Double(L1.get(i+1).getX(), L1.get(i+1).getY());
            Point2D.Double fin = new Point2D.Double(fin_x,fin_y);
            courbe.setCurve(debut,ctrl,fin);
            g2.draw(courbe);

        }
    }

    /**
     * dessiner l'information ce que l'on a besoin de afficher
     * @param g
     */
    public void drawInformation(Graphics g ){
        Font f5 = new Font(null, Font.BOLD+Font.ITALIC,20);
        g.setColor(new Color(0,0,0));
        g.setFont(f5);
        g.setColor(Color.black);
        g.drawString("vitesse : ",620,780);
        g.drawString(Math.round( piste.getter()*10 ) + " KM/H", 700, 780);
        g.drawString("temps restant : ",5,780);
        g.drawString(sablier.getMin() + ":"+(sablier.getSec()<10?0:"")+sablier.getSec(), 155, 780);
        g.drawString("score : ",500,780);
        g.drawString(""+ piste.getPosition(), 570, 780);
    }

    @Override
    /**
     * dessiner les autres éléments
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.clearRect(0, 0, LARG, HAUT);


        //dessiner l'horizon
        g.drawLine(0, HAUT / 4, LARG, HAUT / 4);

        Stroke s = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(s);
        //une liste pour stocker des points aléatoires générés

        this.piste.x_actuel=piste.getXSurCourbe(740);
        drawcourbe(g2);
        drawCheckPoint(g2);
        drawbackground(g2);



//        for (int i = 0; i < L1.size() - 1; i++) {
//            Line2D line = new Line2D.Double(L1.get(i).x, L1.get(i).y, L1.get(i + 1).x, L1.get(i + 1).y);
//            g2.draw(line);

//        }

        //Dessin un concurrent.
        if(concurrent.getConcurrentList().size()>=1) {
            for (int i = 0; i < concurrent.getConcurrentList().size(); i++) {
                Concurrent concurrent = this.concurrent.getConcurrentList().get(i);

                g.drawImage(concurrent.getPicture(), concurrent.getX(), concurrent.getY(), concurrent.W, concurrent.H, null);
            }
        }
        //Dessinez un arrière-plan éloigné.
        g.drawImage(presqueSoir.getPicture(), presqueSoir.getAbsc(), presqueSoir.getY(), presqueSoir.W, presqueSoir.H, null);
        //Dessine une moto.
        g.drawImage(moto.getPicture(), moto.getAbsc(), moto.getY(), WIDTH, HEIGHT, null);
        g.drawImage(ombre.getPicture(), ombre.getAbsc(), ombre.getY(), ombre.W, ombre.H, null);


        if (lune.getY() >= 0) {
            g.drawImage(soir.getPicture(),soir.getAbsc(), soir.getY(), soir.W, soir.H, null);
            lune.run();
            g.drawImage(lune.getPicture(), lune.getAbsc(), lune.getY(), lune.W, lune.H, null);
            nuages.setXL();
        } else if (soleil.getY() >= 0) {
            g.drawImage(montagne.getPicture(), montagne.getAbsc(), montagne.getY(), montagne.W, montagne.H, null);
            soleil.run();
            g.drawImage(soleil.getPicture(), soleil.getAbsc(), soleil.getY(), soleil.W, soleil.H, null);
            nuages.setXR();
        }else{
            lune.retourne();
            soleil.retourne();
        }
        g.drawImage(nuages.getPicture(),nuages.getAbsc(),getY(),nuages.W,nuages.H,null);


        //dessiner les arbres
        drawArbre(g);

        drawInformation(g);

    }

}
