/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import static legend_of_xor.Controls.getMouseTileX;
import static legend_of_xor.Controls.getMouseTileY;
import static legend_of_xor.Controls.isLeftMousePressed;
import static legend_of_xor.Controls.isRightMousePressed;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;
import legend_of_xor.Game.Entity;

import legend_of_xor.Game.Tiles.chest;

/**
 *
 * @author parke
 */
public class Camera {

    private static double xPos;
    private static double yPos;
    private static float zoom = 1;

    private static boolean followEntity = false;
    private static Entity entity;

    private static int cameraTilesX;
    private static int cameraTilesY;

    private static BufferedImage imageBuffer;
    private static ColorModel cm;

    private static Image[] layers;

    public static int getCenterCameraXPos() {
        return (int) (xPos + ((double) cameraTilesX / 2.0));
    }

    public static int getCenterCameraYPos() {
        return (int) (yPos + ((double) cameraTilesY / 2.0));
    }

    public static int getCameraTilesX() {
        return cameraTilesX;
    }

    public static int getCameraTilesY() {
        return cameraTilesY;
    }

    public enum Origin {
        UPPER_LEFT, UPPER_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT, BOTTOM_CENTER, TOP_CENTER, CENTER
    }

    public static void init(int startingX, int startingY, int cameraTilesX, int cameraTilesY) {
        Camera.xPos = startingX;
        Camera.yPos = startingY;

        Camera.cameraTilesX = cameraTilesX;
        Camera.cameraTilesY = cameraTilesY;

        Textures.setResolutuin();
    }

    public static double getXPos() {
        return xPos;
    }

    public static double getYPos() {
        return yPos;
    }

    public static void followEntity(Entity entity) {
        followEntity = true;
        Camera.entity = entity;
    }

    public static void update() {
        if (followEntity) {
            xPos = (-entity.getXPos()) + (Camera.cameraTilesX / 2);
            yPos = (-entity.getYPos()) + (Camera.cameraTilesY / 2);
        }

        if (isLeftMousePressed()) {
            Level.setSmallTiles(new chest(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTiles(null, getMouseTileX(), getMouseTileY());
        }

    }

    public static synchronized void DrawScreen() {

        BufferedImage[] layers = new BufferedImage[5];

        Thread smallTiles = new Thread(new Runnable() {

            BufferedImage[] layers;

            @Override
            public void run() {
                layers[2] = drawSmallTiles();
            }

            public Runnable pass(BufferedImage[] layers) {
                this.layers = layers;
                return this;
            }
        }.pass(layers));
        smallTiles.start();

        Thread entities = new Thread(new Runnable() {

            BufferedImage[] layers;

            @Override
            public void run() {
                layers[3] = drawEntities();
            }

            public Runnable pass(BufferedImage[] layers) {
                this.layers = layers;
                return this;
            }
        }.pass(layers));
        entities.start();

        Graphics2D g2d = (Graphics2D) imageBuffer.getGraphics().create();
        g2d.drawImage(Level.getBackgroundImage(), 0, 0, null);

        try {
            entities.join();
            smallTiles.join();
        } catch (InterruptedException ex) {

        }
        for (int i = 0; i < layers.length; i++) {
            g2d.drawImage(layers[i], 0, 0, null);
        }
        Veiwer.setImage(imageBuffer);

    }

    static public BufferedImage drawSmallTiles() {

        BufferedImage image = Camera.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
        Graphics2D g2d = (Graphics2D) image.getGraphics().create();

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTileWidth());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTileHeight());

        for (int y = cameraTilesX + 1; y >= -1; y--) {

            for (int x = -1; x < Camera.cameraTilesX; x++) {

                if (Level.getSmallTileImage(x + xTileOffset, y + yTileOffset) != null) {

                    Tile temp = Level.getSmallTile(x + xTileOffset, y + yTileOffset);

                    BufferedImage i = temp.getTileImage(x + xTileOffset, y + yTileOffset);

                    int orgX = calcTileOrgX(temp.getOrigin(), i);
                    int orgY = calcTileOrgY(temp.getOrigin(), i);

                    g2d.drawImage(i,
                            x * Textures.getTileWidth() + xPixelOffset + orgX,
                            y * Textures.getTileHeight() + yPixelOffset + orgY,
                            null);
                }
            }
        }
        return image;
    }

    static public BufferedImage drawEntities() {

        BufferedImage image = Camera.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
        Graphics2D g2d = (Graphics2D) image.getGraphics().create();

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTileWidth());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTileHeight());
        //System.out.println(Level.getEntities().size());

        for (Entity entity : Level.getEntities()) {
            try {
                BufferedImage temp = entity.getTileImage();
                //System.out.println(entity);
                g2d.drawImage(temp,
                        (int) ((entity.getXPos() - xTileOffset) * Textures.getTileWidth() + xPixelOffset + calcEntityOrgX(entity.getOrigin(), temp)),
                        (int) ((entity.getYPos() - yTileOffset) * Textures.getTileHeight() + yPixelOffset + calcEntityOrgY(entity.getOrigin(), temp)), null);

            } catch (Exception e) {

            }
        }

        return image;
    }

    private static int calcTileOrgX(Origin origin, BufferedImage image) {
        return calcTileOrgX(origin, image.getWidth(), image.getHeight());
    }

    private static int calcTileOrgY(Origin origin, BufferedImage image) {
        return calcTileOrgY(origin, image.getWidth(), image.getHeight());
    }

    private static int calcTileOrgX(Origin origin, int width, int height) {
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

    private static int calcTileOrgY(Origin origin, int width, int height) {
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

    private static int calcEntityOrgX(Origin origin, BufferedImage image) {
        if (image == null) {
            return 0;
        }
        return calcEntityOrgX(origin, image.getWidth(), image.getHeight());
    }

    private static int calcEntityOrgY(Origin origin, BufferedImage image) {
        if (image == null) {
            return 0;
        }
        return calcEntityOrgY(origin, image.getWidth(), image.getHeight());
    }

    private static int calcEntityOrgX(Origin origin, int width, int hight) {
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

    private static int calcEntityOrgY(Origin origin, int width, int height) {
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

    protected static ColorModel getCompatibleColorModel() {
        GraphicsConfiguration gfx_config = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();
        return gfx_config.getColorModel();
    }

    public static void init() {

        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        System.out.println(Textures.getXRes());

        cm = getCompatibleColorModel();
        imageBuffer = gfxConfig.createCompatibleImage(
                Textures.getXRes(), Textures.getYRes(), BufferedImage.TRANSLUCENT);
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
