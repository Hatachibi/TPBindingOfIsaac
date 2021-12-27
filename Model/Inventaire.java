package Model;

public class Inventaire {
	
	/*
	 * Tableau contenant tout les objets du joueur
	 */
	private ObjetsInventaire[] objets;

	/*
	 * Constructeur
	 */
    public Inventaire() {
    	this.setObjets(new ObjetsInventaire[5]); 
    }

    /*
     * Getters & Setters
     */
	public ObjetsInventaire[] getObjets() {
		return objets;
	}

	public void setObjets(ObjetsInventaire[] objets) {
		this.objets = objets;
	}

}