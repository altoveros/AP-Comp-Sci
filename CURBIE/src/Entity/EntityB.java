package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;



public abstract class EntityB {
	
	public int x, y;
	public int width, height;
	public int velX, velY;
	public int facing = 0; // int that decides if character is facing left or right; right = 1, left = 0.

	public Id id; 
	public Handler handler;
	
	public boolean jumping = false;
	public boolean falling = false;
	public boolean goingDownPipe = false;
	
	public double gravity = 0.0;
	
	public EntityB(int x, int y, int width, int height, Id id, Handler handler){
		this.x = x;
		this.y = y;
		this.width = height; 
		this.height = height;
		this.id = id; 
		this.handler = handler; 
		jumping = false;
	}

	public abstract void render(Graphics g);
	
	public abstract void tick();
	
	public void die2(){
		handler.removeEntityB(this);
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

	public Id getId(){
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	//for collision detector; finds bounds of the rectangle
	public Rectangle getBounds(){
	    return new Rectangle(getX(),getY(),width,height);
	}
	
	public Rectangle getBoundsTop(){
	    return new Rectangle(getX()+10, getY(), width-20, 5);
	}
	
	public Rectangle getBoundsBottom(){
	    return new Rectangle(getX(), getY() +height-5, width-20, 5);
	}
	
	public Rectangle getBoundsLeft(){
	    return new Rectangle(getX(),getY()+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight(){
	    return new Rectangle(getX()+width-5 ,getY()+10, 5, height -20);
	}
}
