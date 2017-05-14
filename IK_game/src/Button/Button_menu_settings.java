package Button;

import java.awt.event.MouseEvent;

import Game.Game;
import Obj.DrawbleObject;
import Scene.Scene_Settings;

public class Button_menu_settings extends GlobalButton
{

	public Button_menu_settings(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Settings");
	}

	@Override
	public void onMouseRelease(int x, int y, int btn)
	{
		Game.theGame.setScene(new Scene_Settings());
	}
}
