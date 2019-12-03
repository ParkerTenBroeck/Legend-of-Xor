/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level_Generator;

import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.*;

/**
 *
 * @author parke
 */
public class LevelGenerator {

    public static Tile[][] makeLevel(int xSize, int ySize) {
        Tile[][] temp = new Tile[ySize][xSize];

        double scale = 0.1;
        double change = 1.5;
        NoiseGenerator gen = new NoiseGenerator((double) System.currentTimeMillis() / 1000000);

        double noise = gen.noise(scale);

        for (int x = 0; x < xSize; x++) {

            noise = (gen.noise(scale) + 1) * 8 + (ySize - 50);
            scale += change;

            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {

                if (y == (ySize - (int) noise)) {
                    temp[y][x] = new grass();
                    if (Math.random() < 0.2) {
                        try {
                            if (temp[y - 1][x - 1] == null && temp[y - 1][x - 2] == null) {
                                if (temp[y][x - 1] != null && temp[y][x - 2] != null) {
                                    temp[y - 1][x - 1] = new big_tree();
                                }
                            } else {
                                temp[y - 1][x] = new small_tree();
                            }
                        } catch (Exception e) {

                        }
                    } else if (Math.random() < 0.06) {
                        temp[y - 1][x] = new torch();
                    } else {
                        //temp[y-1][x] = new explosion();
                    }
                } else {
                    temp[y][x] = new grass();
                }
                if ((y - 3) > ((ySize - (int) noise))) {
                    temp[y][x] = new stone();
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

                        if (temp[y][x].getNameID().equals("stone")) {
                            temp[y][x] = null;
                        }

                    }
                    if (oreVal < -0.6) {
                        if (temp[y][x].getNameID().equals("stone")) {
                            temp[y][x] = new ore();
                        }
                    }

                } catch (Exception e) {
                }

            }
        }
        for (int x = 0; x < xSize; x++) {
            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {
                try{
                if (temp[y][x].getNameID().equals("stone")
                        && temp[y][x + 1].getNameID().equals("stone")
                        && temp[y][x - 1].getNameID().equals("stone")) {
                    
                    boolean empty = true;
                  for(int i = x -1; i <= x + 1; i++){
                      for(int j = y + 3; j >= y + 1; j --){
                          if(temp[j][i] != null){
                              empty = false;
                          }
                      }
                  }
                  if(empty){
                      if(Math.random() > 0.6){
                      temp[y + 1][x] = new stalactite();
                      }
                  }
                }
                }catch(Exception e){
                    
                }
            }
        }

        return temp;
    }

    public static void tree(Tile[][] tiles, int xPos, int yPos) {//x and y are the bottom trunk

        try {
            tiles[yPos][xPos] = new wood();
            tiles[yPos - 1][xPos] = new wood();
            tiles[yPos - 2][xPos] = new wood();
            tiles[yPos - 3][xPos] = new wood();

            tiles[yPos - 3][xPos] = new leaf();
            tiles[yPos - 3][xPos + 1] = new leaf();
            tiles[yPos - 3][xPos - 1] = new leaf();
            tiles[yPos - 3][xPos + 2] = new leaf();
            tiles[yPos - 3][xPos - 2] = new leaf();
            tiles[yPos - 2][xPos + 1] = new leaf();
            tiles[yPos - 2][xPos - 1] = new leaf();
            tiles[yPos - 2][xPos + 2] = new leaf();
            tiles[yPos - 2][xPos - 2] = new leaf();

            tiles[yPos - 4][xPos + 1] = new leaf();
            tiles[yPos - 4][xPos] = new leaf();
            tiles[yPos - 4][xPos - 1] = new leaf();
        } catch (Exception e) {

        }

    }
}
