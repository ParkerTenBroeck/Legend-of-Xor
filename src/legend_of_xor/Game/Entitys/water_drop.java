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
public class water_drop implements Entity {

    public static final String NAMEID = water_drop.class.getName().split("\\.")[3];//name of tile must be unique

    private final int TILESX = 1; //how many tiles there are in the sprite sheet
    private final int TILESY = 6;

    private final double TILE_X_SCALE = 1;  //x and y scale of each tile
    private final double TILE_Y_SCALE = 1;

    private final Camera.Origin ORIGIN = Camera.Origin.TOP_CENTER;

    private double xPos;
    private double yPos;
    
    private double yVel = 0;

    private boolean hit = false;
    private long timeHit = 0;

    private boolean done;

    BufferedImage image;

    public water_drop() {
        image = Textures.getEntityTexture(this);
    }

    public water_drop(double xPos, double yPos) {
        image = Textures.getEntityTexture(this);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public String getNameID() {
        return NAMEID;
    }

    @Override
    public BufferedImage getTileImage() {
        if (done) {
            return null;
        }
        if (!hit) {
            return image.getSubimage(0, 0, image.getWidth(), image.getHeight() / TILESY);
        } else {
            int offset = ((int) ((System.currentTimeMillis() - timeHit) / 100)) % 5;
            offset += 1;
            return image.getSubimage(0, Textures.getTileHeight() * offset, image.getWidth(), image.getHeight() / TILESY);
        }

    }

    @Override
    public void update() {
        yVel += 0.006;
        if (timeHit == 0) {
            yPos += yVel;
        }

        if (Level.getSmallTile((int) (xPos), (int) (yPos + 1)).isSolid() && timeHit == 0) {
            yPos = (int) (yPos);
            hit = true;
            timeHit = System.currentTimeMillis();
        }
        if ((System.currentTimeMillis() - timeHit) > 5 * 100 && timeHit != 0) {
            done = true;
        }
    }

    @Override
    public Camera.Origin getOrigin() {
        return ORIGIN;
    }

    @Override
    public double getXScale() {
        return TILESX * TILE_X_SCALE;
    }

    @Override
    public double getYScale() {
        return TILESY * TILE_Y_SCALE;
    }

    @Override
    public double getXPos() {
        return xPos;
    }

    @Override
    public double getYPos() {
        return yPos;
    }

    @Override
    public boolean terminate() {
        return done;
    }
}
