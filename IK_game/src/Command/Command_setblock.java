package Command;

import Game.Game;
import block.Block;
import block.BlockRegister;
import world.BlockPos;

public class Command_setblock extends GCommand
{

	public Command_setblock()
	{
		super("setblock");
		this.setTypes(new CType[]{CType.INT, CType.INT, CType.INT, CType.STRING});
	}

	@Override
	public String Execute(String[] args)
	{
		if(Game.theGame.currentOpenedWorld == null) return "Can't set block, world doesn't opened";
		
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);
		int l = Integer.parseInt(args[2]);
		
		BlockPos.blockPosLevel lvl = l <= 0 ? BlockPos.blockPosLevel.BACK : l == 1 ? BlockPos.blockPosLevel.MIDDLE : BlockPos.blockPosLevel.HIGH;
		
		String blockName = args[3];
		Block block = BlockRegister.getBlockByUnlocalizedName(blockName);
		
		if(block == null) return "Block doesn't founded";
		
		Game.theGame.currentOpenedWorld.setBlock(x, y, lvl, block);
		
		return "Done";
	}

}
