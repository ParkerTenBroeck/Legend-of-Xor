/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level_Generator;

import legend_of_xor.Game.Tile;
import legend_of_xor.Game.Tiles.dirt;
import legend_of_xor.Game.Tiles.rainbow;

/**
 *
 * @author parke
 */
public class LevelGenerator {

    public static Tile[][] makeLevel(int xSize, int ySize) {
        Tile[][] temp = new Tile[ySize][xSize];

        double scale = 0.1;
        NoiseGenerator gen = new NoiseGenerator(0.45624);

        double noise = gen.noise(scale);

        for (int x = 0; x < xSize; x++) {

            noise = (gen.noise(scale) + 1) * 5;
            scale += 3;

            for (int y = ySize - 1; y >= ySize - (int)noise; y--) {

                temp[y][x] = new rainbow();
            }
        }
        return temp;
    }
}
