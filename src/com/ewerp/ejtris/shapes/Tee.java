package com.ewerp.ejtris.shapes;

import org.newdawn.slick.SlickException;

public class Tee extends AbstractShape {
    public Tee() throws SlickException {
        super(new byte[][] { { 'X', 'X', 'X' },
                             { 'O', 'X', 'O'}});
    }
}