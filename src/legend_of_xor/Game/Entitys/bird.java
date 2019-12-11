/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.AI.AI;
import legend_of_xor.AI.AI.Direction;
import legend_of_xor.Game.Entity;
import legend_of_xor.Physics.BasicSmallTilePhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class bird extends Entity {

    private final Physics phy = new BasicSmallTilePhysics(this, 0, 0, 0.001);
    private final AI ai = new AI<bird>(this) {

        private boolean flying = false;

        private boolean moving = false;

        private Direction direction = Direction.LEFT;

        @Override
        public void update() {

            if (flying) {
                if (phy.getYVelocity() > -0.04) {
                    phy.changeYVel(-0.003);
                }
            }
            if (moving) {
                if (direction == Direction.LEFT) {
                    phy.setXVelocity(-0.05);
                } else if (direction == Direction.RIGHT) {
                    phy.setXVelocity(0.05);
                }
            }

            if (Math.random() > 0.991) {
                moving = !moving;
            }

            if (Math.random() > 0.993) {
                flying = !flying;
            }
            if (Math.random() > 0.993) {
                if (direction == Direction.RIGHT) {
                    direction = Direction.LEFT;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.RIGHT;
                }
            }
        }

        @Override
        public AI.Direction getDirection() {
            return direction;

        }

    };

    private final int type;

    public bird(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        type = (int) (Math.random() * 4);
        image = Textures.getEntityTexture(this);
    }

    @Override
    protected void init() {
        TILESY = 6;
        TILESX = 4;

        TILE_X_SCALE = 1;
        TILE_Y_SCALE = 1;
    }

    public bird() {
        type = (int) (Math.random() * 4);
        image = Textures.getEntityTexture(this);
    }

    @Override
    public void update() {
        phy.update();
        ai.update();
    }

    @Override
    public BufferedImage getEntityImage() {

        int xOffset = (int) (image.getWidth() / TILESX * TILE_X_SCALE * type);

        int yOffset = 0;

        if (phy.onGround()) {
            yOffset = 0;
        } else {
            yOffset = (int) (1 + ((System.currentTimeMillis() / 100) % 2));
        }

        if (ai.getDirection() == Direction.RIGHT) {
            yOffset += 3;
        }
        yOffset = (int) (image.getHeight() / TILESY * TILE_Y_SCALE * yOffset);

        return image.getSubimage(xOffset, yOffset,
                (int) (image.getWidth() / TILESX * TILE_X_SCALE),
                (int) (image.getHeight() / TILESY * TILE_Y_SCALE));
    }
}
