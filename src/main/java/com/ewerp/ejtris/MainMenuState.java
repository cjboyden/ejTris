package com.ewerp.ejtris;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends AbstractGameState {
    
    MainMenuState(int stateID, IEJTrisController ejTrisController) {
        super(stateID, ejTrisController);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame game) throws SlickException {
        imgBackground = new Image(800, 600);

        Graphics gc = imgBackground.getGraphics();
        gc.setColor(new Color(0, 255, 0));

        gc.drawString("ejTris", 370, 30);

        gc.drawString("Press <enter> to continue", 300, 100);

        gc.flush();
    }

    @Override
    public void _render(GameContainer gameContainer, StateBasedGame game, Graphics graphicsContext) throws SlickException {
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame game, int elapsedTime) throws SlickException {
        if (gameContainer.getInput().isKeyPressed(Input.KEY_ENTER)) {
            game.enterState(EJTris.GAMEPLAYSTATE);
        }
    }

}
