/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entitys.water_drop;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class stalactite implements Tile {

    public static final String NAMEID = stalactite.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 1;

    private final double TILE_X_SCALE = 1.5;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 2.75;

    private final Origin ORIGIN = Origin.TOP_CENTER;

    private final boolean SOLID = true;

    BufferedImage image;

    public stalactite() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        return image;
    }

    @Override
    public void update(int xPos, int yPos) {
       if(Math.random() > 0.99){
           Level.addEntity(new water_drop(xPos + 0.5, yPos + 1.75));
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
    public boolean isSolid() {
        return SOLID;
    }
}
