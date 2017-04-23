package Button;

import java.awt.event.MouseEvent;

import Game.Game;
import Obj.DrawbleObject;
import Scene.Scene_WorldLoader;

public class Button_menu_newGame extends GlobalButton
{

	public Button_menu_newGame(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("NEW GAME");
	}
	
	@Override
	public void onMouseClick(MouseEvent e)
	{
		super.onMouseClick(e);
		Game.theGame.theDoubleBuffer.setScene(new Scene_WorldLoader(null));
	}
}
