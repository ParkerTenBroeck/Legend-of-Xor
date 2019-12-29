/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import java.util.Random;
import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class explosion extends Entity {

    private long START;
    private final double RAD;

    @Override
    protected void init() {
        TILESX = 1;
        TILESY = 24;

        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 2.385;
        ORIGIN = Camera.Origin.BOTTOM_CENTER;
    }

    public explosion(double xPos, double yPos, double radius) {
        image = Textures.getEntityTexture(this);
        START = System.currentTimeMillis();
        RAD = radius;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void setXPos(double xPos) {
        this.xPos = xPos;
        START = System.currentTimeMillis();
    }

    @Override
    public void setYPos(double yPos) {
        this.yPos = yPos;
        START = System.currentTimeMillis();
    }

    @Override
    public boolean terminate() {
        return (System.currentTimeMillis() - START) > 50 * 23;
    }

    @Override
    public void update() {
        Random ran = new Random();
        if ((System.currentTimeMillis() - START) < 20) {
            for (int i = 0; i < (int) (RAD * RAD * 60); i++) {
                Game.setSmallTile(null, (int) (((ran.nextGaussian()) * RAD) + xPos), (int) (((ran.nextGaussian()) * RAD) + yPos));
            }

            for (Entity entity : Game.getEntities()) {
                if (entity.getPhysics() != null) {
                    double dis = entity.distance(this);
                    if (dis <= (RAD * 3)) {
                        double xChange = entity.getXPos() - xPos;
                        double yChange = entity.getYPos() - yPos;

                        double th = Math.atan2(yChange, xChange);

                        entity.getPhysics().changeXVel(Math.cos(th) * RAD / 2);

                        entity.getPhysics().changeYVel(Math.sin(th) * RAD / 2);

                    }
                }

            }
        }
    }

    @Override
    public BufferedImage getEntityImage() {

        int xSize = (int) (Textures.getTileWidth() * TILE_X_SCALE);
        int ySize = (int) (Textures.getTileHeight() * TILE_Y_SCALE);

        return image.getSubimage(0, (int) (((System.currentTimeMillis() - START) / 50) % 24) * ySize, xSize, ySize);
    }
}
