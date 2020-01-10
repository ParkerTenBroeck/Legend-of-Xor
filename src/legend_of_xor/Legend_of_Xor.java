package legend_of_xor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import legend_of_xor.Game.Entitys.temp;
import legend_of_xor.Game.Physics.HitBox;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;

/**
 *
 * @author parke
 */
public class Legend_of_Xor {

    public static void main(String[] args) {
        //Textures.enableShit(0.5);

        Renderer.init();
        Game.load();
        Game.start();
        Renderer.start();

    }

}
