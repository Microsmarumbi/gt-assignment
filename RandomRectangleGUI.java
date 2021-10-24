/* Marumbi Solomon Micros */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RandomRectangleGUI {

  JFrame frame;
	RandomRectDrawPanel drawPanel=new RandomRectDrawPanel();
	JButton colorButton;
	JButton sizeButton;
        

	public static void main (String[] args){
		RandomRectangleGUI gui = new RandomRectangleGUI();
		gui.go();
	}

	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                colorButton = new JButton("Click me for a random colour");
                colorButton.addActionListener(new RandomColorListener());
		sizeButton = new JButton("Click me for a random size");
                sizeButton.addActionListener(new SizeListener());
                frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
		frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}

        class RandomColorListener implements ActionListener{
                public void actionPerformed(ActionEvent e){
                drawPanel.changeColor();
            }
        } 
        
        class SizeListener implements ActionListener{
                public void actionPerformed(ActionEvent e){
                drawPanel.randomSize();
            }
        }
        
        class RandomRectDrawPanel extends JPanel{
               GradientPaint color;
		int height = 50;
		int width = 80;
		int x = 50;
		int y = 50;
                
                 public RandomRectDrawPanel(){
                        Color color1 = getrandomColor();
                        Color color2 = getrandomColor();
                        color= new GradientPaint(x, y, color1, x, y, color2);
                }

		public void paintComponent (Graphics g){
			 g.fillRect(0, 0, this.getWidth(), this.getHeight());
                         Graphics2D g2d = (Graphics2D) g;
                         g2d.setPaint(color);
                         g2d.fillRect(x,y,width,height);
		}
                public void changeColor(){
                        Color color1 = getrandomColor();
                        Color color2 = getrandomColor();
                        color = new GradientPaint(x, y, color1, x, y, color2);
                        this.repaint();
                }
		
		Color getrandomColor(){
			int r = (int)(Math.random()*255);
			int gr = (int)(Math.random()*255);
			int b = (int)(Math.random()*255);
                        Color randomColor = new Color(r, gr, b);
                        return randomColor;  
		}
		
		public void randomSize(){
			int displace = 5;
			height = (int)(Math.random()*getHeight());
			width = (int)(Math.random()*getWidth());

			int temp;
			if ((y + height) > getHeight()){  // this to keep all of the height of the rectangle inside the draw panel
				temp = getHeight() - (y + height);
				height = height + temp - displace;  // temp is a negative number
			}
			if (height < 5) height = 5;//minimum height

			if ((x + width) > getWidth()){  // this to keep all of the width of the rectangle inside the draw panel
				temp = getWidth() - (x + width);
				width = width + temp - displace;  // temp is a negative number
			}
			if (width < 5) width = 5; //minimum width
		}				
	}
}




