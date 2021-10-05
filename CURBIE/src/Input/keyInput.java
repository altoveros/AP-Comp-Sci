package Input;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import com.tutorial.mario.Game;
import com.tutorial.mario.Id;

import Entity.Entity;
import Entity.bullet;
import Tile.Tile;




public class keyInput implements KeyListener{
	

	
	public void keyTyped(KeyEvent e) {
		//not using
	}

	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		for(int i = 0; i <Game.handler.entity.size();i++){
			Entity en = Game.handler.entity.get(i);
			if(en.getId() == Id.player){
				
					switch(key){
					case KeyEvent.VK_W:
				if(!en.jumping) en.jumping = true;
				en.gravity = 8.0;
				if(en.gravity >= 16){
					break;
				}
				break;
			case KeyEvent.VK_A:
				en.setVelX(-5);
				en.facing = 0;
				break;
			case KeyEvent.VK_D:
				en.setVelX(5);
				en.facing = 1;
				for(int q = 0; q<Game.handler.tile.size(); q++){
					 Tile t = Game.handler.tile.get(q);
						 if(!en.getBoundsBottom().intersects(t.getBounds())&&!en.jumping){
							 en.falling = true;
							 }
				}
				break;
				
			case KeyEvent.VK_SPACE:
				Game.handler.addEntityB(new bullet(en.getX(), en.getY(), 20, 20, Id.bullet, Game.handler, en.facing));

				
				break;
			case KeyEvent.VK_UP:
				if(!en.jumping) en.jumping = true;
				break;
			case KeyEvent.VK_DOWN:
				if(!en.jumping) en.jumping = true;
				
				break;
			case KeyEvent.VK_LEFT:
				en.setVelX(-5);
				break;
			case KeyEvent.VK_RIGHT:
				en.setVelX(5);
				break;
			



			}
			}
		}
			
		
			
		
	
		
	}




	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i = 0; i <Game.handler.entity.size();i++){
			Entity en = Game.handler.entity.get(i);
			if(en.getId() == Id.player){
			switch(key){
			case KeyEvent.VK_W:
				en.setVelY(0);
				break;
			case KeyEvent.VK_S:
				en.setVelY(0);
				break;
			case KeyEvent.VK_A:
				en.setVelX(0);
				break;
			case KeyEvent.VK_D:
				en.setVelX(0);
				break;
			case KeyEvent.VK_UP:
				en.setVelY(0);
				break;
			case KeyEvent.VK_DOWN:
				en.setVelY(0);
				break;
			case KeyEvent.VK_LEFT:
				en.setVelX(0);
				break;
			case KeyEvent.VK_RIGHT:
				en.setVelX(0);
				break;
		
	}
	

		}
	}
}
}
