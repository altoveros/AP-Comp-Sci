package mob;

import java.awt.Graphics;
import java.util.Random;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

import Entity.Entity;
import Tile.Tile;

public class enemy extends Entity{

	private Random random = new Random();
	public enemy(int x, int y, int width, int height,  Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		// TODO Auto-generated constructor stub
	
	
	int dir =  random.nextInt(2);
	switch(dir){
	case 0:
		setVelX(-2);
		facing = 0;
		break;
	case 1:
		setVelX(2);
		facing = 1;
		break;
		
	}
	}

	
	public void render(Graphics g){
		if(facing == 0) //left
			g.drawImage(Game.enemy[frame].getBufferedImage(), x, y, width, height, null);
			
			
			else {
				g.drawImage(Game.enemy[frame+5].getBufferedImage(), x, y, width, height, null);
			}
			
	}

	
	public void tick(){
		x+=velX;
		y+=velY;
		for(int i =0; i < handler.tile.size();i++){
			Tile t = handler.tile.get(i);
			if(t.isSolid()){

			
			if(getBoundsBottom().intersects(t.getBounds())){
				setVelY(0);
				if(falling) 
					{
					falling = false;
					}
				else if(!falling){
					falling = true;
					gravity = 0.8;
				}
			}
			if(getBoundsLeft().intersects(t.getBounds())){
				setVelX(2);
				facing = 1;
				
				
				
			}
			if(getBoundsRight().intersects(t.getBounds())){
				setVelX(-2);
				facing  = 0;
				
				
			}
				if(animate){
					frameDelay++;
					if(frameDelay >= 3){
						frame++;
						if(frame>=Game.playerLeft.length){
							frame = 0;
						}
						frameDelay = 0;
					}
					
				}
				
			}
		}
	}
}

