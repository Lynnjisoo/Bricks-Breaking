import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Main extends JPanel{

	public static final long serialVersionUID = 1L;
	public static Dimension size;
	public static final int DELAY = 1000 / 100;
	public static ArrayList<Blocks> blocks = new ArrayList<>();
	public static double meter_pixels;
	public static JFrame application;
	
	public void setupGraphics() {
		application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(this);
		application.setExtendedState(JFrame.MAXIMIZED_BOTH);
		application.setUndecorated(true);
		application.setVisible(true);
		application.setFocusable(true);
		application.requestFocus();
		application.requestFocusInWindow();
		size = application.getSize();
		meter_pixels = size.getHeight() / 500;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(10.0f,                     // Line width
                BasicStroke.CAP_ROUND,    // End-cap style
                BasicStroke.JOIN_ROUND)); // Vertex join style	
		
		for(Blocks b : blocks) {
			g2.setColor(b.getColor());
			g2.fill(b.getShape());
		}
	
	public Main() {
		super();
		setBackground(Color.WHITE);
	}
	
	public static void recalculate() {
		for(Blocks b : blocks) {
			Motion 		m = b.getMotion();
			//Rectangle2D r = b.getBounds2D();
			
			AffineTransform at = new AffineTransform();
			at.translate(m.getX(), m.getY());
			b.setShape(at.createTransformedShape(b.getShape()));
			if(b.isAffectedByGravity()) {
				m.addGravity();
			}
		}
		for(int i = 0; i <= blocks.size() - 2; i++) {
			for(int k = i + 1; k <= blocks.size() - 1; k++) {
				Blocks a = blocks.get(i);
				Blocks b = blocks.get(k);
				
				//Check if there is an intersection between a and b
				if(a.getBounds2D().intersects(b.getBounds2D())) {
					a.handleCollision(b);
					b.handleCollision(a);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Main program = new Main();
		program.setupGraphics();
		
		blocks.add(new Blocks( //Create a new block, blocks need rectangle, color, and motion 
				new Rectangle2D.Double(0, size.getHeight() * 9 / 10, size.getWidth(), size.getHeight() / 10), 
				Color.ORANGE, 
				new Motion(0, 0)
				)); 

		blocks.add(new Paddle(
				new Rectangle2D.Double(size.getWidth()/2, size.getHeight() * 16 / 20,
						size.getWidth() / 5, size.getHeight() / 20),
						Color.BLACK,
						new Motion(0,0)));
		
		blocks.get(0).setAffectedByGravity(false);
		blocks.get(1).setAffectedByGravity(false);
		blocks.get(2).setAffectedByGravity(false);
		blocks.get(3).setAffectedByGravity(false);
		blocks.get(4).setAffectedByGravity(false);
		blocks.get(5).setAffectedByGravity(false);
		KeyboardListener.p = (Paddle)blocks.get(5);
		application.addKeyListener(new KeyboardListener());
		ActionListener repainter = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				recalculate();
				program.repaint();
			}
		};
		Timer paintingTimer = new Timer(DELAY, repainter);
		paintingTimer.start();
	}

}