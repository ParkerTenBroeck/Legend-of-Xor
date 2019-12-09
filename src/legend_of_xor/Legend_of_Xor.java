package legend_of_xor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Legend_of_Xor {

    private static boolean pause = false;

    public static void main(String[] args) {

        Veiwer frame = new Veiwer();
        Textures.setTileResolution(24, 24);
        
        Renderer.init();
        
        Level.loadNewLevel("Main");

        Renderer.init();

        //Sound.loadSound();
        Thread renderer = new Thread() {
            public void run() {
                while (true) {

                    long last = System.nanoTime();
                    Renderer.DrawScreen();
                    long newt = System.nanoTime();
                    if (newt - last > 0.017 * 1_000_000_000) {
                        System.err.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
                    }

                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {

                    }
                }
            }
        };
        renderer.setName("rendeer");
        renderer.start();

        Thread update = new Thread() {
            public void run() {

                while (true) {

                    if (!pause) {

                        long last = System.nanoTime();
                        Level.update();
                        long newt = System.nanoTime();
                        if (newt - last > 0.017 * 1_000_000_000) {
                            //System.err.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
                        }
                        try {
                            Thread.sleep((int) ((1000 / 60) - (newt - last) / 1000000));
                        } catch (Exception e) {

                        }
                    } else {
                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        };
        update.setName("update");
        update.start();

        while (true) {
//            if (pausePressed) {
//                Legend_of_Xor.pause();
//            } else {
//                Legend_of_Xor.unpause();
//            }
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
        }

    }

    public static void pause() {
        pause = true;
    }

    public static void unpause() {
        pause = false;
    }

    public static boolean isPaused() {
        return pause;
    }

}
