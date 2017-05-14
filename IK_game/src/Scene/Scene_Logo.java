package Scene;

import java.awt.Color;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import Game.Game;
import Window.MainWindow;

public class Scene_Logo extends GlobalScene
{
	private int timer = 0;
	
	public Scene_Logo()
	{
		super();
	}

	@Override
	public void draw(MainWindow g)
	{
		g.setColorF(1,0,0,1);
		g.fillRect((int)(this.realWidth()/180d)*timer, (int)(this.realHeight()/180d)*timer, 50, 50);
	}

	@Override
	public void logic()
	{
		timer++;
		if(timer >= 180)
		{
			Game.theGame.setScene(new Scene_Load());
		}
	}
	
	@Override
	public void onKeyPress(int key, String name)
	{
		if(key == Keyboard.KEY_RETURN || key == Keyboard.KEY_SPACE) Game.theGame.setScene(new Scene_Load());
		super.onKeyPress(key, name);
	}
}
