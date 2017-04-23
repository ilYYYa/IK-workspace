package Command;

import Game.Game;
import Scene.Scene_Menu;

public class Command_goToMainMenu extends GCommand
{

	public Command_goToMainMenu()
	{
		super("gotomainmenu");
	}

	@Override
	public String Execute(String[] args)
	{
		Game.theGame.setScene(new Scene_Menu());
		
		return "Done";
	}

}
