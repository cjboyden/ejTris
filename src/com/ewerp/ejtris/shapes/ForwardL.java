package com.ewerp.ejtris.shapes;

import org.newdawn.slick.SlickException;

public class ForwardL extends AbstractShape {
    public ForwardL() throws SlickException {
        super(new byte[][] { { 'X', 'O' },
                             { 'X', 'O' },
                             { 'X', 'O' },
                             { 'X', 'X' }});
    }
}
