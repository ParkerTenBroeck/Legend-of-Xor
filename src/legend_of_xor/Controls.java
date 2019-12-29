package legend_of_xor;

import java.awt.event.*;

public class Controls {

    public static boolean isJumpPressed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static enum controlType {
        KEYBOARD, CONTROLLER
    }

    //Keyboard Keys variables
    protected static char jumpKey = ' ', rightKey = 'd', leftKey = 'a',
            upKey = 'w', downKey = 's', attackKey = 'j', blockKey = 'k',
            magicKey = 'i', pauseKey = 27, inventoryKey = 'e';

    //Controller button variables
    private static controlType controlTypeInUse = controlType.KEYBOARD;

    public void setControlType(controlType type) {
        controlTypeInUse = type;
    }

    public static boolean isLeftMousePressed() {
        return KeyboardLisenter.leftMouse;
    }

    public static boolean isRightMousePressed() {
        return KeyboardLisenter.rightMouse;
    }

    public static boolean isMiddlePressed() {
        return KeyboardLisenter.middleMouse;
    }

    public static int getMouseTileX() {
        KeyboardLisenter.calculateMouseTilePos();
        return KeyboardLisenter.mouseXTile;
    }

    public static int getMouseTileY() {
        KeyboardLisenter.calculateMouseTilePos();
        return KeyboardLisenter.mouseYTile;
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

    public static boolean isInventoryActive() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.inventoryActive;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isInventoryPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.inventoryPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isAttackPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.attackPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean isMagicPressed() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.magicPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean getLeftPress() {
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

    public static boolean getRightPress() {
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

    public static boolean getUpPress() {
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

    public static boolean getDownPress() {
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

    public static boolean getInventoryPress() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                return KeyboardLisenter.inventoryPressed;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean getAttackPress() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                boolean temp = KeyboardLisenter.attackPressed;
                KeyboardLisenter.attackPressed = false;
                return temp;
            case CONTROLLER:
                break;
            default:
                return false;
        }
        return false;
    }

    public static boolean getMagicPress() {
        switch (controlTypeInUse) {
            case KEYBOARD:
                boolean temp = KeyboardLisenter.magicPressed;
                KeyboardLisenter.magicPressed = false;
                return temp;
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
