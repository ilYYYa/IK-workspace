package Command;

public class Commands
{
	public static final GCommand[] commands =
		{
				new Command_setblock(),
				new Command_fill(),
				new Command_saveworld(),
				new Command_saveworld2(),
				new Command_loadWorld(),
				new Command_goToMainMenu(),
				new Command_exit(),
				new Command_quit(),
				new Command_triggerCreate(),
				new Command_removeTriggersAt(),
				new Command_help(),
				new Command_gamemode(),
				new Command_kill()
		};
}
