package trigger;

public class Triggers
{
	public static Trigger create(String triggerName, int x, int y, int width, int height)
	{
		triggerName = triggerName.toLowerCase();
		
		if(triggerName.equals("slimespawner")) return new Trigger_SlimeSpawner(x, y, width, height);

		if(triggerName.equals("playerspawner")) return new Trigger_PlayerFirstSpawn(x, y);
		
		if(triggerName.equals("butterflyspawner")) return new Trigger_ButterflySpawner(x, y, width, height);
		
		return null;
	}
}
