package Button;

import java.awt.event.MouseEvent;
import java.io.IOException;

import Panel.GlobalPanel;
import Resources.SettingLoader;
import Resources.Settings;
import Scene.GlobalScene;

public class Button_settings_video_fullScreen extends GlobalButton
{

	public Button_settings_video_fullScreen(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("FullScreen: " + Settings.fullScreen);
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		Settings.fullScreen = !Settings.fullScreen;
		SettingLoader.initSave();
		this.setText("FullScreen: " + Settings.fullScreen);
	}
}
