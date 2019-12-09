/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import legend_of_xor.Renderer.Layers.LightingLayer;
import legend_of_xor.Renderer.Layers.EntityLayer;
import legend_of_xor.Renderer.Layers.BackgroundLayer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import legend_of_xor.Renderer.Layers.Layer;
import legend_of_xor.Renderer.Layers.SmallTileLayer;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Renderer {

    private static final Layer[] LAYERS = new Layer[]{new BackgroundLayer(), new SmallTileLayer(), new EntityLayer(), new LightingLayer()};
    // private static final Layer background = new BackgroundLayer();

    private static BufferedImage imageBuffer;
    private static ColorModel cm;

    public static synchronized void DrawScreen() {

        Graphics graphics = imageBuffer.getGraphics();

        //long start = System.currentTimeMillis();

        for (Layer layer : LAYERS) {
            layer.startRender();
        }

        try {
            for (Layer layer : LAYERS) {
                layer.joinLayer();
                graphics.drawImage(layer.getImage(), 0, 0, null);
            }
        } catch (InterruptedException ex) {

        }
        Veiwer.setImage(imageBuffer);

    }

    public static int calcTileOrgX(Camera.Origin origin, BufferedImage image) {
        return calcTileOrgX(origin, image.getWidth(), image.getHeight());
    }

    public static int calcTileOrgY(Camera.Origin origin, BufferedImage image) {
        return calcTileOrgY(origin, image.getWidth(), image.getHeight());
    }

    public static int calcTileOrgX(Camera.Origin origin, int width, int height) {
        switch (origin) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return (-width) + Textures.getTileWidth();
            case BOTTOM_LEFT:
                return 0;
            case BOTTOM_RIGHT:
                return (-width) + Textures.getTileWidth();
            case BOTTOM_CENTER:
                return (int) (-(width / 2)) + Textures.getTileWidth() / 2;
            case TOP_CENTER:
                return (int) (-(width / 2)) + Textures.getTileWidth() / 2;
            case CENTER:
                return (int) (-(width / 2)) + Textures.getTileWidth() / 2;

            default:
        }
        return -1;
    }

    public static int calcTileOrgY(Camera.Origin origin, int width, int height) {
        switch (origin) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return 0;
            case BOTTOM_LEFT:
                return (-height) + Textures.getTileHeight();
            case BOTTOM_RIGHT:
                return (-height) + Textures.getTileHeight();
            case BOTTOM_CENTER:
                return (-height) + Textures.getTileHeight();
            case TOP_CENTER:
                return 0;
            case CENTER:
                return (int) (-(height / 2)) + Textures.getTileHeight() / 2;
            default:
        }
        return -1;
    }

    public static int calcEntityOrgX(Camera.Origin origin, BufferedImage image) {
        if (image == null) {
            return 0;
        }
        return calcEntityOrgX(origin, image.getWidth(), image.getHeight());
    }

    public static int calcEntityOrgY(Camera.Origin origin, BufferedImage image) {
        if (image == null) {
            return 0;
        }
        return calcEntityOrgY(origin, image.getWidth(), image.getHeight());
    }

    public static int calcEntityOrgX(Camera.Origin origin, int width, int hight) {
        switch (origin) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return (-width);
            case BOTTOM_LEFT:
                return 0;
            case BOTTOM_RIGHT:
                return (-width);
            case BOTTOM_CENTER:
                return (int) (-(width / 2));
            case TOP_CENTER:
                return (int) (-(width / 2));
            case CENTER:
                return (int) (-(width / 2));

            default:
        }
        return -1;
    }

    public static int calcEntityOrgY(Camera.Origin origin, int width, int height) {
        switch (origin) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return 0;
            case BOTTOM_LEFT:
                return (-height);
            case BOTTOM_RIGHT:
                return (-height);
            case BOTTOM_CENTER:
                return (-height);
            case TOP_CENTER:
                return 0;
            case CENTER:
                return (int) (-(height / 2));
            default:
        }
        return -1;
    }

    public static ColorModel getCompatibleColorModel() {
        GraphicsConfiguration gfx_config = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();
        return gfx_config.getColorModel();
    }

    public static void init() {

        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        cm = getCompatibleColorModel();
        imageBuffer = gfxConfig.createCompatibleImage(
                Textures.getXRes(), Textures.getYRes(), BufferedImage.TRANSLUCENT);

        for (Layer layer : LAYERS) {
            layer.init();
        }
    }

    public static BufferedImage createCompatibleImage(int width, int height) {
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        BufferedImage temp = gfxConfig.createCompatibleImage(
                width, height, BufferedImage.TRANSLUCENT);

        return temp;
    }
}
