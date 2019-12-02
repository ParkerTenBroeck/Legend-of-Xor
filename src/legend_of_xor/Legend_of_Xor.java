/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import legend_of_xor.Game.Tiles.TEMPLATE;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Legend_of_Xor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Veiwer frame = new Veiwer(1280 / 2, 704 / 2);
        Level.loadNewLevel("Main");

        Thread renderer = new Thread() {
            public void run() {
                while (true) {
                    long last = System.nanoTime();
                    Camera.DrawScreen();
                    long newt = System.nanoTime();
                    if (newt - last > 0.017 * 1_000_000_000) {
                        System.err.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
                    }
                    try {
                        Thread.sleep(16);
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
                    Level.update();
                    Camera.update();
                    try {
                        Thread.sleep(16);
                    } catch (Exception e) {

                    }
                }
            }
        };
        update.setName("update");
        update.start();

    }

}
