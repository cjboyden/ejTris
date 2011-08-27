package com.ewerp.ejtris.pits;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.ewerp.ejtris.shapes.IShape;
import com.ewerp.ejtris.shapes.OneXSix;
import com.ewerp.ejtris.shapes.ReverseL;
import com.ewerp.ejtris.shapes.ShapeHelper;

public class BasicPit {
    private int pitX = 200;
    private int pitY = 15;
    
    private int width = 20;
    private int height = 28;
    
    private long lastTick = 0;
    
    protected List<IShape> inactiveShapes = new ArrayList<IShape>();
    
    protected IShape activeShape = null;
    
    public BasicPit() throws SlickException {
        activeShape = new ReverseL();
        lastTick = System.currentTimeMillis();
    }
    
    public void render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) {
        activeShape.draw(graphicsContext, pitX + (activeShape.getLeft() * ShapeHelper.BLOCK_WIDTH), pitY + (activeShape.getTop() * ShapeHelper.BLOCK_HEIGHT));
        for(IShape block : inactiveShapes) {
            block.draw(graphicsContext, pitX + (block.getLeft() * ShapeHelper.BLOCK_WIDTH), pitY + (block.getTop() * ShapeHelper.BLOCK_HEIGHT));
        }
    }
    
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) {
        long currentTime = System.currentTimeMillis();
        boolean moveDown = false;
        
        Input input = gameContainer.getInput();

        if (input.isKeyPressed(Input.KEY_SPACE)) {
            // Rotate the block
        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            // Move the block down
            moveDown = true;
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            // Move the block left
            if(activeShape.getLeft() > 0) {
                activeShape.setLeft(activeShape.getLeft() - 1);
                if(isCollision()) {
                    // undo the move, it is not valid
                    activeShape.setLeft(activeShape.getLeft() + 1);
                }
            }
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            // Move the block right
            if(activeShape.getLeft() + activeShape.getWidth() < width) {
                activeShape.setLeft(activeShape.getLeft() + 1);
                if(isCollision()) {
                    // undo the move, it is not valid
                    activeShape.setLeft(activeShape.getLeft() - 1);
                }
            }
        }
        
        if(moveDown || currentTime - lastTick > 1000) {
            // Move block down
            activeShape.setTop(activeShape.getTop() + 1);
            lastTick = currentTime;
            
            if(isCollision()) {
                // Create a new block
                try {
                    // Back it up to stack
                    activeShape.setTop(activeShape.getTop() - 1);
                    inactiveShapes.add(activeShape);
                    activeShape = new OneXSix();
                    
                    if(isCollision()) {
                        // GAME OVER!!!
                    }
                    
                } catch (SlickException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected int getHeight() {
        return height;
    }
    
    protected boolean isCollision() {
        if(activeShape.getBottom() >= getHeight()) { 
            return true;
        }
        
        for(IShape shape : inactiveShapes) {
            if(activeShape.isCollision(shape)) {
                return true;
            }
        }
        
        return false;
    }
}
