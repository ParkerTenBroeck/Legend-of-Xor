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
import legend_of_xor.Game.Physics.BasicTilePhysics;
import legend_of_xor.Game.Physics.HitBox;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class pixel extends Entity {

    final private int pixelX;
    final private int pixelY;
    final private long age;
    private long lifeTime;
    final private int size;
    final private BufferedImage pix;

    @Override
    protected void init() {
        isSolid = false;
        ORIGIN = Origin.UPPER_LEFT;
    }

    public pixel(BufferedImage image, int pixelX, int pixelY, double orgX, double orgY, int size, long lifeTime) {
        age = System.currentTimeMillis();
        this.image = image;
        this.size = size;
        this.pixelX = pixelX;
        this.pixelY = pixelY;
        this.lifeTime = lifeTime;
        this.xPos = ((double) orgX) + (((double) pixelX) * (1.0 / (double) Textures.getTileWidth()));
        this.yPos = ((double) orgY) + (((double) pixelY) * (1.0 / (double) Textures.getTileHeight()));

        TILE_X_SCALE = size / (double) Textures.getTileWidth();
        TILE_Y_SCALE = size / (double) Textures.getTileHeight();

        hitbox = new HitBox(this); // remake hitbox

        pix = image.getSubimage(pixelX, pixelY, size, size);

        phy = new BasicTilePhysics(this, 0, 0, 0.0091);
    }

    @Override
    public void update() {
        phy.update();
    }

    @Override
    public BufferedImage getEntityImage() {
        return pix;
    }

    @Override
    public boolean terminate() {
        return (System.currentTimeMillis() - age) > lifeTime;
    }

    public static void pixilateAndDestroy(Tile tile, int tileX, int tileY, long lifeTime, int lifeTimeVariance) {
        if (tile == null) {
            return;
        }

        int size = Textures.getTileHeight() / 2;
        BufferedImage image = tile.getTileImage(tileX, tileY);

        double orgX = tileX + Renderer.calcTileOrgX(tile.getOrigin(), image) / Textures.getTileWidth();
        double orgY = tileY + Renderer.calcTileOrgY(tile.getOrigin(), image) / Textures.getTileHeight();

        for (int x = 0; x < image.getWidth() / size; x++) {
            for (int y = 0; y < image.getHeight() / size; y++) {
                lifeTime += (long) (((Math.random() * 2) - 1) * lifeTimeVariance);
                Game.addEntity(new pixel(image, x * size, y * size, orgX, orgY, size, lifeTime));
            }
        }
        Game.setSmallTile(null, tileX, tileY);
    }

    public static void pixilateAndDestroy(int tileX, int tileY, long lifeTime, int lifeTimeVariance) {
        pixilateAndDestroy(Game.getSmallTile(tileX, tileY), tileX, tileY, lifeTime, lifeTimeVariance);
    }
}
