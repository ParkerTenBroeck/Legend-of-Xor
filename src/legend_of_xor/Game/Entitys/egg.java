/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Controls;
import legend_of_xor.Game.Entity;
import legend_of_xor.Physics.BasicFallingPhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class egg extends Entity {

    private final int eggType;

    private final Entity entity;

    private final Physics phy = new BasicFallingPhysics(this, 0, 0, 0.01);
    private long start;

    @Override
    protected void init() {

        TILESY = 4;
        ORIGIN = Origin.BOTTOM_CENTER;
    }

    public egg(double xPos, double yPos, Entity entity) {
        start = System.currentTimeMillis() + (long)(Math.random() * 10000) - 5000;
        eggType = (int) (Math.random() * 4);
        this.entity = entity;
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public boolean terminate() {

        double disX = Math.abs(Level.getPlayer().getXPos() - xPos);
        double disY = Math.abs(Level.getPlayer().getYPos() - yPos);
        long time = System.currentTimeMillis() - start;

        if (time > 10000) {
            entity.setXPos(xPos);
            entity.setYPos(yPos - 0.1);
            //Level.addEntity(new explosion(xPos, yPos, 1.5));
            Level.addEntity(entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update() {
        phy.update();
    }

    @Override
    public BufferedImage getEntityImage() {
        return image.getSubimage(0, eggType * image.getWidth(), image.getWidth(), image.getHeight() / TILESY);
    }
}
