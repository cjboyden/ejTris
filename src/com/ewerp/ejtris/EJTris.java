package com.ewerp.ejtris;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EJTris extends StateBasedGame implements IEJTrisController {

    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;
    public static final int GAMEOVERSTATE = 2;
    
    public final MainMenuState stateMainMenu;
    public final GameplayState stateGameplay;
    public final GameoverState stateGameover;
    
    public EJTris() {
        super("ejTris - An ewerp Tetris clone");

        this.addState(stateMainMenu = new MainMenuState(MAINMENUSTATE, this));
        this.addState(stateGameplay = new GameplayState(GAMEPLAYSTATE, this));
        this.addState(stateGameover = new GameoverState(GAMEOVERSTATE, this));
        this.enterState(MAINMENUSTATE);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(MAINMENUSTATE).init(gameContainer, this);
        this.getState(GAMEPLAYSTATE).init(gameContainer, this);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new EJTris());

        app.setDisplayMode(800, 600, false);
        app.start();
    }

    @Override
    public void setGameoverState(Image screenshot) {
        stateGameover.setBackground(screenshot);
        this.enterState(EJTris.GAMEOVERSTATE);        
    }
}