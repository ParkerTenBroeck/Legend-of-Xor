/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Controls;
import legend_of_xor.Game.*;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class player extends Player implements Entity {

    public static final String NAMEID = player.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 4;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1;

    private final Origin ORIGIN = Origin.CENTER;

    private double xPos = 0;
    private double yPos = -4;

    private double xVel;
    private double yVel;

    BufferedImage image;

    public player() {
        image = Textures.getEntityTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {
        return image.getSubimage(0, Textures.getTilePixelSizeY() * ((int) (System.currentTimeMillis() / 150) % 4), Textures.getTilePixelSizeX(), Textures.getTilePixelSizeY());
    }

    @Override
    public void update() {
        if (Level.getSmallTile((int) xPos, (int) yPos).isSolid()) {
            if (yVel > 0) {
                yVel = 0;
                yPos = Math.ceil(yPos);
            }
        } else {
            yVel += 0.04;
        }

        xPos += xVel;
        yPos += yVel;

        xPos *= 0.90;

        if (Controls.isLeftPressed()) {
            xVel -= 0.1;
        }
        if (Controls.isRightPressed()) {
            xVel += 0.1;
        }
        if (Controls.isUpPressed() && yVel == 0) {
            yVel = -0.7;
        }
        if (Level.getSmallTile((int) xPos, (int) (yPos - 1)).isSolid()) {
            yPos = Math.floor(yPos - 1);
        }

    }

    @Override
    public Origin getOrigin() {
        return ORIGIN;
    }

    @Override
    public double getXScale() {
        return TILESX * TILE_X_SCALE;
    }

    @Override
    public double getYScale() {
        return TILESY * TILE_Y_SCALE;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }
}