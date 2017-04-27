package Command;

import Game.Game;
import trigger.Trigger;
import trigger.Triggers;

public class Command_triggerCreate extends GCommand
{

	public Command_triggerCreate()
	{
		super("createtrigger");
		
		this.addType(CType.INT);
		this.addType(CType.INT);
		this.addType(CType.INT);
		this.addType(CType.INT);

		this.addType(CType.STRING);
	}

	@Override
	public String Execute(String[] args)
	{
		int x1 = Integer.parseInt(args[0]);
		int y1 = Integer.parseInt(args[1]);
		int x2 = Integer.parseInt(args[2]);
		int y2 = Integer.parseInt(args[3]);
		
		if(x1 > x2)
		{
			int buff = x2;
			x2 = x1;
			x1 = buff;
		}
		if(y1 > y2)
		{
			int buff = y2;
			y2 = y1;
			y1 = buff;
		}
		
		Trigger trigger = Triggers.create(args[4], x1, y1, x2-x1+1, y2-y1+1);
		
		if(trigger == null) return "trigger doesn't founded";
		
		Game.theGame.currentOpenedWorld.addTrigger(trigger);
		
		return "Done";
	}

}
