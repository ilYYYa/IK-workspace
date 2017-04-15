package Button;

import Obj.DrawbleObject;
import Panel.GlobalPanel;
import Scene.GlobalScene;

public class Button_settings_audio extends GlobalButton
{

	public Button_settings_audio(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Audio");
	}
	
}