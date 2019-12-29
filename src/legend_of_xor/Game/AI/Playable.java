/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.AI;

/**
 *
 * @author parke
 */
public interface Playable {

    public void up();

    public void down();

    public void left();

    public void right();

    public void jump();

    public void attack();
    
    public void magic();
    
    public void block();
}
