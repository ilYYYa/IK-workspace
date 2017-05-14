package KeyEvents;

import java.util.ArrayList;

public class KeyRegister
{
	public static ArrayList<Key> keys = new ArrayList<Key>();
	
	public static void register(int code, String name)
	{
		register(new Key(code, name));
	}
	public static void register(Key key)
	{
		if(!keyExist(key.keyCode))
		{
			keys.add(key);
		}
	}

	private static boolean keyExist(int code)
	{
		for(int i = 0; i < keys.size(); i++)
		{
			if(code == keys.get(i).keyCode) return true;
		}
		
		return false;
	}
	
	public static Key getKeyByKeyCodeAndKeyName(int code, String name)
	{
		for(int i = 0; i < keys.size(); i++)
		{
			if(code == keys.get(i).keyCode) return keys.get(i);
		}
		
		Key key = new Key(code, name);
		register(key);
		return key;
	}
}
