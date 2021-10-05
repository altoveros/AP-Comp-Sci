package com.tutorial.mario;

import Entity.Entity;

public class camera {
	
	public int x,y;
	

	public void tick(Entity player){
		setX(-player.getX() + Game.WIDTH * Game.SCALE / 2);
		setY(-player.getY() + Game.HEIGHT);
		
		
	}

	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
