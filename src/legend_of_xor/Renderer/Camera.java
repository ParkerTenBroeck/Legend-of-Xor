/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;

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

        //drawSmallTiles(g2d, image);
        //drawSmallTiles(g2d, image);
        Veiwer.setImage(image);

    }

    public static void update() {
        xPos -= 0.07;
        //yPos -= 0.06;
    }

    static public BufferedImage drawSmallTiles(Graphics2D g2d, BufferedImage image) {

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTilePixelSizeX());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTilePixelSizeY());

        for (int y = Level.getTilesY() + 1; y >= -1; y--) {

            for (int x = -1; x < Level.getTilesX(); x++) {

                if (Level.getSmallTileImage(x + xTileOffset, y + yTileOffset) != null) {

                    Tile temp = Level.getSmallTile(x + xTileOffset, y + yTileOffset);

                    int orgX = calcOrgX(temp);
                    int orgY = calcOrgY(temp);

                    g2d.drawImage(temp.getTileImage(),
                            x * Textures.getTilePixelSizeX() + xPixelOffset + orgX,
                            y * Textures.getTilePixelSizeY() + yPixelOffset + orgY,
                            null);
                }
            }
        }
        return image;
    }

    private static int calcOrgX(Tile tile) {
        switch (tile.getOrigin()) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return (-tile.getTileImage().getWidth()) + Textures.getTilePixelSizeX();
            case BOTTOM_LEFT:
                return 0;
            case BOTTOM_RIGHT:
                return (-tile.getTileImage().getWidth()) + Textures.getTilePixelSizeX();
            case CENTER_BOTTOM:
                return (int) (-(tile.getTileImage().getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;
            case CENTER_TOP:
                return (int) (-(tile.getTileImage().getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;
            case CENTER:
                return (int) (-(tile.getTileImage().getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;

            default:
        }
        return -1;
    }

    private static int calcOrgY(Tile tile) {
        switch (tile.getOrigin()) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return 0;
            case BOTTOM_LEFT:
                return (-tile.getTileImage().getHeight()) + Textures.getTilePixelSizeY();
            case BOTTOM_RIGHT:
                return (-tile.getTileImage().getHeight()) + Textures.getTilePixelSizeY();
            case CENTER_BOTTOM:
                return (-tile.getTileImage().getHeight()) + Textures.getTilePixelSizeY();
            case CENTER_TOP:
                return 0;
            case CENTER:
                return (int) (-(tile.getTileImage().getHeight() / 2)) + Textures.getTilePixelSizeY() / 2;
            default:
        }
        return -1;
    }

}
