package legend_of_xor;

import java.awt.Frame;
import java.awt.event.*;
import legend_of_xor.Veiwer.Veiwer;

public class KeyboardLisenter {

    private static boolean jumpPressed = false, rightPressed = false, leftPressed = false, attackPressed = false, blockPressed = false, magicPressed = false;

    public static void init(Frame frame) {

        frame.addKeyListener(new KeyListener() {
            @Override

            public void keyTyped(KeyEvent ke) {

            }

            @Override
            public void keyPressed(KeyEvent ke) {
                System.out.println(ke.getKeyChar());
                if (ke.getKeyChar() == Controls.getJumpKey()) {
                    jumpPressed = true;
                }
                if (ke.getKeyChar() == Controls.getRightKey()) {
                    rightPressed = true;
                }
                if (ke.getKeyChar() == Controls.getLeftKey()) {
                    leftPressed = true;
                }
                if (ke.getKeyChar() == Controls.getAttackKey()) {
                    attackPressed = true;
                }
                if (ke.getKeyChar() == Controls.getBlockKey()) {
                    blockPressed = true;
                }
                if (ke.getKeyChar() == Controls.getMagicKey()) {
                    magicPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyChar() == Controls.getJumpKey()) {
                    jumpPressed = false;
                }
                if (ke.getKeyChar() == Controls.getRightKey()) {
                    rightPressed = false;
                }
                if (ke.getKeyChar() == Controls.getLeftKey()) {
                    leftPressed = false;
                }
                if (ke.getKeyChar() == Controls.getAttackKey()) {
                    attackPressed = false;
                }
                if (ke.getKeyChar() == Controls.getBlockKey()) {
                    blockPressed = false;
                }
                if (ke.getKeyChar() == Controls.getMagicKey()) {
                    magicPressed = false;
                }
            }
        });
    }

}
