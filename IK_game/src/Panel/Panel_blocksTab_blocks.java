package Panel;

import java.awt.Color;

import Obj.DrawbleObject;
import Resources.TextureLoader;
import Window.MainWindow;
import block.BlockRegister;

public class Panel_blocksTab_blocks extends GlobalPanel
{
	int blockWH = 0;
	int lines = 0;
	int mouseClickedOnBlockId = BlockRegister.registeredBlocks[0].BlockId;
	int scrolled = 0;
	int mouseOn = 0;
	
	public Panel_blocksTab_blocks(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
	}

	@Override
	public void draw(MainWindow g)
	{
		blockWH = (int)(this.realWidth() * 0.5f);
		lines = (int)this.realHeight()/(blockWH+35);
		
		drawBackgroundAndBorder(g);
		drawBlocks(g);
	}
	
	private void drawBackgroundAndBorder(MainWindow g)
	{
		g.setColor(Color.white);
		g.fillRect((int)this.posXOnScreen(), (int)this.posYOnScreen(), (int)this.realWidth(), (int)this.realHeight());
		g.setColor(Color.black);
		g.drawRect((int)this.posXOnScreen(), (int)this.posYOnScreen(), (int)this.realWidth(), (int)this.realHeight());
	}
	
	private void drawBlocks(MainWindow g)
	{
		for(int i = 0; i < lines && i+scrolled < BlockRegister.registeredBlocks.length; i++)
		{
			g.drawTexture(TextureLoader.getTextureByName(BlockRegister.registeredBlocks[i+scrolled].getTextureName()),
					(int)this.posXOnScreen() + (int)this.realWidth()/2 - blockWH/2, (int)this.posYOnScreen() + i * (blockWH+35) + 5,
					blockWH, blockWH);
			
			g.setColor(Color.BLACK);
			g.drawString(BlockRegister.registeredBlocks[i+scrolled].BlockUnlocalizedName + " | id: " + BlockRegister.registeredBlocks[i+scrolled].BlockId,
					(int)this.posXOnScreen() + (int)this.realWidth()/2 - blockWH/2, (int)this.posYOnScreen() + i * (blockWH+35) + blockWH + 20);
			
			if(mouseClickedOnBlockId == BlockRegister.registeredBlocks[i+scrolled].BlockId)
			{
				g.setColor(Color.red);
				g.drawRect((int)this.posXOnScreen() + 1, (int)this.posYOnScreen() + i * (blockWH+35) + 1, (int)this.realWidth()-2, blockWH + 23);
			}
			
			if(mouseOn == i)
			{
				g.setColor(Color.blue);
				g.drawRect((int)this.posXOnScreen() + 2, (int)this.posYOnScreen() + i * (blockWH+35) + 2, (int)this.realWidth()-4, blockWH + 21);
			}
		}
	}

	@Override
	public void onMousePress(int x, int y, int btn)
	{
		mouseClickedOnBlockId = BlockRegister.registeredBlocks[scrolled + mouseOn].BlockId;
	}
	
	@Override
	public void onMouseMove(int x, int y, int dx, int dy)
	{
		mouseOn = y / (blockWH+35);
		if(mouseOn >= lines) mouseOn = lines-1;
	}
	
	@Override
	public void onMouseWheelMoved(int wheel)
	{
		scrolled+=wheel;
		if(scrolled < 0) scrolled = 0;
		if(scrolled + lines > BlockRegister.registeredBlocks.length) scrolled = BlockRegister.registeredBlocks.length - lines;
	}
}
