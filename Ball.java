import java.awt.Color;
import java.awt.Graphics;

public abstract class Ball
{
	double x, y;
	double vx, vy;
		
	public Ball( int x1, int y1 )
	{
		x = x1; y = y1; 
	}
		
	public Ball()
	{
		do {
			x = Math.random() * 300 + 250;
		} while ( x >= 385 && x <= 415 );
 
		y = Math.random() * 300 + 100;
	}
	
	public Ball( String position ) {
		if ( position.equals("left")) {
			do {
				x = Math.random() * 300 + 250;
			} while ( x >= 385 );
		} else {
			do {
				x = Math.random() * 300 + 250;
			} while ( x <= 415 );
		}
		
		y = Math.random() * 300 + 100;
	}
		
	public void move( double deltat, double doorY )
	{
		x += vx * deltat;
		y += vy * deltat;
		stayOnScreen();
		bounceOffWall( doorY );
		bounceOffDoor( doorY );
	}
		
	public void stayOnScreen()
	{
		if ( x <= 5 ) { vx *= -1; }
		if ( y <= 50 ) { vy *= -1; }
		if ( x >= (800 - 5) ) { vx *= -1; }
		if ( y >= (468 - 5) ) { vy *= -1; }
	}
	
	public void bounceOffWall( double doorY ) {
		if ( y <= (doorY + 66) || y >= 282 ) {
			if ( (int)x == 385 || (int)x == 415 ) {
				 vx *= -1;
			}
		} 
	}
	
	public void bounceOffDoor( double doorY ) {
		if ( x > 385 && x < 415 ) {
			if ( y >= 282 ) {
				vy *= -1;
			}
			if ( y <= (doorY + 66) ) {
				vy *= -1;
			}
		}
	}
			
	public void drawMe( Graphics g )
	{		
		g.setColor( Color.BLACK );
		g.fillOval( (int)(x-5), (int)(y-5), 10, 10 );
	}
	
	public double getVelocity() {
		double avgVelocity = Math.sqrt(vx*vx + vy*vy);
		return avgVelocity;
	}
	
}