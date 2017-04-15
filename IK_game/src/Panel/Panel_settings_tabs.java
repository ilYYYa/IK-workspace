package Panel;

import java.awt.event.MouseEvent;

import Button.Button_back;
import Button.Button_settings_audio;
import Button.Button_settings_game;
import Button.Button_settings_video;
import Obj.DrawbleObject;
import Scene.Scene_Menu;

public class Panel_settings_tabs extends GlobalPanel
{
	private Button_settings_game game_setts_btn = new Button_settings_game(0D, 0D, 1D, 0.1, this);
	private Button_settings_video video_setts_btn = new Button_settings_video(0D, 0.2D, 1D, 0.1, this);
	private Button_settings_audio audio_setts_btn = new Button_settings_audio(0D, 0.4D, 1D, 0.1, this);
	private Button_back back_btn = new Button_back(0D, 0.6D, 1D, 0.1, this);
	
	public Panel_settings_tabs(double x, double y, double w, double h, DrawbleObject parent)
	{
		super(x, y, w, h, parent);
		
		this.addChild(game_setts_btn);
		this.addChild(video_setts_btn);
		this.addChild(audio_setts_btn);
		this.addChild(back_btn);
		
		back_btn.setReturningScene(new Scene_Menu());
	}
	
	@Override
	public void onMouseClick(MouseEvent event)
	{
		if(video_setts_btn.checkingPointCrossingThisObject(event.getX(), event.getY()))
		{
			parent.addChild(new Panel_settings_video(0.3, 0.1, 0.6, 0.8, parent));
		}
		
		super.onMouseClick(event);
	}
}
