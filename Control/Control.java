package Control;

import Model.Moto;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * implanter KeyListener pour controler le mouvemment de la moto.
 */
public class Control implements KeyListener {
    private Moto moto;



    public Control(Moto moto){

    this.moto=moto;

    }

    /**
     * Déterminez ce qu'est le clavier enfoncé et exécutez la fonction relative
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moto.move(Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                moto.move(Direction.down);
                break;
            case KeyEvent.VK_LEFT:
                moto.move(Direction.left);
                break;
            case KeyEvent.VK_RIGHT:
                moto.move(Direction.right);
        }
    }
    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
