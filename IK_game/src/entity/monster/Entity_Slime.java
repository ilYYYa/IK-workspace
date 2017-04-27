package entity.monster;

import world.World;

public class Entity_Slime extends MonsterEntity
{
	int movingCD = 0;

	public Entity_Slime(World world, int uid)
	{
		super(world, "Slime", 1, uid);
		
		this.setWidth(0.9);
		this.setHeight(0.9);
		
		this.setMaxHP(10D);
		this.setMaxMP(0D);
	}

	@Override
	public String getTexture()
	{
		if(this.lifeTime % 80 < 20) return "slime1";
		if(this.lifeTime % 80 >= 20 && this.lifeTime % 80 < 40) return "slime2";
		if(this.lifeTime % 80 >= 40 && this.lifeTime % 80 < 60) return "slime1";
		return "slime3";
	}
}
