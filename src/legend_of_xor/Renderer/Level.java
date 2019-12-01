/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import Level_Generator.LevelGenerator;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.player;
import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.air;

/**
 *
 * @author parke
 */
public class Level {

    private static BufferedImage background;
    private static Tile[][] smallTiles;
    private static Tile[][] largeTiles;
    private static final ArrayList<Entity> entities = new ArrayList();
    private static boolean isTopDown = false;

    private static int tilesX;
    private static int tilesY;

    public static int getTilesX() {
        return tilesX;
    }

    public static int getTilesY() {
        return tilesY;
    }

    public static void update() {
        for (Entity entity : entities) {
            entity.update();
        }
    }

    public static void loadNewLevel(String name) {

        tilesX = 40;
        tilesY = 22;

        Camera.init(0, 0, tilesX, tilesY);

        background = new BufferedImage(Textures.getXRes(), Textures.getYRes(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = background.createGraphics();

        graphics.setColor(new Color(0x89CFF0));
        graphics.fillRect(0, 0, background.getWidth(), background.getHeight());

        graphics.dispose();

        entities.clear();
        Entity player = new player();
        entities.add(player);
        Camera.followEntity(player);

        smallTiles = LevelGenerator.makeLevel(tilesX * 10, tilesY * 10);

    }

    public static void setSmallTiles(Tile[][] smallTiles) {
        Level.smallTiles = smallTiles;
    }

    public static BufferedImage getBackgroundImage() {
        return background;
    }

    public static Tile getSmallTile(int xPos, int yPos) {
        try {
            if (smallTiles[yPos][xPos] != null) {
                return smallTiles[yPos][xPos];
            } else {
                return new air();
            }
        } catch (Exception e) {
            return new air();
        }
    }

    public static Image getSmallTileImage(int xPos, int yPos) {
        Tile temp = getSmallTile(xPos, yPos);
        if (temp != null) {
            return temp.getTileImage(xPos, yPos);
        }
        return null;
    }

    static void setSmallTiles(Tile tile, int xPos, int yPos) {
        try {
            smallTiles[yPos][xPos] = tile;
        } catch (Exception e) {

        }
    }

    static ArrayList<Entity> getEntities() {
        return entities;
    }
}
