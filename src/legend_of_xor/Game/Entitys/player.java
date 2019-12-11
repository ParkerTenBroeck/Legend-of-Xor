/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.AI.Playable;
import legend_of_xor.Controls;
import legend_of_xor.Game.*;
import legend_of_xor.Physics.BasicSmallTilePhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class player extends Entity implements Playable {

    private final Physics phy = new BasicSmallTilePhysics(this, 0, 0, 0.02);

    @Override
    protected void init() {
        TILESX = 3;
        TILESY = 6;

        TILE_X_SCALE = 1.75;
        TILE_Y_SCALE = 1.75;

        xPos = 0;
        yPos = 40;
    }

    public player() {
        image = Textures.getEntityTexture(this);
    }

    @Override
    public BufferedImage getEntityImage() {

        int yOffset = (int) (Textures.getTileHeight() * TILE_Y_SCALE * ((System.currentTimeMillis() / 75) % 6));

        if (phy.getXVelocity() > 0.01) {
            return image.getSubimage(0, yOffset, (int) (Textures.getTileWidth() * TILE_X_SCALE), (int) (Textures.getTileHeight() * TILE_Y_SCALE));
        } else if (phy.getXVelocity() < -0.01) {
            return image.getSubimage((int) (Textures.getTileWidth() * TILE_X_SCALE), yOffset, (int) (Textures.getTileWidth() * TILE_X_SCALE), (int) (Textures.getTileHeight() * TILE_Y_SCALE));
        } else {
            return image.getSubimage((int) (Textures.getTileWidth() * 2 * TILE_X_SCALE), yOffset, (int) (Textures.getTileWidth() * TILE_X_SCALE), (int) (Textures.getTileHeight() * TILE_Y_SCALE));
        }
    }

    @Override
    public synchronized void update() {

        phy.setXVelocity(phy.getXVelocity() * 0.7);
        phy.update();

        if (Controls.isLeftPressed()) {
            left();
        }
        if (Controls.isRightPressed()) {
            right();
        }
        if (Controls.isUpPressed() && phy.onGround()) {
            up();
        }
        if (Controls.getAttackPress()) {
            attack();
        }
        if (Controls.getMagicPress()) {
            magic();
        }
    }

    @Override
    public void up() {
        phy.setYVelocity(-0.4);
    }

    @Override
    public void down() {

    }

    @Override
    public void left() {
        phy.changeXVel(-0.1);
    }

    @Override
    public void right() {
        phy.changeXVel(0.1);
    }

    @Override
    public void jump() {

    }

    @Override
    public void attack() {
        Level.addEntity(new bomb(xPos, yPos - 1.5, phy.getXVelocity() * 1.2, -0.2));
    }

    @Override
    public void magic() {
        if(Math.random() > 0.5){
        Level.addEntity(new egg(xPos, yPos, new goblin_enemy(xPos, yPos)));//new bomb(xPos, yPos - 1.25, xVel * 1.25, -0.25));
        }else{
          Level.addEntity(new egg(xPos, yPos, new goblin_enemy(xPos, yPos)));//new bomb(xPos, yPos - 1.25, xVel * 1.25, -0.25));  
        }
    }

    @Override
    public void block() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
