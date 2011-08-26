package com.ewerp.ejtris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.ewerp.ejtris.pits.BasicPit;

public class GameplayState extends BasicGameState {

    int stateID = -1;

    protected Image background = null;
    
    protected BasicPit pit = null;

    GameplayState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        background = new Image("images/background.png");
        pit = new BasicPit();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
        background.draw(0, 0);
        pit.render(gameContainer, game, graphicsContext);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) throws SlickException {
        pit.update(gameContainer, game, elapsedTime);
    }

}
