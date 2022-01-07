package com.projetpo.bindingofisaac.module.Ressource;

import com.projetpo.bindingofisaac.module.Shaders.Vector2;

public class RoomInfos
{
	public static final int NB_TILES = 9;
	public static final int NB_HEIGHT_TILES = 13;
	public static final int NB_WIDTH_TILES = 9;
	public static final int TAILLE_CARRE = 65;
	public static final double TILE_WIDTH = 1.0 / NB_WIDTH_TILES;
	public static final double TILE_HEIGHT = 1.0 / NB_HEIGHT_TILES;
	public static final Vector2 TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT);
	public static final Vector2 HALF_TILE_SIZE = new Vector2(TILE_WIDTH, TILE_HEIGHT).scalarMultiplication(0.5);
	
	public static final Vector2 POSITION_CENTER_OF_ROOM = new Vector2(0.5, 0.5);
}
