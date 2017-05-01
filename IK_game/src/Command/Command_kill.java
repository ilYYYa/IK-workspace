package Command;

import Game.Game;

public class Command_kill extends GCommand
{

	public Command_kill()
	{
		super("kill");
	}

	@Override
	public String Execute(String[] args)
	{
		if(Game.theGame.currentOpenedWorld.getPlayer() == null) return "Player doesn't created";
		
		Game.theGame.currentOpenedWorld.getPlayer().kill();
		
		return "Player killed";
	}

}
