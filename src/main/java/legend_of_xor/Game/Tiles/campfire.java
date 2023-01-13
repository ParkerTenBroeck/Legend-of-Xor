/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class campfire extends Tile {

    @Override
    protected void init() {
        TILESY = 8;
        
        TILE_Y_SCALE = 1.5;
        TILE_X_SCALE = 1.5;
        
        SOLID = false;
        
        ORIGIN = Origin.BOTTOM_CENTER;
    }

    public campfire() {
        image = Textures.getBlockTexture(this);
    }
    
    @Override
    public BufferedImage getTileImage(int xPos, int yPos){
        
        int offset = (int)(Textures.getTileHeight() * TILE_Y_SCALE * (int)((System.currentTimeMillis() / 50.0) % 8));
        
        return image.getSubimage(0, offset, (int)(Textures.getTileWidth() * TILE_X_SCALE), (int)(Textures.getTileHeight() * TILE_Y_SCALE));
    }

}
