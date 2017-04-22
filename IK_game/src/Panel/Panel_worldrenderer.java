package Panel;

import java.awt.Color;
import java.awt.Graphics;

import Obj.DrawbleObject;
import block.Block;
import entity.PlayingPlayerEntity;
import world.BlockPos;
import world.World;

public class Panel_worldrenderer extends GlobalPanel
{
	public World world = null;
	
	public double cameraPosX = 0;
	public double cameraPosY = 0;
	
	public int blocksAtScreenByWidth = 35;
	public int blocksAtScreenByHeight()
	{
		return (int)((this.realHeight()/(this.realWidth()/blocksAtScreenByWidth))+1);
	}
	
	private PlayingPlayerEntity player = null;

	public Panel_worldrenderer(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
	}
	
	@Override
	public void logic()
	{
		if(world == null) return;
		
		if(player == null)
			player = world.getPlayingPlayerEntity();
		
		if(player != null)
		{
			cameraPosX = player.posX;
			cameraPosY = player.posY;
		}
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(world == null) return;
		
		for(int ix = 0; ix < blocksAtScreenByWidth; ix++)
		{
			for(int iy = 0; iy < blocksAtScreenByHeight(); iy++)
			{
				BlockPos pos = new BlockPos((int)cameraPosX - ix + blocksAtScreenByWidth/2, (int)cameraPosY - iy + blocksAtScreenByHeight()/2, BlockPos.blockPosLevel.BACK);
				Block block = world.getBlock(pos);
				
				/*g.setColor(new Color((int)(255d*((double)ix/(double)blocksAtScreenByWidth)),
						(int)(255d*((double)(iy+ix)/(double)(blocksAtScreenByWidth+blocksAtScreenByHeight()))),
						(int)(255d*((double)iy/(double)blocksAtScreenByHeight()))));*/
				g.setColor(new Color((int)(Math.random() * 255D),(int)(Math.random() * 255D), (int)(Math.random() * 255D)));
				//g.setColor(Color.black);
				
				int screenX = (int)(ix*(this.realWidth()/(double)blocksAtScreenByWidth)) - 1;
				int screenY = (int)(iy*(this.realHeight()/(double)blocksAtScreenByHeight())) - 1;
				int screenW = (int)(this.realWidth()/(double)blocksAtScreenByWidth) + 1;
				int screenH = (int)(this.realHeight()/(double)blocksAtScreenByHeight())+1;
				
				if(block != null) block.drawAtScreen(g, screenX, screenY, screenW, screenH, world.getBlockMeta(pos));
				else g.fillRect(screenX, screenY, screenW, screenH);
			}
		}
	}
	
	public void setWorld(World w)
	{
		world = w;
	}

	public World getWorld()
	{
		return world;
	}
}
