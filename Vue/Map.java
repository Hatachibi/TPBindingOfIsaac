package Vue;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import java.awt.image.BufferedImage;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

import Model.Jeu;
import Model.MapObject;
import Ressource.MapPath;
import Ressource.RoomInfos;

public class Map {	
	
	private MapObject[][] mapobject;
	
	public Map() {
		this.mapobject = new MapObject[RoomInfos.NB_TILES][RoomInfos.NB_TILES];
	}
	
	public void changeMap() {
		int x = (int) ((1+Jeu.room.getPlayer().getPosition().getX())/65);
		int y = (int) ((1+Jeu.room.getPlayer().getPosition().getY())/65);
		if(getRendeMap()[x][y] == 10) {
			if(x==4 && y==8) {
				Jeu.room.getPlayer().getPosition().setY(65);
			}
			if(x==4 && y==0) {
				Jeu.room.getPlayer().getPosition().setY(520);
			}
			if(x==8 && y==4) {
				Jeu.room.getPlayer().getPosition().setX(65);
			}
			if(x==0 && y==4) {
				Jeu.room.getPlayer().getPosition().setX(520);
			}
			Jeu.room.setMapEnCours(MapPath.mapShop());
		}
	
	}
	
	public boolean[][] getCollisionMap() {
		boolean[][] map = new boolean[mapobject.length][mapobject[0].length];
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				map[i][j] = mapobject[i][j].getCollisionMap();
			}
		}
		return map;
	}
	
	public int[][] getRendeMap() {
		int[][] map = new int[mapobject.length][mapobject[0].length];
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				map[i][j] = mapobject[i][j].getRenderMap();
			}
		}
		return map;
	}
	
	public void initMapObject() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				mapobject[i][j] = new MapObject();
			}
		}
	}
	
	public void generateCollisionMap() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				switch(mapobject[i][j].getRenderMap()){
				case 0: mapobject[i][j].setCollisionMap(false); break;
				case 10: mapobject[i][j].setCollisionMap(false); break;
				default: mapobject[i][j].setCollisionMap(true); break;
				}
			}
		}
	}
	
	public void generateEnnemiMap() {
		
	}
	
	/*4, 5, 5, 5, 5, 5, 5, 5, 3,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 9, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	6, 0, 0, 0, 0, 0, 0, 0, 8,
	1, 7, 7, 7, 7, 7, 7, 7, 2 */
	public void generateRenderMap() {
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				if(i==0 && j==0) mapobject[i][j].setRenderMap(4);
				else if(i==0 && j==mapobject.length-1) mapobject[i][j].setRenderMap(1);
				else if(i==mapobject.length-1 && j==mapobject.length-1) mapobject[i][j].setRenderMap(2);
				else if(i==mapobject.length-1 && j==0) mapobject[i][j].setRenderMap(3);
				else if(j==0) mapobject[i][j].setRenderMap(5);
				else if(j==mapobject.length-1) mapobject[i][j].setRenderMap(7);
				else if(i==mapobject.length-1) mapobject[i][j].setRenderMap(8);
				else if(i==0) mapobject[i][j].setRenderMap(6);
				else mapobject[i][j].setRenderMap(0);
			}
		}
	}
	
	public void generateUpDoor() {
		mapobject[4][8].setRenderMap(10);
	}
	
	public void generateDownDoor() {
		mapobject[4][0].setRenderMap(10);
	}
	
	public void generateLeftDoor() {
		mapobject[8][4].setRenderMap(10);
	}
	
	public void generateRightDoor() {
		mapobject[0][4].setRenderMap(10);
	}
	
	public void generateMap(boolean up, boolean down, boolean left, boolean right) {
		initMapObject();
		generateEnnemiMap();
		generateRenderMap();
		if(up)generateUpDoor();
		if(down)generateDownDoor();
		if(left)generateLeftDoor();
		if(right)generateRightDoor();
		generateCollisionMap();
	}
	
	public void drawMap() {
		Shaders.Texture testImage = new Shaders.Texture("/res/Bomb.png");
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				if(mapobject[i][j].getRenderMap()==1) {
					Texture.coinHG.bind();
					
					
				} else if(mapobject[i][j].getRenderMap()==2) {
					Texture.coinHD.bind();
				} else if(mapobject[i][j].getRenderMap()==3) {
					Texture.coinBD.bind();
				} else if(mapobject[i][j].getRenderMap()==4) {
					Texture.coinBG.bind();
				} else if(mapobject[i][j].getRenderMap()==5) {
					Texture.murBas.bind();
				} else if(mapobject[i][j].getRenderMap()==6) {
					Texture.murGauche.bind();
				} else if(mapobject[i][j].getRenderMap()==7) {
					Texture.murHaut.bind();
				} else if(mapobject[i][j].getRenderMap()==8) {
					Texture.murDroite.bind();
				} else if(mapobject[i][j].getRenderMap()==9) {
					Texture.rock.bind();
				} else if(mapobject[i][j].getRenderMap()==10) {
					Texture.closeDoor.bind();
				}  
				else {
					Texture.emptyCell.bind();
				}
				glEnable(GL_TEXTURE_2D);
				Render.getInstance().drawPicture(i*RoomInfos.TAILLE_CARRE, j*RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, RoomInfos.TAILLE_CARRE, 0, 0, new float[] {});
			}
		}
		Texture.coinHG.unbind();
		Texture.coinHD.unbind();
		Texture.coinBG.unbind();
		Texture.coinBD.unbind();
		Texture.murHaut.unbind();
		Texture.murBas.unbind();
		Texture.murGauche.unbind();
		Texture.murDroite.unbind();
		Texture.rock.unbind();
		Texture.closeDoor.unbind();
		Texture.emptyCell.unbind();
	}

	public MapObject[][] getMapobject() {
		return mapobject;
	}

	public void setMapobject(MapObject[][] mapobject) {
		this.mapobject = mapobject;
	}
	
	
	
}
