import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Red_Ball extends Ball {

	Random r = new Random();
	final double inchesPerCM = 0.394;
	final double pixelsPerIN = 120;
	
	
	public Red_Ball() {
		super();

		int magnitudeX = r.nextInt(3) + 4;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 4;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	public Red_Ball ( int x1, int y1 ) {
		super( x1, y1 );
		
		x = x1; y = y1; 
		int magnitudeX = r.nextInt(3) + 4;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 4;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	public Red_Ball ( String position ) {
		super( position );
		
		int magnitudeX = r.nextInt(3) + 4;
		vx = magnitudeX * inchesPerCM * pixelsPerIN;
		
		int magnitudeY = r.nextInt(3) + 4;
		vy = magnitudeY * inchesPerCM * pixelsPerIN;
	}
	
	@Override
	public void drawMe( Graphics g ) {	
		g.setColor( Color.RED );
		g.fillOval( (int)(x-5), (int)(y-5), 10, 10 );
	}
}
