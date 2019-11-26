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
import javax.imageio.ImageIO;
import javax.swing.Timer;
import legend_of_xor.Game.Tiles.TEMPLATE;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Veiwer.Frame;

/**
 *
 * @author parke
 */
public class Legend_of_Xor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Frame frame = new Frame(1280 / 2, 720 / 2);

        Level.loadNewLevel("Main");

        
        Timer timer = new Timer(17, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long last = System.nanoTime();
                Camera.DrawScreen();
                long newt = System.nanoTime();
                System.out.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
            }
        });
        timer.start();
       
    }

}
