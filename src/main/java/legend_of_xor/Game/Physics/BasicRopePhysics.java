/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import java.util.ArrayList;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.temp;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class BasicRopePhysics extends Physics {

    Entity nextNode;
    Entity lastNode;

    boolean floating = false;
    double maxNodeDistance = 3.5;
    double wantedNodeDistance = 0.5;
    double elasticity = 1;

    double elasticXVel;
    double elasticYVel;
    

    public BasicRopePhysics(Entity currentNode, Entity lastNode, Entity nextNode, double gravity, boolean floating) {
        super(currentNode, true, false);
        this.yVel = 0;
        this.xVel = 0;
        this.grav = gravity;
        this.nextNode = nextNode;
        this.lastNode = lastNode;
        this.floating = floating;
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

        if (floating) {
            return;
        }

        updateElasticVel();

        if (!collidingDown()) {
            yVel += grav;
        }
        xVel *= 0.9;
        yVel *= 0.9;

        entity.changeXPos(elasticXVel);
        entity.changeYPos(elasticYVel);
        entity.changeXPos(xVel);
        entity.changeYPos(yVel);

        checkCollision();
    }

    public void updateElasticVel() {
        double dist = lastNodeDistance();
        if (dist > maxNodeDistance) {
            lastNode = null;
        } else {
            double angle = lastNodeAngle();
            double acc = dist - wantedNodeDistance;
            //acc = acc * acc * acc * acc;
            if (acc < 0.1) {
                acc = 0;
            }
            elasticXVel = -acc * Math.sin(angle) * elasticity;
            elasticYVel = -acc * Math.cos(angle) * elasticity;
            try {
                //lastNode.changeXPos(acc * Math.sin(angle) * elasticity);
                //lastNode.changeYPos(acc * Math.cos(angle) * elasticity);
            } catch (Exception e) {

            }

        }
        dist = nextNodeDistance();
        if (dist > maxNodeDistance) {
            nextNode = null;
        } else {
            double angle = nextNodeAngle();
            double acc = dist - wantedNodeDistance;
            if (acc < 0.1) {
                acc = 0;
            }
            //elasticXVel += -acc * Math.sin(angle) * elasticity;
            //elasticYVel += -acc * Math.cos(angle) * elasticity;

            try {
                nextNode.changeXPos(acc * Math.sin(angle) * elasticity);
                nextNode.changeYPos(acc * Math.cos(angle) * elasticity);
            } catch (Exception e) {

            }
        }

    }

    private double lastNodeAngle() {
        if (lastNode == null) {
            return 0;
        } else {
            return Math.atan2(entity.getXPos() - lastNode.getXPos(), entity.getYPos() - lastNode.getYPos());
        }
    }

    private double nextNodeAngle() {
        if (nextNode == null) {
            return 0;
        } else {
            return Math.atan2(entity.getXPos() - nextNode.getXPos(), entity.getYPos() - nextNode.getYPos());
        }
    }

    private double lastNodeDistance() {
        if (lastNode == null) {
            return 0;
        } else {
            double xDist = entity.getXPos() - lastNode.getXPos();
            double yDist = entity.getYPos() - lastNode.getYPos();
            return Math.sqrt((xDist * xDist) + (yDist * yDist));
        }
    }

    private double nextNodeDistance() {
        if (nextNode == null) {
            return 0;
        } else {
            double xDist = entity.getXPos() - nextNode.getXPos();
            double yDist = entity.getYPos() - nextNode.getYPos();
            return Math.sqrt((xDist * xDist) + (yDist * yDist));
        }
    }

    @Override
    protected void leftCollision(HitBox hitbox) {
        if (xVel < 0) {
            xVel = 0;
        }
        if (elasticXVel < 0) {
            elasticXVel = 0;
        }
    }

    @Override
    protected void rightCollision(HitBox hitbox) {
        if (xVel > 0) {
            xVel = 0;
        }
        if (elasticXVel > 0) {
            elasticXVel = 0;
        }
    }

    @Override
    protected void topCollision(HitBox hitbox) {
        if (yVel < 0) {
            yVel = 0;
        }
        if (elasticXVel < 0) {
            elasticXVel = 0;
        }
    }

    @Override
    protected void bottomCollision(HitBox hitbox) {
        if (yVel > 0) {
            yVel = 0;
        }
        if (elasticYVel > 0) {
            elasticYVel = 0;
        }
    }
}
