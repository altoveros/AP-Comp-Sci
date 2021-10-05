package PowerUp;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

import Entity.Entity;
import Tile.Tile;

public class Mush extends Entity{

	public Mush(int x, int y, int width, int height,  Id id, Handler handler) {
		super(x, y, width, height,  id, handler);
		// TODO Auto-generated constructor stub
	}
	

	public void render(Graphics g){
		g.drawImage(Game.mushroom.getBufferedImage(),x,y,width,height,null);
	}
	
	public void tick(){
			
			
		x+=velX;
		y+=velY;

		for(int i=0;i<handler.tile.size();i++){
			Tile t = handler.tile.get(i);
			if(t.isSolid()){
					if(getBoundsTop().intersects(t.getBounds())){
						setVelY(0);
						if(jumping)
						{
							jumping = false;
							gravity = 0.8;
							falling = true;
						}
						
					}
						
						
					if(getBoundsBottom().intersects(t.getBounds())){
						setVelY(0);
						if(falling) 
							{
							falling = false;
							}
						else{
							if(!falling && !jumping){
								gravity = 0.8;
								falling = true;
							}
						}
					}
					if(getBoundsLeft().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX() + t.width;
						
						
					}
					if(getBoundsRight().intersects(t.getBounds())){
						setVelX(0);
						x = t.getX() - t.width;
						
						
					}
			
		}
		
			}
	}
}
