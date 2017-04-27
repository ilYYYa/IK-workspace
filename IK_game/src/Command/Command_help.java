package Command;

public class Command_help extends GCommand
{

	public Command_help()
	{
		super("help");
	}

	@Override
	public String Execute(String[] args)
	{
		String cmnds = "";
		
		for(int i = 0; i < Commands.commands.length; i++)
		{
			cmnds += Commands.commands[i].getCommandName();
			if(i == Commands.commands.length - 1) cmnds += ";";
			else cmnds += ", ";
		}
		
		return cmnds;
	}

}
