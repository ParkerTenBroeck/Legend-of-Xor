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
import javax.imageio.ImageIO;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Frame;

/**
 *
 * @author parke
 */
public class Camera {

    private static double xPos;
    private static double yPos;
    private static float zoom = 1;

    public static void init(int startingX, int startingY, int tilesX, int tilesY) {
        Camera.xPos = startingX;
        Camera.yPos = startingY;

        Textures.setTileResolution(tilesX, tilesY);
    }

    static public void DrawScreen() {

        int imageSizeX = Textures.getXRes();
        int imageSizeY = Textures.getYRes();

        BufferedImage image = new BufferedImage(imageSizeX, imageSizeY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics().create();

        g2d.drawImage(Level.getBackgroundImage(), 0, 0, null);
        drawSmallTiles(g2d, image);

        drawSmallTiles(g2d, image);
        drawSmallTiles(g2d, image);

        Frame.setImage(image);

    }

    static public BufferedImage drawSmallTiles(Graphics2D g2d, BufferedImage image) {

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTilePixelSizeX());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTilePixelSizeY());

        for (int x = -1; x < Level.getTilesX(); x++) {
            for (int y = -1; y < Level.getTilesY(); y++) {
                if (Level.getSmallTileImage(x + xTileOffset, y + yTileOffset) != null) {
                    g2d.drawImage(Level.getSmallTileImage(x + xTileOffset, y + yTileOffset),
                            x * Textures.getTilePixelSizeX() + xPixelOffset,
                            y * Textures.getTilePixelSizeY() + yPixelOffset, null);
                }
            }
        }
        return image;
    }

}
