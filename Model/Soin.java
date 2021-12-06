package Model;

import java.util.*;

/**
 * 
 */
public class Soin extends ObjetsInventaire {

    /**
     * Default constructor
     */
    public Soin() {
    }

    public Integer getSanteEnPlus() {
		return santeEnPlus;
	}

	public void setSanteEnPlus(Integer santeEnPlus) {
		this.santeEnPlus = santeEnPlus;
	}

	/**
     * 
     */
    private Integer santeEnPlus;

}