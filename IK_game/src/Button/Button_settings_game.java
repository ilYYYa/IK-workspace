package Button;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_settings_game extends GlobalButton
{

	public Button_settings_game(int x, int y, int w, int h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Game");
	}
	
}