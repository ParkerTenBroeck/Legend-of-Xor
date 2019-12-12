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
public abstract class Physics {

    protected Entity entity;

    protected double xVel;
    protected double yVel;
    protected double grav;

    public Physics(Entity entity) {
        this.entity = entity;
    }

    public abstract boolean onGround();

    public abstract boolean onCel();

    public abstract boolean onLeftWall();

    public abstract boolean onRightWall();

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
    
    public void setGravity(double grav){
        this.grav = grav;
    }
    public double getGravity(){
        return grav;
    }

    public abstract void update();
}
