package Button;

import java.awt.event.MouseEvent;

import Panel.GlobalPanel;
import Scene.GlobalScene;
import Scene.Scene_Settings;
import Window.DoubleBuffer;

public class Button_menu_settings extends GlobalButton
{

	public Button_menu_settings(double x, double y, double w, double h, GlobalScene scene_parent, GlobalPanel panel_parent)
	{
		super(x, y, w, h, scene_parent, panel_parent);
		this.setText("Settings");
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		DoubleBuffer.setScene(new Scene_Settings(scene_parent.width, scene_parent.height));
	}
}
