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
        double change = 3;
        NoiseGenerator gen = new NoiseGenerator(0.45624);

        double noise = gen.noise(scale);

        for (int x = 0; x < xSize; x++) {

            noise = (gen.noise(scale) + 1) * 6 + ((ySize / 2) - 6);
            scale += change;

            for (int y = ySize - 1; y >= ySize - (int) noise; y--) {

                if (y == (ySize - (int) noise)) {
                    temp[y][x] = new grass();
                    if (Math.random() < 0.2) {
                        try{
                        if(temp[y-1][x-1] == null && temp[y-1][x-2] == null){
                            if(temp[y][x-1] != null && temp[y][x-2] != null ){
                                temp[y - 1][x - 1] = new big_tree();
                            }
                        }else{
                        temp[y - 1][x] = new small_tree();
                        }
                        }catch(Exception e){
                            
                        }
                    } else if (Math.random() < 0.06) {
                        temp[y - 1][x] = new torch();
                    } else {
                        //temp[y-1][x] = new explosion();
                    }
                } else {
                    temp[y][x] = new dirt();
                }
                if ((y - 3) > ((ySize - (int) noise))) {
                    temp[y][x] = new stone();
                    if (Math.random() < 0.04) {
                        temp[y][x] = new ore();
                    }
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
