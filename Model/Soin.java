package Model;

public class Soin extends ObjetsInventaire {
	
	private Integer santeEnPlus;
	
    /**
     * Constructeur
     */
    public Soin() {
    }

    public Integer getSanteEnPlus() {
		return santeEnPlus;
	}

	public void setSanteEnPlus(Integer santeEnPlus) {
		this.santeEnPlus = santeEnPlus;
	}

}