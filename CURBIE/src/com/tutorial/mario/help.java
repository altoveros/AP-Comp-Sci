package com.tutorial.mario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import gui.Button;

public class help {


public static void render(Graphics g){
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		Font fnt1 = new Font("arial", Font.BOLD, 20);
		Font fnt2 = new Font("arial", Font.BOLD, 30);
		
		g.setColor(Color.BLACK);
		//g.drawRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.fillRect(0, 0, Game.getFrameWidth()+100, Game.getFrameHeight()+100);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Help", 350, 100);
		g.setFont(fnt2);
		g.drawString("PLAY?", 270, 150);
		g.setFont(fnt1);
		g.drawString("Use the keys W, A, S, D to move, jump on enemies to kill and score!", Game.WIDTH-160, 270);
		
	}
	
}
