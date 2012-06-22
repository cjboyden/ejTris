package com.ewerp.ejtris;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractGameState extends BasicGameState {
    protected final int stateID;
    protected final IEJTrisController controller;
    
    protected Image imgBackground;
    
    public AbstractGameState(int stateID, IEJTrisController ejTrisController) {
        this.stateID = stateID;
        this.controller = ejTrisController;
    }
    
    @Override
    public int getID() {
        return stateID;
    }
    
    @Override
    public void render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
        graphicsContext.drawImage(imgBackground, 0, 0);
        _render(gameContainer, game, graphicsContext);
    }
    
    public abstract void _render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException;
}