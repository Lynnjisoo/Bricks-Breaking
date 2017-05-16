import java.awt.Color;
import java.awt.Shape;

public class Paddle extends Blocks {

	private boolean holdingLeft;
	private boolean holdingRight;
	
	public Paddle(Shape s, Color c, Motion m) {
		super(s, c, m);
		// TODO Auto-generated constructor stub
	}

	public boolean isHoldingLeft() {
		return holdingLeft;
	}

	public void setHoldingLeft(boolean holdingLeft) {
		this.holdingLeft = holdingLeft;
		this.updateMotion();
	}

	public boolean isHoldingRight() {
		return holdingRight;
	}

	public void setHoldingRight(boolean holdingRight) {
		this.holdingRight = holdingRight;
		this.updateMotion();
	}

	@Override
	public void handleCollision(Blocks b) {
		return;
	}
	
	public void updateMotion() {
		if(this.holdingRight) {
			this.setMotion(new Motion(10, 0));
		}
		if(this.holdingLeft) {
			this.setMotion(new Motion(-10, 0));
		}
		if(!this.holdingRight && !this.holdingLeft) {
			this.setMotion(new Motion(0,0));
		}
	}
}
