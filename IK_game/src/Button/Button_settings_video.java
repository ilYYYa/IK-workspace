package Button;

import java.awt.event.MouseEvent;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Panel.Panel_settings_video;
import Resources.SettingLoader;
import Resources.Settings;
import Scene.GlobalScene;

public class Button_settings_video extends GlobalButton
{
	public Button_settings_video(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Video");
	}
}
