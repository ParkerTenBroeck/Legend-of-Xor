/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.Game.Entity;
import legend_of_xor.Legend_of_Xor;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class open_sign extends Entity {


    private boolean terminate= false;
    
    private long lastCheck;
    
    @Override
    protected void init(){
        ORIGIN = Origin.BOTTOM_CENTER;
        TILE_X_SCALE = 0.75;
        TILE_Y_SCALE = 0.75;
    }
    
    public void terminate(Boolean terminate){
        this.terminate = terminate;
        lastCheck = System.currentTimeMillis();
        
    }
    
    public open_sign(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public boolean terminate() {
        return terminate || (System.currentTimeMillis() - 100) > lastCheck;
    }
}
