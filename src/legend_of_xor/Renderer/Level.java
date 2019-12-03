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
import legend_of_xor.Game.Entitys.water_drop;
import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.air;
import legend_of_xor.Game.Tiles.grass;

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

    private static int levelTilesX;
    private static int levelTilesY;
    
    private static Entity player;

    public static int getLevelTilesX() {
        return levelTilesX;
    }

    public static int getLevelTilesY() {
        return levelTilesY;
    }

    public static synchronized void update() {

        for (int y = levelTilesY - 1; y >= 0; y--) {
            for (int x = levelTilesX - 1; x >= 0; x--) {
                try {
                    smallTiles[y][x].update(x, y);
                } catch (Exception e) {

                }
            }
        }

        if (entities.size() > 0 && entities != null) {
            for (int i = entities.size() - 1; i >= 0; i--) {
                entities.get(i).update();
                if (entities.get(i).terminate()) {
                    entities.remove(i);
                }
            }
        }
    }

    public static void loadNewLevel(String name) {

        levelTilesX = 40 * 10;
        levelTilesY = 22 * 10;

        Camera.init(0, 0, 40, 20);

        background = new BufferedImage(Textures.getXRes(), Textures.getYRes(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = background.createGraphics();

        graphics.setColor(new Color(0x89CFF0));
        graphics.fillRect(0, 0, background.getWidth(), background.getHeight());

        graphics.dispose();

        smallTiles = LevelGenerator.makeLevel(levelTilesX, levelTilesY);

        entities.clear();
        player = new player();
        entities.add(player);
        Camera.followEntity(player);

    }

    public static void setSmallTiles(Tile[][] smallTiles) {
        Level.smallTiles = smallTiles;
    }
    public static Entity getPlayer(){
        return player;
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
        return (ArrayList<Entity>) entities.clone();
    }

    public static void addEntity(Entity entity) {
        entities.add(entity);
    }
}
