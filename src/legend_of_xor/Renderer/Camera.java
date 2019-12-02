/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static legend_of_xor.Controls.getMouseTileX;
import static legend_of_xor.Controls.getMouseTileY;
import static legend_of_xor.Controls.isLeftMousePressed;
import static legend_of_xor.Controls.isRightMousePressed;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Tiles.grass;

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

        Textures.setTileResolution();
    }

    public static double getXPos() {
        return xPos;
    }

    public static double getYPos() {
        return yPos;
    }

    public static synchronized void DrawScreen() {

        int imageSizeX = Textures.getXRes();
        int imageSizeY = Textures.getYRes();

        BufferedImage image = new BufferedImage(imageSizeX, imageSizeY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics().create();

        g2d.drawImage(Level.getBackgroundImage(), 0, 0, null);
        drawSmallTiles(g2d, image);
        drawEntities(g2d, image);

        //drawSmallTiles(g2d, image);
        //drawSmallTiles(g2d, image);
        Veiwer.setImage(image);

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
            Level.setSmallTiles(new grass(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTiles(null, getMouseTileX(), getMouseTileY());
        }

    }

    static public BufferedImage drawSmallTiles(Graphics2D g2d, BufferedImage image) {

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTilePixelSizeX());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTilePixelSizeY());

        for (int y = cameraTilesX + 1; y >= -1; y--) {

            for (int x = -1; x < Camera.cameraTilesX; x++) {

                if (Level.getSmallTileImage(x + xTileOffset, y + yTileOffset) != null) {

                    Tile temp = Level.getSmallTile(x + xTileOffset, y + yTileOffset);

                    BufferedImage i = temp.getTileImage(x + xTileOffset, y + yTileOffset);

                    int orgX = calcTileOrgX(temp.getOrigin(), i);
                    int orgY = calcTileOrgY(temp.getOrigin(), i);

                    g2d.drawImage(i,
                            x * Textures.getTilePixelSizeX() + xPixelOffset + orgX,
                            y * Textures.getTilePixelSizeY() + yPixelOffset + orgY,
                            null);
                }
            }
        }
        return image;
    }

    static public BufferedImage drawEntities(Graphics2D g2d, BufferedImage image) {

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTilePixelSizeX());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTilePixelSizeY());

        for (Entity entity : Level.getEntities()) {
            g2d.drawImage(entity.getTileImage(),
                    (int) ((entity.getXPos() - xTileOffset) * Textures.getTilePixelSizeX() + xPixelOffset + calcTileOrgX(entity.getOrigin(), entity.getTileImage())),
                    (int) ((entity.getYPos() - yTileOffset) * Textures.getTilePixelSizeY() + yPixelOffset - 16), null);
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
                return (-width) + Textures.getTilePixelSizeX();
            case BOTTOM_LEFT:
                return 0;
            case BOTTOM_RIGHT:
                return (-width) + Textures.getTilePixelSizeX();
            case BOTTOM_CENTER:
                return (int) (-(width / 2)) + Textures.getTilePixelSizeX() / 2;
            case TOP_CENTER:
                return (int) (-(width / 2)) + Textures.getTilePixelSizeX() / 2;
            case CENTER:
                return (int) (-(width / 2)) + Textures.getTilePixelSizeX() / 2;

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
                return (-height) + Textures.getTilePixelSizeY();
            case BOTTOM_RIGHT:
                return (-height) + Textures.getTilePixelSizeY();
            case BOTTOM_CENTER:
                return (-height) + Textures.getTilePixelSizeY();
            case TOP_CENTER:
                return 0;
            case CENTER:
                return (int) (-(height / 2)) + Textures.getTilePixelSizeY() / 2;
            default:
        }
        return -1;
    }

}
