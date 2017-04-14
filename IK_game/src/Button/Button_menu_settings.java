package Button;

import java.awt.event.MouseEvent;

import Game.Game;
import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Scene.GlobalScene;
import Scene.Scene_Settings;
import Window.DoubleBuffer;

public class Button_menu_settings extends GlobalButton
{

	public Button_menu_settings(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Settings");
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		Game.theGame.theDoubleBuffer.setScene(new Scene_Settings());
	}
}
