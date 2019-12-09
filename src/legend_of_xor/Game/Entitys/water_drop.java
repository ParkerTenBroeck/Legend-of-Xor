/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Physics.BasicFallingPhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class water_drop extends Entity {

    private double yVel = 0;

    private boolean hit = false;
    private long timeHit = 0;

    private boolean done;

    private final Physics phy = new BasicFallingPhysics(this, 0, 0, 0.001);

    @Override
    protected void init() {
        TILESX = 1; //how many tiles there are in the sprite sheet
        TILESY = 6;

        TILE_X_SCALE = 1;  //x and y scale of each tile
        TILE_Y_SCALE = 1;

        ORIGIN = Camera.Origin.TOP_CENTER;
    }

    public water_drop() {
        image = Textures.getEntityTexture(this);
    }

    public water_drop(double xPos, double yPos) {
        image = Textures.getEntityTexture(this);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public BufferedImage getTileImage() {
        if (done) {
            return null;
        }
        if (!phy.onGround()) {
            return image.getSubimage(0, 0, image.getWidth(), image.getHeight() / TILESY);
        } else {
            int offset = ((int) ((System.currentTimeMillis() - timeHit) / 100)) % 5;
            offset += 1;
            return image.getSubimage(0, Textures.getTileHeight() * offset, image.getWidth(), image.getHeight() / TILESY);
        }

    }

    @Override
    public void update() {

        phy.update();

        if (phy.onGround() && timeHit == 0) {
            timeHit = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - timeHit) > 5 * 100 && timeHit != 0) {
            done = true;
        }
    }
}
