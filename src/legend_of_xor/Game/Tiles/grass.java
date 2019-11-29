/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class grass implements Tile {

    public static final String NAMEID = grass.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 14;

    private final double TILE_X_SCALE = 1.25;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1.25;

    private final Origin ORIGIN = Origin.CENTER;

    BufferedImage image;

    public grass() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {

        long time = System.currentTimeMillis();

        int frame = (int) ((time / 200) % 14);

        int offest = 0;//(int) (Textures.getTilePixelSizeY() * TILE_Y_SCALE) * frame;

        return image.getSubimage(0, offest, (int) (Textures.getTilePixelSizeX() * TILE_X_SCALE), (int) (Textures.getTilePixelSizeY() * TILE_Y_SCALE));
    }

    @Override
    public Origin getOrigin() {
        return ORIGIN;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getXScale() {
        return TILESX * TILE_X_SCALE;
    }

    @Override
    public double getYScale() {
        return TILESY * TILE_Y_SCALE;
    }

}
