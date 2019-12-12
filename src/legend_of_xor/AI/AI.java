/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.AI;


/**
 *
 * @author parke
 * @param <T>
 * entity type
 * 
 * @param <S>
 * user state
 * 
 * @param <R>
 * return type
 * 
 */
public abstract class AI<T, R, S> {

    protected final T entity;

    public AI(T entity) {
        this.entity = entity;
    }

    /**
     *
     */
    public abstract void update();
    
    public abstract R getState(S state);
}
