package Button;

import java.awt.event.MouseEvent;

import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_menu_exit extends GlobalButton
{

	public Button_menu_exit(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("Exit");
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		System.exit(0);
	}
}
