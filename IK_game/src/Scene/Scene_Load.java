package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import Game.Game;
import Resources.LanguageLoader;
import Resources.SoundLoader;
import Resources.TextureLoader;
import Window.DoubleBuffer;

public class Scene_Load extends GlobalScene
{
	private loader l = new loader();
	private String progress = "";
	
	public Scene_Load()
	{
		super();
		l.start();
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("Loading resources", 10, 20);
		g.drawString(progress, 10, 40);
	}

	@Override
	public void logic()
	{
		if(l.progress == 0) progress = "Loading Textures";
		if(l.progress == 1) progress = "Loading Sounds";
		if(l.progress == 2) progress = "Loading Languages";
		if(l.progress == 3)
		{
			Game.theGame.theDoubleBuffer.setScene(new Scene_Login());
		}
	}
}

class loader extends Thread
{
	int progress = 0;
	
	@Override
	public void run()
	{
		try
		{
			TextureLoader.initLoad();
			progress = 1;
			SoundLoader.initLoad();
			progress = 2;
			LanguageLoader.initLoad();
			progress = 3;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}