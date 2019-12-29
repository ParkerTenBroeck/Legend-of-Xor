/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game;

/**
 *
 * @author parke
 */
public abstract class Level {

    public final String LEVELID = "";

    public abstract void update();

    public boolean objectiveComplete() {
        return false;
    }

    public String nextLevel() {
        return "main_level";
    }

}
