/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

import java.awt.image.BufferedImage;
import legend_of_xor.Renderer.Camera.Origin;

/**
 *
 * @author parke
 */
public abstract class Tile {

    public final String NAMEID;

    protected int TILESX = 1; //how many tiles there are in the sprite sheet
    protected int TILESY = 1;

    protected double TILE_X_SCALE = 1;  //x and y scale of each tile
    protected double TILE_Y_SCALE = 1;

    protected Origin ORIGIN = Origin.TOP_CENTER;

    protected boolean SOLID = true;
    protected boolean INVENTORY = false;

    protected BufferedImage image;

    protected void init() {
    }

    public Tile() {
        init();
        NAMEID = this.getClass().getName().split("\\.")[3];//name of tile must be unique
    }

    public String getNameID() {
        return NAMEID;
    }

    public BufferedImage getTileImage(int xPos, int yPos) {
        return image;
    }

    public void update(int xPos, int yPos) {

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

    public boolean isSolid() {
        return SOLID;
    }

    public boolean hasInventory() {
        return INVENTORY;
    }

    public double getXTileScale() {
        return TILE_X_SCALE;
    }

    public double getYTileScale() {
        return TILE_Y_SCALE;
    }
}
