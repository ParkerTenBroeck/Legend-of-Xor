package legend_of_xor;

import java.awt.Frame;
import java.awt.event.*;
import javax.swing.JComponent;
import javax.swing.event.MouseInputListener;
import legend_of_xor.Renderer.Camera;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Veiwer.Veiwer;

public class KeyboardLisenter extends Controls {

    protected static boolean jumpPressed, rightPressed,
            leftPressed, downPressed, upPressed, attackPressed,
            blockPressed, magicPressed, inventoryActive, inventoryPressed,
            leftMouse, rightMouse, middleMouse,
            pausePressed;

    protected static int mouseXTile, mouseYTile, mouseXReal, mouseYReal;

    protected static JComponent comp;

    public static void init(JComponent mouseArea, Frame frame) {

        KeyboardLisenter.comp = mouseArea;

        mouseArea.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
                mouseXReal = me.getX();
                mouseYReal = me.getY();

            }

            @Override
            public void mouseMoved(MouseEvent me) {
                mouseXReal = me.getX();
                mouseYReal = me.getY();

                double xD = Math.abs(Camera.getXPos() - (int) Camera.getXPos());
                double yD = Math.abs(Camera.getYPos() - (int) Camera.getYPos());

                xD = xD * (double) Veiwer.getXSize() / (double) Game.getLevelTilesX();
                yD = yD * (double) Veiwer.getYSize() / (double) Game.getLevelTilesY();

                //  System.out.println(xD);
                mouseXTile = (int) (((me.getX() * Game.getLevelTilesX()) / Veiwer.getXSize()) + (-Camera.getXPos()) + xD);
                mouseYTile = (int) (((me.getY() * Game.getLevelTilesY()) / Veiwer.getYSize()) + (-Camera.getYPos()) + yD);
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
                if (ke.getKeyChar() == Controls.inventoryKey) {
                    inventoryActive = !inventoryActive;
                }
                if(ke.getKeyChar() == Controls.pauseKey){
//                    if(Legend_of_Xor.isPaused()){
//                        Legend_of_Xor.unpause();
//                    }else {
//                        Legend_of_Xor.pause();
//                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.jumpKey) {
                    jumpPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.rightKey) {
                    rightPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.upKey) {
                    upPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.downKey) {
                    downPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.leftKey) {
                    leftPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.attackKey) {
                    attackPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.blockKey) {
                    blockPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.magicKey) {
                    magicPressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.pauseKey) {
                    pausePressed = true;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.inventoryKey) {
                    inventoryPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.jumpKey) {
                    jumpPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.rightKey) {
                    rightPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.upKey) {
                    upPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.downKey) {
                    downPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.leftKey) {
                    leftPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.attackKey) {
                    attackPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.blockKey) {
                    blockPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.magicKey) {
                    magicPressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.pauseKey) {
                    pausePressed = false;
                }
                if (Character.toLowerCase(ke.getKeyChar()) == Controls.inventoryKey) {
                    inventoryPressed = false;
                }
            }
        });
    }

    protected static void calculateMouseTilePos() {

        try {

            double mouseX = comp.getMousePosition().x * (double) Camera.getCameraTilesX() / (double) Veiwer.getXSize();
            double mouseY = comp.getMousePosition().y * (double) Camera.getCameraTilesY() / (double) Veiwer.getYSize();

            mouseXTile = (int) (mouseX + -Camera.getXPos());
            mouseYTile = (int) (mouseY + -Camera.getYPos());

        } catch (Exception e) {
            mouseXTile = -1;
            mouseYTile = -1;
        }
    }

}
