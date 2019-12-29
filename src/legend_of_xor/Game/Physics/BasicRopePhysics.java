/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class BasicRopePhysics extends legend_of_xor.Physics.Physics {

    Entity nextNode;
    Entity lastNode;

    boolean floating = false;
    double maxNodeDistance = 3.5;
    double wantedNodeDistance = 0.3;
    double elasticity = 1;

    double elasticXVel;
    double elasticYVel;

    public BasicRopePhysics(Entity currentNode, Entity lastNode, Entity nextNode, double gravity, boolean floating) {
        super(currentNode);
        this.yVel = 0;
        this.xVel = 0;
        this.grav = gravity;
        this.nextNode = nextNode;
        this.lastNode = lastNode;
        this.floating = floating;
    }

    @Override
    public boolean onGround() {
        if (entity.getOrigin() == Camera.Origin.UPPER_LEFT) {
            return Game.getSafeSmallTile((int) entity.getXPos(), (int) (entity.getYPos() + 1)).isSolid();
        } else if (entity.getOrigin() == Camera.Origin.CENTER) {
            return Game.getSafeSmallTile((int) (entity.getXPos() - 0.5), (int) (entity.getYPos() + 0.5)).isSolid();
        }

        return Game.getSafeSmallTile((int) entity.getXPos(), (int) entity.getYPos()).isSolid();
    }

    @Override
    public boolean onCel() {
        return Game.getSafeSmallTile((int) (entity.getXPos()), (int) entity.getYPos() - 1).isSolid();
    }

    @Override
    public boolean onLeftWall() {
        return Game.getSafeSmallTile((int) ((entity.getXPos() - 0.5)), (int) (entity.getYPos() - 0.2)).isSolid();
    }

    @Override
    public boolean onRightWall() {
        return Game.getSafeSmallTile((int) ((entity.getXPos() + 0.5)), (int) (entity.getYPos() - 0.2)).isSolid();
    }

    @Override
    public void update() {

        if (floating) {
            return;
        }

        updateElasticVel();

        if (onLeftWall()) {
            if (xVel < 0) {
                xVel = 0;
            }
            if (elasticXVel < 0) {
                elasticYVel = lastNodeDistance() / (elasticity + wantedNodeDistance);
                elasticXVel = 0;
            }
        }
        if (onRightWall()) {
            if (xVel > 0) {
                xVel = 0;
            }
            if (elasticXVel > 0) {
                elasticYVel = -lastNodeDistance() / (elasticity + wantedNodeDistance);
                elasticXVel = 0;
            }
        }

        if (onGround()) {
            if (yVel > 0) {
                yVel = 0;
                entity.setYPos((int) entity.getYPos());
            }
            if (elasticYVel > 0) {
                elasticYVel = 0;
            }
        } else if (onCel()) {
            if (yVel < 0) {
                yVel = 0;
            }
            if (elasticXVel < 0) {
                elasticXVel = 0;
            }
            yVel += grav;
        } else {
            yVel += grav;
        }
        yVel *= 0.9;
        xVel *= 0.9;
        entity.changeXPos(xVel + elasticXVel);
        entity.changeYPos(yVel + elasticYVel);
    }

    public void updateElasticVel() {
        double dist = lastNodeDistance();
        if (dist > maxNodeDistance) {
            lastNode = null;
        } else {
            double angle = lastNodeAngle();
            double acc = dist - wantedNodeDistance;
            if (acc < 0) {
                acc = 0;
            }
            elasticXVel = -acc * Math.sin(angle) * elasticity;
            elasticYVel = -acc * Math.cos(angle) * elasticity;
        }

    }

    private double lastNodeAngle() {
        if (lastNode == null) {
            return 0;
        } else {
            return Math.atan2(entity.getXPos() - lastNode.getXPos(), entity.getYPos() - lastNode.getYPos());
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
}
