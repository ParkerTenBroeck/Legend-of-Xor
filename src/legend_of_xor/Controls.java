package legend_of_xor;

import java.awt.event.*;

public class Controls {

    private static enum controlType {
        KEYBOARD, CONTROLLER
    }

    //Keyboard Keys variables
    protected static char jumpKey = ' ', rightKey = 'd', leftKey = 'a', upKey = 'w', downKey = 's', attackKey = 'j', blockKey = 'k', magicKey = 0;

    //Controller button variables
    private static controlType controlTypeInUse = controlType.KEYBOARD;

    public void setControlType(controlType type) {
        controlTypeInUse = type;
    }

    public static boolean isLeftPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.leftPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isRightPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.rightPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isLeftMousePressed() {
        return KeyboardLisenter.leftMouse;
    }

    public static boolean isRightMousePressed() {
        return KeyboardLisenter.rightMouse;
    }

    public static int getMouseTileX() {
        return KeyboardLisenter.mouseXTile;
    }

    public static int getMouseTileY() {
        return KeyboardLisenter.mouseYTile;
    }

    public static boolean isUpPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.upPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isDownPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.downPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    //---------METHODS FOR USER CHANGING AND GETTING THE CONTROLS------------
    public static void setJumpKey(char key) {
        jumpKey = key;
    }

    public static void setRightKey(char key) {
        rightKey = key;
    }

    public static void setLeftKey(char key) {
        leftKey = key;
    }

    public static void setAttackKey(char key) {
        attackKey = key;
    }

    public static void setBlockKey(char key) {
        blockKey = key;
    }

    public static void setMagicKey(char key) {
        magicKey = key;
    }
}
