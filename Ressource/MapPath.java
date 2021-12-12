package Ressource;

import Vue.Map;

public class MapPath {
		
	public static final Map mapStart() {
		Map start = new Map();
		start.generateMap(true, true, true, true);
		return start;
	}
	
	public static final Map mapShop() {
		Map start = new Map();
		start.generateMap(true, false, false, false);
		return start;
	}
	
}
