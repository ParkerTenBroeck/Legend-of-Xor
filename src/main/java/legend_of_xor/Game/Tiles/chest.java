/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import legend_of_xor.Game.Entitys.open_sign;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class chest extends Tile {

    private open_sign sign;

    @Override
    protected void init() {
        INVENTORY = true;
        SOLID = false;
        TILE_X_SCALE = 1.25;
    }

    public chest() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public void update(int xPos, int yPos) {
        if (Math.abs(xPos - Game.getPlayer().getXPos()) < 2 && Math.abs(yPos - Game.getPlayer().getYPos()) < 2) {
            if (sign == null) {
                sign = new open_sign(xPos + 0.5, yPos );
                Game.addEntity(sign);
            }
            sign.terminate(false);
        } else {
            if (sign != null) {
                sign.terminate(true);
                sign = null;
            }
        }
    }
}
