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
public class ore implements Tile {

    public static final String NAMEID = ore.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 15;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1;

    private final byte ORIGIN = 0;//0 upper left,1 upper right,2 bottom left,3 bottom right

    BufferedImage image;

    public ore() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {

        long time = System.currentTimeMillis();

        int frame = (int) ((time / 50) % 15);

        return image.getSubimage(0, image.getWidth() * frame, image.getWidth(), image.getWidth());
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte getOrigin() {
        return ORIGIN;
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
