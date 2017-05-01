package Command;

import Game.Game;

public class Command_uncontrollable extends GCommand
{

	public Command_uncontrollable()
	{
		super("uncontrollable");
	}

	@Override
	public String Execute(String[] args)
	{
		Game.theGame.currentOpenedWorld.controller.disbandEntity();
		
		return "Done";
	}
}
