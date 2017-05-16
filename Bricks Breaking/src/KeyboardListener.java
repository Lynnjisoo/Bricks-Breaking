import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

		public static Paddle p;
		
		@Override
		public void keyPressed(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
				p.setHoldingLeft(true);
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				p.setHoldingRight(true);
			}
		}

		@Override
		public void keyReleased(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
				p.setHoldingLeft(false);
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				p.setHoldingRight(false);
			}
		}

		@Override
		public void keyTyped(KeyEvent ke) {
			if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
				p.setHoldingLeft(true);
			}
			if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
				p.setHoldingRight(true);
			}
		}

	}

