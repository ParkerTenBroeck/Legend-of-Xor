package legend_of_xor;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Level;
import legend_of_xor.Veiwer.Veiwer;

public class KeyboardLisenter extends Controls {

    protected static boolean jumpPressed, rightPressed,
            leftPressed, downPressed, upPressed, attackPressed,
            blockPressed, magicPressed,
            leftMouse, rightMouse, middleMouse;

    protected static int mouseXTile, mouseYTile, mouseXReal, mouseYReal;

    public static void init(JComponent mouseArea, Frame frame) {

        mouseArea.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
                mouseXReal = me.getX();
                mouseYReal = me.getY();
                mouseXTile = (int) (((me.getX() * Level.getTilesX()) / Veiwer.getXSize()) + -Camera.getXPos());
                mouseYTile = (int) (((me.getY() * Level.getTilesY()) / Veiwer.getYSize()) + -Camera.getYPos());
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                mouseXReal = me.getX();
                mouseYReal = me.getY();
                
                double xD = Math.abs(Camera.getXPos() - (int)Camera.getXPos());
                double yD = Math.abs(Camera.getYPos() - (int)Camera.getYPos());
               
                xD = xD * (double)Veiwer.getXSize() / (double)Level.getTilesX();
                yD = yD * (double)Veiwer.getYSize() / (double)Level.getTilesY();
                
             //  System.out.println(xD);
                
                mouseXTile = (int) (((me.getX() * Level.getTilesX()) / Veiwer.getXSize()) + (-Camera.getXPos()) + xD);
                mouseYTile = (int) (((me.getY() * Level.getTilesY()) / Veiwer.getYSize()) + (-Camera.getYPos()) + yD);
            }
        });

        mouseArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {

            }

            @Override
            public void mousePressed(MouseEvent me) {
                switch (me.getButton()) {
                    case 1:
                        leftMouse = true;
                        break;
                    case 2:
                        middleMouse = true;
                        break;
                    case 3:
                        rightMouse = true;
                        break;

                    default:
                }
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                switch (me.getButton()) {
                    case 1:
                        leftMouse = false;
                        break;
                    case 2:
                        middleMouse = false;
                        break;
                    case 3:
                        rightMouse = false;
                        break;

                    default:
                }
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

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
