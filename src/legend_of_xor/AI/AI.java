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
public abstract class AI<T> {

    protected final T entity;
    
    public enum Direction{UP, DOWN, LEFT, RIGHT, STILL, FALLING, RISING}

    public AI(T entity) {
        this.entity = entity;
    }

    /**
     *
     */
    public abstract void update();
    
    public abstract Direction getDirection();
}
