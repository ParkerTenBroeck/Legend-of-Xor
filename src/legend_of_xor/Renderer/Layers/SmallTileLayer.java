/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class SmallTileLayer extends Layer {

    @Override
    public void drawLayer() {
        Graphics2D g2d = (Graphics2D) image.getGraphics().create();

        int xTileOffset = 0 - (int) Math.floor(Camera.getXPos());
        int yTileOffset = 0 - (int) Math.floor(Camera.getYPos());

        int xPixelOffset = (int) ((Camera.getXPos() - Math.floor(Camera.getXPos())) * Textures.getTileWidth());
        int yPixelOffset = (int) ((Camera.getYPos() - Math.floor(Camera.getYPos())) * Textures.getTileHeight());

        for (int y = Camera.getCameraTilesY() + 1; y >= -1; y--) {

            for (int x = -1; x < Camera.getCameraTilesX(); x++) {

                Tile temp = Level.getSafeSmallTile(x + xTileOffset, y + yTileOffset);

                BufferedImage i = temp.getTileImage(x + xTileOffset, y + yTileOffset);

                if (i != null) {
                    int orgX = Renderer.calcTileOrgX(temp.getOrigin(), i);
                    int orgY = Renderer.calcTileOrgY(temp.getOrigin(), i);
                    
                    g2d.drawImage(i,
                            x * Textures.getTileWidth() + xPixelOffset + orgX,
                            y * Textures.getTileHeight() + yPixelOffset + orgY,
                            null);
                }

            }
        }
    }

}
