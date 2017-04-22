package Button;

import Obj.DrawbleObject;

public class Button_OK extends GlobalButton
{

	public Button_OK(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("OK");
	}
}
