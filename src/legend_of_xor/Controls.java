package legend_of_xor;

import java.awt.event.*;

public class Controls {

    private static enum controlType {KEYBOARD, CONTROLLER}
    
    //Keyboard Keys variables
    private static char jumpKey = 'w', rightKey = 'd', leftKey = 'a', attackKey = 'j', blockKey = 'k', magicKey = ' ';
            
    
    //Controller button variables
    
    private static controlType controlTypeInUse = controlType.CONTROLLER;
    
    public void setControlType(controlType type){
        controlTypeInUse = type;
    }
    
    public static boolean isLeftPressed(){
        switch(controlTypeInUse){
            case KEYBOARD:
                
                break;
            case CONTROLLER:
                break;
            default: return false;
        }
        return false;
    }
    
    //---------METHODS FOR USER CHANGING AND GETTING THE CONTROLS------------
    public static void setJumpKey(char key){
        jumpKey = key;
    }
    
    public static void setRightKey(char key){
        rightKey = key;
    }
    
    public static void setLeftKey(char key){
        leftKey = key;
    }
    
    public static void setAttackKey(char key){
        attackKey = key;
    }
    
    public static void setBlockKey(char key){
        blockKey = key;
    }
    
    public static void setMagicKey(char key){
        magicKey = key;
    }
    
    public static char getJumpKey(){
        return jumpKey;
    }
    
    public static char getRightKey(){
        return rightKey;
    }
    
    public static char getLeftKey(){
        return leftKey;
    }
    
    public static char getAttackKey(){
        return attackKey;
    }
    
    public static char getBlockKey(){
        return blockKey;
    }
    
    public static char getMagicKey(){
        return jumpKey;
    }
}
