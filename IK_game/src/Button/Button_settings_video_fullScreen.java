package Button;

import java.awt.event.MouseEvent;

import Game.Game;
import Obj.DrawbleObject;

public class Button_settings_video_fullScreen extends GlobalButton
{

	public Button_settings_video_fullScreen(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("FullScreen: " + Game.theGame.gameSettingSaver.getBoolean("FullScreen"));
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		Game.theGame.gameSettingSaver.updateBoolean(!Game.theGame.gameSettingSaver.getBoolean("FullScreen"), "FullScreen");
		this.setText("FullScreen: " + Game.theGame.gameSettingSaver.getBoolean("FullScreen"));
		Game.theGame.setWindowVisible(false);
		Game.theGame.createWnindow();
		Game.theGame.setupDoubleBuffer();
		Game.theGame.setWindowVisible(true);
		Game.theGame.SaveSettings();
	}
}
