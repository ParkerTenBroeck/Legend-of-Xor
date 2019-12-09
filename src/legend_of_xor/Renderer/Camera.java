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
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import static legend_of_xor.Controls.getMouseTileX;
import static legend_of_xor.Controls.getMouseTileY;
import static legend_of_xor.Controls.isLeftMousePressed;
import static legend_of_xor.Controls.isRightMousePressed;
import legend_of_xor.Game.BackgroundTile;
import legend_of_xor.Game.Tile;
import legend_of_xor.Veiwer.Veiwer;
import legend_of_xor.Game.Entity;

import legend_of_xor.Game.Tiles.chest;
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
        if (-xPos < 0) {
            xPos = 0;
        } else if (-xPos + cameraTilesX > Level.getLevelTilesX()) {
            xPos = -(Level.getLevelTilesX() - cameraTilesX);
        }
        if (-yPos < 0) {
            yPos = 0;
        } else if (-yPos + cameraTilesY > Level.getLevelTilesY()) {
            yPos = -(Level.getLevelTilesY() - cameraTilesY);
        }

        if (isLeftMousePressed()) {
            Level.setSmallTiles(new grass(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTiles(null, getMouseTileX(), getMouseTileY());
        }

    }

    
    static private BufferedImage drawSmallTiles() {

        BufferedImage image = Renderer.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
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

                    int orgX = Renderer.calcTileOrgX(temp.getOrigin(), i);
                    int orgY = Renderer.calcTileOrgY(temp.getOrigin(), i);

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

        BufferedImage image = Renderer.createCompatibleImage(Textures.getXRes(), Textures.getYRes());
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
                        (int) ((entity.getXPos() - xTileOffset) * Textures.getTileWidth() + xPixelOffset + Renderer.calcEntityOrgX(entity.getOrigin(), temp)),
                        (int) ((entity.getYPos() - yTileOffset) * Textures.getTileHeight() + yPixelOffset + Renderer.calcEntityOrgY(entity.getOrigin(), temp)), null);

            } catch (Exception e) {

            }
        }

        return image;
    }
}
