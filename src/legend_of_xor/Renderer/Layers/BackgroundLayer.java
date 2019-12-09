/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Graphics;
import legend_of_xor.Game.BackgroundTile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Layers.Layer;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class BackgroundLayer extends Layer {

    @Override
    public void drawLayer() {

        int tilesWidth = Level.getBackgroundTilesWidth() * (Level.getBackgroundTilesX() - 1);
        int tilesHeight = Level.getBackgroundTilesHeight() * (Level.getBackgroundTilesY() - 1);

        int offsetX = (int) (Camera.getXPos() / ((double) (Level.getLevelTilesX() - Camera.getCameraTilesX())) * tilesWidth) / (Level.getBackgroundTilesX());
        int offsetY = (int) (Camera.getYPos() / ((double) (Level.getLevelTilesY() - Camera.getCameraTilesY())) * tilesHeight) / (Level.getBackgroundTilesY());

        for (int y = 0; y < Level.getBackgroundTilesY(); y++) {
            for (int x = 0; x < Level.getBackgroundTilesX(); x++) {

                BackgroundTile tile = Level.getBackgroundTile(x, y);

                image.getGraphics().drawImage(tile.getImage(),
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
