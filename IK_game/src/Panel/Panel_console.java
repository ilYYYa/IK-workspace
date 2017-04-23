package Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Command.Commands;
import InputLine.GlobalInputLine;
import Obj.DrawbleObject;
import TextField.GlobalTextField;
import block.Block;
import block.BlockRegister;
import world.BlockPos;

public class Panel_console extends GlobalPanel
{

	Panel_worldrenderer renderer;
	GlobalInputLine inline;
	GlobalTextField txt; 
	
	public Panel_console(double x, double y, double w, double h, DrawbleObject parent, Panel_worldrenderer renderer)
	{
		super(x, y, w, h, parent);
		this.renderer = renderer;
		
		inline = new GlobalInputLine(0, 0.95, 1, 0.05, this);
		inline.setMaxSymbols(128);
		
		txt = new GlobalTextField(0, 0, 1, 0.95, this);
		txt.setColor(255, 255, 255);

		this.addChild(txt);
		this.addChild(inline);
	}
	
	private void onEnterPressed()
	{
		String[] command = inline.getText().toLowerCase().split(" ");
		String[] args = new String[command.length - 1];
		for(int i = 0; i < args.length; i++) args[i] = command[i+1];
		
		txt.addTextForDraw("--> " + inline.getText().toLowerCase());
		inline.setText("");
		
		boolean f = false;
		int cc = -1;
		
		if(command.length <= 0) txt.addTextForDraw("Command doesn't founded");
		else
		{
			for(int i = 0; i < Commands.commands.length; i++)
			{
				if(Commands.commands[i].getCommandName().equals(command[0]))
				{
					f = true;
					
					if(Commands.commands[i].CheckTypes(args))
					{
						txt.addTextForDraw("[" + Commands.commands[i].getCommandName() + "] " + Commands.commands[i].Execute(args));
						cc = -2;
					}
					else
					{
						if(cc != -2) cc = i;
					}
				}
			}
			
			if(cc>=0)
			{
				if(Commands.commands[cc].getTypes().length == 0) txt.addTextForDraw("[" + Commands.commands[cc].getCommandName() + "] this command do not need args");
				else txt.addTextForDraw("[" + Commands.commands[cc].getCommandName() + "] args for this command: " + Commands.commands[cc].typesToString());
			}
			
			if(!f) txt.addTextForDraw("Command " + command[0] + " doesn't founded");
		}
		
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.setColor(new Color(0,0,0,150));
		g.fillRect((int)this.realPosX(), (int)this.realPosY(), (int)this.realWidth()+1, (int)this.realHeight()+1);
		super.draw(g);
	}
	
	@Override
	public void onKeyPress(KeyEvent e)
	{
		if(e.getKeyCode() == 10) onEnterPressed();
		super.onKeyPress(e);
	}
}
