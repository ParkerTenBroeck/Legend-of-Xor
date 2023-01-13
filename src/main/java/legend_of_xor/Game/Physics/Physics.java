/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public abstract class Physics {

    protected Entity entity;

    protected double xVel;
    protected double yVel;
    protected double grav;

    protected boolean colliding;
    protected boolean collidingDown;
    protected boolean collisingUp;
    protected boolean collidingLeft;
    protected boolean collidingRight;

    public final boolean TILE_INTERACTION;
    public final boolean ENTITY_INTERACTION;

    public Physics(Entity entity, boolean tileInteraction, boolean entityInteraction) {
        this.entity = entity;
        TILE_INTERACTION = tileInteraction;
        ENTITY_INTERACTION = entityInteraction;
    }
    
    public boolean colliding(){
        return colliding;
    }

    public boolean collidingDown() {
        return collidingDown;
    }

    public boolean collidingUp() {
        return collisingUp;
    }

    public boolean collidingLeft() {
        return collidingLeft;
    }

    public boolean collidingRight() {
        return collidingRight;
    }

    public void resetCollision() {
        colliding = false;
        collidingDown = false;
        collidingLeft = false;
        collidingRight = false;
        collisingUp = false;
    }

    public void checkCollision(HitBox hitbox) {
        if (this.entity.getHitBox().Colliding(hitbox)) {
            colliding = true;
            if (this.entity.getHitBox().isCollidingLeftPost(hitbox)) {
                collidingLeft = true;
                entity.setXPos(-entity.getHitBox().getXOffset() + hitbox.Right());
                leftCollision(hitbox);
                return;
            }
            if (this.entity.getHitBox().isCollidingRightPost(hitbox)) {
                collidingRight = true;
                entity.setXPos(-entity.getHitBox().getXOffset() - entity.getHitBox().width() + hitbox.Left());
                rightCollision(hitbox);
                return;
            }
            if (this.entity.getHitBox().isCollidingUpPost(hitbox)) {
                collisingUp = true;
                entity.setYPos(-entity.getHitBox().getYOffset() + hitbox.Bottom());
                topCollision(hitbox);
                return;
            }

            if (this.entity.getHitBox().isCollidingDownPost(hitbox)) {
                collidingDown = true;
                entity.setYPos(-entity.getHitBox().getYOffset() - entity.getHitBox().height() + hitbox.Top());
                bottomCollision(hitbox);
                return;
            }
        }
    }

    protected abstract void leftCollision(HitBox hitbox);

    protected abstract void rightCollision(HitBox hitbox);

    protected abstract void topCollision(HitBox hitbox);

    protected abstract void bottomCollision(HitBox hitbox);

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

    public void setGravity(double grav) {
        this.grav = grav;
    }

    public double getGravity() {
        return grav;
    }

    public abstract void update();
}
