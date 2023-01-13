package legend_of_xor.Renderer.Layers;

import legend_of_xor.Game.Entity;
import legend_of_xor.Renderer.Game;
import legend_of_xor.Renderer.Renderer;

import java.awt.*;

public class DebugText extends Layer{

    public static boolean showDebugText = true;
    private Graphics frame_graphics = null;
    private int position = 0;

    @Override
    public void drawLayer() {
        if (!showDebugText)
            return;

        this.position = 10;
        this.frame_graphics = image.getGraphics();
        frame_graphics.setColor(Color.BLACK);

        drawString(String.format("FPS: %.2f", Renderer.FPS()));
        drawString(String.format("UPS: %.2f", Game.getUPS()));
        drawString(String.format("Frame Time: %.3f ms", Renderer.frameTimes() / 1000000.0));
        drawString(String.format("Update Time: %.3f ms", Game.getUpdateTime() / 1000000.0));
        drawString("Game Tick: " + Game.getTick());
        
        Entity e = Game.getPlayer();
        drawString(String.format("Plater -> x: %.2f, y:%.2f", e.getXPos(), e.getYPos()));
        drawString("Entities: " + Game.getEntities().size());
        drawString("Level tiles: x: " + Game.getLevelTilesX() + "  y: " + Game.getLevelTilesY());

        frame_graphics = null;
    }

    public void drawString(String str){
        frame_graphics.drawString(str, 0, position);
        position += 10;
    }
}
