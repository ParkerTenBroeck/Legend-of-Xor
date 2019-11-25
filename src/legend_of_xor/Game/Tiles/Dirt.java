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
public class Dirt implements Tile{

    BufferedImage image;
    private static final String NAME_ID = "dirt";
    
    public Dirt(){
      image = Textures.getBlockTexture(this);
    }
    
    
    @Override
    public String getNameID() {
        return NAME_ID;
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
    public double getYScale() {
        return 1;
    }

    @Override
    public double getXScale() {
        return 1;
    }
    
}
