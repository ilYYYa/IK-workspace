package Command;

import java.util.ArrayList;

public abstract class GCommand
{
	private String commandName = "";
	private ArrayList<CType> argTypes = new ArrayList<CType>();
	
	public GCommand(String command)
	{
		commandName = command;
	}
	
	public String getCommandName()
	{
		return commandName;
	}
	public void setCommandName(String name)
	{
		commandName = name;
	}
	
	public void setTypes(CType[] types)
	{
		argTypes = new ArrayList<CType>();
		for(CType tt : types) argTypes.add(tt);
	}
	
	public void addType(CType type)
	{
		argTypes.add(type);
	}
	
	public CType[] getTypes()
	{
		return argTypes.toArray(new CType[0]);
	}
	
	public boolean CheckTypes(String[] args)
	{
		if(args.length != argTypes.size()) return false;

		for(int i = 0; i < args.length; i++)
		{
			if(argTypes.get(i) == CType.DOUBLE)
			{
				try
				{
					Double.parseDouble(args[i]);
				}
				catch(NumberFormatException e)
				{
					return false;
				}
			}
			if(argTypes.get(i) == CType.INT)
			{
				try
				{
					Integer.parseInt(args[i]);
				}
				catch(NumberFormatException e)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public abstract String Execute(String[] args);
	
	public static enum CType
	{
		STRING, DOUBLE, INT
	}

	public String typesToString()
	{
		String ret = "";
		for(CType tt : argTypes) ret += "<" + tt + "> ";
		return ret;
	}
}









