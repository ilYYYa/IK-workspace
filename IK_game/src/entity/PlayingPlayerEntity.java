package entity;

import java.awt.event.KeyEvent;

import Game.Game;
import world.World;

public class PlayingPlayerEntity extends PlayerEntity
{
	private boolean KeyUpPress = false;
	private boolean KeyDownPress = false;
	private boolean KeyLeftPress = false;
	private boolean KeyRightPress = false;
	private boolean KeyRunPress = false;

	public PlayingPlayerEntity(World world, int uid)
	{
		super(world, uid);
	}
	
	public void setPlayerGamemode(int gm)
	{
		this.playerGamemode = gm;
	}
	public int getPlayerGamemode()
	{
		return this.playerGamemode;
	}
	
	@Override
	public void onEntityUpdate()
	{
		if(KeyRunPress) this.setMoveSpeed(2D);
		else this.setMoveSpeed(1D);
		
		if(KeyLeftPress)
		{
			this.motionX = -this.getMoveSpeed();
		}
		if(KeyRightPress)
		{
			this.motionX = this.getMoveSpeed();
		}
		if(KeyUpPress)
		{
			this.motionY = -this.getMoveSpeed();
		}
		if(KeyDownPress)
		{
			this.motionY = this.getMoveSpeed();
		}
		if(!KeyRightPress && !KeyLeftPress) this.motionX = 0;
		if(!KeyUpPress && !KeyDownPress) this.motionY = 0;
		
		super.onEntityUpdate();
	}

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = true;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = true;
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = false;
		if(e.getKeyCode() == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = false;
	}
}
