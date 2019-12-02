/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class torch implements Tile {

    public static final String NAMEID = torch.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 91;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 2.5;

    private final Origin ORIGIN = Origin.BOTTOM_CENTER;
    
    private final boolean SOLID = false;

    BufferedImage image;

    public torch() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        long time = System.currentTimeMillis();

        int frame = (int) ((time / 50) % 91);

        int startingY = (int) ((image.getHeight() / (double) TILESY) * frame);

        return image.getSubimage(0, startingY, image.getWidth(), image.getHeight() / TILESY);

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

    @Override
    public boolean isSolid() {
        return SOLID;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
