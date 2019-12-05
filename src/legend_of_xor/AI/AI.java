/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.AI;

import legend_of_xor.Game.Entity;

/**
 *
 * @author parke
 */
public abstract class AI {
    Entity entity;
    
   public AI(Entity entity){
       this.entity = entity;
   }
   /**
    * 
    */
   public abstract void update();
}

