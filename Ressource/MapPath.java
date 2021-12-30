package com.projetpo.bindingofisaac.module.Ressource;

import com.projetpo.bindingofisaac.module.Model.ObjetsInventaire;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Map;

public class MapPath {
		
	/**
	 * @return Map de départ
	 */
	public static final Map mapStart() {
		Map start = new Map();
		start.generateMap(true, true, true, true);
		start.generateCollisionMap();
		return start;
	}
	
	/**
	 * @return Map du shop
	 */
	public static final Map mapShop() {
		Map shop = new Map();
		shop.generateMap(true, true, true, true);
		shop.generateCollisionMap();
		int random = (int) (1+Math.random()*9);
		int cpt = 0;
		int[] tab = new int[3];
		while (cpt != 3 || random == tab[0] || random == tab[1] || random == tab[2]) {
			if(random != tab[0] && random != tab[1] && random != tab[2]) {
				tab[cpt] = random;
				cpt ++;
			}
			random = (int) (1+Math.random()*9);
		}
		for(int i=0; i<tab.length; i++) {
			shop.getObjet().add(new ObjetsInventaire(tab[i], 10, 10, new Vector2(130*(i+1), 292.5), ""));
		}
		return shop;
	}
	
	/**
	 * @return Map avec l'ennemi fly
	 */
	public static final Map flyMap() {
		Map flyMap = new Map();
		flyMap.generateMap(true, true, true, true);
		flyMap.getMapobject()[4][4].setEnnemiMap(1);
		flyMap.generateRandomObstacle(2);
		flyMap.generateCollisionMap();
		return flyMap;
	}
	
	/**
	 * @return Map avec l'ennemi spider
	 */
	public static final Map spiderMap() {
		Map spiderMap = new Map();
		spiderMap.generateMap(true, true, true, true);
		spiderMap.getMapobject()[4][4].setEnnemiMap(2);
		spiderMap.generateRandomObstacle(2);
		spiderMap.generateCollisionMap();
		return spiderMap;
	}
	
	/**
	 * @return Map le boss
	 */
	public static final Map bossMap() {
		Map bossMap = new Map();
		bossMap.generateMap(true, true, true, true);
		bossMap.getMapobject()[4][4].setEnnemiMap(3);
		bossMap.generateRandomObstacle(2);
		bossMap.generateCollisionMap();
		return bossMap;
	}
	
}
