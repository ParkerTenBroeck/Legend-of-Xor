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
public class air implements Tile {

    public static final String NAMEID = TEMPLATE.class.getName().split("\\.")[3];//name of tile must be unique

    public air() {
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        return null;
    }

    @Override
    public void update(int xPos, int yPos) {
    }

    @Override
    public Origin getOrigin() {
        return Origin.UPPER_LEFT;
    }

    @Override
    public double getXScale() {
        return 0;
    }

    @Override
    public double getYScale() {
        return 0;
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
