package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import Game.Game;
import Game.GameInfo;
import Obj.DrawbleObject;
import block.Block;
import block.Blocks;
import entity.PlayingPlayerEntity;
import world.BlockPos;
import world.World;

public class Panel_worldrenderer extends GlobalPanel
{
	public World world = null;
	
	public double cameraPosX = 0;
	public double cameraPosY = 0;
	public double cameraSpeed = 0.25;

	public final int blocksAtScreenByWidthCONST = 35;
	public int blocksAtScreenByWidth = 35;
	public int blocksAtScreenByHeight() { return (int)((this.realHeight()/(this.realWidth()/blocksAtScreenByWidth))+1); }
	
	public PlayingPlayerEntity player = null;
	
	public boolean KeyUpPress = false;
	public boolean KeyDownPress = false;
	public boolean KeyLeftPress = false;
	public boolean KeyRightPress = false;
	public boolean KeyRunPress = false;
	
	public int mouseX = 0;
	public int mouseY = 0;
	public double mouseOnBlockX = 0;
	public double mouseOnBlockY = 0;
	
	public Panel_techinfo info;

	public Panel_worldrenderer(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		info = new Panel_techinfo(0,0,1,1,this);
	}
	
	@Override
	public void logic()
	{
		if(world == null) return;
		
		if(player == null)
			player = world.getPlayingPlayerEntity();
		
		if(player == null || player.getPlayerGamemode() == 1)
		{
			if(KeyUpPress) cameraPosY -= cameraSpeed;
			if(KeyDownPress) cameraPosY += cameraSpeed;
			if(KeyLeftPress) cameraPosX -= cameraSpeed;
			if(KeyRightPress) cameraPosX += cameraSpeed;
			
			if(KeyRunPress) cameraSpeed = 0.5;
			else cameraSpeed = 0.25;
		}
		else
		{
			cameraPosX = player.posX;
			cameraPosY = player.posY;
			
			if(this.blocksAtScreenByWidth < blocksAtScreenByWidthCONST) this.blocksAtScreenByWidth++;
			if(this.blocksAtScreenByWidth > blocksAtScreenByWidthCONST) this.blocksAtScreenByWidth--;
		}
		
		mouseOnBlockX = cameraPosX + (mouseX / (this.realWidth()/this.blocksAtScreenByWidth)) - blocksAtScreenByWidth/2;
		mouseOnBlockY = cameraPosY + (mouseY / (this.realHeight()/this.blocksAtScreenByHeight())) - blocksAtScreenByHeight()/2;
		
		world.WorldTick();
	}
	
	@Override
	public void draw(Graphics g)
	{
		if(world == null) return;
		
		drawBlocks(g, BlockPos.blockPosLevel.BACK);
		drawBlocks(g, BlockPos.blockPosLevel.MIDDLE);
		drawBlocks(g, BlockPos.blockPosLevel.HIGH);
		
		if(player == null || player.getPlayerGamemode() == 1) drawMousePosAndInfo(g);
		
		super.draw(g);
	}
	
	public void drawMousePosAndInfo(Graphics g)
	{
		int screenX = 0;
		int screenY = 0;
		int screenW = 0;
		int screenH = 0;

		screenX = (int)(-(cameraPosX - (double)(int)cameraPosX) * (this.realWidth()/(double)this.blocksAtScreenByWidth) + (getIntedMouseX() - ((int)cameraPosX - blocksAtScreenByWidth/2))*(this.realWidth()/(double)blocksAtScreenByWidth));
		screenY = (int)(-(cameraPosY - (double)(int)cameraPosY) * (this.realHeight()/(double)this.blocksAtScreenByHeight()) + (getIntedMouseY() - ((int)cameraPosY - blocksAtScreenByHeight()/2))*(this.realHeight()/(double)blocksAtScreenByHeight()));
		screenW = (int)(this.realWidth()/(double)blocksAtScreenByWidth);
		screenH = (int)(this.realHeight()/(double)blocksAtScreenByHeight());
		
		g.setColor(Color.YELLOW);
		g.drawRect(screenX, screenY, screenW, screenH);
		
		g.setColor(Color.black);
		g.fillRect(mouseX - 50, mouseY - 25, 100, 20);
		g.setColor(Color.white);
		g.fillRect(mouseX - 49, mouseY - 24, 98, 18);
		g.setColor(Color.black);
		g.drawString("x: " + (int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX) + "; y: " + (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), mouseX - 45, mouseY - 10);
	}
	
	public void drawBlocks(Graphics g, BlockPos.blockPosLevel lvl)
	{
		int screenX = 0;
		int screenY = 0;
		int screenW = 0;
		int screenH = 0;
		
		for(int ix = -1; ix < blocksAtScreenByWidth+1; ix++)
		{
			for(int iy = -1; iy < blocksAtScreenByHeight()+1; iy++)
			{
				BlockPos pos = new BlockPos((int)cameraPosX + ix - blocksAtScreenByWidth/2, (int)cameraPosY + iy - blocksAtScreenByHeight()/2, lvl);
				Block block = world.getBlock(pos);
				
				screenX = (int)(-(cameraPosX - (double)(int)cameraPosX) * (this.realWidth()/(double)this.blocksAtScreenByWidth)) + (int)(ix*(this.realWidth()/(double)blocksAtScreenByWidth)) - 1;
				screenY = (int)(-(cameraPosY - (double)(int)cameraPosY) * (this.realHeight()/(double)this.blocksAtScreenByHeight())) + (int)(iy*(this.realHeight()/(double)blocksAtScreenByHeight())) - 1;
				screenW = (int)(this.realWidth()/(double)blocksAtScreenByWidth) + 1;
				screenH = (int)(this.realHeight()/(double)blocksAtScreenByHeight())+1;
				
				if(block != null) block.drawAtScreen(g, screenX, screenY, screenW, screenH, world, pos);
				else
				{
					g.setColor(new Color((int)(Math.random() * 255D),(int)(Math.random() * 255D), (int)(Math.random() * 255D)));
					g.fillRect(screenX, screenY, screenW, screenH);
				}
			}
		}
	}
	
	public void setWorld(World w)
	{
		world = w;
		
		Game.theGame.currentOpenedWorld = world;
	}

	public World getWorld()
	{
		return world;
	}
	
	public int getIntedMouseX()
	{
		return (int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX);
	}

	public int getIntedMouseY()
	{
		return (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY);
	}
	
	int mPress = 0;
	
	@Override
	public void onMousePress(MouseEvent e)
	{
		if(e.getButton() == e.BUTTON1) mPress = 1;
		if(e.getButton() == e.BUTTON2) mPress = 2;
		if(e.getButton() == e.BUTTON3) mPress = 3;
		if(mPress == 1) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		if(mPress == 2) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.STONE);
		if(mPress == 3) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.GRASS);
	}
	
	@Override
	public void onMouseRelease(MouseEvent e)
	{
		mPress = 0;
	}
	
	@Override
	public void onMouseMove(MouseEvent e)
	{
		mouseX = e.getX();
		mouseY = e.getY();
		if(mPress == 1) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.DIRT);
		if(mPress == 2) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.STONE);
		if(mPress == 3) world.setBlock((int)(mouseOnBlockX < 0 ? mouseOnBlockX - 1 : mouseOnBlockX), (int)(mouseOnBlockY < 0 ? mouseOnBlockY - 1 : mouseOnBlockY), BlockPos.blockPosLevel.BACK, Blocks.GRASS);
	}
	
	@Override
	public void onMouseWheelMoved(MouseWheelEvent e)
	{
		if((player == null || player.getPlayerGamemode() == 1) && this.blocksAtScreenByWidth + e.getWheelRotation() > 2)this.blocksAtScreenByWidth += e.getWheelRotation();
	}
	
	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() == 112 && !this.existChild(info)) this.addChild(info);
		else if(e.getKeyCode() == 112 && this.existChild(info)) this.removeChild(info);
		
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = true;
		super.onKeyPress(e);
	}
	
	@Override
	public void onKeyRelease(KeyEvent e)
	{
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = false;
		super.onKeyRelease(e);
	}
}
