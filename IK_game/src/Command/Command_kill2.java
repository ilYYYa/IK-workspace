package Command;

import Game.Game;
import entity.Entity;
import entity.monster.MonsterEntity;
import world.World;

public class Command_kill2 extends GCommand
{

	public Command_kill2()
	{
		super("kill");
		
		this.addType(CType.STRING);
	}

	@Override
	public String Execute(String[] args)
	{
		String ek = args[0];
		
		Entity[] entitiesForKill = new Entity[0];
		
		World world = Game.theGame.currentOpenedWorld;
		
		if(ek.equals("entities") || ek.equals("all") || ek.equals("entity")) entitiesForKill = world.getEntitiesInWorld();
		else if(ek.equals("monster") || ek.equals("monsters")) entitiesForKill = world.getEntitiesInWorldByClass(MonsterEntity.class).toArray(entitiesForKill);
		else entitiesForKill = world.getEntitiesByName(ek);
		
		for(int i = 0; i < entitiesForKill.length; i++)
		{
			entitiesForKill[i].kill();
		}
		
		return "Done. Killed " + entitiesForKill.length + " entities";
	}

}
