package Model;

import Vue.Render;
import Vue.Texture;

public class BarreDeVie {

	/**
	 * Entier représentant le max de vie 
     */
    private Integer viePleine;

    /**
     * Entier représentant la vie en cours
     */
    private Integer vieEnCours;
    
    /**
     * Constructeur
     */
    public BarreDeVie(int tailleVie) {
    	this.setViePleine(tailleVie);
    	this.setVieEnCours(tailleVie);
    }
    
    /**
     * @return Dessine la Barre de Vie du joueur
     */
    public void drawBarDeVie() {
    	float x = 25;
    	float y = 540;   
    	for(int i=0; i<vieEnCours; i+=2) {
    		if(i==vieEnCours-1) {
    			Texture.halfHeart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25, 1, 1, new float[]{});
    		} else {
    			Texture.heart.bind();
    			Render.getInstance().drawPicture(x, y, 25, 25, 1, 1, new float[]{});
    		}
    		x += 25;
    	}
    	Texture.halfHeart.unbind();
    	Texture.heart.unbind();
    }
    
    public boolean absorbeImpact(int degats)
	{
		return (vieEnCours > degats);
	}
		
	public void soin(Soin soin)
	{
		if((vieEnCours + soin.getSanteEnPlus()) <= viePleine)
		{
			vieEnCours+=soin.getSanteEnPlus();
		}
		else
		{
			vieEnCours = viePleine;
		}
	}

	/*
	 * Getters & Setters
	 */
    public Integer getViePleine() {
		return viePleine;
	}

	public void setViePleine(Integer viePleine) {
		this.viePleine = viePleine;
	}

	public Integer getVieEnCours() {
		return vieEnCours;
	}

	public void setVieEnCours(Integer vieEnCours) {
		this.vieEnCours = vieEnCours;
	}

}