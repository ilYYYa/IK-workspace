package Scene;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import Resources.LanguageLoader;
import Resources.SoundLoader;
import Resources.TextureLoader;
import Window.DoubleBuffer;

public class Scene_Load extends GlobalScene
{
	private loader l = new loader();
	
	public Scene_Load(double w, double h)
	{
		super(w, h);
		l.start();
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawString("Loading resources", 10, 20);
		if(l.progress == 0) g.drawString("Loading Textures", 10, 40);
		if(l.progress == 1) g.drawString("Loading Sounds", 10, 40);
		if(l.progress == 2) g.drawString("Loading Languages", 10, 40);
		if(l.progress == 3)
		{
			DoubleBuffer.setScene(new Scene_Menu(width, height));
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