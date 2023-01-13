/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.Game.Entity;

/**
 *
 * @author parke
 */
public class temp extends Entity{
    public temp(double xPos, double yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    @Override
    public boolean terminate(){
        return true;
    }
}
