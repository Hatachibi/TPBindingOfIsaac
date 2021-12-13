package Ressource;

import Vue.Map;

public class MapPath {
		
	public static final Map mapStart() {
		Map start = new Map();
		start.generateMap(true, true, true, true);
		start.getMapobject()[3][3].setRenderMap(11);
		start.getMapobject()[4][4].setRenderMap(9);
		start.getMapobject()[4][5].setRenderMap(9);
		start.generateCollisionMap();
		return start;
	}
	
	public static final Map mapShop() {
		Map start = new Map();
		start.generateMap(true, false, false, false);
		start.generateCollisionMap();
		return start;
	}
	
}
