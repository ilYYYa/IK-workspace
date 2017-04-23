package Command;

import Game.Game;

public class Command_saveworld2 extends GCommand
{

	public Command_saveworld2()
	{
		super("saveworld");
		this.addType(CType.STRING);
	}

	@Override
	public String Execute(String[] args)
	{
		if(Game.theGame.currentOpenedWorld == null) return "World doesn't opened";
		
		Game.theGame.currentOpenedWorld.setWorldName(args[0]);
		Game.theGame.currentOpenedWorld.getWorldSaver().initSave();
		
		return "Done";
	}

}
