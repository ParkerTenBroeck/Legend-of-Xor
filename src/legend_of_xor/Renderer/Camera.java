/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import static legend_of_xor.Controls.getMouseTileX;
import static legend_of_xor.Controls.getMouseTileY;
import static legend_of_xor.Controls.isLeftMousePressed;
import static legend_of_xor.Controls.isRightMousePressed;

import legend_of_xor.Game.Entity;

import legend_of_xor.Game.Tiles.*;

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
            Level.setSmallTile(new grass(), getMouseTileX(), getMouseTileY());
        }
        if (isRightMousePressed()) {
            Level.setSmallTile(null, getMouseTileX(), getMouseTileY());
        }

    }
}
