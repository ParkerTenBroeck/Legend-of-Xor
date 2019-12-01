/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import com.sun.prism.paint.Gradient;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class grass implements Tile {

    public static final String NAMEID = grass.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 256;

    private final double TILE_X_SCALE = 1.25;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1.25;

    private final Origin ORIGIN = Origin.CENTER;

    private final boolean SOLID = true;

    BufferedImage image;

    public grass() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {

        int offset = 0;

        try {
            if (!Level.getSmallTile(xPos, yPos - 1).isSolid()) {
                offset += 1;
            }
        } catch (Exception e) {
            offset += 1;
        }
        try {
            if (!Level.getSmallTile(xPos + 1, yPos).isSolid()) {
                offset += 2;
            }
        } catch (Exception e) {
            offset += 2;
        }
        try {
            if (!Level.getSmallTile(xPos, yPos + 1).isSolid()) {
                offset += 4;
            }
        } catch (Exception e) {
            offset += 4;
        }
        try {
            if (!Level.getSmallTile(xPos - 1, yPos).isSolid()) {
                offset += 8;
            }
        } catch (Exception e) {
            offset += 8;
        }
        offset *= 16;

        try {
            if (!Level.getSmallTile(xPos - 1, yPos - 1).isSolid()) {
                offset += 1;
            }
        } catch (Exception e) {
            offset += 1;
        }
        try {
            if (!Level.getSmallTile(xPos + 1, yPos - 1).isSolid()) {
                offset += 2;
            }
        } catch (Exception e) {
            offset += 2;
        }
        try {
            if (!Level.getSmallTile(xPos + 1, yPos + 1).isSolid()) {
                offset += 4;
            }
        } catch (Exception e) {
            offset += 4;
        }
        try {
            if (!Level.getSmallTile(xPos - 1, yPos + 1).isSolid()) {
                offset += 8;
            }
        } catch (Exception e) {
            offset += 8;
        }
        
        offset = offset * (int) (Textures.getTilePixelSizeY() * TILE_Y_SCALE);
        return image.getSubimage(0, offset, (int) (Textures.getTilePixelSizeX() * TILE_X_SCALE), (int) (Textures.getTilePixelSizeY() * TILE_Y_SCALE));
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
