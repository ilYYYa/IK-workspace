package Button;

import java.awt.event.MouseEvent;
import java.io.IOException;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Resources.SettingLoader;
import Resources.Settings;
import Scene.GlobalScene;

public class Button_settings_video_fullScreen extends GlobalButton
{

	public Button_settings_video_fullScreen(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("FullScreen: " + Settings.fullScreen);
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		Settings.fullScreen = !Settings.fullScreen;
		SettingLoader.initSave();
		this.setText("FullScreen: " + Settings.fullScreen);
		Game.Game.theGame.setWindowVisible(false);
		Game.Game.theGame.createWnindow();
		Game.Game.theGame.setupDoubleBuffer();
		Game.Game.theGame.setWindowVisible(true);
	}
}
