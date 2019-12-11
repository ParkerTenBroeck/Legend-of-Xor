/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level_Generator;

import legend_of_xor.Game.Entitys.bird;
import legend_of_xor.Game.Entitys.bunny;
import legend_of_xor.Game.Entitys.player;
import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.*;
import legend_of_xor.Renderer.Level;

/**
 *
 * @author parke
 */
public class LevelGenerator {

    public static void makeLevel(int xSize, int ySize) {

        Level.newLevel(xSize, ySize, new player());

        double scale = 0.1;
        double change = 1.5;
        NoiseGenerator gen = new NoiseGenerator((double) System.currentTimeMillis() / 1000000);

        double noise = gen.noise(scale);

        for (int x = 0; x < xSize; x++) {

            noise = (gen.noise(scale) + 1) * 8 + (ySize - 50);
            scale += change;

            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {

                if (y == (ySize - (int) noise)) {
                    Level.setSmallTile(new grass(), x, y);
                    if (Math.random() < 0.2) {
                        try {
                            if (Level.getSmallTile(x - 1, y - 1) == null && Level.getSmallTile(x - 2, y - 1) == null) {
                                if (Level.getSmallTile(x - 1, y) != null && Level.getSmallTile(x - 2, y) != null) {
                                    Level.setSmallTile(new big_tree(), x - 1, y - 1);
                                }
                            } else {
                                Level.setSmallTile(new small_tree(), x, y - 1);
                            }
                        } catch (Exception e) {

                        }
                    } else if (Math.random() < 0.06) {
                        Level.setSmallTile(new chest(), x, y - 1);
                    } else {
                        //temp[y-1][x] = new explosion();
                    }
                } else {
                    Level.setSmallTile(new grass(), x, y);
                }
                if ((y - 3) > ((ySize - (int) noise))) {
                    Level.setSmallTile(new stone(), x, y);
                    Level.setAestheticTile(new background_stone(), x, y);
                }
            }
        }
        gen.setSeed((double) System.currentTimeMillis() / 1000000);
        NoiseGenerator oreGen = new NoiseGenerator((double) System.currentTimeMillis() / 13002040);

        scale = 0.4;
        double oreScale = 4;

        for (int x = 0; x < xSize; x++) {

            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {
                double val = gen.noise(x * scale, y * scale);
                double oreVal = oreGen.noise(x * oreScale, y * oreScale);

                try {
                    if (val > 0.4 && val < 0.5 || (val < -0.2 && val > -0.3)) {

                        if (Level.getSmallTile(x, y).getNameID().equals("stone")) {
                            Level.setSmallTile(null, x, y);
                        }

                    }
                    if (oreVal < -0.6) {
                        if (Level.getSmallTile(x, y).getNameID().equals("stone")) {
                            Level.setSmallTile(new ore(), x, y);
                        }
                    }

                } catch (Exception e) {
                }

            }
        }
        for (int x = 0; x < xSize; x++) {
            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {
                try {
                    if (Level.getSafeSmallTile(x, y).getNameID().equals("stone")
                            && Level.getSafeSmallTile(x + 1, y).getNameID().equals("stone")
                            && Level.getSafeSmallTile(x - 1, y).getNameID().equals("stone")) {

                        boolean empty = true;
                        for (int i = x - 1; i <= x + 1; i++) {
                            for (int j = y + 3; j >= y + 1; j--) {
                                if (Level.getSmallTile(i, j) != null) {

                                    empty = false;
                                }
                            }
                        }
                        if (empty) {
                            if (Math.random() > 0.6) {
                                Level.setSmallTile(new stalactite(), x, y + 1);
                            }
                        }
                    }
                } catch (Exception e) {

                }
            }
        }

        for (int i = 0; i < xSize / 10; i++) {
            Level.addEntity(new bird(Math.random() * xSize, 0));
            Level.addEntity(new bird(Math.random() * xSize, 0));
            Level.addEntity(new bunny(Math.random() * xSize, 0));
        }
        for(int i = 0; i < 1000; i ++){
            Level.update();
        }
    }
}
