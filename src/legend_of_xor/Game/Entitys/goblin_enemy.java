/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.AI.AI;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Sound;
import legend_of_xor.Physics.BasicSmallTilePhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class goblin_enemy extends Entity {

    private final Physics phy = new BasicSmallTilePhysics(this, 0, 0, 0.02);

    private final AI ai = new AI<goblin_enemy>(this) {
        @Override
        public void update() {
            if (Math.abs(Level.getPlayer().getXPos() - xPos) < 10) {//tracks the player
                trackPlayerAI();
            } else {
                patrolElevationAI();
            }
        }

        private void trackPlayerAI() {
            if ((Level.getPlayer().getXPos() - xPos) > 0) {
                phy.setXVelocity(0.05);
            } else {
                phy.setXVelocity(-0.05);
            }
        }

        private void move() {
            if (rightLeft) {
                phy.setXVelocity(-0.03);
            } else {
                phy.setXVelocity(0.03);
            }
        }

        private void patrolElevationAI() {// lol sorry this is wrong
            if (!rightLeft && !((!Level.getSafeSmallTile((int) (xPos + 0.5), (int) yPos - 1).isSolid()) && Level.getSafeSmallTile((int) (xPos + 0.5), (int) yPos).isSolid())) {// if dif elev on right
                rightLeft = true;
            } else if (rightLeft && !((!Level.getSafeSmallTile((int) (xPos - 0.5), (int) yPos - 1).isSolid()) && Level.getSafeSmallTile((int) (xPos - 0.5), (int) yPos).isSolid())) {// if dif elev on left
                rightLeft = false;
            }

            move();
        }

        @Override
        public AI.Direction getDirection() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private boolean rightLeft = false;

    public goblin_enemy() {
        // image = Textures.getEntityTexture(this);

    }

    goblin_enemy(double xPos, double yPos) {
        Sound.loadSound("/Sounds/goblin.wav");
        Sound.playSound();

        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public void update() {

        phy.update();
        ai.update();

    }
}
