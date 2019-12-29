/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Physics;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class BasicFallingPhysics extends Physics {

    public BasicFallingPhysics(Entity entity, double initYVel, double initXVel, double gravity) {
        super(entity);
        this.yVel = initYVel;
        this.xVel = initXVel;
        this.grav = gravity;
    }

    @Override
    public boolean onGround() {
        return Game.getSafeSmallTile((int) entity.getXPos(), (int) entity.getYPos()).isSolid();
    }

    @Override
    public void update() {
        if (!onGround()) {
            entity.changeYPos(yVel);
            entity.changeXPos(xVel);
            yVel += grav;
            if (onGround()) {
                entity.setYPos((int)entity.getYPos());
                yVel = 0;
            }
        } else {
            entity.setYPos((int)entity.getYPos());
            yVel = 0;
        }
    }

    @Override
    public boolean onCel() {
        return false;
    }

    @Override
    public boolean onLeftWall() {
        return false;
    }

    @Override
    public boolean onRightWall() {
        return false;
    }

}
