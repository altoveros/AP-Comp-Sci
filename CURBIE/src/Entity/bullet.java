package Entity;

import java.awt.Graphics;

import com.tutorial.mario.Game;
import com.tutorial.mario.Handler;
import com.tutorial.mario.Id;

import Tile.Tile;
import gfx.sprite;




public class bullet extends EntityB {

	private int facing;

	public Entity e;
	public sprite image;
	


	public bullet(int x, int y, int width, int height, Id id, Handler handler,
			int facing) {
		super(x, y, width, height, id, handler);
		this.facing = facing;
		
		image = new sprite(Game.sheet, 12, 1);
	}

	public void tick() {
		if (facing == 0)
			x -= 6;
		else
			x += 6;

		for (Tile t : handler.tile) { // collision detector
			if (t.isSolid()) {
				if (t.getId() == Id.wall || t.getId() == Id.mushroom)
				{
					if (t.getId() == Id.mushroom
							&& this.getBounds().intersects(t.getBounds())) {
						this.die2();
					}
					else if (t.getId() == Id.wall
							&& this.getBounds().intersects(t.getBounds())) {
						this.die2();
					}
				}
			}
		}
		for (int i = handler.entity.size() - 1; i >= 0; i--) { // for every
			
			e = handler.entity.get(i);
			if (e.getId() == Id.enemy || e.getId() == Id.wall
					|| e.getId() == Id.mushroom) {
				if (e.getId() == Id.enemy
						&& this.getBounds().intersects(e.getBounds())) {
					e.die();
					Game.coins++;
				}
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(image.getBufferedImage(), x, y, width, height, null);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}