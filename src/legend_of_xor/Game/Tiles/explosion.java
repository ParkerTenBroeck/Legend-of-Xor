/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class explosion extends Tile {
    
    @Override
    protected void init(){
        TILESX = 1;
        TILESY = 24;
        
        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 2.385;
        ORIGIN = Origin.BOTTOM_LEFT;
        SOLID = false;
    }

    public explosion() {
        //image = Textures.getBlockTexture(this);
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {

        int xSize = (int)(Textures.getTileWidth() * TILE_X_SCALE);
        int ySize = (int)(Textures.getTileHeight() * TILE_Y_SCALE);

        return image.getSubimage(0, (int)(((System.currentTimeMillis()) / 50) % 24) * ySize, xSize, ySize);
    }
}
