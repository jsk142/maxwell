import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Blue_Ball extends Ball {

	Random r = new Random();
	final double inchesPerCM = 0.394;
	final double pixelsPerIN = 120;
	
	
	public Blue_Ball() {
		super();

		int magnitudeX = r.nextInt(3) + 2;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 2;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	public Blue_Ball ( int x1, int y1 ) {
		super( x1, y1 );
		
		x = x1; y = y1; 
		int magnitudeX = r.nextInt(3) + 2;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 2;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	public Blue_Ball ( String position ) {
		super( position );
		
		int magnitudeX = r.nextInt(3) + 2;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 2;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	@Override
	public void drawMe( Graphics g ) {	
		g.setColor( Color.BLUE );
		g.fillOval( (int)(x-5), (int)(y-5), 10, 10 );
	}
}
