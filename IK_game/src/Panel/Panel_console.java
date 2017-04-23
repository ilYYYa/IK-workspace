package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import InputLine.GlobalInputLine;
import Obj.DrawbleObject;
import TextField.GlobalTextField;
import block.Block;
import block.BlockRegister;
import world.BlockPos;

public class Panel_console extends GlobalPanel
{

	Panel_worldrenderer renderer;
	GlobalInputLine inline;
	GlobalTextField txt; 
	
	public Panel_console(double x, double y, double w, double h, DrawbleObject parent, Panel_worldrenderer renderer)
	{
		super(x, y, w, h, parent);
		this.renderer = renderer;
		
		inline = new GlobalInputLine(0, 0.95, 1, 0.05, this);
		inline.setMaxSymbols(128);
		
		txt = new GlobalTextField(0, 0, 1, 0.95, this);
		txt.setColor(255, 255, 255);

		this.addChild(txt);
		this.addChild(inline);
	}
	
	private void onEnterPressed()
	{
		String[] command = inline.getText().toLowerCase().split(" ");
		
		txt.addTextForDraw("--> " + inline.getText().toLowerCase());
		inline.setText("");
		
		if(command.length <= 0) return;
		
		if(command[0].equals("setblock")) if(setBlockCommand(command)) txt.addTextForDraw(command[0] + " Done"); else txt.addTextForDraw(command[0] + " can't execute! (setblock <x> <y> <lvl> <blockName/blockID>)");
		else if(command[0].equals("fill")) if(fillCommand(command)) txt.addTextForDraw(command[0] + " Done"); else txt.addTextForDraw(command[0] + " can't execute! (fill <x1> <y1> <lvl1> <x2> <y2> <lvl2> <blockName/blockID>)");
		else if(command[0].equals("saveworld")) if(saveworldCommand(command)) txt.addTextForDraw(command[0] + " Done"); else txt.addTextForDraw(command[0] + " can't execute! (saveworld or saveworld <worldName>)");
		else txt.addTextForDraw("Command " + command[0] + " doesn't found");
	}
	
	public boolean setBlockCommand(String[] args)
	{
		if(args.length != 5) return false;
		String str = args[0];
		
		try
		{
			Integer.parseInt(args[1]);
			Integer.parseInt(args[2]);
			Integer.parseInt(args[3]);
		}
		catch(NumberFormatException e)
		{
			return false;
		}

		int x = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		int lvl = Integer.parseInt(args[3]);
		BlockPos.blockPosLevel level = lvl == 0 ? BlockPos.blockPosLevel.BACK : lvl == 1 ? BlockPos.blockPosLevel.MIDDLE : BlockPos.blockPosLevel.HIGH;
		
		String blockName = "";
		int blockId = -1;
		
		try
		{
			blockId = Integer.parseInt(args[4]);
		}
		catch(NumberFormatException e)
		{
			blockName = args[4];
			blockId = -1;
		}
		
		Block block;
		block = BlockRegister.getBlockById(blockId);
		if(block == null) block = BlockRegister.getBlockByUnlocalizedName(blockName);
		if(block == null) return false;
		
		renderer.getWorld().setBlock(x, y, level, block);
		
		return true;
	}
	public boolean fillCommand(String[] args)
	{
		if(args.length != 8) return false;
		String str = args[0];
		
		try
		{
			Integer.parseInt(args[1]);
			Integer.parseInt(args[2]);
			Integer.parseInt(args[3]);
			Integer.parseInt(args[4]);
			Integer.parseInt(args[5]);
			Integer.parseInt(args[6]);
		}
		catch(NumberFormatException e)
		{
			return false;
		}

		int x1 = Integer.parseInt(args[1]);
		int y1 = Integer.parseInt(args[2]);
		int lvl1 = Integer.parseInt(args[3]);

		int x2 = Integer.parseInt(args[4]);
		int y2 = Integer.parseInt(args[5]);
		int lvl2 = Integer.parseInt(args[6]);
		
		if(x1 > x2)
		{
			int b = x1;
			x1 = x2;
			x2 = b;
		}
		if(y1 > y2)
		{
			int b = y1;
			y1 = y2;
			y2 = b;
		}
		if(lvl1 > lvl2)
		{
			int b = lvl1;
			lvl1 = lvl2;
			lvl2 = b;
		}
		
		String blockName = "";
		int blockId = -1;
		
		try
		{
			blockId = Integer.parseInt(args[7]);
		}
		catch(NumberFormatException e)
		{
			blockName = args[7];
			blockId = -1;
		}
		
		Block block;
		block = BlockRegister.getBlockById(blockId);
		if(block == null) block = BlockRegister.getBlockByUnlocalizedName(blockName);
		if(block == null) return false;
		
		for(int ix = x1; ix <= x2; ix++)
		{
			for(int iy = y1; iy <= y2; iy++)
			{
				for(int l = lvl1; l <= lvl2; l++)
				{
					BlockPos.blockPosLevel level = l == 0 ? BlockPos.blockPosLevel.BACK : l == 1 ? BlockPos.blockPosLevel.MIDDLE : BlockPos.blockPosLevel.HIGH;
					renderer.getWorld().setBlock(ix, iy, level, block);
				}
			}
		}
		
		return true;
	}
	public boolean saveworldCommand(String[] args)
	{
		if(args.length < 1 || args.length > 2) return false;
		if(args.length == 1)
		{
			renderer.world.getWorldSaver().initSave();
		}
		else if(args.length == 2)
		{
			renderer.world.setWorldName(args[1]);
			renderer.world.getWorldSaver().initSave();
		}
		
		return true;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(0,0,0,150));
		g.fillRect((int)this.realPosX(), (int)this.realPosY(), (int)this.realWidth()+1, (int)this.realHeight()+1);
		super.draw(g);
	}
	
	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() == 10) onEnterPressed();
		super.onKeyPress(e);
	}
}
