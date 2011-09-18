package com.ewerp.ejtris.pits;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.ewerp.ejtris.IEJTrisController;
import com.ewerp.ejtris.shapes.AbstractShape;
import com.ewerp.ejtris.shapes.IShape;
import com.ewerp.ejtris.shapes.ShapeHelper;

public class BasicPit {
    private int pitX = 200;
    private int pitY = 15;

    private int width = 20;
    private int height = 28;

    private long lastTick = 0;
    private long lastMoveLeftTick = 0;
    private long lastMoveRightTick = 0;

    protected IShape inactiveShape;
    protected final byte[][] inactiveShapeDefinition;

    protected IShape activeShape;
    
    protected IEJTrisController controller;

    public BasicPit(IEJTrisController ejTrisController) throws SlickException {
        inactiveShapeDefinition = new byte[height][width];
        controller = ejTrisController;
        initialize();
    }
    
    protected void initialize() throws SlickException {
        for (int y = 0; y < height; y++) {
            Arrays.fill(inactiveShapeDefinition[y], (byte) 'O');
        }

        inactiveShape = new AbstractShape(inactiveShapeDefinition) {};

        activeShape = ShapeHelper.generateShape();
        lastTick = System.currentTimeMillis();
    }

    public void render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) {
        inactiveShape.draw(graphicsContext, pitX, pitY);
        activeShape.draw(graphicsContext, pitX + (activeShape.getLeft() * ShapeHelper.BLOCK_WIDTH), pitY + (activeShape.getTop() * ShapeHelper.BLOCK_HEIGHT));
    }

    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) {
        long currentTime = System.currentTimeMillis();
        boolean moveDown = false;
        boolean moveLeft = false;
        boolean moveRight = false;

        Input input = gameContainer.getInput();

        if (input.isKeyDown(Input.KEY_DOWN)) {
            if (currentTime - lastTick > 100) {
                moveDown = true;
            }
        } else if(input.isKeyDown(Input.KEY_LEFT)) {
            if (currentTime - lastMoveLeftTick > 200) {
                moveLeft = true;
            }
        } else if(input.isKeyDown(Input.KEY_RIGHT)) {
            if (currentTime - lastMoveRightTick > 200) {
                moveRight = true;
            }
        }

        if (input.isKeyPressed(Input.KEY_DOWN)) {
            // Move the block down
            moveDown = true;
        } else if (input.isKeyPressed(Input.KEY_SPACE)) {
            activeShape.rotateClockwise();
            if(isCollision()) {
                // Undo the rotation, we just rotated into another shape
                activeShape.rotateCounterClockwise();
            }
        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            // Move the block left
            moveLeft = true;
        } else if (input.isKeyPressed(Input.KEY_RIGHT)) {
            // Move the block right
            moveRight = true;
        }

        if (moveLeft) {
            if (activeShape.getLeft() > 0) {
                activeShape.setLeft(activeShape.getLeft() - 1);
                if (isCollision()) {
                    // undo the move, it is not valid
                    activeShape.setLeft(activeShape.getLeft() + 1);
                }
            }
            lastMoveLeftTick = currentTime;
        } else if (moveRight) {
            if (activeShape.getLeft() + activeShape.getWidth() < width) {
                activeShape.setLeft(activeShape.getLeft() + 1);
                if (isCollision()) {
                    // undo the move, it is not valid
                    activeShape.setLeft(activeShape.getLeft() - 1);
                }
            }
            lastMoveRightTick = currentTime;
        }

        if (moveDown || currentTime - lastTick > 1000) {
            // Move block down
            activeShape.setTop(activeShape.getTop() + 1);
            lastTick = currentTime;

            if (isCollision()) {
                // Create a new block
                try {
                    // Back it up to stack
                    activeShape.setTop(activeShape.getTop() - 1);
                    mergeActiveShape();

                    lineTest();

                    activeShape = ShapeHelper.generateShape();

                    if (isCollision()) {
                        // TODO: GAME OVER!!!
                        // Take a screenshot
                        Image screenshot = new Image(gameContainer.getWidth(), gameContainer.getHeight());
                        gameContainer.getGraphics().copyArea(screenshot, 0, 0);
                        screenshot.setAlpha(0.4f);
                        initialize();
                        controller.setGameoverState(screenshot);
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
        if (activeShape.getBottom() >= getHeight()) {
            return true;
        }

        if (activeShape.isCollision(inactiveShape)) {
            return true;
        }

        return false;
    }

    /**
     * Merge the active shape into the inactive shape
     */
    protected void mergeActiveShape() throws SlickException {
        for (Point p : activeShape.getTranslatedPoints()) {
            inactiveShapeDefinition[p.y][p.x] = 'X';
        }

        inactiveShape = new AbstractShape(inactiveShapeDefinition) {
        };
    }

    protected void lineTest() {
        List<Integer> lines = new ArrayList<Integer>();

        for (int y = 0; y < inactiveShapeDefinition.length; y++) {
            for (int x = 0; x <= inactiveShapeDefinition[y].length; x++) {
                if (x == inactiveShapeDefinition[y].length) {
                    // We have a complete line, add it to the list
                    lines.add(y);
                    break;
                }
                if (inactiveShapeDefinition[y][x] != 'X') {
                    break;
                }
            }
        }

        if (lines.size() > 0) {
            // We have matching lines, do something!
            for (Integer i : lines) {
                System.out.println("Line found: " + i);
            }
        }
    }
}
