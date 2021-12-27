package Vue;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import Model.Fly;
import Model.Jeu;
import Model.MapObject;
import Ressource.MapPath;
import Ressource.RoomInfos;
import Shaders.Vector2;

public class Map {	
	
	private MapObject[][] mapobject;
	private boolean isVisited;
	
	public Map() {
		this.mapobject = new MapObject[RoomInfos.NB_TILES][RoomInfos.NB_TILES];
		this.isVisited = false;
	}
	
	public void changeMap() {
		int x = (int) ((Jeu.room.getPlayer().getPosition().getX())/65);
		int y = (int) ((Jeu.room.getPlayer().getPosition().getY())/65);
		if(getRenderMap()[x][y] < 0) {
			if(x==4 && y==8) {
				Jeu.room.getPlayer().getPosition().setY(65);
				Jeu.room.setMapEnCours(Jeu.room.getEtage()[(int) Jeu.room.getEtageCoos().getX()][(int) (Jeu.room.getEtageCoos().getY() + 1)]);
				Jeu.room.getEtageCoos().setY(Jeu.room.getEtageCoos().getY() + 1);
			}
			if(x==4 && y==0) {
				Jeu.room.getPlayer().getPosition().setY(520);
				Jeu.room.setMapEnCours(Jeu.room.getEtage()[(int) Jeu.room.getEtageCoos().getX()][(int) (Jeu.room.getEtageCoos().getY() - 1)]);
				Jeu.room.getEtageCoos().setY(Jeu.room.getEtageCoos().getY() - 1);
			}
			if(x==8 && y==4) {
				Jeu.room.getPlayer().getPosition().setX(65);
				Jeu.room.setMapEnCours(Jeu.room.getEtage()[(int) Jeu.room.getEtageCoos().getX() - 1][(int) (Jeu.room.getEtageCoos().getY())]);
				Jeu.room.getEtageCoos().setX(Jeu.room.getEtageCoos().getX() - 1);
			}
			if(x==0 && y==4) {
				Jeu.room.getPlayer().getPosition().setX(520);
				Jeu.room.setMapEnCours(Jeu.room.getEtage()[(int) Jeu.room.getEtageCoos().getX() + 1][(int) (Jeu.room.getEtageCoos().getY())]);
				Jeu.room.getEtageCoos().setX(Jeu.room.getEtageCoos().getX() + 1);
			}
			if(!Jeu.room.getMapEnCours().isVisited) {
				Jeu.room.addEnnemis();
			}
		//	Jeu.room.setMapEnCours(MapPath.mapShop());
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
	
	public int[][] getRenderMap() {
		int[][] map = new int[mapobject.length][mapobject[0].length];
		for(int i=0; i<mapobject.length; i++) {
			for(int j=0; j<mapobject[i].length; j++) {
				map[i][j] = mapobject[i][j].getRenderMap();
			}
		}
		return map;
	}
	
	public void setRenderMap(int i, int j, int k) {
		mapobject[i][j].setRenderMap(k);
	}
	
	public void setEnnemiMap(int i, int j, int k) {
		mapobject[i][j].setEnnemiMap(k);
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
				case -1: mapobject[i][j].setCollisionMap(false); break;
				case -2: mapobject[i][j].setCollisionMap(false); break;
				case -3: mapobject[i][j].setCollisionMap(false); break;
				case -4: mapobject[i][j].setCollisionMap(false); break;
				case 11: mapobject[i][j].setCollisionMap(false); break;
				default: mapobject[i][j].setCollisionMap(true); break;
				}
			}
		}
	}
	
	public void generateEnnemiMap() {
		mapobject[4][4].setEnnemiMap(3);
	/*	mapobject[4][0].setEnnemiMap(1);
		mapobject[4][2].setEnnemiMap(1); */  
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
		mapobject[4][8].setRenderMap(12);
	}
	
	public void generateDownDoor() {
		mapobject[4][0].setRenderMap(13);
	}
	
	public void generateLeftDoor() {
		mapobject[8][4].setRenderMap(14);
	}
	
	public void generateRightDoor() {
		mapobject[0][4].setRenderMap(15);
	}
	
	public void generateMap(boolean up, boolean down, boolean left, boolean right) {
		initMapObject();
		generateEnnemiMap();
		generateRenderMap();
		if(up)generateUpDoor();
		if(down)generateDownDoor();
		if(left)generateLeftDoor();
		if(right)generateRightDoor();
	}
	
	public void drawMap() {
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
			/*	} else if(mapobject[i][j].getRenderMap()==-1) {
					Texture.closeDoor_up.bind();
				} else if(mapobject[i][j].getRenderMap()==-2) {
					Texture.closeDoor_down.bind();
				} else if(mapobject[i][j].getRenderMap()==-3) {
					Texture.closeDoor_right.bind();
				} else if(mapobject[i][j].getRenderMap()==-4) {
					Texture.closeDoor_left.bind(); */
				} else if(mapobject[i][j].getRenderMap()==11) {
					Texture.spikes.bind();
				} else if(mapobject[i][j].getRenderMap()==12) {
					Texture.closeDoor_up.bind();
				} else if(mapobject[i][j].getRenderMap()==13) {
					Texture.closeDoor_down.bind();
				} else if(mapobject[i][j].getRenderMap()==14) {
					Texture.closeDoor_right.bind();
				} else if(mapobject[i][j].getRenderMap()==15) {
					Texture.closeDoor_left.bind();
				} else if(mapobject[i][j].getRenderMap()==-1 || mapobject[i][j].getRenderMap()==-2 || mapobject[i][j].getRenderMap()==-3 || mapobject[i][j].getRenderMap()==-4) {
					Texture.openDoor.bind();
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
		Texture.openDoor.unbind();
		Texture.closeDoor_up.unbind();
		Texture.closeDoor_down.unbind();
		Texture.closeDoor_right.unbind();
		Texture.closeDoor_left.unbind();
		Texture.spikes.unbind();
		Texture.emptyCell.unbind();
	}

	public MapObject[][] getMapobject() {
		return mapobject;
	}

	public void setMapobject(MapObject[][] mapobject) {
		this.mapobject = mapobject;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public void setVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}
	
	
	
}
