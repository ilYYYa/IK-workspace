package Button;

import java.awt.event.MouseEvent;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_menu_exit extends GlobalButton
{

	public Button_menu_exit(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Exit");
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		System.exit(0);
	}
}
