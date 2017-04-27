package Command;

import Game.Game;

public class Command_removeTriggersAt extends GCommand
{

	public Command_removeTriggersAt()
	{
		super("removetriggersat");
		this.addType(CType.INT);
		this.addType(CType.INT);
	}

	@Override
	public String Execute(String[] args)
	{
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		
		Game.theGame.currentOpenedWorld.removeTriggersAt(x, y);
		
		return "Done";
	}
}
