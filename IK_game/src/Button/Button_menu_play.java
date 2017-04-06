package Button;

import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_menu_play extends GlobalButton
{

	public Button_menu_play(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("PLAY");
	}

}
