package Command;

import Game.Game;

public class Command_gamemode extends GCommand
{

	public Command_gamemode()
	{
		super("gamemode");
		
		this.addType(CType.INT);
	}

	@Override
	public String Execute(String[] args)
	{
		int gm = Integer.parseInt(args[0]);
		
		if(gm < 0) return gm + " is not available";
		if(gm > 1) return gm + " is not available";
		
		if(Game.theGame.currentOpenedWorld.getBufferedPlayingPlayerEntity() == null) return "player didn't created";

		Game.theGame.currentOpenedWorld.getBufferedPlayingPlayerEntity().setPlayerGamemode(gm);
		
		return "Done";
	}
}
