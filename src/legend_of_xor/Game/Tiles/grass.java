/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class grass extends Tile {

    @Override
    protected void init(){
        TILESX = 1;
        TILESY = 256;
        TILE_X_SCALE = 1.25;
        TILE_Y_SCALE = 1.25;
        ORIGIN = Origin.CENTER;
        SOLID = true;
    }
    
    public grass() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {

        int offset = 0;

            if (!Game.getSafeSmallTile(xPos, yPos - 1).isSolid()) {
                offset += 1;
            }
            if (!Game.getSafeSmallTile(xPos + 1, yPos).isSolid()) {
                offset += 2;
            }
            if (!Game.getSafeSmallTile(xPos, yPos + 1).isSolid()) {
                offset += 4;
            }
            if (!Game.getSafeSmallTile(xPos - 1, yPos).isSolid()) {
                offset += 8;
            }
        offset *= 16;

            if (!Game.getSafeSmallTile(xPos - 1, yPos - 1).isSolid()) {
                offset += 1;
            }
            if (!Game.getSafeSmallTile(xPos + 1, yPos - 1).isSolid()) {
                offset += 2;
            }
            if (!Game.getSafeSmallTile(xPos + 1, yPos + 1).isSolid()) {
                offset += 4;
            }
            if (!Game.getSafeSmallTile(xPos - 1, yPos + 1).isSolid()) {
                offset += 8;
            }
        
        offset = offset * (int) (Textures.getTileHeight() * TILE_Y_SCALE);
        return image.getSubimage(0, offset, (int) (Textures.getTileWidth() * TILE_X_SCALE), (int) (Textures.getTileHeight() * TILE_Y_SCALE));
    }
}
