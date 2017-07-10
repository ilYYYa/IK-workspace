package block;

public class TECH_BLOCK_MOVESTOPPER extends Block
{
	public TECH_BLOCK_MOVESTOPPER(int blockId, String blockUnlocalizedName)
	{
		super(blockId, blockUnlocalizedName);
		
		this.setBlockMovementSlow(1.0D);
	}

	@Override
	public String getTextureName()
	{
		if(Game.Game.theGame.currentOpenedWorld.getGamemode() == 0) return "air";
		else return "tech_movestopper";
	}
}
