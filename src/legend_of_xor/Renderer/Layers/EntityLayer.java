/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Layers.Layer;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class EntityLayer extends Layer {

    @Override
    public void drawLayer() {
                Graphics g2d =  image.getGraphics().create();

        int xTileOffset = 0 - (int) Math.floor(Camera.getXPos());
        int yTileOffset = 0 - (int) Math.floor(Camera.getYPos());

        int xPixelOffset = (int) ((Camera.getXPos() - Math.floor(Camera.getXPos())) * Textures.getTileWidth());
        int yPixelOffset = (int) ((Camera.getYPos() - Math.floor(Camera.getYPos())) * Textures.getTileHeight());
        //System.out.println(Level.getEntities().size());

        for (Entity entity : Game.getEntitiesClone()) {
            try {
                BufferedImage temp = entity.getEntityImage();
                
                g2d.drawImage(temp,
                        (int) ((entity.getXPos() - xTileOffset) * Textures.getTileWidth() + xPixelOffset + Renderer.calcEntityOrgX(entity.getOrigin(), temp)),
                        (int) ((entity.getYPos() - yTileOffset) * Textures.getTileHeight() + yPixelOffset + Renderer.calcEntityOrgY(entity.getOrigin(), temp)), null);

            } catch (Exception e) {

            }
        }
    }
    
}
