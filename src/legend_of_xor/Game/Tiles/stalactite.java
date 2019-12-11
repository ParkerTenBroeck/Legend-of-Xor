/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import legend_of_xor.Game.Entitys.water_drop;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class stalactite extends Tile {

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 1;
        TILE_X_SCALE = 1.5;
        TILE_Y_SCALE = 2.75;
        ORIGIN = Origin.TOP_CENTER;
    }

    public stalactite() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public void update(int xPos, int yPos) {
        if (Math.random() > 0.99) {
            Level.addEntity(new water_drop(xPos, yPos + 2.75));
        }
    }
}
