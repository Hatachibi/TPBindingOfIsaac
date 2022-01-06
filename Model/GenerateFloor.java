package com.projetpo.bindingofisaac.module.Model;

import java.util.Random;
import java.util.stream.BaseStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.projetpo.bindingofisaac.module.Ressource.MapPath;
import com.projetpo.bindingofisaac.module.Vue.Carte;

public abstract class GenerateFloor {
	
	private final static Personnage player = Jeu.gameWorld.getPlayer(); 
	
	public static Carte defaultMap()
	{
		Carte c = new Carte();
		
		c.setRenderMap(0, 0, 4);
		c.setRenderMap(0, 8, 1);
		c.setRenderMap(8, 0, 3);
		c.setRenderMap(8, 8, 2);
		for(int i = 0; i<c.getRenderMap().length; i++)
		{
			if(i != 0 && i != 8)
			{
				c.setRenderMap(i, 0, 5);
				c.setRenderMap(0, i, 6);
				c.setRenderMap(i, 8, 7);
				c.setRenderMap(8, i, 8);
			}
		}
		
		return c;
	}
	
	public static Carte generateRandomCarte(int nbCailloux, int nbEnnemis, boolean bossRoom)
	{
		Carte c = defaultMap();
		
		for(int i = 0; i<nbCailloux; i++)
		{
			int x = new Random().nextInt(5);
			int y = new Random().nextInt(5);
		//	c.setRenderMap(x, y, 9);
		}
		
		for(int i = 0; i<nbEnnemis; i++)
		{
			int x = new Random().nextInt(455)+65;
			int y = new Random().nextInt(455)+65;
			//System.out.println(x+" "+y);
			int ennemi = new Random().nextInt(4)+1;
			if(!bossRoom)
			{
				while(ennemi == 3)
				{
					ennemi = new Random().nextInt(7)+1;
				}
			}
			
			c.addEnnemi(x, y, ennemi);
		}
		c.generateCollisionMap();
		return c;
	}
	
	/*
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 */
	public static Room[][] generateFloor(int nbRooms, int nbMaxCailloux, int nbMaxEnnemis)
	{
		Room[][] etage = new Room[9][9];
		int cptRooms = 0;
		int dernierX = 4;
		int dernierY = 4;
		etage[dernierX][dernierY] = new Room(player, MapPath.mapStart());
		while(cptRooms != nbRooms)
		{
			int direction = new Random().nextInt(5);
			Carte c = generateRandomCarte(new Random().nextInt(nbMaxCailloux), new Random().nextInt(nbMaxEnnemis), false);
			int aleaRetourBase = new Random().nextInt(4);
			if(aleaRetourBase == 3)
			{
				dernierX = 4;
				dernierY = 4;
			}
			if(cptRooms == nbRooms - 1)
			{
				c = bossMap();
			}
			if(etage[dernierX][dernierY] != null && !etage[dernierX][dernierY].isBossRoom())
			{
				switch(direction) 
				{
					case 1:
						if(dernierY>1)
						{
							while(dernierY!=0 && etage[dernierX][(dernierY-1)] != null)
							{
								dernierY--;
							}
							if(dernierY != 0)
							{
								c.generateUpDoor();
								if(c.isBossRoom())
								{
									etage[dernierX][dernierY].getcarte().generateDownBossDoor();
								}
								else
								{
									etage[dernierX][dernierY].getcarte().generateDownDoor();
								}
								dernierY--;
								etage[dernierX][dernierY] = new Room(player, c);
								cptRooms++;
							}
							else
							{
								dernierY = 4;
							}
						}
						else
						{
							dernierY = 4;
						}
						break;
					case 2:
						if(dernierY<8)
						{
							while(dernierY!=8 && etage[dernierX][(dernierY+1)] != null)
							{
								dernierY++;
							}
							if(dernierY != 8)
							{
								c.generateDownDoor();
								if(c.isBossRoom())
								{
									etage[dernierX][dernierY].getcarte().generateUpBossDoor();
								}
								else
								{
									etage[dernierX][dernierY].getcarte().generateUpDoor();
								}
								dernierY++;
								etage[dernierX][dernierY] = new Room(player, c);
								cptRooms++;
							}
							else
							{
								dernierY = 4;
							}
						}
						else
						{
							dernierY = 4;
						}
						break;
					case 3:
						if(dernierX>1)
						{
							while(dernierX!=0 && etage[(dernierX-1)][dernierY] != null)
							{
								dernierX--;
							}
							if(dernierX!=0)
							{
								c.generateRightDoor();
								if(c.isBossRoom())
								{
									etage[dernierX][dernierY].getcarte().generateLeftBossDoor();
								}
								else
								{
									etage[dernierX][dernierY].getcarte().generateLeftDoor();
								}
								dernierX--;
								etage[dernierX][dernierY] = new Room(player, c);
								cptRooms++;
							}
							else
							{
								dernierX = 4;
							}
						}
						else
						{
							dernierX = 4;
						}
						break;
					case 4:
						if(dernierX<8)
						{
							while(dernierX!=8 && etage[(dernierX+1)][dernierY] != null)
							{
								dernierX++;
							}
							if(dernierX != 8)
							{
								c.generateLeftDoor();
								if(c.isBossRoom())
								{
									etage[dernierX][dernierY].getcarte().generateRightBossDoor();
								}
								else
								{
									etage[dernierX][dernierY].getcarte().generateRightDoor();
								}
								dernierX++;
								etage[dernierX][dernierY] = new Room(player, c);
								cptRooms++;
							}
							else
							{
								dernierX = 4;
							}
						}
						else
						{
							dernierX = 4;
						}
						break;
					}
				}
			}
			
		
		/*if(etage[dernierX][dernierY] != null)
		{
			if(etage[(dernierX+1)][dernierY] == null)
			{
				etage[dernierX][dernierY].getcarte().generateRightBossDoor();
				dernierX++;
				etage[dernierX][dernierY] = new Room(player, bossMap(3));
			}
			else if(etage[(dernierX-1)][dernierY] == null)
			{
				etage[dernierX][dernierY].getcarte().generateLeftBossDoor();
				dernierX--;
				etage[dernierX][dernierY] = new Room(player, bossMap(4));
			}
			else if(etage[dernierX][(dernierY+1)] == null)
			{
				etage[dernierX][dernierY].getcarte().generateUpBossDoor();
				dernierY++;
				etage[dernierX][dernierY] = new Room(player, bossMap(2));
			}
			else if(etage[dernierX][(dernierY-1)] == null)
			{
				etage[dernierX][dernierY].getcarte().generateDownBossDoor();
				dernierY--;
				etage[dernierX][dernierY] = new Room(player, bossMap(1));
			}
		}*/
		
		for(Room[] room: etage)
		{
			for(Room r: room)
			{
				if(r!=null)
				{
					r.getcarte().setVisited(true);
				}
			}
		}
		
		return etage;
	}
	
	private static Carte bossMap() {
		Carte bossMap = new Carte(true);
		bossMap.setRenderMap(0, 0, 4);
		bossMap.setRenderMap(0, 8, 1);
		bossMap.setRenderMap(8, 0, 3);
		bossMap.setRenderMap(8, 8, 2);
		for(int i = 0; i<bossMap.getRenderMap().length; i++)
		{
			if(i != 0 && i != 8)
			{
				bossMap.setRenderMap(i, 0, 5);
				bossMap.setRenderMap(0, i, 6);
				bossMap.setRenderMap(i, 8, 7);
				bossMap.setRenderMap(8, i, 8);
			}
		}
		bossMap.addEnnemi(4*65, 4*65, 3);
		bossMap.generateRandomObstacle(2);
		bossMap.generateCollisionMap();
		return bossMap;
	}

}
