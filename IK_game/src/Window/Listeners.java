package Window;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listeners implements KeyListener, MouseListener, MouseMotionListener
{
	private DoubleBuffer buff;
	
	public Listeners(DoubleBuffer buffer)
	{
		buff = buffer;
	}

	@Override
	public void mouseDragged(MouseEvent event)
	{
		buff.onMouseMove(event);
	}

	@Override
	public void mouseMoved(MouseEvent event)
	{
		buff.onMouseMove(event);
	}

	@Override
	public void mouseClicked(MouseEvent event)
	{
		buff.onMouseClick(event);
	}

	@Override
	public void mouseEntered(MouseEvent event)
	{
		//FUCK THIS
	}

	@Override
	public void mouseExited(MouseEvent event)
	{
		//FUCK THIS
	}

	@Override
	public void mousePressed(MouseEvent event)
	{
		buff.onMousePress(event);
	}

	@Override
	public void mouseReleased(MouseEvent event)
	{
		buff.onMouseRelease(event);
	}

	@Override
	public void keyPressed(KeyEvent event)
	{
		buff.onKeyPress(event);
	}

	@Override
	public void keyReleased(KeyEvent event)
	{
		buff.onKeyRelease(event);
	}

	@Override
	public void keyTyped(KeyEvent event)
	{
		//FUCK THIS
	}
	
}
