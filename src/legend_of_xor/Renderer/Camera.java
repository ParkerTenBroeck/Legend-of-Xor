/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;
import static legend_of_xor.Controls.*;
import legend_of_xor.Game.Tiles.grass;

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

    public static double getXPos() {
        return xPos;
    }

    public static double getYPos() {
        return yPos;
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
        if (isLeftMousePressed()) {
            Level.setSmallTiles(new grass(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTiles(null, getMouseTileX(), getMouseTileY());
        }
        
        if (isDownPressed()) {
            yPos -= 0.2;
        }
        if (isUpPressed()) {
            yPos += 0.2;
        }
        if (isLeftPressed()) {
            xPos += 0.2;
        }
        if (isRightPressed()) {
            xPos -= 0.2;
        }

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

                    BufferedImage i = temp.getTileImage(x + xTileOffset, y + yTileOffset);

                    int orgX = calcOrgX(temp, i);
                    int orgY = calcOrgY(temp, i);

                    g2d.drawImage(i,
                            x * Textures.getTilePixelSizeX() + xPixelOffset + orgX,
                            y * Textures.getTilePixelSizeY() + yPixelOffset + orgY,
                            null);
                }
            }
        }
        return image;
    }

    private static int calcOrgX(Tile tile, BufferedImage image) {
        switch (tile.getOrigin()) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return (-image.getWidth()) + Textures.getTilePixelSizeX();
            case BOTTOM_LEFT:
                return 0;
            case BOTTOM_RIGHT:
                return (-image.getWidth()) + Textures.getTilePixelSizeX();
            case CENTER_BOTTOM:
                return (int) (-(image.getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;
            case CENTER_TOP:
                return (int) (-(image.getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;
            case CENTER:
                return (int) (-(image.getWidth() / 2)) + Textures.getTilePixelSizeX() / 2;

            default:
        }
        return -1;
    }

    private static int calcOrgY(Tile tile, BufferedImage image) {
        switch (tile.getOrigin()) {
            case UPPER_LEFT:
                return 0;
            case UPPER_RIGHT:
                return 0;
            case BOTTOM_LEFT:
                return (-image.getHeight()) + Textures.getTilePixelSizeY();
            case BOTTOM_RIGHT:
                return (-image.getHeight()) + Textures.getTilePixelSizeY();
            case CENTER_BOTTOM:
                return (-image.getHeight()) + Textures.getTilePixelSizeY();
            case CENTER_TOP:
                return 0;
            case CENTER:
                return (int) (-(image.getHeight() / 2)) + Textures.getTilePixelSizeY() / 2;
            default:
        }
        return -1;
    }

}
