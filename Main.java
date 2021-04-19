
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		JFrame obj = new JFrame();
		
		
		GamePlay game = new GamePlay();
		
        //int b = difficulty();
       //GamePlay game1 = new GamePlay(b);
		
		obj.setTitle("Brick Breaker");
		obj.setBounds(10, 10, 700, 600);	
		obj.setResizable(false);
		
		obj.add(game);
		
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		

		
	}

}
