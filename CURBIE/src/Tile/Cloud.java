package Tile;

import java.awt.Color;
import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public class Cloud extends Tile{

	public Cloud(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
		// TODO Auto-generated constructor stub
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.cloud.getBufferedImage(), x, y, width, height, null);
	}

	@Override
	public void tick() {
		
	}

}