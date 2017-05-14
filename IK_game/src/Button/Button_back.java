package Button;

import java.awt.event.MouseEvent;

import Game.Game;
import Obj.DrawbleObject;
import Scene.GlobalScene;

public class Button_back extends GlobalButton
{
	int type = 0;
	private GlobalScene Scene;

	public Button_back(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		this.setText("Back");
	}

	public void setReturningScene(GlobalScene scene)
	{
		type = 1;
		Scene = scene;
	}
	
	@Override
	public void onMouseRelease(int x, int y, int btn)
	{
		if(type == 0)parent.parent.removeChild(parent);
		if(type == 1)Game.theGame.setScene(Scene);
		
		super.onMouseRelease(x,y,btn);
	}
}
