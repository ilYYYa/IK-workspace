package block;

public class HugeBlock_Fountain extends HugeBlock
{

	public HugeBlock_Fountain(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setWidth(2);
		this.setHeight(2);
		
		this.setTextureName("fountain");
	}
	
	
	@Override
	public String getTextureName()
	{
		return "fountain" + (Game.Game.theGame.currentOpenedWorld.worldTicks % 40 / 10 + 1);
	}
}
