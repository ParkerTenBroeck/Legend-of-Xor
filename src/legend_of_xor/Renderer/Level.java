/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.dirt;
import legend_of_xor.Game.Tiles.explosion;
import legend_of_xor.Game.Tiles.rainbow;
import legend_of_xor.Veiwer.Frame;

/**
 *
 * @author parke
 */
public class Level {

    private static BufferedImage background;
    private static Tile[][] smallTiles;
    private static Tile[][] largeTiles;
    private static Entity[][] entities;
    private static boolean isTopDown = false;

    private static int tilesX;
    private static int tilesY;

    public static int getTilesX() {
        return tilesX;
    }

    public static int getTilesY() {
        return tilesY;
    }

    public static void loadNewLevel(String name) {

        tilesX = 40;
        tilesY = 22;

        Camera.init(0, 0, tilesX, tilesY);

        background = new BufferedImage(Textures.getXRes(), Textures.getYRes(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = background.createGraphics();

        graphics.setColor(new Color(0xFF, 0xFF, 0xFF));
        graphics.fillRect(0, 0, background.getWidth(), background.getHeight());

        graphics.dispose();

        Tile[][] temp = new Tile[22][];

        for (int y = 0; y < 22; y++) {
            temp[y] = new Tile[40];
            for (int x = 0; x < 40; x++) {
                temp[y][x] = new explosion();
            }
        }

        //temp2[6][0] = new Ex_test();
        //temp2[7][0] = null;
        smallTiles = temp;
          //smallTiles = new Tile[][]{{new Dirt()}, {new Ex_test()}, {}, {}};
    }

    public static void setSmallTiles(Tile[][] smallTiles) {
        Level.smallTiles = smallTiles;
    }

    public static BufferedImage getBackgroundImage() {
        return background;
    }

    public static Image getSmallTileImage(int xPos, int yPos) {
        if (smallTiles == null) {
            return Textures.nullImage();
        }
        try {
            return smallTiles[yPos][xPos].getTileImage();
        } catch (Exception e) {
            return Textures.nullImage();
        }
    }
}
