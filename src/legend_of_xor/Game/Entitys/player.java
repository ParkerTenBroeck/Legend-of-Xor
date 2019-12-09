/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Controls;
import legend_of_xor.Game.*;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class player extends Entity {

    private double xVel;
    private double yVel;

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 4;

        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 1;

        xPos = 0;
        yPos = 40;
    }

    public player() {
        image = Textures.getEntityTexture(this);
    }

    @Override
    public BufferedImage getTileImage() {
        return image.getSubimage(0, (int) (Textures.getTileHeight() * TILE_Y_SCALE * ((int) (System.currentTimeMillis() / 150) % 4)),
                (int) (Textures.getTileWidth() * TILE_X_SCALE), (int) (Textures.getTileHeight() * TILE_Y_SCALE));
    }

    @Override
    public void update() {

        for (int i = (int) Math.floor(yPos); i <= Math.floor(yPos + yVel); i++) {
            if (Level.getSafeSmallTile((int) xPos, i).isSolid()) {
                if (yVel > 0) {
                    yVel = 0;
                    yPos = i;
                    break;

                }
            } else if (i == (int) Math.floor(yPos + yVel)) {
                yVel += 0.031;
            }
        }
        if (yPos > Level.getLevelTilesY()) {
            if (yVel > 0) {
                yVel = 0;
                yPos = Level.getLevelTilesY();
            }
        }

        xPos += xVel;
        yPos += yVel;

        if (Controls.isDownPressed()) {
            Level.addEntity(new bomb(xPos, yPos - 1.25, xVel * 1.25, -0.25));
        }

        xVel *= 0.70;

        if (Controls.isLeftPressed()) {
            xVel -= 0.1;
        }
        if (Controls.isRightPressed()) {
            xVel += 0.1;
        }
        if (Controls.isUpPressed() && yVel == 0) {
            yVel = -0.4;
        }
    }
}
