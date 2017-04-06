package Button;

import java.awt.event.MouseEvent;

public class Button_menu_exit extends GlobalButton
{

	public Button_menu_exit(double x, double y, double w, double h)
	{
		super(x, y, w, h);
		this.setText("Exit");
	}

	@Override
	public void onMouseClick(MouseEvent event)
	{
		System.exit(0);
	}
}
