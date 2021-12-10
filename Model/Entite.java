package Model;

public class Entite {
	
	private int width;
	private int heigth;
	protected double x;
	protected double y;
	private String url;
	
	public Entite(int width, int heigth, double x, double y) {
		this.width = width;
		this.heigth = heigth;
		this.x = x;
		this.y = y;
	}
	
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	
}
