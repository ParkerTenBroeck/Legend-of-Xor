/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class fire extends Tile {

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 8;
        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 1;
        SOLID = false;

    }

    public fire() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        long time = System.currentTimeMillis();

        int frame = (int) ((time / 50) % 8);

        return image.getSubimage(0, image.getWidth() * frame, image.getWidth(), image.getWidth());
    }

}
