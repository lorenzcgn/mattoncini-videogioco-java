
import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
            JFrame obj=new JFrame();
            Gameplay gioco = new Gameplay();
            
            obj.setBounds(10, 10, 700, 600);
            obj.setTitle("Mattoncini 1.1.2 - Huqi & Cagni");		
            obj.setResizable(false);
            obj.setVisible(true);
            obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            obj.add(gioco);
            obj.setVisible(true);
	}
}