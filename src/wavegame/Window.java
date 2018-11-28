package wavegame;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 *
 */
public class Window extends Canvas {
/**
 * demande la taille, titre et le contexte(information actuel quand le jeu est lancé)
 */
    public Window(int width, int height, String title, WaveGame game) {
        JFrame frame = new JFrame(title);
        //taille au moment du lancement
        frame.setPreferredSize(new Dimension(width, height));
        //taille maximum au redimensionnement si possible
        frame.setMaximumSize(new Dimension(width, height));
        //taille minimum au redimensionnement si possible
        frame.setMinimumSize(new Dimension(width, height));
        //fermer le programme au moment de cliquer sur la croix
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //bloquer/debloquer la redimension 
        frame.setResizable(false);
        //centrer la fenetre
        frame.setLocationRelativeTo(null);
        //fait le lien entre le jeu(context) et la fenetre
        frame.add(game);
        //rendre la fenetre visible/masquer
        frame.setVisible(true);
        //execution du jeu dans la fenetre
        game.start();

    }
}