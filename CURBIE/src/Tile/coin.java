package Tile;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

public class coin extends Tile{

	public coin(int x, int y, int width, int height, boolean solid, Id id, Handler handler) {
		super(x, y, width, height, solid, id, handler);
	}

	
	public void render(Graphics g) {
		g.drawImage(Game.coin.getBufferedImage(), x, y, width, height, null);
		
	}

	
	public void tick() {
		
	}

}
