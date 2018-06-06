import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class BlockBreakerPanel extends JPanel implements KeyListener{
	
	ArrayList<Block> blocks = new ArrayList<>();
	ArrayList<Block> balls = new ArrayList<>();
	ArrayList<Block> powerup = new ArrayList<>();
	Block paddle;
	Thread thread;
	Animate animate;
	int score = 0;
	int ballDiameter = 25;
	
	
	BlockBreakerPanel(){
		
		paddle = new Block(175, 480, 150, 25, "paddle.png");
		balls.add(new Block(237, 437, 25, 25, "ball.png"));
		
		for(int i=0; i<8; i++) {
			blocks.add(new Block(i*60+2, 0, 60, 25, "blue.png"));
		}
		for(int i=0; i<8; i++) {
			blocks.add(new Block(i*60+2, 25, 60, 25, "yellow.png"));
		}
		for(int i=0; i<8; i++) {
			blocks.add(new Block(i*60+2, 50, 60, 25, "green.png"));
		}
		for(int i=0; i<8; i++) {
			blocks.add(new Block(i*60+2, 75, 60, 25, "red.png"));
		}
		
		Random random = new Random();
		int noOfPowerUpBlocks = 10;
		for(int i=0; i<noOfPowerUpBlocks; i++) {
			blocks.get(random.nextInt(4*8)).isPowerup = true;
		}
		
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Block b: blocks) {
			b.draw(g,  this);
		}
		for(Block b: balls) {
			b.draw(g, this);
		}
		for(Block p: powerup) {
			p.draw(g,  this);
		}
		paddle.draw(g, this);
		g.drawString("Score: "+score+"/32", 20, 550);
	}

	public void update() {
		
		for(Block p : powerup) {
			p.y+=1;
			if(p.intersects(paddle) && !p.isDestroyed) {
				p.isDestroyed = true;
				balls.add(new Block(p.x, 437 ,25,25,"ball.png"));
			}
		}
		
		
		for(Block ball : balls) {
			
			ball.x += ball.dx;			
			//if the ball is on left hand side of the screen change x direction or if on RHS.
			if(ball.x > (getWidth()-ballDiameter) && ball.dx>0 || ball.x<0) {
				ball.dx*=-1;
			}
			
			//if ball reaches the top of the screen or the paddle then change y direction
			if(ball.y < 0 || ball.intersects(paddle)) {
				ball.dy*=-1;
			}
			ball.y += ball.dy;
			//System.out.println(ball.x +", "+ ball.y);
			
			for(Block block : blocks) {
				if(ball.intersects(block) && !block.isDestroyed) {
					score+=1;
					if(block.isPowerup) {
						powerup.add(new Block(block.x, block.y, 25, 19, "extra.png"));
					}
					
					block.isDestroyed = true;
					ball.dy*=-1;
				
				
				}else if((ball.intersects(block.left) || ball.intersects(block.right)) && !block.isDestroyed) {
					score+=1;
					if(block.isPowerup) {
						powerup.add(new Block(block.x, block.y, 25, 19, "extra.png"));
					}
					
					
					ball.dx*=-1;
					block.isDestroyed = true;
				}
			}
			
		}
		repaint();
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//To Start Game Use Enter
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x>-5) {
			paddle.x -= 25;
			System.out.println(paddle.x);
			

		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x<340) {
			paddle.x += 25;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
