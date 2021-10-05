package gui;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import com.tutorial.mario.Game;

import com.tutorial.mario.State;

public class Button {
	
	public int x,y;
	public int width,height;
	
	public String label;

	public Button(int x, int y, int width, int height, String label) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.label = label;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans",Font.BOLD,30));
		
		FontMetrics fm = g.getFontMetrics();
		int stringX = (getWidth() + fm.stringWidth(getLabel())) / 2;
		int stringY = (fm.getAscent() + (getHeight() - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(getLabel(), getX() + stringX, getY() + stringY);
		
		
			
	}
	
	public void triggerEvent(){
		if(getLabel().toLowerCase().contains("start")) {
			Game.state = State.game;
			Game.coins = 0;
		}
		else if(getLabel().toLowerCase().contains("play again?") || getLabel().toLowerCase().contains("play?")) {
			Game.state = State.game;
			Game.coins = 0;
		}
		else if(getLabel().toLowerCase().contains("exit game")) System.exit(0);
		else if(getLabel().toLowerCase().contains("help")) Game.state = State.help;
		
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	

}
