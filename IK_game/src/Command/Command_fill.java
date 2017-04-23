package Command;

import Game.Game;
import block.Block;
import block.BlockRegister;
import world.BlockPos;

public class Command_fill extends GCommand
{

	public Command_fill()
	{
		super("fill");
		
		this.addType(CType.INT); //x1
		this.addType(CType.INT); //y2
		this.addType(CType.INT); //lvl1
		
		this.addType(CType.INT); //x2
		this.addType(CType.INT); //y2
		this.addType(CType.INT); //lvl2
		
		this.addType(CType.STRING); //blockName
	}

	@Override
	public String Execute(String[] args)
	{
		if(Game.theGame.currentOpenedWorld == null) return "Can't set block, world doesn't opened";
		
		int x1 = Integer.parseInt(args[0]);
		int y1 = Integer.parseInt(args[1]);
		int l1 = Integer.parseInt(args[2]);
		int x2 = Integer.parseInt(args[3]);
		int y2 = Integer.parseInt(args[4]);
		int l2 = Integer.parseInt(args[5]);
		
		String blockName = args[6];
		Block block = BlockRegister.getBlockByUnlocalizedName(blockName);
		
		if(block == null) return "Block doesn't founded";
		
		for(int ix = x1; ix <= x2; ix++)
		{
			for(int iy = y1; iy <= y2; iy++)
			{
				for(int il = l1; il <= l2; il++)
				{
					BlockPos.blockPosLevel lvl = il <= 0 ? BlockPos.blockPosLevel.BACK : il == 1 ? BlockPos.blockPosLevel.MIDDLE : BlockPos.blockPosLevel.HIGH;
					Game.theGame.currentOpenedWorld.setBlock(ix, iy, lvl, block);
				}
			}
		}

		return "Done";
	}

}
