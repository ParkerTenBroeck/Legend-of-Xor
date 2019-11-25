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
public class Ex_test implements Tile {

    BufferedImage image;
    private static final String NAME_ID = "ex";
    double frame = 0;

    public Ex_test() {
        image = Textures.getBlockTexture(this);
    }

    @Override
    public String getNameID() {
        return NAME_ID;
    }

    @Override
    public BufferedImage getTileImage() {

        int xSize = image.getWidth();
        int ySize = (int) (image.getWidth() * 2.5);
        frame += 0.1;
        if (frame > 24) {
            frame = 0;
        }
        return image.getSubimage(0, (int) Math.floor(frame) * ySize, xSize, ySize);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getYScale() {
        return 60;
    }

    @Override
    public double getXScale() {
        return 1;
    }

}
