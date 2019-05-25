
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MapGen
{
	public int mappa[][];
	public int brickWidth;
	public int brickHeight;
        public int mattoncini;

    public int getMattoncini() {
        return mattoncini;
    }

    public void setMattoncini(int mattoncini) {
        this.mattoncini = mattoncini;
    }
        
        
	
	public MapGen (int scelta)
	{
            int row=0;
            int col=0;
            switch (scelta){
                                    case 0: 
                                        mappa = new int[4][7];
                                        setMattoncini(28);
                                         row=4;
                                         col=7;
                                        break;
                                    case 1: 
                                        mappa = new int[5][12];
                                        setMattoncini(60);
                                         row=4;
                                         col=12;
                                        break;
                                    case 2:
                                        mappa = new int[7][12];
                                        setMattoncini(72);
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
					//g.setColor(Color.white);
                                        BufferedImage image = this.mattoncino();
                                        g.drawImage(image, j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight, null);
                                        g.setStroke(new BasicStroke(3));
					//g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
					
					// this is just to show separate brick, game can still run without it
					
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
        
                public BufferedImage mattoncino()
  {
      BufferedImage image = null;
    try
    {
        image = ImageIO.read(new File("mattoncini.jpg"));
    } 
    catch (IOException e)
    {
    }
    return image;
  }
                
}