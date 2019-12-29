/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Levels.LevelLoader;

import legend_of_xor.Game.Tile;
import legend_of_xor.Renderer.Game;

/**
 *
 * @author parke
 */
public class LevelLoader {

    public static void loadNewLevel(String name) {

        //String className = "";
        
        
        Object object = null;
        try {
            Class classDefinition = Class.forName(name);
            object = classDefinition.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(e);
        }
        Game.setSmallTile((Tile)object, 0, 0);
    }

}
