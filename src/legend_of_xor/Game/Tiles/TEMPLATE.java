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
public class TEMPLATE implements Tile {

    public static final String NAMEID = TEMPLATE.class.getName().split("\\.")[3];//name of tile must be unique

    private int tilesX; //how many tiles there are in the sprite sheet
    private int tilesY;

    private double tileXScale;  //x and y scale of each tile
    private double tileYScale;

    BufferedImage image;

    public TEMPLATE() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {
        return image;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getXScale() {
        return tilesX * tileXScale;
    }

    @Override
    public double getYScale() {
        return tilesY * tileYScale;
    }
}
