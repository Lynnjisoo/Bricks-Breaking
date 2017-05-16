public class Motion {
	private double x;
	private double y;
	
	public Motion(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void addGravity() {
		this.y += 9.8 * Main.meter_pixels / 60;
	}
}