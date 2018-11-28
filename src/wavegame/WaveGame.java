package wavegame;

import java.awt.Canvas;
import java.util.Random;

/**
 *
 * @author nuf-nuf
 */
// Extends Canvas = 
// implements Runnable = Permet d'être lancé (lancement de Thread)
public class WaveGame extends Canvas implements Runnable {

    // ------------- Propriétés -------------
    //public permet d'y acceder depuis une autre classe
    //static permet de la rendre unique
    //final donne des valeurs fixe aux variables fixes
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    // Choix du nbr FPS
    private final double UPDATE_CAP = 1.0 / 60.0;

    private Thread thread;
    private boolean running = false;
    private Random r;

    // ------------- Fonctions -------------
    //ceci est un constructeur (On sais que c'est un constructeur, grâce à sont noms, nom classe == nom fonction)
    public WaveGame() {
        new Window(WIDTH, HEIGHT, "Adrien", this);
    }

    /**
     * Implémenté par l'ajout de "implements Runnable" après le noms de la
     * classe Permet de lancer un "Thread" Start : Permet de lancer le Thread
     * Synchronized : La synchronisation est un élément essentiel dès lors que
     * vous utilisez plusieurs threads (c'est-à-dire dans quasiment toutes les
     * applications). En effet, sans synchronisation, il est impossible de
     * développer une application robuste qui fonctionne quel que soit
     * l'entrelacement de l'exécution des threads.
     */
    public synchronized void start() {
        // Créer un Thread
        Thread thread = new Thread(this);
        // Lance le Thread précédemment crée
        thread.start();
        // Vu que le thread à été lancé (et qu'on arrive ici donc pas d'erreur)
        // On met le Boolean "running" déclaré plus tôt, à true (vrai)
        running = true;
    }

    /**
     * Fonction à lancer pour fermer proprement le Thread
     */
    public synchronized void stop() {
        //Lance le code, si erreur aller dans "catch"
        try {
            //Ferme le thread
            thread.join();
            // Vu que le thread à été fermé (et qu'on arrive ici donc pas d'erreur)
            // On met le Boolean "running" déclaré plus tôt, à false (faux)
            running = false;
        } catch (Exception e) {
            // Affiche dans les logs, l'erreur si il y en à une
            e.printStackTrace();
        }
    }

    /**
     * Fonction de run sur 60 FPS trouvé sur internet par Ikikay Copyrigt :
     * Majoolwip
     */
    public void run() {
        //Permet de passer la fenêtre en premier plan (supposition)
        this.requestFocus();

        //Affiche un saut de ligne dans les Logs
        System.out.println("");

        // ------------- FONCTION MAGIQUUUUEEUUU -------------
        boolean render = false;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while (running) {
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            // /!\ TANT QUE LE THREAD EST LANCER, FAIRE CECI /!\
            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;

                // /!\ LANCE LA FONCTION this.TICK A CHAQUE FRAME /!\
                tick();

                if (frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }
            // /!\ FIN TANT QUE /!\

            if (render) {
                frames++;

                // /!\ LANCE LA FONCTION this.RENDER A CHAQUE FRAME /!\
                render();

            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        // ------------- FIN DE LA FONCTION MAGIQUUUUEEUUU -------------

        stop();

//        // Code Julian
//        long lastTime = System.nanoTime();
//        double amountOfTicks = 60.0;
//        double ns = 1000000000.0 / amountOfTicks;
//        double delta = 0;
//        long timer = System.currentTimeMillis();
//        int frames = 0;
//        while (running) {
//            long now = System.nanoTime();
//            delta += (now - lastTime) / ns;
//            lastTime = now;
//            while (delta >= 1) {
//                tick();
//                delta--;
//            }
//            if (running) {
//                frames++;
//                render();
//            }
//            
//
//            if (System.currentTimeMillis() - timer > 1000) {
//                timer += 1000;
//                System.out.println("FPS: " + frames);
//                frames = 0;
//            }
//        }
//        stop();
    }

    // LANCER A CHAQUE FRAME
    private void tick() {

    }

    // LANCER A CHAQUE FRAME
    private void render() {

    }

    public static void main(String[] args) {
        new WaveGame();
    }

}
