/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Graphics;
import java.awt.Graphics2D;
import legend_of_xor.Game.BackgroundTile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Layers.Layer;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class BackgroundLayer extends Layer {

    @Override
    public void drawLayer() {

        Graphics graphics = image.getGraphics();
        
        int tilesWidth = Game.getBackgroundTilesWidth() * (Game.getBackgroundTilesX() - 1);
        int tilesHeight = Game.getBackgroundTilesHeight() * (Game.getBackgroundTilesY() - 1);

        int offsetX = (int) (Camera.getXPos() / ((double) (Game.getLevelTilesX() - Camera.getCameraTilesX())) * tilesWidth) / (Game.getBackgroundTilesX());
        int offsetY = (int) (Camera.getYPos() / ((double) (Game.getLevelTilesY() - Camera.getCameraTilesY())) * tilesHeight) / (Game.getBackgroundTilesY());

        for (int y = 0; y < Game.getBackgroundTilesY(); y++) {
            for (int x = 0; x < Game.getBackgroundTilesX(); x++) {

                BackgroundTile tile = Game.getBackgroundTile(x, y);

                graphics.drawImage(tile.getImage(),
                        tile.getXPos(x, y) + offsetX,
                        tile.getYPos(x, y) + offsetY,
                         null);

//                Renderer.copySrcIntoDstAt(tile.getImage(), image,
//                        tile.getXPos(x, y) + offsetX,
//                        tile.getYPos(x, y) + offsetY);

            }
        }
    }

}
