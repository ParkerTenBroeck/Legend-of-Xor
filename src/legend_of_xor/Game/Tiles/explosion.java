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
public class explosion implements Tile {

    public static final String NAMEID = explosion.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 24;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 2.385;
    
        private final byte ORIGIN = 3;//0 upper left,1 upper right,2 bottom left,3 bottom right

    BufferedImage image;

    public explosion() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {

        int xSize = (int)(Textures.getTilePixelSizeX() * TILE_X_SCALE);
        int ySize = (int)(Textures.getTilePixelSizeY() * TILE_Y_SCALE);

        return image.getSubimage(0, (int)((System.currentTimeMillis() / 50) % 24) * ySize, xSize, ySize);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        @Override
    public byte getOrigin(){
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