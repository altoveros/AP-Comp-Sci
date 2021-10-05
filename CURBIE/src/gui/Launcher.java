package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.tutorial.mario.Game;

public class Launcher {
	
	public Button[] buttons;
	
	
	public Launcher(){
		buttons = new Button[3];
		
		
		buttons[0] = new Button(270,150,100,100,"Start");
		buttons[1] = new Button(200,320,100,100,"Exit Game");
		buttons[2] = new Button(275,225,100,100,"Help");
		
	}
	
	public void render(Graphics g){

		g.drawImage(Game.bg1, 0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE, null);
		g.setColor(Color.PINK);
		g.setFont(new Font("Courier",Font.BOLD,50));
		g.drawString("CURBIE", 280,140);
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i].render(g);
		}
	}

}
