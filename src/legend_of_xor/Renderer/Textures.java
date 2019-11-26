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
import java.util.LinkedList;
import javax.imageio.ImageIO;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Frame;

/**
 *
 * @author parke
 */
public class Textures {

    static final private LinkedList<Object[]> blockTextures = new LinkedList();

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

    public static void setTileResolution(int xTiles, int yTiles) {
        blockTextures.clear();
        tilePixelSizeX = xRes / xTiles;
        tilePixelSizeY = yRes / yTiles;
    }

    public static BufferedImage getBlockTexture(Tile tile) {
        try {
            if (tile.getNameID().equals(blockTextures.getFirst()[0])) {
                return (BufferedImage) blockTextures.getFirst()[1];
            }
        } catch (Exception e) {

        }

        Image image = null;
        try {
            System.out.println(tile.getNameID());
            image = ImageIO.read(new File("src//textures//blocks//" + tile.getNameID()+ ".png"));

            image = image.getScaledInstance((int) (tilePixelSizeX * tile.getXScale()),
                    (int) (tilePixelSizeY * tile.getYScale()),
                    Image.SCALE_AREA_AVERAGING);

        } catch (Exception e) {
            image = Textures.nullImage();
            e.printStackTrace();
        }
        
        blockTextures.add(new Object[]{tile.getNameID(), toBufferedImage(image)});
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
