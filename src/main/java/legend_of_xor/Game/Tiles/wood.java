/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class wood extends Tile {
    public wood() {
        image = Textures.getBlockTexture(this);
    }
}
