/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Physics.BasicRopePhysics;
import legend_of_xor.Physics.BasicFallingPhysics;
import legend_of_xor.Physics.BasicSmallTilePhysics;
import legend_of_xor.Physics.Physics;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class rope extends Entity {

    @Override
    protected void init() {
        TILE_X_SCALE = 0.5;
        TILE_Y_SCALE = 0.5;
    }

    public rope(int length, Entity lastNode, boolean floating) {
        xPos = lastNode.getXPos();
        yPos = lastNode.getYPos();
        Entity nextNode = null;
        if (length > 0) {
            nextNode = new rope(length - 1, this, false);
            Game.addEntity(nextNode);
        }
        phy = new BasicRopePhysics(this, lastNode, nextNode, 0.02, floating);
        image = Textures.getEntityTexture(this);

    }

    @Override
    public void update() {
        phy.update();

    }
}
