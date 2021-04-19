
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener, ActionListener 
{

	private boolean play = false;
	private int score = 0;
	private int totalBricks = 21;
	public int x; 
	private Timer timer;
	public int delay = 7; // easy = 7 , medium = 5 , hard = 4
	
	private int playerX = 310; 
	
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;  // in which direction the ball moves (its next location)
	private int ballYdir = -2;  // in which direction the ball moves (its next location)
	
	private MapGenerator map;
	
	public GamePlay() {       //public GamePlay(int a) {
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
//		delay = a;
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void level(int x) {
		
		
	}
        public void paint(Graphics g) {
		
		g.setColor(Color.black); //background
		g.fillRect(1, 1, 692, 592);
		
		g.setColor(Color.black); //border
		g.fillRect(0, 0, 6, 592);
		g.fillRect(0, 0, 692, 6);
		g.fillRect(692, 0, 6, 592);
		
		map.draw((Graphics2D)g); //Bricks
		
//		if (!play) {
//			g.setColor(Color.white);
//			g.setFont(new Font("serif", Font.BOLD, 30));
//			g.drawString("Press 1 for EASY", 200, 300);
//			g.drawString("Press 2 for MEDIUM", 200, 350);
//			g.drawString("Press 3 for HARD", 200, 400);
//		}
		
		
		g.setColor(Color.white); //pedal
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.white); //ball
		g.fillOval(ballposX, ballposY, 20, 20);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 30));
		g.drawString("" + score, 590, 40);
		
		
		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("YOU WON", 250, 246);
			
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Your Score:  " + score, 250, 296);
			g.drawString("Press ENTER to Restart", 250, 346);
		}
		
		if (ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			
			g.setColor(Color.red);
			
			g.setFont(new Font("serif", Font.BOLD, 35));
			g.drawString("GAME OVER", 250, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("Your Score:  " + score, 250, 350);
			g.drawString("Press ENTER to Restart", 250, 400);
		}
		
		g.dispose();
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if(play) {
			//Ball and Pedal interation
			if(new Rectangle(ballposX, ballposY, 20, 30).intersects(new Rectangle(playerX, 550, 100, 8)))
			{
				ballYdir = - ballYdir;
			}
			//(the object map of GamePlay class has variable map of MapGenerator class) - its length
			for(int i=0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.brickWidth + 80;
						int brickY = i*map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeigth = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeigth); 
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						
						if(ballRect.intersects(brickRect)) { //to delete the brick which the ball hit
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								//ball bounces back after hitting brick 
								// brickRect.x means x location of brickRect 
								ballXdir = - ballXdir;
							}
								else {
									ballYdir = - ballYdir;
								}
							}
						}
					}
				}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0) {
				ballXdir = - ballXdir;
			} //left end side
			
			if(ballposX > 670) {
				ballXdir = - ballXdir;
			} //right end side
			
			if(ballposY < 0) {
				ballYdir = - ballYdir;
			} //top side
			
			repaint();
		}
	}
		
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
//		if(e.getKeyCode() == KeyEvent.VK_1) {
//			x = 1;
//			difficulty();
//		}
//		if(e.getKeyCode() == KeyEvent.VK_2) {
//			x = 2;
//			difficulty();
//		}
//		if(e.getKeyCode() == KeyEvent.VK_3) {
//			x = 3;
//			difficulty();
//		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
				
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play ) {
				
				play = true;
				score = 0;
				totalBricks = 21;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;  
				ballYdir = -2; 
				map = new MapGenerator(3,7);
				
				repaint();
			}
		}
	}
	
//	public int difficulty() {
//		int y = 0;
//		if (x == 1) {
//			y = 7;
//		}
//		
//		if (x == 2) {
//			y = 5;
//		}
//		
//		if (x == 3) {
//			y = 4;
//		}
//		return y; 
//	}
	
	public void moveRight() {
	       play = true;
	       playerX += 20;
	}	
	public void moveLeft() {
	       play = true;
	       playerX -= 20;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}


 
