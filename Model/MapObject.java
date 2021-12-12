package Model;

public class MapObject {
	
	private boolean collisionMap;
	private int renderMap;
	private int ennemiMap;
	
	public MapObject() {
		this.collisionMap = false;
		this.renderMap = 0;
		this.ennemiMap = 0;
	}

	public boolean getCollisionMap() {
		return collisionMap;
	}

	public void setCollisionMap(boolean collisionMap) {
		this.collisionMap = collisionMap;
	}

	public int getRenderMap() {
		return renderMap;
	}

	public void setRenderMap(int renderMap) {
		this.renderMap = renderMap;
	}

	public int getEnnemiMap() {
		return ennemiMap;
	}

	public void setEnnemiMap(int ennemiMap) {
		this.ennemiMap = ennemiMap;
	}
	
	
	
}
