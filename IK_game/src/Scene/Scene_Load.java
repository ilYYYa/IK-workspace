package Scene;

import java.awt.Color;
import java.io.IOException;

import Game.Game;
import Resources.LanguageLoader;
import Resources.SoundLoader;
import Resources.TextureLoader;
import Window.MainWindow;

public class Scene_Load extends GlobalScene
{
	private loader l = new loader();
	private String progress = "";
	
	public Scene_Load()
	{
		super();
	}

	@Override
	public void draw(MainWindow g)
	{
		g.setColor(Color.BLACK);
		g.drawLine(50, 50, 50, 100);
		g.drawLine(50, 100, 100, 100);
		g.drawLine(100, 100, 100, 50);
		g.drawLine(100, 50, 50, 50);
		g.drawLine(50, 50, 75, 75);
		g.drawLine(75, 75, 100, 50);
		if(l.progress < -1) l.progress++;
		if(l.progress == -1) l.run();
	}

	@Override
	public void logic()
	{
		if(l.progress == 0) progress = "Loading Textures";
		if(l.progress == 1) progress = "Loading Sounds";
		if(l.progress == 2) progress = "Loading Languages";
		if(l.progress == 3)
		{
			Game.theGame.setScene(new Scene_Menu());
		}
	}
}

class loader
{
	int progress = -5;

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