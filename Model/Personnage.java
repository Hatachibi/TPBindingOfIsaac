package com.projetpo.bindingofisaac.module.Model;

import java.io.IOException;
import java.util.LinkedList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.lwjgl.opengl.GL11;
import com.projetpo.bindingofisaac.module.Controler.ListeBalle;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;
import com.projetpo.bindingofisaac.module.Vue.Render;
import com.projetpo.bindingofisaac.module.Vue.Texture;

public class Personnage extends Entite{

	/*
	 * Nom du joueur
	 */
	private String name;
	
	/*
	 * Liste de balle du joueur
	 */
	private ListeBalle munitions;
	
	/*
	 * Degat infligé par le joueur.
	 */
	private double degat;
	
	/*
	 * Multiplicateur de degat
	 */
	private double multiplicator;
	
	/*
	 * Portée des balles du joueur
	 */
	private double range;
	
	/*
	 * Vitesse du joueur
	 */
	private double speed;
	
	/*
	 * Taille du joueur
	 */
	private Vector2 size;
	
	/*
	 * Direction de deplacement du joueur
	 */
	private Vector2 direction;
	
	/*
	 * Bar de Vie du joueur
	 */
	private BarreDeVie life;
	
	/*
	 * Argent du joueur
	 */
	private int coin;
	
	/**
	 * Clefs du joueur
	 */
	private int key;
	
	/*
	 * Inventaire du joueur
	 */
	private Inventaire inv;
	
	/*
	 * Compteur permettant la mise en place d'un cooldown
	 */
	private int cooldownDegat;
	
	/*
	 * Boolean qui indique si le joueur est touché (permet de mettre un cooldown)
	 */
	private boolean isTouch;
	
	/*
	 * Angle du joueur (pour les pièces en Raycastings)
	 */
	private double a;
	
	/*
	 * Distance du joueur au mur (pour les pièces en Raycasting)
	 */
	private float distance;
	
	/*
	 * Boolean qui indique si le joueur est invisible 
	 */
	private boolean isInvincible;
	
	/*
	 * Image du joueur
	 */
	private int face;
	
	/*
	 * Indique si le joueur est en mouvement
	 */
	private boolean isMoving;
	
	/*
	 * Indique si le joueur a tiré une balle
	 */
	private boolean isShot;
	
	/*
	 * Constructeur
	 */
    public Personnage(int degat, int width, int heigth, Vector2 position, Vector2 size, String url) {
    	super(width, heigth, position, url);
    	this.degat = 2;
    	this.multiplicator = 1.0;
    	this.munitions = new ListeBalle();
		this.size = size;
		this.speed = 5.85;
		this.setTouch(false);
		this.cooldownDegat = 0;
		this.direction = new Vector2();
		this.isInvincible = false;
		this.munitions.setRange(range);
		this.setFace(2);
		this.setMoving(false);
		this.setShot(false);
		this.inv = new Inventaire();
		this.getInv().setNbBombe(1);
    }
    
    /**
     * @return le nombre de degat qu'inflige le joueur quand il attaque
     */
    public double attaque() {
    	return degat*multiplicator;
    }
    
    /**
     * @param degats subit par le joueur
     * @return Enlève la vie correspondante aux nombres de degats au joueur sauf s'il est invisible
     */
    public void subitDegats(double degats) {
    	if(!isInvincible) {
    		life.setVieEnCours((int)(life.getVieEnCours() - degats));
    		playHurtEffect((int)(Math.random()*3));
    	}	
    }
    
    private void playHurtEffect(int sound) {
		switch(sound) {
			case 0: try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Hurt_grunt_1.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
			case 1:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Hurt_grunt_2.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}	
			break;
			case 2:try {
				Jeu.music("/com/projetpo/bindingofisaac/module/libMusic/Hurt_grunt_3.wav", false);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			break;
		}
	}
    
    /**
     * @return si le joueur est en vie
     */
    public boolean isAlive() {
    	return life.getVieEnCours() > 0;
    }
    
    /**
     * @return Update les caractéristiques du joueur
     */
    public void updateGameObject()
	{
		move();
		this.updateHitbox();
		this.getHitbox().collisionPlayer(this);
		updateLife();
		this.boucleCooldownJoueur();
		munitions.update();
		LinkedList bombListClone = (LinkedList) Jeu.gameWorld.getMapEnCours().getBombList().clone();
		for(Bombe b: Jeu.gameWorld.getMapEnCours().getBombList()) {
			b.update();
			if(b.isDoRemove()) {
				bombListClone.remove(b);
			}
		} 
		Jeu.gameWorld.getMapEnCours().setBombList(bombListClone);
	}
    
    /**
     * @return Update la vie du joueur
     */
    public void updateLife() {
    	for(int i=0; i<Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().size(); i++) {
    		if(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).collisionEnnemi(this) && this.isTouch == false) {
        		this.subitDegats(Jeu.gameWorld.getMapEnCours().getListeEnnemi().getListe().get(i).getDegat());
        		this.setTouch(true);
        	}
    	}	
    }
    
    /**
     * @return Update la Hitbox du joueur
     */
    public void updateHitbox() {
    	this.getHitbox().setPosition(position);
    }

    /**
     * @return Deplacement du joueur
     */
	private void move()
	{
		Vector2 normalizedDirection = getNormalizedDirection();
		Vector2 positionAfterMoving = getPosition().addVector(normalizedDirection);
		setPosition(positionAfterMoving);
		direction = new Vector2();
	}
	
	/**
	 * @return Ajoute 1 en Y pour la direction
	 */
	public void goUpNext()
	{
		getDirection().addY(1);
	}

	/**
	 * @return Enlève 1 en Y pour la direction
	 */
	public void goDownNext()
	{
		getDirection().addY(-1);
	}

	/**
	 * @return Enlève 1 en X pour la direction
	 */
	public void goLeftNext()
	{
		getDirection().addX(-1);
	}

	/**
	 * @return Ajoute 1 en X pour la direction
	 */
	public void goRightNext()
	{
		getDirection().addX(1);
	}
	
	/**
	 * @return Le vecteur direction normalisée
	 */
	public Vector2 getNormalizedDirection()
	{
		Vector2 normalizedVector = new Vector2(direction);
		normalizedVector.euclidianNormalize(this.getSpeed());
		return normalizedVector;
	}
	
	public void animation() {
		double coef = 1.5;
		int fps = 40;
		LinkedList<Texture> liste1 = new LinkedList<Texture>();
		liste1.add(Texture.top_bot_isaac);
		liste1.add(Texture.animation2Isaac);
		liste1.add(Texture.animation3Isaac);
		liste1.add(Texture.animation4Isaac);
		liste1.add(Texture.animation5Isaac);
		liste1.add(Texture.animation6Isaac);
		liste1.add(Texture.animation7Isaac);
		liste1.add(Texture.animation8Isaac);
		liste1.add(Texture.animation9Isaac);
		liste1.add(Texture.animation10Isaac);
		LinkedList<Texture> liste2 = new LinkedList<Texture>();
		liste2.add(Texture.top_bot_isaac);
		liste2.add(Texture.animationCote1Isaac);
		liste2.add(Texture.animationCote2Isaac);
		liste2.add(Texture.animationCote3Isaac);
		liste2.add(Texture.animationCote4Isaac);
		liste2.add(Texture.animationCote5Isaac);
		liste2.add(Texture.animationCote6Isaac);
		liste2.add(Texture.animationCote7Isaac);
		liste2.add(Texture.animationCote8Isaac);
		liste2.add(Texture.animationCote9Isaac);
		liste2.add(Texture.animationCote10Isaac);
		if(face == 1 || face == 2) {
			Animation anim = new Animation(liste1, fps, new Vector2(position.getX(), position.getY()), new Vector2(25*coef, 25*coef));
			anim.anim();
		} else {
			Animation anim = new Animation(liste2, fps, new Vector2(position.getX(), position.getY()), new Vector2(25*coef, 25*coef));
			anim.anim();
		}
	}
	
	/**
	 * @Note Dessine le joueur
	 */
    public void drawPlayer() {
    	GL11.glColor4f(1f, 0f, 0f, 0.5f);
    	Texture.shadow.bind();
    	Render.getInstance().drawPicture((float)position.getX() - width/4,(float) position.getY(), 50, 10);
    	Texture.shadow.unbind();
    	GL11.glColor4f(1f, 1f, 1f, 1f);
    	if(cooldownDegat > 0 && Fenetre.tick%20 > 10) {
    		GL11.glColor4f(1f, 0f, 0f, 1f);
    	}
    	double coef = 1.5;
    	if(isMoving) {
    		animation();
    		isMoving = false;
    	} else {
    		if(face == 1 || face == 2) {
    			Texture.top_bot_isaac.bind();
        		Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()),(float)this.getPosition().getY(), (int)(25*coef), (int)(25*coef));
        		Texture.top_bot_isaac.unbind();
    		} else {
    			Texture.left_right_isaac.bind();
        		Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()),(float)this.getPosition().getY(), (int)(25*coef), (int)(25*coef));
        		Texture.left_right_isaac.unbind();
    		}
    	}
    	switch(face) {
    	case 1:
    		if(isShot) {
    	    	Texture.IsaacShotDown.bind();
    	    	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.IsaacShotDown.unbind();
			} else {
	        	Texture.bas.bind();
	        	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.bas.unbind();
			}
    		if(name == "Magdalene") {
    			Texture.MagdaleneDown.bind();
    			Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5*coef),(float)this.getPosition().getY() +23, (int)(50*coef), (int)(50*coef));
    			Texture.MagdaleneDown.unbind();
    		}
    		break;
    	case 2:
    		if(isShot) {
    			Texture.IsaacShotUp.bind();
    
    			Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.IsaacShotUp.unbind();
			} else {
	        	Texture.haut.bind();
	        	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 ,(int)(35*coef), (int)(35*coef));
	        	Texture.haut.unbind();
			}
    		if(name == "Magdalene") {
    			Texture.MagdaleneUp.bind();
    			Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5*coef),(float)this.getPosition().getY() +23, (int)(50*coef), (int)(50*coef));
    			Texture.MagdaleneUp.unbind();
    		}
    		break;
    	case 3:
    		if(isShot) {
    			Texture.IsaacShotL.bind();
    	    	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.IsaacShotL.unbind();
			} else {
	        	Texture.right.bind();
	        	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.right.unbind();
			}
    		if(name == "Magdalene") {
    			Texture.MagdaleneL.bind();
    			Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5*coef),(float)this.getPosition().getY() +23, (int)(50*coef), (int)(50*coef));
    			Texture.MagdaleneL.unbind();
    		}
    		break;
    	case 4:
    		if(isShot) {
    			Texture.IsaacShotR.bind();
    	    	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.IsaacShotR.unbind();
			} else {
	        	Texture.left.bind();
	        	Render.getInstance().drawPicture((float) ((float)this.getPosition().getX()-5*coef),(float)this.getPosition().getY() +25 , (int)(35*coef), (int)(35*coef));
	        	Texture.left.unbind();
			}
    		if(name == "Magdalene") {
    			Texture.MagdaleneR.bind();
    			Render.getInstance().drawPicture((float) ((float)this.getPosition().getX() - 12.5*coef),(float)this.getPosition().getY() +23, (int)(50*coef), (int)(50*coef));
    			Texture.MagdaleneR.unbind();
    		}
        	break;
    	}
    	if(isShot) {
    		this.setShot(false);
    	}
    	GL11.glColor4f(1f, 1f, 1f, 1f);
    }
    
    public void drawInventaire() {
    	
    }
    
    /**
     * @return Calcul tous les cooldown en cours
     */
    public void boucleCooldownJoueur()
    {
    	if(!this.getMunitions().isNotShot()) { //Cooldown balle
    		this.getMunitions().setCoolDown(this.getMunitions().getCoolDown()+1);
			if(this.getMunitions().getCoolDown() == Fenetre.getInstance().getFPS()/3) {
				this.getMunitions().setCoolDown(0);
				this.getMunitions().setShot(true);
			};
		}
    	if(isTouch()) { //Cooldown degat
			setCooldownDegat((this.getCooldownDegat()+1));
			if(getCooldownDegat() == Fenetre.getInstance().getFPS()) {
				setCooldownDegat(0);
				setTouch(false);
			};
		}
    }
    
    /**
     * @param b une balle
     * @return si une balle ennemi est en collision avec le joueur
     */
    public boolean collisionBalle(Balle b) {
		return (Hitbox.rectangleCollision(b.position, new Vector2(b.getHitbox().getWidth(), b.getHitbox().getHeigth()), position, new Vector2(hitbox.getWidth(), hitbox.getHeigth())) && !isTouch); 
	}
     
    /*
     * Getters & Setters
     */
	public ListeBalle getMunitions() {
		return munitions;
	}

	public void setMunitions(ListeBalle munitions) {
		this.munitions = munitions;
	}

	public double getDegat() {
		return degat;
	}

	public void setDegat(double degat) {
		this.degat = degat;
	}
	

	public double getMultiplicator() {
		return multiplicator;
	}

	public void setMultiplicator(double multiplicator) {
		this.multiplicator = multiplicator;
	}

	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
		this.munitions.setRange(range);
	}

	public BarreDeVie getLife() {
		return life;
	}

	public void setLife(BarreDeVie life) {
		this.life = life;
	}

	public Inventaire getInv() {
		return inv;
	}

	public void setInv(Inventaire inv) {
		this.inv = inv;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getDirection() {
		return direction;
	}

	public void setDirection(Vector2 direction) {
		this.direction = direction;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}

	public void setHitbox(Hitbox hitbox) {
		this.hitbox = hitbox;
	}

	public boolean isTouch() {
		return isTouch;
	}

	public void setTouch(boolean isTouch) {
		this.isTouch = isTouch;
	}

	public int getCooldownDegat() {
		return cooldownDegat;
	}

	public void setCooldownDegat(int cooldownDegat) {
		this.cooldownDegat = cooldownDegat;
	}

	public boolean isInvincible() {
		return isInvincible;
	}

	public void setInvincible(boolean isInvincible) {
		this.isInvincible = isInvincible;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	public boolean isShot() {
		return isShot;
	}

	public void setShot(boolean isShot) {
		this.isShot = isShot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
}