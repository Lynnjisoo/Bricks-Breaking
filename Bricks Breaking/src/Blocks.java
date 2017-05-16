import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

public class Blocks {
	private Shape shape;
	private Color color;
	private Motion motion;
	private boolean affectedByGravity;
	
	public Blocks(Shape s, Color c, Motion m) {
		this.shape = s;
		this.color = c;
		this.motion = m;
		this.affectedByGravity = true;
	}
	

	public Blocks(Double s, Color orange, Motion m) {
		// TODO Auto-generated constructor stub
	}


	public void setShape(Shape s) {
		this.shape = s;
	}
	
	public Shape getShape() {
		return shape;
	}
	public Rectangle2D getBounds2D() {
		return this.shape.getBounds2D();
	}
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Motion getMotion() {
		return motion;
	}
	public void setMotion(Motion motion) {
		this.motion = motion;
	}

	public boolean isAffectedByGravity() {
		return affectedByGravity;
	}

	public void setAffectedByGravity(boolean affectedByGravity) {
		this.affectedByGravity = affectedByGravity;
	}
	
	public void handleCollision(Blocks b) {
		/*
		 *        _____
		 *       | this|
		 * ----- |_____|---
		 * | 			  |
		 * |	b		  |
		 * |              |
		 * ----------------
		 */
		
		
		boolean upperVerticalCollision = false;
		boolean lowerVerticalCollision = false;
		boolean leftHorizontalCollision = false;
		boolean rightHorizontalCollision = false;
		
		//size.getHeight() * (9 / 10)
		
		if(b.getBounds2D().getMinX() < this.getBounds2D().getMinX() &&
				this.getBounds2D().getMaxX() < b.getBounds2D().getMaxX()) {
			
			if(this.getBounds2D().getMaxX() < b.getBounds2D().getMaxY()
					&& this.getBounds2D().getCenterY() > b.getBounds2D().getMaxY()) {
				upperVerticalCollision = true;
			} 
			if (this.getBounds2D().getCenterY() < b.getBounds2D().getMinY()
					&& this.getBounds2D().getCenterY() > b.getBounds2D().getMinY()) {
				lowerVerticalCollision = true;
			}
			/*if(this.getBounds2D().getMinY() < b.getBounds2D().getMinY()) {
				lowerVerticalCollision = true;
			}
			if (this.getBounds2D().getMaxY() > b.getBounds2D().getMaxY()) {
				upperVerticalCollision = true;
			}*/
		}
		
		if(b.getBounds2D().getMinY() < this.getBounds2D().getMinY() &&
				this.getBounds2D().getMaxY() < b.getBounds2D().getMaxY()) {
			
			if(this.getBounds2D().getMinX() < b.getBounds2D().getMaxX()) {
				leftHorizontalCollision = true;
			}
			if (this.getBounds2D().getMaxX() > b.getBounds2D().getMaxX()) {
				rightHorizontalCollision = true;
			}
		}
		
		if(lowerVerticalCollision) {
			if(this.getMotion().getY() > 0) this.getMotion().setY(-this.getMotion().getY());
		}
		
		if(upperVerticalCollision) {
			if(this.getMotion().getY() < 0) this.getMotion().setY(-this.getMotion().getY());
		}
		
		/*if(lowerVerticalCollision || upperVerticalCollision) {
			this.getMotion().setY( -this.getMotion().getY() );
		}*/
		
		if(leftHorizontalCollision && this.getMotion().getX() > 0) {
			this.getMotion().setX(-this.getMotion().getX());
			
		}
		if(rightHorizontalCollision && this.getMotion().getX() < 0) {
			this.getMotion().setX(-this.getMotion().getX());	
		}
		
		/*if(leftHorizontalCollision || rightHorizontalCollision) {
			this.getMotion().setX( -this.getMotion().getX());
		}*/
		
	}
}