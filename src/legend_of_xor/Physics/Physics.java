/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Physics;

import legend_of_xor.Game.Entity;

/**
 *
 * @author parke
 */
public abstract class Physics {

    protected Entity entity;

    double xVel;
    double yVel;

    public Physics(Entity entity) {
        this.entity = entity;
    }

    public abstract boolean onGround();

    public void setXVelocity(double xVel) {
        this.xVel = xVel;
    }

    public void setYVelocity(double yVel) {
        this.yVel = yVel;
    }

    public void changeXVel(double xVelChange) {
        this.xVel += xVelChange;
    }

    public void changeYVel(double yVelChange) {
        this.yVel += yVelChange;
    }

    public double getXVelocity() {
        return xVel;
    }

    public double getYVelocity() {
        return yVel;
    }

    public abstract void update();
}
