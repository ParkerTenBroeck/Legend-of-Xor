/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.temp;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class BasicTilePhysics extends Physics {

    public BasicTilePhysics(Entity entity, double initXVel, double initYVel, double gravity) {
        super(entity, true, false);
        this.yVel = initYVel;
        this.xVel = initXVel;
        this.grav = gravity;
    }

    private void checkCollision() {

        resetCollision();

        HitBox[] temp = Game.getSurroundingTileHitBoxes(new temp(entity.getHitBox().orgX(), entity.getHitBox().orgY()));
        for (int i = 0; i < temp.length; i++) {
            try {
                checkCollision(temp[i]);
            } catch (Exception e) {

            }
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

}
