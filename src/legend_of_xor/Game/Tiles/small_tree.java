/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class small_tree extends Tile {

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 1;
        TILE_X_SCALE = 3;
        TILE_Y_SCALE = 3;
        SOLID = false;
        ORIGIN = Origin.BOTTOM_CENTER;
    }

    public small_tree() {
        image = Textures.getBlockTexture(this);
    }
}
