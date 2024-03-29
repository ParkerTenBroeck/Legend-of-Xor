/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Renderer;

import Level_Generator.LevelGenerator;
import java.util.ArrayList;
import java.util.logging.Logger;
import legend_of_xor.Game.BackgroundTile;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Entitys.rope;
import legend_of_xor.Game.Level;
import legend_of_xor.Game.Levels.main_level;
import legend_of_xor.Game.Physics.HitBox;
import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.air;
import legend_of_xor.Veiwer.Veiwer;

/**
 *
 * @author parke
 */
public class Game {

    private static boolean pause = false;

    private static BackgroundTile[][] backgroundTiles = new BackgroundTile[1][1];

    private static Tile[][] smallTiles;
    private static Tile[][] aestheticTiles;
    private static final ArrayList<Entity> entities = new ArrayList();
    private static boolean isTopDown = false;

    private static double[] UPS = new double[64];
    private static long[] updateTime = new long[64];

    Level level = new main_level();

    private static int levelTilesX;
    private static int levelTilesY;

    private static Entity player;

    private static long tick = 0;

    public static int getLevelTilesX() {
        return levelTilesX;
    }

    public static int getLevelTilesY() {
        return levelTilesY;
    }

    public static double getUPS() {
        double sum = 0;
        for(double ups : UPS){
            sum += ups;
        }
        return sum / UPS.length;
    }

    public static double getUpdateTime() {
        long sum = 0;
        for(long ups : updateTime){
            sum += ups;
        }
        return sum / updateTime.length;
    }

    public static void start() {

        Game.loadNewLevel("Main");

        Thread update = new Thread() {
            public void run() {

                int index = 0;

                while (true) {
                    long last = System.nanoTime();
                    long newt;

                    if (!pause) {

                        Game.update();
                        newt = System.nanoTime();
                        // if (newt - last > 0.017 * 1_000_000_000) {
                        // System.err.println((double) (newt - last) / 1_000_000_000 + " " + (newt - last));
                        // }
                        try {
                            Thread.sleep((int) ((1000 / 60) - (newt - last) / 1000000));
                        } catch (Exception e) {

                        }
                    } else {
                        newt = System.nanoTime();
                        try {
                            Thread.sleep(80);
                        } catch (Exception e) {

                        }
                    }

                    long newtt = System.nanoTime();
                    updateTime[index] = newt - last;
                    UPS[index] = 1000000000.0 / (newtt - last);
                    index++;
                    if (index >= updateTime.length){
                        index = 0;
                    }
                }
            }
        };
        update.setName("update");
        update.start();
    }

    public static synchronized void update() {

        Thread updateTiles = new Thread() {
            public void run() {

                int xDist = 30;
                int yDist = 50;

                int xPos = (int) (-Camera.getXPos() + (Camera.getCameraTilesX() / 2.0));
                int yPos = (int) (-Camera.getYPos() + (Camera.getCameraTilesY() / 2.0));

                for (int y = yPos + yDist; y >= yPos - yDist; y--) {
                    for (int x = xPos + xDist; x >= xPos - xDist; x--) {
                        try {
                            smallTiles[y][x].update(x, y);
                        } catch (Exception e) {

                        }
                    }
                }
            }
        };
        updateTiles.start();

        if (entities.size() > 0 && entities != null) {
            for (int i = entities.size() - 1; i >= 0; i--) {
                try {
                    entities.get(i).update();
                    if (entities.get(i).terminate()) {
                        entities.get(i).delete();
                        entities.remove(i);
                        //System.out.println(entities.size());
                    }
                } catch (Exception e) {

                }
            }
        }
        Camera.update();
        try {
            updateTiles.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        tick++;
    }

    public static long getTick() {
        return tick;
    }

    public static void load() {
        Camera.init(40, 20);
        loadNewLevel("menu");
    }

    public static void loadNewLevel(String name) {

        levelTilesX = 40 * 10;
        levelTilesY = 22 * 10;

        Camera.init(40, 20);

        backgroundTiles = new BackgroundTile[1][2];

        backgroundTiles[0][0] = new BackgroundTile("main", Textures.getXRes(), (int) (Textures.getYRes())).setTopYTileLock(0).setBottomYTileLock(50);
        //backgroundTiles[1][0] = new BackgroundTile("cave", Textures.getXRes(), Textures.getYRes()).setTopYTileLock(50).setBottomYTileLock(20 * 10);

        LevelGenerator.makeLevel(levelTilesX, levelTilesY);

        Camera.followEntity(player);

        entities.add(new rope(20, player, false));

        Veiwer.refreshImageSize();
    }

    public static void newLevel(int tilesX, int tilesY, Entity player) {
        aestheticTiles = new Tile[tilesY][tilesX];
        smallTiles = new Tile[tilesY][tilesX];
        entities.clear();
        entities.add(player);
        Game.player = player;
    }

    public static Entity getPlayer() {
        return player;
    }

    public static HitBox[] getSurroundingTileHitBoxes(Entity entity) {
        int xPos = (int) entity.getXPos();
        int yPos = (int) entity.getYPos();

        HitBox[] temp = new HitBox[5];

        temp[0] = Game.getSafeSmallTile(xPos, yPos).getHitBox(xPos, yPos);
        temp[1] = Game.getSafeSmallTile(xPos + 1, yPos).getHitBox(xPos + 1, yPos);
        temp[2] = Game.getSafeSmallTile(xPos, yPos + 1).getHitBox(xPos, yPos + 1);
        temp[3] = Game.getSafeSmallTile(xPos - 1, yPos).getHitBox(xPos - 1, yPos);
        temp[4] = Game.getSafeSmallTile(xPos, yPos - 1).getHitBox(xPos, yPos - 1);

        return temp;
    }

    public static void setSmallTile(Tile tile, int xPos, int yPos) {
        try {
            Game.smallTiles[yPos][xPos] = tile;
        } catch (Exception e) {
        }
    }

    public static Tile getSmallTile(int xPos, int yPos) {
        try {
            return smallTiles[yPos][xPos];
        } catch (Exception e) {
            return null;
        }
    }

    public static Tile getSafeSmallTile(int xPos, int yPos) {
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

    public static ArrayList<Entity> getEntities() {
        return entities;
    }

    public static ArrayList<Entity> getEntitiesClone() {
        return (ArrayList<Entity>) entities.clone();
    }

    public static void addEntity(Entity entity) {
        entities.add(entity);
        //System.out.println("new Entity " + entity);
    }

    public static int getBackgroundTilesX() {
        return backgroundTiles[0].length;
    }

    public static int getBackgroundTilesY() {
        return backgroundTiles.length;
    }

    public static BackgroundTile getBackgroundTile(int x, int y) {
        if (y < 0) {
            return getBackgroundTile(x, 0);
        } else if (y >= getBackgroundTilesY()) {
            return getBackgroundTile(x, getBackgroundTilesY() - 1);
        } else if (x < 0) {
            return getBackgroundTile(0, y);
        } else if (x >= getBackgroundTilesX()) {
            return getBackgroundTile(getBackgroundTilesX() - 1, y);
        }
        if (backgroundTiles[y][x] != null) {
            return backgroundTiles[y][x];
        } else {
            if (x != 0) {
                return getBackgroundTile(x - 1, y);
            } else {
                return null;
            }
        }
    }

    public static int getBackgroundTilesWidth() {
        int temp = 0;

        for (int i = 0; i < backgroundTiles[0].length; i++) {
            try {
                temp += getBackgroundTile(i, 0).getWidth();
            } catch (Exception e) {
                return 0;
            }
        }
        return temp;
    }

    public static int getBackgroundTilesHeight() {
        int temp = 0;

        for (int i = 0; i < backgroundTiles.length; i++) {
            try {
                temp += getBackgroundTile(0, i).getHeight();
            } catch (Exception e) {
                return 0;
            }
        }
        return temp;
    }

    public static void setAestheticTile(Tile tile, int x, int y) {
        try {
            aestheticTiles[y][x] = tile;
        } catch (Exception e) {

        }
    }

    public static Tile getSafeAestheticTile(int x, int y) {
        try {
            if (aestheticTiles[y][x] != null) {
                return aestheticTiles[y][x];
            } else {
                return new air();
            }
        } catch (Exception e) {
            return new air();
        }
    }
}
