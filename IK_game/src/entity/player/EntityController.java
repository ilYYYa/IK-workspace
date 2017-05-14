package entity.player;

import java.awt.event.KeyEvent;

import Game.Game;
import entity.Entity;

public class EntityController
{
	public Entity entity = null;
	
	public boolean KeyUpPress = false;
	public boolean KeyDownPress = false;
	public boolean KeyLeftPress = false;
	public boolean KeyRightPress = false;
	public boolean KeyRunPress = false;
	
	public void setControllableEntity(Entity e)
	{
		entity = e;
	}
	
	public Entity getControllableEntity()
	{
		return entity;
	}
	
	public void disbandEntity()
	{
		this.entity = null;
	}

	public void onEntityUpdate()
	{
		if(entity == null) return;
		
		if(KeyLeftPress)
		{
			entity.motionX = -entity.motion;
		}
		if(KeyRightPress)
		{
			entity.motionX = entity.motion;
		}
		if(KeyUpPress)
		{
			entity.motionY = -entity.motion;
		}
		if(KeyDownPress)
		{
			entity.motionY = entity.motion;
		}
		if(!KeyRightPress && !KeyLeftPress) entity.motionX = 0;
		if(!KeyUpPress && !KeyDownPress) entity.motionY = 0;

		if(KeyRunPress)
		{
			entity.motionX *= 2;
			entity.motionY *= 2;
		}
		
		entity.onEntityUpdate();
	}

	public void keyPressed(int code)
	{
		if(code == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = true;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = true;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = true;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = true;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = true;
	}

	public void keyReleased(int code)
	{
		if(code == Game.theGame.gameSettingSaver.getInt("Key_NorthMotion")) KeyUpPress = false;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_SouthMotion")) KeyDownPress = false;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_WestMotion")) KeyLeftPress = false;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_EastMotion")) KeyRightPress = false;
		if(code == Game.theGame.gameSettingSaver.getInt("Key_Run")) KeyRunPress = false;
	}
}
