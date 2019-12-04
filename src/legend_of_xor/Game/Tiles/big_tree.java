/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class big_tree extends Tile {

    @Override
    protected void init(){
        ORIGIN = Origin.BOTTOM_CENTER;
        TILE_X_SCALE = 3;
        TILE_Y_SCALE = 7;
        SOLID = false;
    }

    public big_tree() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public void update(int xPos, int yPos) {
    }

    @Override
    public Origin getOrigin(){
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
        return SOLID;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
