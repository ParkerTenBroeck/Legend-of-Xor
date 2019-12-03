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
import legend_of_xor.Game.Tiles.brick;
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

        Textures.setResolutuin();
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
            Level.setSmallTiles(new brick(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTiles(null, getMouseTileX(), getMouseTileY());
        }

    }

    static public BufferedImage drawSmallTiles(Graphics2D g2d, BufferedImage image) {

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

    static public BufferedImage drawEntities(Graphics2D g2d, BufferedImage image) {

        int xTileOffset = 0 - (int) Math.floor(xPos);
        int yTileOffset = 0 - (int) Math.floor(yPos);

        int xPixelOffset = (int) ((xPos - Math.floor(xPos)) * Textures.getTileWidth());
        int yPixelOffset = (int) ((yPos - Math.floor(yPos)) * Textures.getTileHeight());
        try {
            for (Entity entity : Level.getEntities()) {
                BufferedImage temp = entity.getTileImage();

                g2d.drawImage(temp,
                        (int) ((entity.getXPos() - xTileOffset) * Textures.getTileWidth() + xPixelOffset + calcEntityOrgX(entity.getOrigin(), temp)),
                        (int) ((entity.getYPos() - yTileOffset) * Textures.getTileHeight() + yPixelOffset + calcEntityOrgY(entity.getOrigin(), temp)), null);

            }
        } catch (Exception e) {

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

}
