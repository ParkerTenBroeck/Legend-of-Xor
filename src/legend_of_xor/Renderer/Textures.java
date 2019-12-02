/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
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

    private static int tilePixelSizeX = 32;
    private static int tilePixelSizeY = 32;

    public static int getXRes() {
        return xRes;
    }

    public static int getYRes() {
        return yRes;
    }

    public static int getTilePixelSizeX() {
        return tilePixelSizeX;
    }

    public static int getTilePixelSizeY() {
        return tilePixelSizeY;
    }

    public static void setResolutuin(int xRes, int yRes) {
        blockTextures.clear();
        Textures.xRes = xRes;
        Textures.yRes = yRes;
    }

    public static void setTileResolution() {
        blockTextures.clear();
        tilePixelSizeX = xRes / Camera.getCameraTilesX();
        tilePixelSizeY = yRes / Camera.getCameraTilesY();
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
            image = ImageIO.read(new File("src//textures//Tiles//" + tile.getNameID() + ".png"));

            image = image.getScaledInstance((int) (tilePixelSizeX * tile.getXScale()),
                    (int) (tilePixelSizeY * tile.getYScale()),
                    Image.SCALE_AREA_AVERAGING);

        } catch (Exception e) {
            image = Textures.nullImage();
            e.printStackTrace();
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
            image = ImageIO.read(new File("src//textures//Entities//" + entity.getNameID() + ".png"));

            image = image.getScaledInstance((int) (tilePixelSizeX * entity.getXScale()),
                    (int) (tilePixelSizeY * entity.getYScale()),
                    Image.SCALE_AREA_AVERAGING);

        } catch (Exception e) {
            image = Textures.nullImage();
            e.printStackTrace();
        }

        entityTextreus.addFirst(new Object[]{entity.getNameID(), toBufferedImage(image)});
        return toBufferedImage(image);
    }
    
    static Image nullImage() {
        return null;
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
