/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import java.awt.Dimension;
import java.awt.Point;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Renderer;

/**
 *
 * @author parke
 */
public class HitBox {

    private double width = 0;
    private double height = 0;

    private double xOffset = 0;
    private double yOffset = 0;

    private final double gray = 0.01;
    private final double inset = 0;

    private boolean solid;
    private boolean enabled = true;

    private final double ANGLE_1;
    private final double ANGLE_2;

    private Entity entity;

//    public HitBox(double width, double height, double xOffset, double yOffset, Entity entity) {
//        this.width = width;
//        this.height = height;
//        this.xOffset = xOffset;
//        this.yOffset = yOffset;
//        this.entity = entity;
//
//        ANGLE_1 = Math.atan2(height, width);
//        ANGLE_2 = Math.PI - ANGLE_1;
//    }
//
//    public HitBox(double width, double height, double xOffset, double yOffset) {
//        this.width = width;
//        this.height = height;
//        this.xOffset = xOffset;
//        this.yOffset = yOffset;
//
//        ANGLE_1 = Math.atan2(height, width);
//        ANGLE_2 = Math.PI - ANGLE_1;
//    }
    public HitBox(double width, double height, Entity entity) {
        this.width = width;
        this.height = height;
        this.xOffset = width * Renderer.calcEntityOrgX(entity.getOrigin(), 2, 2) / 2.0;
        this.yOffset = height * Renderer.calcEntityOrgY(entity.getOrigin(), 2, 2) / 2.0;
        this.entity = entity;

        solid = entity.isSolid();
        
        ANGLE_1 = Math.PI / 4;//Math.atan2(height, width);
        ANGLE_2 = Math.PI * 3 / 4;
//        ANGLE_1 = Math.atan2(height, width);
//        ANGLE_2 = Math.PI - ANGLE_1;
    }

    public HitBox(int xPos, int yPos, Tile tile) {
        this.width = 1;
        this.height = 1;
        this.xOffset = 0;//width * (Renderer.calcEntityOrgX(tile.getOrigin(), 2, 2) + 1) / 2.0;
        this.yOffset = 0;//height * (Renderer.calcEntityOrgY(tile.getOrigin(), 2, 2) + 1) / 2.0;
        this.xOffset += xPos;
        this.yOffset += yPos;
        
        solid = true;

        ANGLE_1 = Math.PI / 4;//Math.atan2(height, width);
        ANGLE_2 = Math.PI * 3 / 4;
//        ANGLE_1 = Math.atan2(height, width);
//        ANGLE_2 = Math.PI - ANGLE_1;
    }

    public HitBox(Entity entity) {
        this.width = entity.getXTileScale();
        this.height = entity.getYTileScale();
        // this.width = 8.0 / 24.0;
        //this.height = width;
        //System.out.println(width);
        this.xOffset = width * Renderer.calcEntityOrgX(entity.getOrigin(), 2, 2) / 2.0;
        this.yOffset = height * Renderer.calcEntityOrgY(entity.getOrigin(), 2, 2) / 2.0;
        this.entity = entity;
        
        solid = entity.isSolid();

        ANGLE_1 = Math.PI / 4;//Math.atan2(height, width);
        ANGLE_2 = Math.PI * 3 / 4;
    }

    public double Top() {
        return yOffset + ((entity != null) ? entity.getYPos() : 0);
    }

    public double Bottom() {
        return Top() + height;
    }

    public double Left() {
        return xOffset + ((entity != null) ? entity.getXPos() : 0);
    }

    public double Right() {
        return Left() + width;
    }

    public double orgX() {
        return Left() + (width / 2);
    }

    public double orgY() {
        return Top() + (height / 2);
    }

    public double getXOffset() {
        return xOffset;
    }

    public double getYOffset() {
        return yOffset;
    }

    public boolean Colliding(HitBox b) {
        if (this == b || b == null) {
            return false;
        }
        if (b.Left() > this.Right() - inset) {
            return false;
        }
        if (b.Right() < this.Left() + inset) {
            return false;
        }
        if (b.Bottom() < this.Top() + inset) {
            return false;
        }
        if (b.Top() > this.Bottom() - inset) {
            return false;
        }
        return true;
    }

    public boolean isCollidingUp(HitBox b) {
        if (Colliding(b)) {
            return isCollidingUpPost(b);
        }
        return false;

    }

    public boolean isCollidingDown(HitBox b) {
        if (Colliding(b)) {
            return isCollidingDownPost(b);
        }
        return false;

    }

    public boolean isCollidingLeft(HitBox b) {
        if (Colliding(b)) {
            return isCollidingLeftPost(b);
        }
        return false;

    }

    public boolean isCollidingRight(HitBox b) {
        if (Colliding(b)) {
            return isCollidingRightPost(b);
        }
        return false;

    }

    public boolean isCollidingUpPost(HitBox b) {
        double angle = Math.atan2(b.orgY() - this.orgY(), this.orgX() - b.orgX());
        return -ANGLE_1 - gray >= angle && angle >= -ANGLE_2 + gray;
    }

    public boolean isCollidingDownPost(HitBox b) {
        double angle = Math.atan2(b.orgY() - this.orgY(), this.orgX() - b.orgX());
        return ANGLE_1 + gray <= angle && angle <= ANGLE_2 - gray;
    }

    public boolean isCollidingLeftPost(HitBox b) {
        double angle = Math.atan2(b.orgY() - this.orgY(), this.orgX() - b.orgX());
        return (ANGLE_1 - gray >= angle && angle >= 0) || (angle >= -ANGLE_1 + gray && angle <= 0);
    }

    public boolean isCollidingRightPost(HitBox b) {
        double angle = Math.atan2(b.orgY() - this.orgY(), this.orgX() - b.orgX());
        return (ANGLE_2 + gray <= angle && Math.PI >= angle) || (angle <= -ANGLE_2 - gray && -Math.PI <= angle);
    }

    //scale the angles baced on the width and height in the cunstructor good night
    public double width() {
        return width;
    }

    public double height() {
        return height;
    }
    
    public boolean enabled(){
        return enabled;
    }
    
    public void enabled(boolean state){
        enabled = state;
    }
    
    public boolean isSolid(){
        return solid;
    }

}
