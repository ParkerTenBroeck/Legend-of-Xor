/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Sound;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class goblin_enemy implements Entity {

    public static final String NAMEID = goblin_enemy.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 1;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1;

    private final Origin ORIGIN = Origin.BOTTOM_CENTER;

    private double xPos;
    private double yPos;

    private double xVel;
    private double yVel;

    BufferedImage image;

    public goblin_enemy() {
        image = Textures.getEntityTexture(this);

    }

    goblin_enemy(double xPos, double yPos) {

        Sound.loadSound("/Sounds/goblin.wav");
        Sound.playSound();

        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {
        return image;
    }

    @Override
    public void update() {
        if (Level.getSmallTile((int) xPos, (int) yPos).isSolid()) {
            yPos = (int) yPos;
            yVel = 0;
        } else {
            yVel += 0.01;
        }
        yPos += yVel;
        xPos += xVel;

        if ((Level.getPlayer().getXPos() - xPos) > 0) {
            xVel = 0.1;
        } else {
            xVel = -0.1;
        }

        while (Level.getSmallTile((int) xPos, (int) (yPos - 1)).isSolid()) {
            if (Level.getSmallTile((int) xPos, (int) (yPos - 1)).isSolid()) {
                yPos = Math.floor(yPos - 1);
            }
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

    @Override
    public boolean terminate() {
        return false;
    }
}
