package Scene;

import java.io.File;
import java.net.URL;

import Game.Game;
import Resources.Saver;
import Window.MainWindow;
import world.World;

public class Scene_WorldLoader extends GlobalScene
{
	private String defaultWorldPath = "";
	private String WorldPath = "";
	
	int a = 0;
	
	public Scene_WorldLoader(String world)
	{
		WorldPath = world;
	}
	
	@Override
	public void draw(MainWindow g)
	{
		g.drawString("Loading world", (int)this.realPosX() + 5, (int)this.realPosY() + 15);
		
		if(a < 15) a++;
		if(a == 15)
		{
			if(WorldPath == null) this.loadDefaultWorld();
			else this.loadWorld(WorldPath);
			a++;
		}
	}

	private void loadWorld(String path)
	{
		File f = new File("worlds/" + path + ".world");
		if(!f.exists()) this.loadDefaultWorld();
		else
		{
			Saver saver = new Saver(f);
			saver.initLoad();
			
			World world = new World("");
			world.readWorldFromSaver(saver);
			
			Game.theGame.currentOpenedWorld = world;
			
			Game.theGame.setScene(new Scene_Playground());
		}
	}

	private void loadDefaultWorld()
	{
		URL url = World.class.getResource("DefaultWorld.world");
		if(url != null)defaultWorldPath = url.getFile();
		File f = new File(defaultWorldPath);
		if(url == null || !f.exists())
		{
			System.err.println("Default world doesn't founded! " + defaultWorldPath);

			World world = new World("emptyWorld");
			
			Game.theGame.currentOpenedWorld = world;
			
			Game.theGame.setScene(new Scene_Playground());
		}
		else
		{
			Saver saver = new Saver(f);
			saver.initLoad();
			
			World world = new World("");
			world.readWorldFromSaver(saver);
			
			Game.theGame.currentOpenedWorld = world;
			
			Game.theGame.setScene(new Scene_Playground());
		}
	}
}
