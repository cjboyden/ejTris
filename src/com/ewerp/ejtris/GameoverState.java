package com.ewerp.ejtris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class GameoverState extends AbstractGameState {

    GameoverState(int stateID, IEJTrisController ejTrisController) {
        super(stateID, ejTrisController);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
    }

    @Override
    public void _render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)) {
            game.enterState(EJTris.MAINMENUSTATE);
        }
    }
    
    public void setBackground(Image background) {
        this.imgBackground = background;
    }

}
