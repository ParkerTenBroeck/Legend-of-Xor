/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer.Layers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Physics.HitBox;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class HitBoxLayer extends Layer {

    public static boolean showHitboxes = false;

    @Override
    public void drawLayer() {
        if (!showHitboxes)
            return;

        Graphics g2d = image.getGraphics().create();

        int xTileOffset = 0 - (int) Math.floor(Camera.getXPos());
        int yTileOffset = 0 - (int) Math.floor(Camera.getYPos());

        int xPixelOffset = (int) ((Camera.getXPos() - Math.floor(Camera.getXPos())) * Textures.getTileWidth());
        int yPixelOffset = (int) ((Camera.getYPos() - Math.floor(Camera.getYPos())) * Textures.getTileHeight());
        //System.out.println(Level.getEntities().size());

        for (Entity entity : Game.getEntitiesClone()) {
            try {
                if (entity.isSolid()) {
                    g2d.setColor(Color.RED);
                } else {
                    g2d.setColor(Color.BLUE);
                }
                HitBox hitbox = entity.getHitBox();

                g2d.drawRect((int) ((hitbox.Left() - xTileOffset) * Textures.getTileWidth() + xPixelOffset),
                        (int) ((hitbox.Top() - yTileOffset) * Textures.getTileHeight() + yPixelOffset),
                        (int) (Textures.getTileWidth() * hitbox.width()),
                        (int) (Textures.getTileHeight() * hitbox.height()));

                g2d.setColor(Color.YELLOW);

                g2d.drawLine((int) ((hitbox.orgX() - xTileOffset) * Textures.getTileWidth() + xPixelOffset),
                        (int) ((hitbox.orgY() - yTileOffset) * Textures.getTileHeight() + yPixelOffset),
                        (int) ((hitbox.Right() - xTileOffset) * Textures.getTileWidth() + xPixelOffset),
                        (int) ((hitbox.Top() - yTileOffset) * Textures.getTileHeight() + yPixelOffset));

            } catch (Exception e) {

            }
        }
    }
}
