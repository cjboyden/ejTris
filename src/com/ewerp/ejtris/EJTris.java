package com.ewerp.ejtris;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class EJTris extends StateBasedGame {

    public static final int MAINMENUSTATE = 0;
    public static final int GAMEPLAYSTATE = 1;

    public EJTris() {
        super("ejTris - An ewerp Tetris clone");

        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GameplayState(GAMEPLAYSTATE));
        this.enterState(GAMEPLAYSTATE);
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
}