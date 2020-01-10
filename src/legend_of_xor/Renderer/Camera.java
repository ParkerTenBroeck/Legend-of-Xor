/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import legend_of_xor.Controls;
import static legend_of_xor.Controls.getMouseTileX;
import static legend_of_xor.Controls.getMouseTileY;
import static legend_of_xor.Controls.isLeftMousePressed;
import static legend_of_xor.Controls.isRightMousePressed;

import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.open_sign;
import legend_of_xor.Game.Entitys.pixel;
import legend_of_xor.Game.Entitys.rope;
import legend_of_xor.Game.Entitys.temp;
import legend_of_xor.Game.Physics.HitBox;
import legend_of_xor.Game.Tile;

import legend_of_xor.Game.Tiles.*;

/**
 *
 * @author parke
 */
public class Camera {

    private static String tileClassName = "legend_of_xor.Game.Tiles.sand";

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

    public static void init(int cameraTilesX, int cameraTilesY) {

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
        } else if (-xPos + cameraTilesX > Game.getLevelTilesX()) {
            xPos = -(Game.getLevelTilesX() - cameraTilesX);
        }
        if (-yPos < 0) {
            yPos = 0;
        } else if (-yPos + cameraTilesY > Game.getLevelTilesY()) {
            yPos = -(Game.getLevelTilesY() - cameraTilesY);
        }

        if (isLeftMousePressed()) {
            //Game.addEntity(new rope(20, new temp(getMouseTileX(), getMouseTileY()), true));
            ArrayList<Entity> entities = Game.getEntities();
            Entity mouse = new open_sign(getMouseTileX(), getMouseTileY());
            boolean inside = false;

            for (int i = Game.getEntities().size() - 1; i >= 0; i--) {
                try {
                    if (entities.get(i).distance(mouse) < 1.4) {
                        inside = true;
                        break;
                    }
                } catch (Exception e) {

                }
            }
            if (!inside) {
                try {
                    Game.setSmallTile((Tile) Class.forName(tileClassName).newInstance(), getMouseTileX(), getMouseTileY());
                } catch (Exception e) {
                    Logger.getLogger(Camera.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        if (isRightMousePressed()) {
            //Game.setSmallTile(null, getMouseTileX(), getMouseTileY());
            pixel.pixilateAndDestroy(getMouseTileX(), getMouseTileY(), 500000, 1000);
        }
        if (Controls.isMiddlePressed()) {
            tileClassName = Game.getSafeSmallTile(getMouseTileX(), getMouseTileY()).getClass().getName();
            System.out.println(tileClassName);
        }

    }
}
