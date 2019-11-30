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
        Veiwer frame = new Veiwer(1280 / 2, 720 / 2);
        Level.loadNewLevel("Main");

        Timer renderer = new Timer(1000 / 30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long last = System.nanoTime();
                Camera.DrawScreen();
                long newt = System.nanoTime();
                if (newt - last > 0.017 * 1_000_000_000) {
                    System.err.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
                }
            }
        });
        renderer.start();

        Timer updates = new Timer(1000 / 20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Camera.update();
            }
        });
        updates.start();

    }

}
