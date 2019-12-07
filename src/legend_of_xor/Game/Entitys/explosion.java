/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class explosion extends Entity {

    private final long START;

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 24;

        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 2.385;
        ORIGIN = Camera.Origin.BOTTOM_CENTER;
    }

    public explosion(double xPos, double yPos) {
        START = System.currentTimeMillis();
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public boolean terminate() {
        return (System.currentTimeMillis() - START) > 50 * 23;
    }

    @Override
    public void update() {
        if ((System.currentTimeMillis() - START) < 20) {
            for(int i = 0; i <= 5; i ++){
            Level.setSmallTile(null, (int)(((Math.random() - 0.5) * 5) + xPos),(int)(((Math.random() - 0.5) * 5) + yPos));
            }
        }
    }

    @Override
    public BufferedImage getTileImage() {

        int xSize = (int) (Textures.getTileWidth() * TILE_X_SCALE);
        int ySize = (int) (Textures.getTileHeight() * TILE_Y_SCALE);

        return image.getSubimage(0, (int) (((System.currentTimeMillis() - START) / 50) % 24) * ySize, xSize, ySize);
    }
}
