import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Fun extends JFrame implements ActionListener, MouseListener {
	
	Timer timer;
	ArrayList<Red_Ball> redBalls = new ArrayList<>();
	ArrayList<Blue_Ball> blueBalls = new ArrayList<>();
	
	int redBallCount;
	int blueBallCount;
	int ballCount;
	double deltaT = 0.003;
	
	JPanel temperaturePanel;
	JPanel panel1;
	JPanel controllerPanel;
	
	JLabel leftLabel;
	JLabel rightLabel;
	
	JButton resetButton;
	JButton addButton;
	
	double doorY = 226;
	double doorVY = 0.0;
	
	double leftAvgTemp;
	double rightAvgTemp;
	
	public static void main(String[] args) {
		new Fun();
	}

	public Fun() {
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		setTitle( "Maxwell's Demon" );
		setLayout( new BorderLayout() );

		temperaturePanel = new JPanel();
		temperaturePanel.setBackground( Color.LIGHT_GRAY );
		add( temperaturePanel, BorderLayout.NORTH );
		temperaturePanel.setLayout( new GridLayout( 1, 2 ) );
		
		leftLabel = new JLabel( "" );
		temperaturePanel.add( leftLabel );
		leftLabel.setHorizontalAlignment(JLabel.CENTER);
		
		rightLabel = new JLabel( "" );
		temperaturePanel.add( rightLabel );
		rightLabel.setHorizontalAlignment(JLabel.CENTER);
		
		
		controllerPanel = new JPanel();
		controllerPanel.setBackground( Color.gray );
		add( controllerPanel, BorderLayout.SOUTH );
		controllerPanel.setLayout( new GridLayout( 1, 2 ) );
		
		resetButton = new JButton( "Reset" );
		resetButton.addActionListener(this);
		resetButton.setActionCommand( "Reset" );
		controllerPanel.add( resetButton );
		
		addButton = new JButton( "Add" );
		addButton.addActionListener(this);
		addButton.setActionCommand( "Add" );
		controllerPanel.add( addButton );
		
		addMouseListener(this);
		
		timer = new Timer((int)(1000 * deltaT), this);
		timer.start();
		
		redBallCount = 4;
		for ( int i=0; i< redBallCount; i++ ) {
			if ( i%2 == 0 ) {
				redBalls.add(i, new Red_Ball("left"));
			} else {
				redBalls.add(i, new Red_Ball("right"));
			}

		}

		blueBallCount = 4;
		for ( int i=0; i< blueBallCount; i++ ) {
			if ( i%2 == 0 ) {
				blueBalls.add(i, new Blue_Ball("left"));
			} else {
				blueBalls.add(i, new Blue_Ball("right"));
			}
		}
		
		ballCount = redBallCount + blueBallCount;
		
		setSize(800, 500);
		setVisible(true);
	}
	
	@Override
	public void paint( Graphics g ) {
		super.paint(g);
		
		g.setColor( Color.black );
		g.fillRect( 390, 45, 20, 181);
		g.fillRect( 390, 287, 20, 181);
		
		drawDoor(g);
		for ( int i=0; i<redBallCount; i++ ) { redBalls.get(i).drawMe(g); }
		for ( int i=0; i<blueBallCount; i++ ) { blueBalls.get(i).drawMe(g); }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if ( e.getSource()==timer ) {
			moveDoor(); moveAll(); calcTemp();
		} else if ( e.getActionCommand().equals( "Add" ) ) {
			add();
		} else if ( e.getActionCommand().equals( "Reset" ) ) {
			reset();
		}

		repaint();
	}	

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Door opening...");
		doorVY = -100;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Door closing...");
		doorVY = 100;
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}

	public void moveDoor() {
		doorY += doorVY * deltaT;
		if ( doorY >= 226 ) {
			doorY = 226;
		} else if ( doorY <= 165 ) {
			doorY = 165;
		}
	}

	public void drawDoor( Graphics g ) {
		g.setColor(Color.black);
		g.fillRect( 390, (int)doorY, 20, 61);
	}
	
	public void moveAll()
	{
		for ( int i=0; i<redBallCount; i++ ) { redBalls.get(i).move(deltaT, doorY); }
		for ( int i=0; i<blueBallCount; i++ ) { blueBalls.get(i).move(deltaT, doorY); }
	}
	
	public void add() {
		for ( int i=redBallCount; i< redBallCount+2; i++ ) {
			if ( i%2 == 0 ) {
				redBalls.add(i, new Red_Ball("left"));
			} else {
				redBalls.add(i, new Red_Ball("right"));
			}
		}
		
		redBallCount += 2;
		
		for ( int i=blueBallCount; i< blueBallCount+2; i++ ) {
			if ( i%2 == 0 ) {
				blueBalls.add(i, new Blue_Ball("left"));
			} else {
				blueBalls.add(i, new Blue_Ball("right"));
			}
		}
		
		blueBallCount += 2;
		
		ballCount = redBallCount + blueBallCount;
	}
	
	public void reset() {
		redBalls.clear();
		blueBalls.clear();
		
		redBallCount = 4;
		for ( int i=0; i< redBallCount; i++ ) {
			if ( i%2 == 0 ) {
				redBalls.add(i, new Red_Ball("left"));
			} else {
				redBalls.add(i, new Red_Ball("right"));
			}

		}
		
		blueBallCount = 4;
		for ( int i=0; i< blueBallCount; i++ ) {
			if ( i%2 == 0 ) {
				blueBalls.add(i, new Blue_Ball("left"));
			} else {
				blueBalls.add(i, new Blue_Ball("right"));
			}
		}
		
		ballCount = redBallCount + blueBallCount;
		
	}
	
	public void calcTemp () {
		double leftTotalTemp = 0.0;
		double rightTotalTemp = 0.0;
		int leftNum = 0;
		int rightNum = 0;
		
		for ( int i=0; i< redBallCount; i++ ) {
			if ( redBalls.get(i).x < 385 ) {
				leftTotalTemp += redBalls.get(i).getVelocity();
				leftNum++;
			} else if ( redBalls.get(i).x > 415 ){
				rightTotalTemp += redBalls.get(i).getVelocity();
				rightNum++;
			}
		}
		
		for ( int i=0; i< blueBallCount; i++ ) {
			if ( blueBalls.get(i).x < 385 ) {
				leftTotalTemp += redBalls.get(i).getVelocity();
				leftNum++;
			} else if ( blueBalls.get(i).x > 415 ){
				rightTotalTemp += blueBalls.get(i).getVelocity();
				rightNum++;
			}
		}
		
		leftAvgTemp = leftTotalTemp / leftNum;
		rightAvgTemp = rightTotalTemp / rightNum;
		
		drawTemp();
	}

	public void drawTemp () {
		String leftString;
		String rightString;
		
		leftString = "Left Temperature: " + leftAvgTemp;
		rightString = "Right Temperature: " + rightAvgTemp;
		
		leftLabel.setText(leftString);
		rightLabel.setText(rightString);

	}

}