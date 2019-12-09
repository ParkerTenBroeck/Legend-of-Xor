/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Physics;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Level;

/**
 *
 * @author parke
 */
public class BasicFallingPhysics extends Physics {

    private final double grav;

    public BasicFallingPhysics(Entity entity, double initYVel, double initXVel, double gravity) {
        super(entity);
        this.yVel = initYVel;
        this.xVel = initXVel;
        this.grav = gravity;
    }

    @Override
    public boolean onGround() {
        return Level.getSafeSmallTile((int) entity.getXPos(), (int) entity.getYPos() + 1).isSolid();
    }

    @Override
    public void update() {
        if (!onGround()) {
            entity.changeYPos(yVel);
            entity.changeXPos(xVel);
            yVel += grav;
        }
    }

}
