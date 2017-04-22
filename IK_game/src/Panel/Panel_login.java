package Panel;

import java.awt.event.MouseEvent;

import Button.Button_OK;
import Game.Game;
import InputLine.GlobalInputLine;
import Obj.DrawbleObject;
import Scene.Scene_Menu;
import TextField.GloblaTextField;

public class Panel_login extends GlobalPanel
{
	GloblaTextField text;
	GlobalInputLine inline;
	Button_OK okBtn;
	
	public Panel_login(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		text = new GloblaTextField(0,0,1,0.2,this); text.setTextForDraw("Write your nickName:");
		inline = new GlobalInputLine(0,0.2,1,0.2,this);
		okBtn = new Button_OK(0,0.8,1,0.2,this);

		if(Game.theGame.gameSettingSaver.existString("UserNickName")) inline.setText(Game.theGame.gameSettingSaver.getString("UserNickName"));
		
		this.addChild(text);
		this.addChild(inline);
		this.addChild(okBtn);
	}

	@Override
	public void onMouseClick(MouseEvent e)
	{
		if(okBtn.checkingPointCrossingThisObject(e.getX(), e.getY()) && inline.getText().length() > 2)
		{
			if(!Game.theGame.gameSettingSaver.existString("UserNickName")) Game.theGame.gameSettingSaver.addString(inline.getText(), "UserNickName");
			else Game.theGame.gameSettingSaver.updateString(inline.getText(), "UserNickName");
			
			Game.theGame.SaveSettings();
			
			Game.theGame.theDoubleBuffer.setScene(new Scene_Menu());
		}
		else if(inline.getText().length() <= 2)
		{
			text.setTextForDraw("NickName must be 3 or more Symbols! Write your nickName:");
		}
	}
}
