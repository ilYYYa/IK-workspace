package Command;

import java.io.File;

import Game.Game;
import Resources.Saver;
import Scene.Scene_WorldLoader;
import world.World;

public class Command_loadWorld extends GCommand
{

	public Command_loadWorld()
	{
		super("loadworld");
		
		this.addType(CType.STRING);
	}

	@Override
	public String Execute(String[] args)
	{
		File f = new File("worlds/" + args[0] + ".world");
		File d = f.getParentFile();
		d.mkdirs();
		
		Game.theGame.SaveSettings();
		Game.theGame.currentOpenedWorld.getWorldSaver().initSave();
		
		if(!f.exists())
		{
			World world = new World(args[0]);
			Saver saver = world.getWorldSaver();
			saver.initSave();
			
			Game.theGame.setScene(new Scene_WorldLoader(args[0]));
			
			return "Created new file. Done";
		}
		else
		{
			Game.theGame.setScene(new Scene_WorldLoader(args[0]));
			
			return "Done";
		}
		
	}
}
