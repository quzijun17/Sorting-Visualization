import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.color.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

public class ArrayBars extends JComponent{

	
	public synchronized void setValues(Double[] values, Double a, Double b) {
		this.values = values.clone();
		this.key1 = a;
		this.key2 = b;
		repaint();
	}
	
	public synchronized void paintComponent(Graphics gra) {
		if (values == null)
			return ;
		Graphics2D g2d = (Graphics2D) gra;
		g2d.setColor(Color.black);
	
		int width = getWidth()/(values.length);
		
		for (int i=0; i<values.length; i++) 
		{
			double height = values[i] * getHeight();

			Rectangle2D Rectan = new Rectangle2D.Double(width*i, this.getHeight()-height-0.5, width, height);
			if (values[i] == key1 || values[i] == key2)
			{
				g2d.fill(Rectan);

			}
			else 
				g2d.draw(Rectan);
		}
	}
	
	private Double key1;
	private Double key2;
	private Double[] values;
}
