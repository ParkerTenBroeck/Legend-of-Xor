/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entitys.goblin_enemy;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public abstract class Entity {

    public final String NAMEID;

    protected int TILESX = 1; //how many tiles there are in the sprite sheet
    protected int TILESY = 1;

    protected double TILE_X_SCALE = 1;  //x and y scale of each tile
    protected double TILE_Y_SCALE = 1;

    protected Origin ORIGIN = Origin.BOTTOM_CENTER;

    protected double xPos = 0;
    protected double yPos = 0;

    protected BufferedImage image;

    protected void init() {
    }

    public Entity() {
        init();
        NAMEID = this.getClass().getName().split("\\.")[3];//name of tile must be unique
    }

    public String getNameID() {
        return NAMEID;
    }

    public BufferedImage getTileImage() {
        return image;
    }

    public void update() {

    }

    public Origin getOrigin() {
        return ORIGIN;
    }

    public double getXScale() {
        return TILESX * TILE_X_SCALE;
    }

    public double getYScale() {
        return TILESY * TILE_Y_SCALE;
    }

    public double getXPos() {
        return xPos;
    }

    public void setXPos(double xPos) {
        this.xPos = xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void setYPos(double yPos) {
        this.yPos = yPos;
    }

    public boolean terminate() {
        return false;
    }

    public void changeXPos(double xOffset) {
        xPos += xOffset;
    }

    public void changeYPos(double yOffset) {
        yPos += yOffset;
    }
}
