package com.projetpo.bindingofisaac.module.Model;

import java.util.ArrayList;
import java.util.Random;

import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Vue.Carte;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class StockageRoom {
	
	private ArrayList <Carte> cartes = new ArrayList<Carte>();
	
	public StockageRoom()
	{
		
	}
	
	public static Carte generateRandomCarte(int nbCailloux, int nbEnnemis, boolean bossRoom)
	{
		Carte c = new Carte();
		
		for(int i = 0; i<nbCailloux; i++)
		{
			int x = new Random().nextInt(6)+1;
			int y = new Random().nextInt(6)+1;
			c.setRenderMap(x, y, 9);
		}
		
		for(int i = 0; i<nbEnnemis; i++)
		{
			int x = new Random().nextInt(6)+1;
			int y = new Random().nextInt(6)+1;
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
		
		return c;
	}
	
	/*
	 * 1 = gauche
	 * 2 = droite
	 * 3 = haut
	 * 4 = bas
	 */
	public static Carte[][] generateFloor(int nbRooms, int nbMaxCailloux, int nbMaxEnnemis)
	{
		Carte[][] etage = new Carte[9][9];
		int cptRooms = 0;
		int dernierX = 4;
		int dernierY = 4;
		Carte parDefaut = new Carte();
		etage[dernierX][dernierY] = parDefaut;
		while(cptRooms != nbRooms)
		{
			int direction = new Random().nextInt(3)+1;
			Carte c = generateRandomCarte(new Random().nextInt(nbMaxCailloux)+1, new Random().nextInt(nbMaxEnnemis)+1, false);
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
						c.generateRightDoor();
						etage[dernierX][dernierY].generateLeftDoor();
						dernierY--;
						etage[dernierX][dernierY] = c;
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
						c.generateLeftDoor();
						etage[dernierX][dernierY].generateRightDoor();
						dernierY++;
						etage[dernierX][dernierY] = c;
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
						c.generateDownDoor();
						etage[dernierX][dernierY].generateUpDoor();
						dernierX--;
						etage[dernierX][dernierY] = c;
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
							dernierX--;
						}
						c.generateDownDoor();
						etage[dernierX][dernierY].generateUpDoor();
						dernierX++;
						etage[dernierX][dernierY] = c;
						cptRooms++;
					}
					else
					{
						dernierX = 4;
					}
					break;
			}
				
					
		}
		return etage;
	}

}
