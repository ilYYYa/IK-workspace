package Button;

import Obj.DrawbleObject;

public class TestingButton extends GlobalButton
{
	public TestingButton(int x, int y, int w, int h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("TEST!!");
	}
}
