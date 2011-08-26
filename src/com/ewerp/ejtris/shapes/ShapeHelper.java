package com.ewerp.ejtris.shapes;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class ShapeHelper {
    public static final int BLOCK_WIDTH = 20;
    public static final int BLOCK_HEIGHT = 20;
    
    public static Image createShape(byte[][] shape) throws SlickException {
        Image image = new Image(shape[0].length * BLOCK_WIDTH, shape.length * BLOCK_HEIGHT);
        
        Graphics gc = image.getGraphics();
        gc.setColor(new Color(0, 255, 0));
        
        for(int y = 0; y < shape.length; y++) {
            for(int x = 0; x < shape[y].length; x++) {
                if(shape[y][x] == 'X' || shape[y][x] == 'x') {
                    gc.fill(new Rectangle(BLOCK_WIDTH * x, BLOCK_HEIGHT * y, 20, 20));
                }
            }
        }
        gc.flush();
        
        return image;
    }
}
