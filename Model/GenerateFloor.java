package com.projetpo.bindingofisaac.module.Model;

import java.util.Random;

import com.projetpo.bindingofisaac.module.Ressource.RoomInfos;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Carte;
import com.projetpo.bindingofisaac.module.Vue.Jeu;

public abstract class GenerateFloor {
	
	private final static Personnage player = Jeu.gameWorld.getPlayer(); 
	
	public static Carte defaultMap()
	{
		
		Carte c = new Carte();
		
		c.setRenderMap(0, 0, 4);
		c.setRenderMap(0, RoomInfos.NB_WIDTH_TILES-1, 1);
		c.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, 0, 3);
		c.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, RoomInfos.NB_WIDTH_TILES-1, 2);
		for(int i = 0; i<c.getRenderMap().length; i++)
		{
			if(i != 0 && i != RoomInfos.NB_HEIGHT_TILES-1)
			{
				c.setRenderMap(i, 0, 5);
				if(i<RoomInfos.NB_WIDTH_TILES-1)
				{
					c.setRenderMap(0, i, 6);
					c.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, i, 8);
				}
				c.setRenderMap(i, RoomInfos.NB_WIDTH_TILES-1, 7);
				
			}
		}
		
		return c;
	}
	
	public static Carte generateRandomCarte(int nbCailloux, int nbEnnemis, int nbSpikes, boolean bossRoom)
	{
		Carte c = defaultMap();
		
		for(int i = 0; i<nbCailloux; i++)
		{
			int x;
			int y;
			do {
				x = new Random().nextInt(6)+1;
				y = new Random().nextInt(6)+1;
			}while((x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==1) || (x==RoomInfos.NB_HEIGHT_TILES-2 && y==(RoomInfos.NB_WIDTH_TILES-1)/2) || (x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==RoomInfos.NB_WIDTH_TILES-2) || (x==1 && y==(RoomInfos.NB_WIDTH_TILES-1)/2));
			
			c.setRenderMap(x, y, 9);
		}
		for(int i = 0; i<nbSpikes; i++)
		{
			int x;
			int y;
			do {
				x = new Random().nextInt(6)+1;
				y = new Random().nextInt(6)+1;
			}while((x==4 && y==1) || (x==7 && y==4) || (x==4 && y==7) || (x==1 && y==4));
			
			c.setRenderMap(x, y, 11);
		}
		
		for(int i = 0; i<nbEnnemis; i++)
		{
			int x = new Random().nextInt(455)+65;
			int y = new Random().nextInt(455)+65;
			int ennemi = new Random().nextInt(13)+1;
			if(!bossRoom)
			{
				while(ennemi == 3 || ennemi == 10 || ennemi == 11 || ennemi == 12)
				{
					ennemi = new Random().nextInt(13)+1;
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
	public static Room[][] generateFloor(int nbRooms, int nbMaxCailloux, int nbMaxSpikes, int nbMaxEnnemis)
	{
		Room[][] etage = new Room[RoomInfos.NB_HEIGHT_TILES][RoomInfos.NB_WIDTH_TILES];
		int cptRooms = 0;
		int dernierX = (RoomInfos.NB_TILES-1)/2;
		int dernierY = (RoomInfos.NB_TILES-1)/2;
		etage[dernierX][dernierY] = new Room(player, mapStart());
		while(cptRooms != nbRooms)
		{
			int direction = new Random().nextInt(5);
			Carte c = generateRandomCarte(new Random().nextInt(nbMaxCailloux),new Random().nextInt(nbMaxSpikes), new Random().nextInt(nbMaxEnnemis), false);
			int aleaRetourBase = new Random().nextInt(4);
			if(aleaRetourBase == 3)
			{
				dernierX = (RoomInfos.NB_TILES-1)/2;
				dernierY = (RoomInfos.NB_TILES-1)/2;
			}
			if(cptRooms == nbRooms - 3)
			{
				c = mapShop();
			}
			if(cptRooms == nbRooms - 2)
			{
				c = bossMap();
			}
			if(cptRooms == nbRooms - 1)
			{
				c = secretMap();
			}
			if(etage[dernierX][dernierY].isNormalRoom() && etage[dernierX][dernierY] != null)
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
								else if(c.isShopRoom())
								{
									etage[dernierX][dernierY].getcarte().generateDownLockedDoor();
								}
								else if(c.isSecretRoom())
								{
									etage[dernierX][dernierY].getcarte().generateDownFakeWall();
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
								else if(c.isShopRoom())
								{
									etage[dernierX][dernierY].getcarte().generateUpLockedDoor();
								}
								else if(c.isSecretRoom())
								{
									etage[dernierX][dernierY].getcarte().generateUpFakeWall();
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
								else if(c.isShopRoom())
								{
									etage[dernierX][dernierY].getcarte().generateLeftLockedDoor();
								}
								else if(c.isSecretRoom())
								{
									etage[dernierX][dernierY].getcarte().generateLeftFakeWall();
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
								else if(c.isShopRoom())
								{
									etage[dernierX][dernierY].getcarte().generateRightLockedDoor();
								}
								else if(c.isSecretRoom())
								{
									etage[dernierX][dernierY].getcarte().generateRightFakeWall();
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
		/*for(int i = 0; i<etage.length; i++)
		{
			if(etage[i] != null)
			{
				for(int j = 0; j<etage[i].length; j++)
				{
					if(etage[i][j] != null)
					{
						etage[i][j].getcarte().setVisited(true);
					}
				}
			}
		}*/
		return etage;
	}

	public static Carte generateRandomObstacle(Carte c, int nbObstacle)
	{
		for(int i = 0; i<nbObstacle; i++)
		{
			int x;
			int y;
			do {
				x = new Random().nextInt(11)+1;
				y = new Random().nextInt(6)+1;
			}while((x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==1) || (x==RoomInfos.NB_HEIGHT_TILES-2 && y==(RoomInfos.NB_WIDTH_TILES-1)/2) || (x==(RoomInfos.NB_HEIGHT_TILES-1)/2 && y==RoomInfos.NB_WIDTH_TILES-2) || (x==1 && y==(RoomInfos.NB_WIDTH_TILES-1)/2));
			c.setRenderMap(x, y, 9);
		}
		return c;
	}
	
	private static Carte bossMap() {
		Carte bossMap = new Carte(1);
		bossMap.setRenderMap(0, 0, 4);
		bossMap.setRenderMap(0, RoomInfos.NB_WIDTH_TILES-1, 1);
		bossMap.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, 0, 3);
		bossMap.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, RoomInfos.NB_WIDTH_TILES-1, 2);
		for(int i = 0; i<bossMap.getRenderMap().length; i++)
		{
			if(i != 0 && i != RoomInfos.NB_HEIGHT_TILES-1)
			{
				bossMap.setRenderMap(i, 0, 5);
				if(i<RoomInfos.NB_WIDTH_TILES-1)
				{
					bossMap.setRenderMap(0, i, 6);
					bossMap.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, i, 8);
				}
				bossMap.setRenderMap(i, RoomInfos.NB_WIDTH_TILES-1, 7);
				
			}
		}
		switch(Jeu.gameWorld.getFloor()) {
			case 0: bossMap.addEnnemi(6*65, 4*65, 3);break;
			case 1: bossMap.addEnnemi(6*65, 4*65, 10);break;
			case 2: bossMap.addEnnemi(6*65, 4*65, 11);break;
			case 3: bossMap.addEnnemi(6*65, 4*65, 12); break;
			case 4: bossMap.addEnnemi(6*65, 4*65, 15); break;
			case 5: bossMap.addEnnemi(6*65, 4*65, 14); break;
		}
		if(Jeu.gameWorld.getFloor() != 4)
		{
			bossMap = generateRandomObstacle(bossMap, 4+Jeu.gameWorld.getFloor());
		}
		bossMap.generateCollisionMap();
		return bossMap;
	}
	
	private static Carte mapShop() {
		Carte shop = new Carte(2);
		shop.setRenderMap(0, 0, 4);
		shop.setRenderMap(0, RoomInfos.NB_WIDTH_TILES-1, 1);
		shop.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, 0, 3);
		shop.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, RoomInfos.NB_WIDTH_TILES-1, 2);
		for(int i = 0; i<shop.getRenderMap().length; i++)
		{
			if(i != 0 && i != RoomInfos.NB_HEIGHT_TILES-1)
			{
				shop.setRenderMap(i, 0, 5);
				if(i<RoomInfos.NB_WIDTH_TILES-1)
				{
					shop.setRenderMap(0, i, 6);
					shop.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, i, 8);
				}
				shop.setRenderMap(i, RoomInfos.NB_WIDTH_TILES-1, 7);
				
			}
		}
		shop.generateCollisionMap();
		int random = (int) (1+Math.random()*9);
		if(Math.random() > 0.5) {
			random = (int) (15 + Math.random()*2);
		}
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
			shop.getObjet().add(new ObjetsInventaire(tab[i], 30, 30, new Vector2(130*(i+1) + 100, 292.5), ""));
		}
		return shop;
	}
	
	private static Carte secretMap() {
		Carte secret = new Carte(3);
		secret.setRenderMap(0, 0, 4);
		secret.setRenderMap(0, RoomInfos.NB_WIDTH_TILES-1, 1);
		secret.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, 0, 3);
		secret.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, RoomInfos.NB_WIDTH_TILES-1, 2);
		for(int i = 0; i<secret.getRenderMap().length; i++)
		{
			if(i != 0 && i != RoomInfos.NB_HEIGHT_TILES-1)
			{
				secret.setRenderMap(i, 0, 5);
				if(i<RoomInfos.NB_WIDTH_TILES-1)
				{
					secret.setRenderMap(0, i, 6);
					secret.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, i, 8);
				}
				secret.setRenderMap(i, RoomInfos.NB_WIDTH_TILES-1, 7);
				
			}
		}
		secret.generateCollisionMap();
		int item = (int) (1+Math.random()*9);
		secret.getObjet().add(new ObjetsInventaire(item, 30, 30, new Vector2(130*(2) + 100, 292.5), ""));
		return secret;
	}
	
	private static Carte mapStart() {
		Carte start = new Carte();
		start.setRenderMap(0, 0, 4);
		start.setRenderMap(0, RoomInfos.NB_WIDTH_TILES-1, 1);
		start.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, 0, 3);
		start.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, RoomInfos.NB_WIDTH_TILES-1, 2);
		for(int i = 0; i<start.getRenderMap().length; i++)
		{
			if(i != 0 && i != RoomInfos.NB_HEIGHT_TILES-1)
			{
				start.setRenderMap(i, 0, 5);
				if(i<RoomInfos.NB_WIDTH_TILES-1)
				{
					start.setRenderMap(0, i, 6);
					start.setRenderMap(RoomInfos.NB_HEIGHT_TILES-1, i, 8);
				}
				start.setRenderMap(i, RoomInfos.NB_WIDTH_TILES-1, 7);
				
			}
		}
		start.generateCollisionMap();
		return start;
	}

}
