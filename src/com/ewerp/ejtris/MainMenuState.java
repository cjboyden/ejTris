package com.ewerp.ejtris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {

    int stateID = -1;

    MainMenuState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) throws SlickException {
        // TODO Auto-generated method stub

    }

}
