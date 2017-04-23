package Command;

import Game.Game;

public class Command_exit extends GCommand
{

	public Command_exit()
	{
		super("exit");
	}

	@Override
	public String Execute(String[] args)
	{
		Game.theGame.SaveSettings();
		Game.theGame.currentOpenedWorld.getWorldSaver().initSave();
		
		System.exit(0);
		return "Done";
	}
	
}
