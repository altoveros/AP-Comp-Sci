package mob;

import java.awt.Graphics;


import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

import Entity.Entity;
import State.PlayerState;
import Tile.Tile;
import com.tutorial.mario.State;

public class Player extends Entity{
	
	
	
	private int frame = 0;
	private int frameDelay = 0;
	
	private boolean animate = false;
	private boolean size;
	private int jumpF = 0;
	private int jumpFC = 0;

	public Player(int x, int y, int width, int height,  Id id, Handler handler) {
		super(x, y, width, height, id, handler);
		
	
	}

	
	public void render(Graphics g) {
		
		if(facing == 0) //left
		g.drawImage(Game.playerLeft[frame].getBufferedImage(), x, y, width, height, null);
		
		
		else if(jumping){
			
				g.drawImage(Game.playerFly[jumpF].getBufferedImage(),x,y,width,height,null);
			}
		
		
		
		else{
			g.drawImage(Game.playerRight[frame].getBufferedImage(), x, y, width, height, null);
		}
			
	
		
		

		
		
		
	}
	

	
	public void tick() {
		x+=velX;
		y+=velY;
		//if(getY() > Game.deathY) die();
//		if(x <= 0) x = 0;
//		if(x+width>=810) x = 810 - width;
		//if(y+height >= 578) y = 578-height;
		if(velX!=0) animate = true;
		else animate = false;
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
							if(getBoundsBottom().intersects(t.getBounds())&&!jumping){
								 falling = true;
								 gravity = 0.8;
									falling = true;
								 }
							//if(!falling && !jumping){
								//gravity = 0.8;
								//falling = true;
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
					if(getBounds().intersects(t.getBounds()) && t.getId() == Id.coin){
						Game.coins++;
						t.die();
						
					}
			
		}
		
			
	}
			
		
		
		for(int i = 0;  i < handler.entity.size();i++){
			Entity e = handler.entity.get(i);
			if(e.getId()==Id.mushroom)
			{
				if(getBounds().intersects(e.getBounds()))	{
				
					width*=2;
					height*=2;
					
					size = true;
					
					e.die();
				}
			}else if(e.getId() == Id.enemy){
				if(getBoundsBottom().intersects(e.getBoundsTop())){
					e.die();
					Game.coins++;
					
				}
				else if(getBounds().intersects(e.getBounds())){
					if(size){
						e.die();
						width/=2;
						height/=2;
						size = false;
						Game.coins++;
						falling = true;
					}
					else{
						die();
						Game.state = State.gameOver;
					}
					
				}
			}
		}

		
		if(jumping) {
			
			gravity-=0.17;
			setVelY((int) -gravity);
			jumpFC++;
			if(jumpFC % 5 == 0){
				jumpF++;
			}
			if(jumpF>= Game.playerFly.length){
				jumpF = 0;
			}
			if(gravity<=0.5) {
			jumping = false;
			falling = true;
			
			}
			}

			if(falling) {
			gravity+=0.17;
			setVelY((int) gravity);
			
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






