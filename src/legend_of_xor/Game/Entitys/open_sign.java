/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Tiles.chest;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class open_sign extends Entity {


    private boolean terminate= false;
    
    @Override
    protected void init(){
        ORIGIN = Origin.BOTTOM_CENTER;
    }
    
    public void terminate(Boolean terminate){
        this.terminate = terminate;
    }
    
    public open_sign(double xPos, double yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    @Override
    public boolean terminate() {
        return terminate;
    }
}
