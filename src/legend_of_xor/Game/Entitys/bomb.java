/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class bomb extends Entity {

    private double yVel = 0;
    private double xVel = 0;

    private boolean hit = false;


    public bomb() {
        image = Textures.getEntityTexture(this);
    }

    @Override
    protected void init() {
        TILESX = 1; //how many tiles there are in the sprite sheet
        TILESY = 1;

        TILE_X_SCALE = 0.75;  //x and y scale of each tile
        TILE_Y_SCALE = 0.75;

        ORIGIN = Camera.Origin.TOP_CENTER;
    }

    public bomb(double xPos, double yPos, double xVel, double yVel) {
        image = Textures.getEntityTexture(this);
        this.yVel = yVel;
        this.xVel = xVel;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public boolean terminate() {
        return hit;
    }

    @Override
    public void update() {
        yVel += 0.01;
        yPos += yVel;
        xPos += xVel;

        if (Level.getSmallTile((int)(xPos), (int) (yPos)).isSolid()) {
            yPos = (int) (yPos);
            hit = true;
            Level.addEntity(new explosion(xPos, yPos, 1.75));
        }
    }
}
