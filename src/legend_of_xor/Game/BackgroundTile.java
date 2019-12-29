/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

import java.awt.image.BufferedImage;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class BackgroundTile {

    protected int topYTileLock = -1;
    protected int bottomYTileLock = -1;

    protected int leftXTileLock = -1;
    protected int rightXTileLock = -1;

    BufferedImage image;

    public BackgroundTile(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public BackgroundTile(String name, int xRes, int yRes) {
        this.image = Textures.getBackgroundTexture(name, xRes, yRes);
    }

    public BackgroundTile setTopYTileLock(int topYTileLock) {
        this.topYTileLock = topYTileLock;
        return this;
    }

    public BackgroundTile setBottomYTileLock(int bottomYTileLock) {
        this.bottomYTileLock = bottomYTileLock;
        return this;
    }

    public BackgroundTile setLeftXTileLock(int leftXTileLock) {
        this.leftXTileLock = leftXTileLock;
        return this;
    }

    public BackgroundTile setRightXTileLock(int rightXTileLock) {
        this.rightXTileLock = rightXTileLock;
        return this;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getXPos(int tileX, int tileY) {

        if (tileX == 0) {
            return 0;
        } else if (tileX > 0) {
            return Game.getBackgroundTile(tileX, tileY).getWidth() + getXPos(tileX - 1, tileY);
        } else {
            return Game.getBackgroundTile(tileX, tileY).getWidth() + getXPos(tileX + 1, tileY);
        }
    }

    public int getYPos(int tileX, int tileY) {
//        System.out.println(image.getHeight() + " " + topYTileLock * Textures.getTileHeight());
//
//        if (topYTileLock != -1 && bottomYTileLock != -1) {
//                  if(Camera.getXPos() + Camera.getCameraTilesX() > bottomYTileLock){
//                      return (int) ((bottomYTileLock + Camera.getYPos() - Camera.getCameraTilesX()) * Textures.getTileHeight());
//                  }else {
//                      
//                  }
//        } else if (topYTileLock != -1) {
//            if(Camera.getXPos())
//            return (int) ((topYTileLock + Camera.getYPos()) * Textures.getTileHeight());
//        } else if (bottomYTileLock != -1) {
//            
//        }

        if (tileY == 0) {
            return 0;
        } else if (tileY > 0) {
            return Game.getBackgroundTile(tileX, tileY).getHeight() + getYPos(tileX, tileY - 1);
        } else {
            return Game.getBackgroundTile(tileX, tileY).getHeight() + getYPos(tileX, tileY + 1);
        }
    }

}
