/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import java.util.Random;
import legend_of_xor.AI.AI;
import legend_of_xor.AI.Playable;
import legend_of_xor.Game.Entity;
import legend_of_xor.Physics.BasicFallingPhysics;
import legend_of_xor.Physics.BasicSmallTilePhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class bunny extends Entity {

    protected final Physics phy = new BasicSmallTilePhysics(this, 0, 0, 0.01);

    private final AI ai = new AI<bunny>(this) {

        public boolean direaction;

        @Override
        public void update() {

            Random ran = new Random(System.currentTimeMillis());

            if (Math.random() > 0.993) {
                if (phy.onGround()) {
                    phy.setYVelocity(-0.2);
                    rightLeft = Math.random() > 0.5;
                }

            }
            if (!phy.onGround()) {
                if (rightLeft) {
                    phy.setXVelocity(0.15);
                } else {
                    phy.setXVelocity(-0.15);
                }
            } else {
                phy.setXVelocity(0);
            }
        }

        @Override
        public AI.Direction getDirection() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    protected boolean rightLeft = Math.random() > 0.5;

    @Override
    protected void init() {
        TILESY = 5;
        TILESX = 2;

    }

    public bunny(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public void update() {
        ai.update();
        phy.update();
    }

    @Override
    public BufferedImage getEntityImage() {
        if (rightLeft) {
            return image.getSubimage(image.getWidth() / 2, 0, image.getWidth() / 2, image.getWidth() / 2);
        } else {
            return image.getSubimage(0, 0, image.getWidth() / 2, image.getWidth() / 2);
        }

    }
}
