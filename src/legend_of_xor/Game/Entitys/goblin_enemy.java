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
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class goblin_enemy extends Entity {

    private final AI ai = new AI<goblin_enemy, Boolean, Integer>(this) {
        @Override
        public void update() {
            if (Math.abs(Game.getPlayer().getXPos() - xPos) < 10) {//tracks the player
                trackPlayerAI();
            } else {
                patrolElevationAI();
            }
        }

        private void trackPlayerAI() {
            if ((Game.getPlayer().getXPos() - xPos) > 0) {
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
            if (!rightLeft && !((!Game.getSafeSmallTile((int) (xPos + 0.5), (int) yPos - 1).isSolid()) && Game.getSafeSmallTile((int) (xPos + 0.5), (int) yPos).isSolid())) {// if dif elev on right
                rightLeft = true;
            } else if (rightLeft && !((!Game.getSafeSmallTile((int) (xPos - 0.5), (int) yPos - 1).isSolid()) && Game.getSafeSmallTile((int) (xPos - 0.5), (int) yPos).isSolid())) {// if dif elev on left
                rightLeft = false;
            }

            move();
        }

        @Override
        public Boolean getState(Integer state) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    private boolean rightLeft = false;

    @Override
    protected void init() {
        phy = new BasicSmallTilePhysics(this, 0, 0, 0.02);
    }

    public goblin_enemy() {
        image = Textures.getEntityTexture(this);

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
