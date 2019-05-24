
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGen
{
	public int mappa[][];
	public int brickWidth;
	public int brickHeight;
	
	public MapGen (int scelta)
	{
            int row=0;
            int col=0;
            switch (scelta){
                                    case 0: 
                                        mappa = new int[4][7];
                                         row=4;
                                         col=7;
                                        break;
                                    case 1: 
                                        mappa = new int[5][12];
                                         row=4;
                                         col=12;
                                        break;
                                    case 2:
                                        mappa = new int[7][12];
                                         row=5;
                                         col=12;
                                        break;
                                }			
		for(int i = 0; i<mappa.length; i++)
		{
			for(int j =0; j<mappa[0].length; j++)
			{
				mappa[i][j] = 1;
			}			
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
        
	
	public void draw(Graphics2D g)
	{
		for(int i = 0; i<mappa.length; i++)
		{
			for(int j =0; j<mappa[0].length; j++)
			{
				if(mappa[i][j] > 0)
				{
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					// this is just to show separate brick, game can still run without it
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col)
	{
		mappa[row][col] = value;
	}
}