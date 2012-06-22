package com.ewerp.ejtris.shapes;

import org.newdawn.slick.SlickException;

public class ReverseL  extends AbstractShape {
    public ReverseL() throws SlickException {
        super(new byte[][] { { 'O', 'X' },
                             { 'O', 'X' },
                             { 'X', 'X' }});
    }

}
