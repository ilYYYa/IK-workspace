package Button;

import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_settings_audio extends GlobalButton
{

	public Button_settings_audio(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("Audio");
	}
	
}