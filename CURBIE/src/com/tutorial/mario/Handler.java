package com.tutorial.mario;

import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Entity.Entity;
import Entity.EntityB;
import Entity.bullet;

import PowerUp.Mush;
import Tile.Cloud;
import Tile.Tile;
import Tile.Wall;

import mob.Player;
import mob.enemy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Handler {

	public List<Entity> entity = new CopyOnWriteArrayList<Entity>();
	public List<EntityB> entityB = new CopyOnWriteArrayList<EntityB>();
	public List<Tile> tile = new CopyOnWriteArrayList<Tile>();
	public List<bullet> b = new CopyOnWriteArrayList<bullet>();

	public Handler() {

	}

	public void render(Graphics g) {
		for (Entity en : entity) {
			if(Game.getVisibleArea() != null && en.getBounds().intersects(Game.getVisibleArea())) en.render(g);
		}

		for (Tile ti : tile) {
			if(Game.getVisibleArea() != null && ti.getBounds().intersects(Game.getVisibleArea())) ti.render(g);
		}
		
		for(bullet bi: b){
			bi.render(g);
		}
	}

	public void tick() {
		for (Entity en : entity) {
			en.tick();
		}

		for (Tile ti : tile) {
			if(Game.getVisibleArea() != null && ti.getBounds().intersects(Game.getVisibleArea())) ti.tick();
		}
		
		for(bullet bi: b){
			bi.tick();
		}
	}

	public void addEntity(Entity en) {
		entity.add(en);
	}

	public void removeEntity(Entity en) {
		entity.remove(en);
	}

	public void addTile(Tile ti) {
		tile.add(ti);
	}

	public void removeTile(Tile ti){
		tile.remove(ti);
	}
	
	public void addEntityB(EntityB block){
		entityB.add(block);
	}
	public void removeEntityB(EntityB block){
		entityB.remove(block);
	}

	public void createLevel(BufferedImage level){
		int width = level.getWidth();
		int height = level.getHeight();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int pixel = level.getRGB(x, y);

				int red = (pixel >> 16) & 0xff; // gets full value of each pixel
				int green = (pixel >> 8) & 0xff; // >> shifts the binary code,
				// red shifts 16 times,
				// green 8.
				int blue = (pixel) & 0xff;

				if (red == 0 && green == 0 && blue == 0) { // if its a black dot
					// @level.png it
					// will draw a
					// sidewalk block at
					// it.
					this.addTile(new Wall(x * 64, y * 64, 64, 64, true,
							Id.wall, this));
				}
				if (red == 0 && green == 0 && blue == 255) { // the pure blue
					// dot blue:255
					// will spawn
					// our player
					this.addEntity(new Player(x * 64, y * 64, 64, 64,Id.player, this));
				}
				
				if(red==255 && green== 0 && blue == 0){
					addEntity(new Mush(x*64,y*64,64,64,Id.mushroom,this));
				}

				if(red==0 && green == 255 && blue == 0){
					addEntity(new enemy(x*64,y*64,64,64,Id.enemy,this));
				}
				
				if(red==255 && green == 0 && blue == 255){
					this.addTile(new Cloud(x * 64, y * 64, 64, 64, true,
							Id.wall, this));
				}

			}



//Game.deathY = Game.getDeathY();




		}
		
	}
	public void clearLevel(){
		entity.clear();
		tile.clear();
	}

	
}

