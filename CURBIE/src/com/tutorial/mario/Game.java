package com.tutorial.mario;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import Entity.Entity;
import Input.MouseInput;
import Input.keyInput;
import Tile.Tile;
import gfx.SpriteSheet;
import gfx.sprite;
import gui.Launcher;
import mob.Player;


public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 270;
	public static final int HEIGHT = WIDTH / 14 * 10;
	public static final int SCALE = 3;
	public static final String TITLE = "CURBIE";
	
	private BufferedImage image;
	public static int coins = 0;
	
	private Thread thread;
	private boolean running = false;
	private GameOver gameOver;
	
	
	
	public static Handler handler;
	public static SpriteSheet sheet;
	public static camera cam; 
	public static Launcher launcher;
	public static SpriteSheet sheet2;
	public static int lives = 0;
	public static int deathScreenTime = 0;
	
	public static boolean playing = false;
	private static BufferedImage background;
	
	public static boolean showDeathScreen = false;
	public static boolean playAgain = false;
	public static MouseInput mouse;
	public static State state = State.menu;
	public static int deathY = 0;
	
	public static sprite grass;
	public static sprite playerRight[] = new sprite[11]; //subject to change
	public static sprite playerLeft[] = new sprite[11]; //subject to change
	public static sprite playerFly[] = new sprite[3]; 
	public static sprite playerAtk[] = new sprite[2];
	public static sprite mushroom;
	public static sprite enemy[] = new sprite[11];
	public static sprite coin;
	public static sprite cloud;
	public static sprite idle[] = new sprite[5];
	public static sprite playerFall[] = new sprite[3];
	public static BufferedImage die;
	public static BufferedImage bg1;
	
	
	
	
	public synchronized void start(){
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	
	public synchronized void stop(){
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
		init();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int frames  = 0;
		int ticks  = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames + " Frames Per Second " + ticks + " Updates per Second");
				frames = 0;
				ticks = 0;
			}

		}
		
		stop();
	}
	
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		
		//g.drawImage(coin.getBufferedImage(), 20	, 20, 75,75,null);
		if(state == State.game){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier",Font.BOLD,50));
			g.drawString("x" + coins, 100,95);
			g.translate(cam.getX(), cam.getY());
			handler.render(g);
		}
		else if(state == State.gameOver){
			
			gameOver.render(g);
			//g.setColor(Color.WHITE);
			//g.setFont(new Font("Courier",Font.BOLD,50));
			//g.drawImage(die, 355,225,75,75,null);
			//g.drawString("Game Over!", 250,200);
		}
	
		
		
		
		else if(state == State.menu) launcher.render(g);
		else if(state == State.help) help.render(g);
		g.dispose();
		bs.show();
	}
	
	public void tick() {
		 if(state == State.game)handler.tick();
		  for(int i=0;i<handler.entity.size();i++) {
			   Entity e = handler.entity.get(i);
			   if(e.getId()==Id.player) {
			     cam.tick(e);
			   }
			  }
		  //if(showDeathScreen) deathScreenTime++;
		  if(state == State.gameOver){
			  showDeathScreen = false;
			  handler.clearLevel();
			  handler.createLevel(image);
			  
		  }
		  }
	
		
		  
			
		
		
	
	
	public static int getFrameWidth(){
		return WIDTH * SCALE;
	}
	
	public static int getFrameHeight(){
		return HEIGHT * SCALE;
	}
	
	public static Rectangle getVisibleArea(){
		for(int i =0; i< handler.entity.size();i++){
			Entity e = handler.entity.get(i);
			if(e.getId() == Id.player) return new Rectangle(e.getX()-(getFrameWidth()/2-5),e.getY()-(getFrameHeight()/2-1),getFrameWidth()+10,getFrameHeight() + 100);
			}
		return null;
	}
	
	/*public static int getDeathY(){
		LinkedList<Tile> tempList = (LinkedList<Tile>) handler.tile;
		
		Comparator<Tile> tileSorter = new Comparator<Tile>(){

			public int compare(Tile ti, Tile t2) {
				if(ti.getY() > t2.getY()) return -1;
				if(ti.getY() < t2.getY()) return 1;
				return 0;
			}
			
		};
		
		Collections.sort(tempList,tileSorter);
		return tempList.getFirst().getY() + tempList.getFirst().getHeight();
	}*/
	
	
	public Game(){
		
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		
		
	}
	
	public void init(){
		handler = new Handler();
		sheet = new SpriteSheet("/SpriteSheet.png");
		cam  = new camera();
		launcher  = new Launcher();
		mouse = new MouseInput();
		
		addKeyListener(new keyInput());
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		grass = new sprite(sheet,1,1);
		mushroom = new sprite(sheet,2,4);
		//enemy = new sprite(sheet2,1,2);
		coin = new 	sprite(sheet,2,4);
		cloud = new sprite(sheet,1,4);
		
		
		for(int i=0; i < enemy.length;i++){
			enemy[i] = new sprite(sheet,i+1,3);
		}
		for(int i = 0; i < playerRight.length; i++)
		{
			playerRight[i] = new sprite(sheet,i+1,7);
		}
		for(int i = 0;  i < playerLeft.length;i++)
		{
			playerLeft[i] = new sprite(sheet, i+1, 8);
		}
		
	
		for(int i =0; i < playerFly.length;i++){
			playerFly[i] = new sprite(sheet,i+1,9);
		}
		for(int i = 0; i < playerFall.length;i++){
			playerFall[i] = new sprite(sheet,4,9);
		}
		
		for(int i = 0; i < playerAtk.length; i++){
			playerAtk[i] = new sprite(sheet,11,2);
		}
		
		
//		handler.addEntity(new Player(-200,-200,64,64,true,Id.player,handler));
		try {
			image = ImageIO.read(getClass().getResource("/Level4.png"));
			background = ImageIO.read(getClass().getResource("/grassbg1.png"));
			bg1 = ImageIO.read(getClass().getResource("/background.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		try {
			die = ImageIO.read(getClass().getResource("/Death.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//handler.addEntity(new Player(300,512,64,64,true,Id.player,handler));
		handler.createLevel(image);
	}
	

		
	
	
	public static void main(String[] args)
	{
		Game game = new Game();
		JFrame frame = new JFrame(TITLE);
		frame.add(game);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
			
	}
	

	


}
	
	
	
	

	
	
	
	
	



