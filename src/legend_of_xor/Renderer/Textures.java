/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import static java.util.Collections.list;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Textures {

    static final private LinkedList<Object[]> blockTextures = new LinkedList();
    static final private LinkedList<Object[]> entityTextreus = new LinkedList();

    private static int xRes = 1280; // default resolution
    private static int yRes = 720;

    private static int tileWidth = 32;
    private static int tileHeight = 32;

    public static int getXRes() {
        return xRes;
    }

    public static int getYRes() {
        return yRes;
    }

    public static int getTileWidth() {
        return tileWidth;
    }

    public static int getTileHeight() {
        return tileHeight;
    }

    public static void setResolutuin() {
        blockTextures.clear();
        Textures.xRes = tileWidth * Camera.getCameraTilesX();
        Textures.yRes = tileWidth * Camera.getCameraTilesY();
        Veiwer.setResolutiom(xRes, yRes);
    }

    public static void setTileResolution(int width, int height) {
        blockTextures.clear();
        tileWidth = width;
        tileHeight = height;
    }

    public static BufferedImage getBlockTexture(Tile tile) {
        try {

            ListIterator list_Iter = blockTextures.listIterator(0);

            while (list_Iter.hasNext()) {
                Object[] temp = (Object[]) list_Iter.next();

                if (tile.getNameID().equals(temp[0])) {
                    return (BufferedImage) temp[1];
                }
            }

        } catch (Exception e) {

        }

        Image image = null;
        try {
            System.out.println(tile.getNameID());
            image = ImageIO.read(Textures.class.getResourceAsStream("/Textures/Tiles/" + tile.getNameID() + ".png"));

            image = image.getScaledInstance((int) (tileWidth * tile.getXScale()),
                    (int) (tileHeight * tile.getYScale()),
                    Image.SCALE_AREA_AVERAGING);

        } catch (Exception e) {
            image = Textures.nullImage((int) (tileWidth * tile.getXScale()), (int) ((tileHeight * tile.getYScale())));
        }

        blockTextures.addFirst(new Object[]{tile.getNameID(), toBufferedImage(image)});
        return toBufferedImage(image);
    }

    public static BufferedImage getEntityTexture(Entity entity) {
        try {

            ListIterator list_Iter = entityTextreus.listIterator(0);

            while (list_Iter.hasNext()) {
                Object[] temp = (Object[]) list_Iter.next();

                if (entity.getNameID().equals(temp[0])) {
                    return (BufferedImage) temp[1];
                }
            }

        } catch (Exception e) {

        }

        Image image = null;
        try {
            System.out.println(entity.getNameID());
            image = ImageIO.read(Textures.class.getResourceAsStream("/Textures/Entities/" + entity.getNameID() + ".png"));

            image = image.getScaledInstance((int) (tileWidth * entity.getXScale()),
                    (int) (tileHeight * entity.getYScale()),
                    Image.SCALE_AREA_AVERAGING);

        } catch (Exception e) {
            image = Textures.nullImage((int) (tileWidth * entity.getXScale()), (int) ((tileHeight * entity.getYScale())));
        }

        entityTextreus.addFirst(new Object[]{entity.getNameID(), toBufferedImage(image)});
        return toBufferedImage(image);
    }

    static Image nullImage(int width, int height) {
        BufferedImage error = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = error.getGraphics();

        int rectX = width / (tileWidth / 4);
        int rectY = height / (tileHeight / 4);

        System.out.println(rectX);

        for (int x = 0; x < rectX; x++) {
            for (int y = 0; y < rectY; y++) {

                if ((x % 2 ^ y % 2) == 1) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(new Color(0xFF00FF));
                }
                g.fillRect(x * tileWidth / 4, y * tileHeight / 4, tileWidth / 4, tileHeight / 4);
            }
        }

        return error;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

}
