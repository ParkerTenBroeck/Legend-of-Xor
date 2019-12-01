/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Veiwer;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.Timer;
import legend_of_xor.KeyboardLisenter;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class Veiwer extends javax.swing.JFrame {

    private static BufferedImage image;

    public Veiwer(int xSize, int ySize) {
        initComponents();
        KeyboardLisenter.init(jPanel1, this);
        Textures.setResolutuin(xSize, ySize);
        this.setSize(new Dimension(xSize, ySize));
        jPanel1.setSize(new Dimension(xSize, ySize));
        Textures.setResolutuin(xSize, ySize);

        System.out.println(jPanel1.getWidth());
        this.show();

        jPanel1.setDoubleBuffered(true);

        Timer imageDrawer = new Timer(1000 / 60, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                // public void run() {
                if (image != null && jPanel1 != null) {
                    Graphics2D g2d = (Graphics2D) jPanel1.getGraphics().create();
                    Image i = image.getScaledInstance(jPanel1.getSize().width, jPanel1.getSize().height, Image.SCALE_FAST);
                    g2d.drawImage(i, 0, 0, null);
                    //Graphics g2d = i.getGraphics();
                    //jPanel1.paint(g2d);
                    // g2d.dispose();
                    //jPanel1.repaint();
                    //jPanel1.repaint();
                }
                //  }
                // });
            }
        });
        imageDrawer.start();
    }

    public static void setImage(BufferedImage image) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        Veiwer.image = image;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    static public int getXSize() {
        return jPanel1.getSize().width;

    }

    static public int getYSize() {
        return jPanel1.getSize().height;
    }
}