package legend_of_xor;

import java.awt.Frame;
import java.awt.event.*;
import legend_of_xor.Veiwer.Veiwer;

public class KeyboardLisenter extends Controls{

    protected static boolean jumpPressed, rightPressed,
            leftPressed, downPressed, upPressed, attackPressed, blockPressed, magicPressed;

    public static void init(Frame frame) {

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() == Controls.jumpKey) {
                    jumpPressed = true;
                }
                if (ke.getKeyChar() == Controls.rightKey) {
                    rightPressed = true;
                }
                if (ke.getKeyChar() == Controls.upKey) {
                    upPressed = true;
                }
                if (ke.getKeyChar() == Controls.downKey) {
                    downPressed = true;
                }
                if (ke.getKeyChar() == Controls.leftKey) {
                    leftPressed = true;
                }
                if (ke.getKeyChar() == Controls.attackKey) {
                    attackPressed = true;
                }
                if (ke.getKeyChar() == Controls.blockKey) {
                    blockPressed = true;
                }
                if (ke.getKeyChar() == Controls.magicKey) {
                    magicPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyChar() == Controls.jumpKey) {
                    jumpPressed = false;
                }
                if (ke.getKeyChar() == Controls.rightKey) {
                    rightPressed = false;
                }
                if (ke.getKeyChar() == Controls.upKey) {
                    upPressed = false;
                }
                if (ke.getKeyChar() == Controls.downKey) {
                    downPressed = false;
                }
                if (ke.getKeyChar() == Controls.leftKey) {
                    leftPressed = false;
                }
                if (ke.getKeyChar() == Controls.attackKey) {
                    attackPressed = false;
                }
                if (ke.getKeyChar() == Controls.blockKey) {
                    blockPressed = false;
                }
                if (ke.getKeyChar() == Controls.magicKey) {
                    magicPressed = false;
                }
            }
        });
    }

}
