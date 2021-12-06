package Model;

import java.util.*;

/**
 * 
 */
public class BarreDeVie {

	/**
     * 
     */
    private Integer viePleine;

    /**
     * 
     */
    private Integer vieEnCours;
    
    /**
     * Default constructor
     */
    public BarreDeVie(int tailleVie) {
    	this.setViePleine(tailleVie);
    	this.setVieEnCours(tailleVie);
    }

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
	
	
	public boolean absorbeImpact(int degats)
	{
		return (vieEnCours > degats);
	}
	
	/*public void recoitDommage(Ennemi ennemi)
	{
		if(absordeImpact(ennemi.getArme().getDegat()))
		{
			vieEnCours-=ennemi.gatArme().getDegat();
		}
		else
		{
			vieEnCours = 0;
		}
		
	}*/
	
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


}