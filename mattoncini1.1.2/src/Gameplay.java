

import java.awt.event.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;
//import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    
    
	private boolean avvio = false;
	private int punteggio = 0;
	private int bricksTotali = 48;
	
	private Timer timer;
	private int delay=8;
	
	private int giocatoreX = 310;
	
	private int pallapX = 120;
	private int pallapY = 350;
	private int direzionepX = -1;
	private int direzionepY = -2;
	
	private MapGen mappa;
	
	public Gameplay()
	{	
            Menu1 menu=new Menu1();
                        JFrame obj2 = new JFrame();
                        obj2.setBounds(705, 100, 320, 509);
            obj2.setResizable(false);
            obj2.setVisible(true);
            obj2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            obj2.add(menu);
            obj2.setVisible(true);
            int temp=-1;
            do{
            System.out.println("Fai la tua scelta di difficolta col menu");
            }while(menu.getContatore()==0);
            temp=menu.getDifficolta();
            

		mappa=new MapGen(temp);            
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
                timer=new Timer(delay,this);
		timer.start();
                
                repaint();
	}
	
        @Override
	public void paint(Graphics g)
	{
		// sfondo
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// disegnamo la mappa
		mappa.draw((Graphics2D) g);
		
		// bordi della finestra
		g.setColor(Color.pink);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// punteggio	
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial Black",Font.BOLD, 25));
		g.drawString(""+punteggio, 590,40);
                
                g.setColor(Color.WHITE);
		g.setFont(new Font("Arial Black",Font.BOLD, 18));
		g.drawString("MATTOncini", 90,40);
  
		
		// barra comandata
		g.setColor(Color.pink);
		g.fillRect(giocatoreX, 550, 100, 8);
		
		// palla
		g.setColor(Color.pink);
		g.fillOval(pallapX, pallapY, 20, 20);
	
		// when you won the game
		if(bricksTotali <= 0)
		{
			 avvio = false;
             direzionepX = 0;
     		 direzionepY = 0;
             g.setColor(Color.pink);
             g.setFont(new Font("Arial Black",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.pink);
             g.setFont(new Font("Arial Black",Font.BOLD, 20));           
             g.drawString("Clicca invio per restartare il gioco", 230,350);  
		}
		
		// when you lose the game
		if(pallapY > 570)
        {
			 avvio = false;
             direzionepX = 0;
     		 direzionepY = 0;
             g.setColor(Color.pink);
             g.setFont(new Font("Arial Black",Font.BOLD, 30));
             g.drawString("Game Over",200,300);
             g.drawString("Hai fatto: "+punteggio+" punti", 150,400);
             
             g.setColor(Color.pink);
             g.setFont(new Font("Arial Black",Font.BOLD, 20));           
             g.drawString("Clicca invio per restartare il gioco", 210,350);        
        }
		
		g.dispose();
	}	

        @Override
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_RIGHT){        
                    if(giocatoreX >= 600){
                        giocatoreX = 600;
                    }else{
                        moveRight();
                    }
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(giocatoreX < 10)
			{
				giocatoreX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
                    
			if(!avvio)
			{
				avvio = true;
				pallapX = ThreadLocalRandom.current().nextInt(250, 550);
				pallapY = 350;
                                do {
                                    direzionepX = ThreadLocalRandom.current().nextInt(-4, 3);
                                }while (direzionepX==0);
                                do {
                                    direzionepY = ThreadLocalRandom.current().nextInt(-2, 1);
                                }while (direzionepY==0);
				giocatoreX = 310;
				punteggio = 0;
			
                                mappa = new MapGen(2);
                                bricksTotali = 48;
                                        	
				repaint();
			}
        }		
	}

	public void keyReleased(KeyEvent e) {
        repaint();
        }
	public void keyTyped(KeyEvent e) {
        repaint();
        }
	
	public void moveRight()
	{
		avvio = true;
		giocatoreX+=30;	
	}
	
	public void moveLeft()
	{
		avvio = true;
		giocatoreX-=30;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(avvio)
		{			
			if(new Rectangle(pallapX, pallapY, 20, 20).intersects(new Rectangle(giocatoreX, 550, 30, 8)))
			{
				direzionepY = -direzionepY;
				direzionepX = -2;
			}
			else if(new Rectangle(pallapX, pallapY, 20, 20).intersects(new Rectangle(giocatoreX + 70, 550, 30, 8)))
			{
				direzionepY = -direzionepY;
				direzionepX = direzionepX + 1;
			}
			else if(new Rectangle(pallapX, pallapY, 20, 20).intersects(new Rectangle(giocatoreX + 30, 550, 40, 8)))
			{
				direzionepY = -direzionepY;
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<mappa.mappa.length; i++)
			{
				for(int j =0; j<mappa.mappa[0].length; j++)
				{				
					if(mappa.mappa[i][j] > 0)
					{
						//scores++;
						int brickX = j * mappa.brickWidth + 80;
						int brickY = i * mappa.brickHeight + 50;
						int brickWidth = mappa.brickWidth;
						int brickHeight = mappa.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(pallapX, pallapY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							mappa.setBrickValue(0, i, j);
							punteggio+=5;	
							bricksTotali--;
							
							// when ball hit right or left of brick
							if(pallapX + 19 <= brickRect.x || pallapX + 1 >= brickRect.x + brickRect.width)	
							{
								direzionepX = -direzionepX;
							}
							// when ball hits top or bottom of brick
							else
							{
								direzionepY = -direzionepY;				
							}
							
							break A;
						}
					}
				}
			}
			
			pallapX += direzionepX;
			pallapY += direzionepY;
			
			if(pallapX < 0)
			{
				direzionepX = -direzionepX;
			}
			if(pallapY < 0)
			{
				direzionepY = -direzionepY;
			}
			if(pallapX > 670)
			{
				direzionepX = -direzionepX;
			}		
			
			repaint();		
		}
	}
}
