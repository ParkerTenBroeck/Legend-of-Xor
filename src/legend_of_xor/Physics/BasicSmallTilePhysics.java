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
public class BasicSmallTilePhysics extends Physics {

    double grav;

    public BasicSmallTilePhysics(Entity entity, double initXVel, double initYVel, double gravity) {
        super(entity);
        this.yVel = initYVel;
        this.xVel = initXVel;
        this.grav = gravity;
    }

    @Override
    public boolean onGround() {
        return Level.getSafeSmallTile((int) entity.getXPos(), (int) entity.getYPos()).isSolid();
    }

    @Override
    public boolean onCel() {
        return Level.getSafeSmallTile((int) (entity.getXPos()), (int) entity.getYPos() - 1).isSolid();
    }

    @Override
    public boolean onLeftWall() {
        return Level.getSafeSmallTile((int) ((entity.getXPos() - 0.5)), (int) (entity.getYPos() - 0.2)).isSolid();
    }

    @Override
    public boolean onRightWall() {
        return Level.getSafeSmallTile((int) ((entity.getXPos() + 0.5)), (int) (entity.getYPos() - 0.2)).isSolid();
    }

    @Override
    public void update() {

        if (xVel < 0 && onLeftWall()) {
            xVel = 0;
        }
        if (xVel > 0 && onRightWall()) {
            xVel = 0;
        }

        if (onGround()) {
            if (yVel > 0) {
                yVel = 0;
                entity.setYPos((int) entity.getYPos());
            }
        } else if (onCel()) {
            if (yVel < 0) {
                yVel = 0;
            }
            yVel += grav;
        } else {
            yVel += grav;
        }

        entity.changeXPos(xVel);
        entity.changeYPos(yVel);
    }

}
