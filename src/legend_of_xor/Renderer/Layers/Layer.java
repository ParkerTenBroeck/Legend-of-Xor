/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public abstract class Layer {

    protected Thread thread;

    protected BufferedImage image;
    protected static BufferedImage CLEAR;

    public void init() {
        CLEAR = Renderer.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
        image = Renderer.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
        initThread();

    }

    private void initThread() {
        thread = new Thread(new Runnable() {
            Layer layer;

            @Override
            public void run() {
                //long start = System.currentTimeMillis();
                layer.clearImage();
                layer.drawLayer();
                //System.out.println(thread.getName() + " run time = " + (System.currentTimeMillis()- start));
            }

            public Runnable pass(Layer layers) {
                this.layer = layers;
                return this;
            }
        }.pass(this));
        thread.setName(this.getClass().getName().split("\\.")[3]);
        //System.out.println(this.getClass().getName().split("\\.")[3]);
    }

    public void clearImage() {
        image = Renderer.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
    }

    public void startRender() {
        thread.start();
    }

    public void joinLayer() throws InterruptedException {
        thread.join();
        initThread();
    }

    public abstract void drawLayer();

    public BufferedImage getImage() {
        return image;
    }

}
