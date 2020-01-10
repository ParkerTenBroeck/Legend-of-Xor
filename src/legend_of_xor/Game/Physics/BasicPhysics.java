/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import java.util.ArrayList;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.temp;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class BasicPhysics extends Physics {

    public BasicPhysics(Entity entity, double initXVel, double initYVel, double gravity) {
        super(entity, true, true);
        this.yVel = initYVel;
        this.xVel = initXVel;
        this.grav = gravity;
    }

    private void checkCollision() {

        colliding = false;
        collidingDown = false;
        collidingLeft = false;
        collidingRight = false;
        collisingUp = false;

        ArrayList<Entity> entities = Game.getEntities();
        for (int i = entities.size() - 1; i >= 0; i--) {
            Entity entity = entities.get(i);
            if (entity.getHitBox() != null && entity.isSolid()) {

                checkCollision(entity.getHitBox());

            }
        }
        HitBox[] temp = Game.getSurroundingTileHitBoxes(new temp(entity.getHitBox().orgX(), entity.getHitBox().orgY()));
        for (int i = 0; i < temp.length; i++) {
            try {
                checkCollision(temp[i]);
            } catch (Exception e) {

            }
        }
    }

    @Override
    protected void leftCollision(HitBox hitbox) {
        if (xVel < 0) {
            xVel = 0;
        }
    }

    @Override
    protected void rightCollision(HitBox hitbox) {
        if (xVel > 0) {
            xVel = 0;
        }
    }

    @Override
    protected void topCollision(HitBox hitbox) {
        if (yVel < 0) {
            yVel = 0;
        }
    }

    @Override
    protected void bottomCollision(HitBox hitbox) {
        if (yVel > 0) {
            yVel = 0;
        }
    }

    @Override
    public void update() {

        checkCollision();

        entity.changeXPos(xVel);
        entity.changeYPos(yVel);

        if (!collidingDown()) {
            yVel += grav;
        }
    }

}
