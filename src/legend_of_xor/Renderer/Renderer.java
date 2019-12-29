/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import legend_of_xor.Renderer.Layers.AestheticLayer;
import legend_of_xor.Renderer.Layers.LightingLayer;
import legend_of_xor.Renderer.Layers.EntityLayer;
import legend_of_xor.Renderer.Layers.BackgroundLayer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferInt;
import java.awt.image.VolatileImage;
import legend_of_xor.Renderer.Layers.Layer;
import legend_of_xor.Renderer.Layers.SmallTileLayer;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Renderer {

    private static final Layer[] LAYERS = new Layer[]{new BackgroundLayer(), new AestheticLayer(), new SmallTileLayer(), new EntityLayer()};
    // private static final Layer background = new BackgroundLayer();

    private static BufferedImage imageBuffer;
    private static ColorModel cm;

    public static synchronized void DrawScreen() {

        Graphics graphics = imageBuffer.getGraphics();

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
        graphics.dispose();
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

        cm = getCompatibleColorModel();
        imageBuffer = createCompatibleImage(Textures.getXRes(), Textures.getYRes());
        for (Layer layer : LAYERS) {
            layer.init();
        }
    }

    public static BufferedImage createCompatibleImage(int width, int height) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
        BufferedImage temp = gc.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        temp.setAccelerationPriority(1);
        return temp;
    }

//    public static VolatileImage createCompatibleVolatileImage(int width, int height) {
//
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
//        VolatileImage temp = gc.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
//        temp.setAccelerationPriority(1);
//        return temp;
//    }

    public static void copySrcIntoDstAt(final BufferedImage src,
            final BufferedImage dst, final int dx, final int dy) {
        int[] srcbuf = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
        int[] dstbuf = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();
        int width = src.getWidth();
        int height = src.getHeight();
        int dstoffs = dx + dy * dst.getWidth();
        int srcoffs = 0;
        for (int y = 0; y < height; y++, dstoffs += dst.getWidth(), srcoffs += width) {
            try {
                System.arraycopy(srcbuf, srcoffs, dstbuf, dstoffs, width);
            } catch (Exception e) {

            }
        }
    }
}
