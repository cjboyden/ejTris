package com.ewerp.ejtris.shapes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class AbstractShape implements IShape {
    protected Image image;
    
    protected int pitX;
    protected int pitY;
    
    protected int height;
    protected int width;
    
    protected byte[][]shapeDefinition;
    
    protected Orientation orientation = Orientation.NORTH;
    
    public AbstractShape(byte[][] shape) throws SlickException {
        image = ShapeHelper.createImage(shape);
        height = shape.length;
        width = shape[0].length;
        shapeDefinition = shape;
    }
    
    @Override
    public void draw(Graphics gc, float x, float y) {
        gc.drawImage(image, x, y);
    }
    
    @Override
    public int getLeft() {
        return pitX;
    }
    
    @Override
    public void setLeft(int x) {
        this.pitX = x;
    }
    
    @Override
    public int getTop() {
        return pitY;
    }
    
    @Override
    public void setTop(int y) {
        this. pitY = y;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public int getBottom() {
        // Subtract one translate position into pit coordinates
        return getTop() + getHeight() - 1;
    }
    
    @Override
    public int getWidth() {
        return width;
    }
    
    @Override
    public int getRight() {
        // Subtract one translate position into pit coordinates
        return getLeft() + getWidth() - 1;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }
    
    @Override
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    @Override
    public boolean isCollision(IShape shape) {
        for(Point p1 : getTranslatedPoints()) {
            for(Point p2 : shape.getTranslatedPoints()) {
                if(p1.x == p2.x && p1.y == p2.y) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    protected List<Point> getPoints() {
        List<Point> points = new ArrayList<Point>();
        
        for(int y = 0; y < shapeDefinition.length; y++) {
            for(int x = 0; x < shapeDefinition[y].length; x++) {
                if(shapeDefinition[y][x] == 'X') {
                    points.add(new Point(x, y));
                }
            }
        }
        
        return points;
    }
    
    @Override
    public List<Point> getTranslatedPoints() {
        List<Point> points = getPoints();
        for(Point p : points) {
            p.setLocation(p.getX() + getLeft(), p.getY() + getTop());
        }
        
        return points;
    }
    
    @Override
    public void rotateCounterClockwise() {
        try {
            byte[][] newShapeDefinition = new byte[width][height];
            
            for(int originY = 0; originY < height; originY++) {
                for(int originX = 0; originX < width; originX++) {
                    newShapeDefinition[width - (originX + 1)][originY] = shapeDefinition[originY][originX];
                }
            }
            
            image = ShapeHelper.createImage(newShapeDefinition);
            height = newShapeDefinition.length;
            width = newShapeDefinition[0].length;
            shapeDefinition = newShapeDefinition;
        } catch (SlickException e) {
            //TODO: Log this error or something
            e.printStackTrace();
        }
    }
    
    @Override
    public void rotateClockwise() {
        try {
            byte[][] newShapeDefinition = new byte[width][height];
            
            for(int originY = 0; originY < height; originY++) {
                for(int originX = 0; originX < width; originX++) {
                    newShapeDefinition[originX][height - (originY + 1)] = shapeDefinition[originY][originX];
                }
            }
            
            image = ShapeHelper.createImage(newShapeDefinition);
            height = newShapeDefinition.length;
            width = newShapeDefinition[0].length;
            shapeDefinition = newShapeDefinition;
        } catch (SlickException e) {
            //TODO: Log this error or something
            e.printStackTrace();
        }
    }
}
