package com.projetpo.bindingofisaac.module.Model;

import java.util.Random;

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
			int x = new Random().nextInt(6)+1;
			int y = new Random().nextInt(6)+1;
			c.setRenderMap(x, y, 9);
		}
		
		for(int i = 0; i<nbEnnemis; i++)
		{
			int x = new Random().nextInt(5)+2;
			int y = new Random().nextInt(5)+2;
			System.out.println(x+" "+y);
			int ennemi = new Random().nextInt(4)+1;
			if(!bossRoom)
			{
				while(ennemi == 3)
				{
					ennemi = new Random().nextInt(6)+1;
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
			int direction = new Random().nextInt(3)+1;
			Carte c = generateRandomCarte(new Random().nextInt(nbMaxCailloux), new Random().nextInt(nbMaxEnnemis), false);
			int aleaRetourBase = new Random().nextInt(4);
			if(aleaRetourBase == 3)
			{
				dernierX = 4;
				dernierY = 4;
			}
			switch(direction) 
			{
				case 1:
					if(dernierY>1)
					{
						while(etage[dernierX][(dernierY-1)] != null)
						{
							dernierY--;
						}
						c.generateUpDoor();
						etage[dernierX][dernierY].getcarte().generateDownDoor();
						dernierY--;
						etage[dernierX][dernierY] = new Room(player, c);
						cptRooms++;
					}
					else
					{
						dernierY = 4;
					}
					break;
				case 2:
					if(dernierY<8)
					{
						while(etage[dernierX][(dernierY+1)] != null)
						{
							dernierY++;
						}
						c.generateDownDoor();
						etage[dernierX][dernierY].getcarte().generateUpDoor();
						dernierY++;
						etage[dernierX][dernierY] = new Room(player, c);
						cptRooms++;
					}
					else
					{
						dernierY = 4;
					}
					break;
				case 3:
					if(dernierX>1)
					{
						while(etage[(dernierX-1)][dernierY] != null)
						{
							dernierX--;
						}
						c.generateRightDoor();
						etage[dernierX][dernierY].getcarte().generateLeftDoor();
						dernierX--;
						etage[dernierX][dernierY] = new Room(player, c);
						cptRooms++;
					}
					else
					{
						dernierX = 4;
					}
					break;
				case 4:
					if(dernierX<8)
					{
						while(etage[(dernierX+1)][dernierY] != null)
						{
							dernierX++;
						}
						c.generateLeftDoor();
						etage[dernierX][dernierY].getcarte().generateRightDoor();
						dernierX++;
						etage[dernierX][dernierY] = new Room(player, c);
						cptRooms++;
					}
					else
					{
						dernierX = 4;
					}
					break;
			}
		}
		for(Room[] r: etage)
		{
			for(Room room: r)
			{
				if(room != null)
				{
					room.getcarte().setVisited(true);
				}
			}
		}
		return etage;
	}

}
