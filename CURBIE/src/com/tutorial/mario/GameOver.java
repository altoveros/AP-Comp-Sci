package com.tutorial.mario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;



public class GameOver {
	
public static void render(Graphics g){
		
	g.setColor(Color.BLACK);
	g.setFont(new Font("Courier",Font.BOLD,50));
	g.drawImage(Game.die, 355,225,75,75,null);
	g.drawString("Game Over!", 260,375);
	
	g.drawString("Play Again?", 240, 170);
	g.drawString("Quit?", 600, 500);
	}

}
