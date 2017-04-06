package Button;

import java.awt.event.MouseEvent;

import Panel.GlobalPanel;
import Panel.Panel_settings_video;
import Resources.SettingLoader;
import Resources.Settings;
import Scene.GlobalScene;

public class Button_settings_video extends GlobalButton
{
	private Panel_settings_video psv;
	
	public Button_settings_video(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("Video");
		psv = new Panel_settings_video(x + w + 50, y, scene_parent.width - x - w, h, scene_parent);
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		if(!scene_parent.existsPanel(psv))
		{
			scene_parent.addPanel(psv);
		}
	}
}
