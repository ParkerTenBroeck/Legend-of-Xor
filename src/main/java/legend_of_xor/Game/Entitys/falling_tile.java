/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Physics.BasicPhysics;
import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class falling_tile extends Entity {

    Tile tile;

    @Override
    protected void init() {
        isSolid = true;
    }

    public falling_tile(Tile tile, int xPos, int yPos) {
        this.tile = tile;
        phy = new BasicPhysics(this, 0, 0, 0.0091);
        this.xPos = xPos + 0.5;
        this.yPos = yPos + 0.5;
    }

    @Override
    public void update() {
        phy.update();
    }

    @Override
    public BufferedImage getEntityImage() {
        return tile.getTileImage((int) xPos, (int) (yPos));
    }

    @Override
    public boolean terminate() {
        if (Game.getSafeSmallTile((int) Math.floor(hitbox.orgX()), (int)(hitbox.Bottom())).isSolid()) {
            Game.setSmallTile(tile, (int) xPos, (int) (yPos - 0.5));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Origin getOrigin() {
        return Origin.CENTER;
    }
}
