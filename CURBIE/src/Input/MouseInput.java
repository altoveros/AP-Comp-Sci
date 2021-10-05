package Input;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.tutorial.mario.Game;

import gui.Button;
import com.tutorial.mario.State;


public class MouseInput implements MouseListener,MouseMotionListener{
	
	public int x,y;

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	
	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
		for(int i = 0; i < Game.launcher.buttons.length;i++){
			Button button = Game.launcher.buttons[i];
		
			if(x>= button.getX() && y >= button.getY()) button.triggerEvent();
			
		}
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

}
