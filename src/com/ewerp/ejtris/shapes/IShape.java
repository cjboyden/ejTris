package com.ewerp.ejtris.shapes;

import java.awt.Point;
import java.util.List;

import org.newdawn.slick.Graphics;

public interface IShape {
    public enum Orientation {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    
    public void draw(Graphics gc, float x, float y);
    
    public int getLeft();
    public int getRight();
    public void setLeft(int x);
    public int getTop();
    public int getBottom();
    public void setTop(int y);
    
    public int getHeight();
    public int getWidth();
    
    public Orientation getOrientation();
    public void setOrientation(Orientation orientation);
    
    public List<Point> getTranslatedPoints();
    
    public boolean isCollision(IShape shape);
    
    public void rotate();
}
