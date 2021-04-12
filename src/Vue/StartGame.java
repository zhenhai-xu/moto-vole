package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Vue.Affichage.HAUT;
import static Vue.Affichage.LARG;

/**
 * Démarrer l'interface, appuyez sur Démarrer le jeu pour accéder à l'interface suivante
 */

public class StartGame extends JPanel {
    private BufferedImage pictrue;
    private JButton jButton;
    private Boolean gameStart=false;
    public StartGame() {
        setLayout(null);
        //Ajouter un bouton, ajouter un ActionListener
        jButton= new JButton("Start Game");
        jButton.setBounds(350,700,100,50);
        this.add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStart=true;
                //Supprimer ActionListener
                jButton.removeActionListener(this);
            }
        });
        setPreferredSize(new Dimension(LARG, HAUT));
        repaint();
    }

    public BufferedImage getPicture() {
        try {
            this.pictrue = ImageIO.read(new File("src/png/gamestart.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this.pictrue;
    }

    public Boolean getGameStart() {
        return gameStart;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(getPicture(), 0, 0, this.getWidth(), this.getHeight(), this);
        Font f5 = new Font(null, Font.BOLD+Font.ITALIC,40);
        g.setFont(f5);
        g.drawString("Vole Moto",300,200);
    }
}