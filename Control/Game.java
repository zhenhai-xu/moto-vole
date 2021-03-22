package Control;

import Model.Moto;
import Model.Piste;
import Model.Sablier;
import Vue.Affichage;

import javax.swing.*;
import java.awt.*;

    public class Game implements Runnable{
        private Sablier sablier;
        private Affichage affichage;
        private Moto moto;
        private Frame frame;
        private Piste piste;
        private boolean flag=true;

        public Game(Sablier sablier, Affichage affichage, Moto moto, Piste piste, Frame frame){
            this.sablier=sablier;
            this.affichage=affichage;
            this.moto=moto;
            this.frame=frame;
            this.piste=piste;
        }
        @Override
        public void run() {
            while (flag) {
                try {
                    Thread.sleep(100);
                    piste.setterPosition();
                    if(sablier.getMin()==0&&sablier.getSec()==0 || moto.gameOver== true){
                        frame.remove(affichage);
                        String x = "<html><body>" + "LOSE" + "<br/>"+"You got : "+piste.getPosition()+ "</body></html> ";
                        JLabel label = new JLabel(x);
                        label.setFont(new Font("Verdana", 1, 20));
                        label.setHorizontalAlignment(SwingConstants.CENTER);
                        label.setSize(frame.getSize());
                        frame.add(label);
                        frame.repaint();
                        flag=false;

                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
