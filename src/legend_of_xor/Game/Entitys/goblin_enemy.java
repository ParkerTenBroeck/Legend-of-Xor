/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package legend_of_xor.Game.Entitys;

import com.sun.javafx.scene.traversal.Direction;
import java.awt.image.BufferedImage;
import legend_of_xor.Game.Entity;
import legend_of_xor.Game.Sound;
import legend_of_xor.Game.Tiles.brick;
import legend_of_xor.Game.Tiles.torch;
import legend_of_xor.Renderer.Camera.Origin;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Renderer.Textures;

/**
 *
 * @author parke
 */
public class goblin_enemy extends Entity {

    private double xVel;
    private double yVel;

    private enum DirectionState {
        RIGHT, LEFT
    }
    private DirectionState AIDirection = DirectionState.RIGHT;
    private int movementNumber = 0;

    public goblin_enemy() {
       // image = Textures.getEntityTexture(this);

    }

    goblin_enemy(double xPos, double yPos) {
        Sound.loadSound("/Sounds/goblin.wav");
        Sound.playSound();

        this.xPos = xPos;
        this.yPos = yPos;
        image = Textures.getEntityTexture(this);
    }

    public void AI() {
        if (Math.abs(Level.getPlayer().getXPos() - xPos) < 10) {//tracks the player
            trackPlayerAI();
        } else {
            //movingLeftAndRightAI();
            patrolElevationAI();
        }

    }

    //----- START OF AI FUNCTIONS ------
    
    public void trackPlayerAI() {
        if ((Level.getPlayer().getXPos() - xPos) > 0) {
            xVel = 0.05;
        } else {
            xVel = -0.05;
        }
    }

    public void move(DirectionState state) {
        
//        if(Level.getSmallTile((int)xPos, (int)yPos).isSolid()){
//            Level.setSmallTile(new brick(), (int)xPos, (int)yPos);
//        }else{
//           Level.setSmallTile(new torch(), (int)xPos, (int)yPos); 
//        }
        
        if (state == DirectionState.LEFT) {
            xVel = -0.03;
        }
        if (state == DirectionState.RIGHT) {
            xVel = 0.03;
        }
    }

    public void patrolElevationAI() {// lol sorry this is wrong
        if (AIDirection == DirectionState.RIGHT && !((!Level.getSmallTile((int) (xPos + 0.5), (int) yPos -1 ).isSolid()) && Level.getSmallTile((int) (xPos + 0.5), (int) yPos).isSolid())) {// if dif elev on right
            AIDirection = DirectionState.LEFT;
        }
        
        else if (AIDirection == DirectionState.LEFT && !((!Level.getSmallTile((int) (xPos - 0.5), (int) yPos -1 ).isSolid()) && Level.getSmallTile((int) (xPos - 0.5), (int) yPos).isSolid())) {// if dif elev on left
            AIDirection = DirectionState.RIGHT;
        }
        
        move(AIDirection);
    }

    public void movingLeftAndRightAI() { // just moving left and right (from -100 to 100 in terms of cycles of movement of 0.03 from the position of the start of this movement.)
        if (AIDirection == DirectionState.RIGHT) {
            move(AIDirection);
            movementNumber++;
            if (movementNumber >= 100) {
                AIDirection = DirectionState.LEFT;
            }
        } else if (AIDirection == DirectionState.LEFT) {
            move(AIDirection);
            movementNumber--;
            if (movementNumber <= -100) {
                AIDirection = DirectionState.RIGHT;
            }
        }
    }

    @Override
    public void update() {
        
        if (Level.getSmallTile((int) xPos, (int) yPos).isSolid()) {
            yPos = (int) yPos;
            yVel = 0;
        } else {
            yVel += 0.01;
        }
        
        AI();
        
        yPos += yVel;
        xPos += xVel;

        while (Level.getSmallTile((int) xPos, (int) (yPos - 1)).isSolid()) {
            if (Level.getSmallTile((int) xPos, (int) (yPos - 1)).isSolid()) {
                yPos = Math.floor(yPos - 1);
            }
        }

    }
}
