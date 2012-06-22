package com.ewerp.ejtris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.ewerp.ejtris.pits.BasicPit;

public class GameplayState extends AbstractGameState {
    protected BasicPit pit = null;

    GameplayState(int stateID, IEJTrisController ejTrisController) {
        super(stateID, ejTrisController);
    }
    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        imgBackground = new Image("images/background.png");
        pit = new BasicPit(controller);
    }

    @Override
    public void _render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
        pit.render(gameContainer, game, graphicsContext);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) throws SlickException {
        pit.update(gameContainer, game, elapsedTime);
    }

}
