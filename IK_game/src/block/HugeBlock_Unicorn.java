package block;

public class HugeBlock_Unicorn extends HugeBlock
{

	public HugeBlock_Unicorn(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setTextureName("unicorn");
		this.setWidth(2);
		this.setHeight(3);
	}
	
	@Override
	public String getTextureName()
	{
		int a = (int) (Game.Game.theGame.currentOpenedWorld.worldTicks % 60 / 10 + 1);
		if(a > 4) a = Math.abs(8 - a);
		return "unicorn" + a;
	}
}
