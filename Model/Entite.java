package Model;

public class Entite {
	
	private int width;
	private int heigth;
	protected float x;
	protected float y;
	private String url;
	
	public Entite(int width, int heigth) {
		this.width = width;
		this.heigth = heigth;
	}
	
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
	
}
