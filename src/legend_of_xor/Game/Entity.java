/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

import java.awt.image.BufferedImage;
import legend_of_xor.Renderer.Camera.Origin;

/**
 *
 * @author parke
 */
public interface Entity {

    public String getNameID();

    public BufferedImage getTileImage();

    public Origin getOrigin();

    /**
     * Gets the X scale of the image
     *
     * Must be Tile X scale * Tiles X
     *
     * @return
     */
    public double getXScale();

    /**
     * Gets the Y scale of the image
     *
     * Must be Tile Y scale * Tiles Y
     *
     * @return
     */
    public double getYScale();

    public void update();
    
    public double getXPos();
    
    public double getYPos();
    
    public boolean terminate();
}
