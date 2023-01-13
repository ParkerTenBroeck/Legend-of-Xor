/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import legend_of_xor.Game.Entitys.falling_tile;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class sand extends Tile {

    @Override
    protected void init(){
        TILE_Y_SCALE = 1.25;
        TILE_X_SCALE = 1.25;
        
        ORIGIN = Origin.CENTER;
    }
    
    public sand() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public void update(int xPos, int yPos) {
        if (!Game.getSafeSmallTile(xPos, 1 + yPos).isSolid()) {
            Game.setSmallTile(null, xPos, yPos);
            Game.addEntity(new falling_tile(this, xPos, yPos));
        }
    }
}
