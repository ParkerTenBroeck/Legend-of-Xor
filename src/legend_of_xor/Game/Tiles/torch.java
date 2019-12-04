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
public class torch extends Tile {

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 91;
        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 2.5;
        SOLID = false;
        ORIGIN = Origin.BOTTOM_CENTER;
    }

    public torch() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        long time = System.currentTimeMillis();

        int frame = (int) ((time / 40) % 91);

        int startingY = (int) ((image.getHeight() / (double) TILESY) * frame);

        return image.getSubimage(0, startingY, image.getWidth(), image.getHeight() / TILESY);

    }
}
