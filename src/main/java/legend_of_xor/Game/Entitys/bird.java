/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.AI.AI;
import legend_of_xor.Game.AI.Direction;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Livable.LivableBasics;
import legend_of_xor.Game.Physics.BasicTilePhysics;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class bird extends Entity {

    protected enum STATE {
        left, right, leftFlying, rightFlying
    }
    private final AI ai = new AI<bird, STATE, Integer>(this) {

        private boolean flying = false;
        private boolean falling = false;

        private boolean moving = false;

        private Direction direction = Direction.LEFT;
        
        private int birdMaxHealth = 100;
        LivableBasics birdLivable = new LivableBasics(birdMaxHealth);

        @Override
        public void update() {

            if (flying) {
                if (phy.getYVelocity() > -0.04) {

                    phy.changeYVel(-0.003);
                }
                phy.setGravity(0.001);
            }
            if (phy.collidingDown()) {
                phy.setXVelocity(0);
                falling = false;
                if (moving && !flying) {
                    phy.setGravity(0.007);

                    if (phy.collidingLeft()) {
                        phy.setYVelocity(-0.12);
                    } else if (phy.collidingRight()) {
                        phy.setYVelocity(-0.12);
                    } else {
                        phy.setYVelocity(-0.05);
                    }

                }
            }

            if (moving) {
                if (direction == Direction.LEFT) {
                    phy.setXVelocity(-0.05);
                } else if (direction == Direction.RIGHT) {
                    phy.setXVelocity(0.05);
                }

            } else {
                phy.setXVelocity(phy.getXVelocity() * 0.93);
            }

            if (Math.random() > 0.991) {
                moving = !moving;
            }

            if (Math.random() > 0.993) {
                flying = !flying;
                falling = true;
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
        public STATE getState(Integer state) {
            if (flying || falling) {
                if (direction == Direction.LEFT) {
                    return STATE.leftFlying;
                } else {
                    return STATE.rightFlying;
                }
            } else {
                if (direction == Direction.LEFT) {
                    return STATE.left;
                } else {
                    return STATE.right;
                }
            }
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

        phy = new BasicTilePhysics(this, 0, 0, 0.001);
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

        STATE state = (STATE) ai.getState(0);

        int xOffset = (int) (image.getWidth() / TILESX * TILE_X_SCALE * type);

        int yOffset = 0;

        if (state == STATE.leftFlying || state == STATE.rightFlying) {
            yOffset = (int) (1 + ((System.currentTimeMillis() / 100) % 2));
        }

        if (state == STATE.right || state == STATE.rightFlying) {
            yOffset += 3;
        }
        yOffset = (int) (image.getHeight() / TILESY * TILE_Y_SCALE * yOffset);

        return image.getSubimage(xOffset, yOffset,
                (int) (image.getWidth() / TILESX * TILE_X_SCALE),
                (int) (image.getHeight() / TILESY * TILE_Y_SCALE));
    }
}
