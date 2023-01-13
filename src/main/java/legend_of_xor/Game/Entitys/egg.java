/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Physics.BasicTilePhysics;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class egg extends Entity {

    private final int eggType;

    private final Entity entity;
    private long start;

    
    
    @Override
    protected void init() {

        TILESY = 4;
        ORIGIN = Origin.BOTTOM_CENTER;
        isSolid = true;
        phy = new BasicTilePhysics(this, 0, 0, 0.01);
    }

    public egg(double xPos, double yPos, Entity entity) {
        start = System.currentTimeMillis() + (long)(Math.random() * 10000) - 5000;
        eggType = (int) (Math.random() * 4);
        this.entity = entity;
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
        //System.out.println(hitbox.width());
    }

    @Override
    public boolean terminate() {

        long time = System.currentTimeMillis() - start;

        if (time > 10000) {
            entity.setXPos(xPos);
            entity.setYPos(yPos - 0.1);
            //Level.addEntity(new explosion(xPos, yPos, 1.5));
            Game.addEntity(entity);
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
