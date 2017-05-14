package KeyEvents;

public class Key
{
	public int keyCode = 0;
	public String keyName = "";
	
	public boolean pressed = false;
	
	public Key(int code, String keyName)
	{
		keyCode = code;
		this.keyName = keyName;
		
		if(this.keyName == null) this.keyName = "?";
	}
}
