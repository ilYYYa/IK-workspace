package Game;

public class GameInfo
{
	public static final String NameOfTheGame = "Name Of The Game";
	public static final String StageOfDevelopment = "Pre-alpha";
	public static final int GlobalVersion = 0;
	public static final int LocalVersion = 2;
	
	public static String getGameFullestName()
	{
		return "" + NameOfTheGame + " " + StageOfDevelopment + " " + GlobalVersion + "." + LocalVersion;
	}
}
