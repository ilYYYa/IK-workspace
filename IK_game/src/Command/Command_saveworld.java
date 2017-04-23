package Command;

import Game.Game;

public class Command_saveworld extends GCommand
{

	public Command_saveworld()
	{
		super("saveworld");
	}

	@Override
	public String Execute(String[] args)
	{
		if(Game.theGame.currentOpenedWorld == null) return "World doesn't opened";
		
		Game.theGame.currentOpenedWorld.getWorldSaver().initSave();
		
		return "Done";
	}

}
