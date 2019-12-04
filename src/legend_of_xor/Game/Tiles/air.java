/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Tiles;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;

/**
 *
 * @author parke
 */
public class air extends Tile {

    @Override
    protected void init(){
        SOLID = false;
    }

    public air() {
    }

    @Override
    public BufferedImage getTileImage(int xPos, int yPos) {
        return null;
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
