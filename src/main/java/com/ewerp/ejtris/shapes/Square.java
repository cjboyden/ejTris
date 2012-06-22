package com.ewerp.ejtris.shapes;

import org.newdawn.slick.SlickException;

public class Square extends AbstractShape {
    public Square() throws SlickException {
        super(new byte[][] { { 'X', 'X' },
                             { 'X', 'X' }});
    }
}