package gfx;

import java.awt.image.BufferedImage;

public class sprite {
	
	public SpriteSheet sheet;
	
	public BufferedImage image;
	
	public sprite(SpriteSheet sheet, int x, int y){
		image = sheet.getSprite(x, y);
	}
	
	public BufferedImage getBufferedImage(){
		return image;
	}

}
