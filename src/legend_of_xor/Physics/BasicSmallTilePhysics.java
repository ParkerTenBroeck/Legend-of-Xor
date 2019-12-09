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

    public BasicSmallTilePhysics(Entity entity, double initXVel, double initYVel) {
        super(entity);
    }

    @Override
    public boolean onGround() {
        return Level.getSmallTile((int) entity.getXPos(), (int) entity.getYPos() + 1).isSolid();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
