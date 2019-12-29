/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Physics;

import java.awt.Dimension;
import java.awt.Point;
import legend_of_xor.Game.Entity;

/**
 *
 * @author parke
 */
public class HitBox {

    public enum Type {
        CIRCLE, BOX, POLLYGON, NULL
    }

    private Entity entity;
    private Point.Double origin;
    public final Type TYPE;

    Point.Double[] points;//only used if a type pollygon
    Dimension dimension;//only used if type box
    double rad;//only used if type circle

    public HitBox(Entity entity, Point.Double[] points, Point.Double origin) {
        this.entity = entity;
        this.points = points;
        this.origin = origin;
        TYPE = Type.POLLYGON;
    }

    public HitBox(Entity entity, Point.Double[] points) {
        this.entity = entity;
        this.points = points;

        double xSum = 0;
        double ySum = 0;
        for (Point.Double point : points) {
            xSum += point.x;
            ySum += point.y;
        }

        this.origin = new Point.Double(xSum / points.length, ySum / points.length);

        TYPE = Type.POLLYGON;
    }

    public HitBox(Entity entity, Dimension dimension, Point.Double origin) {
        this.entity = entity;
        this.dimension = dimension;
        this.origin = origin;
        TYPE = Type.BOX;
    }

    public HitBox(Entity entity, Dimension dimension) {
        this.entity = entity;
        this.dimension = dimension;

        origin = new Point.Double(dimension.width / 2, dimension.height / 2);

        TYPE = Type.BOX;
    }

    public HitBox(Entity entity, double rad, Point.Double origin) {
        this.entity = entity;
        this.origin = origin;
        this.rad = rad;
        TYPE = Type.CIRCLE;
    }

    public HitBox(Entity entity, double rad) {
        this.entity = entity;
        this.rad = rad;

        origin = new Point.Double(rad, rad);

        TYPE = Type.CIRCLE;
    }

    public HitBox() {//no collision;
        TYPE = Type.NULL;
    }
    
    

}
