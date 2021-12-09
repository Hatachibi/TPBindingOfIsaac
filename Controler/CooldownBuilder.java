package Controler;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import Model.Balle;

/**
 * source : https://forum.minestrator.com/threads/java-creer-un-cooldown.14/
 * @author Maxlego08
 *
 */
public class CooldownBuilder {
	
	public static HashMap<String, HashMap<UUID, Long>> cooldownHashMap = new HashMap<>();
	
	public static void createCooldown(String cooldown) {
		
		if (cooldownHashMap.containsKey(cooldown)) {
	        throw new IllegalArgumentException("Ce cooldown existe déjà.");
	    }

		cooldownHashMap.put(cooldown, new HashMap<>());

	}
	
	public static void addCooldown(String s, Balle balle, int seconds) throws IOException {
	    if (!cooldownHashMap.containsKey(s)) {
	        throw new IllegalArgumentException(String.valueOf(s) + " n'existe pas.");
	    }

	    long next = System.currentTimeMillis() + seconds * 1000L;
	    (cooldownHashMap.get(s)).put(balle.getUniqueId(), Long.valueOf(next));
	}
	
	public static boolean isCooldown(String s, Balle balle) {
	    return (cooldownHashMap.containsKey(s)) && ((cooldownHashMap.get(s)).containsKey(balle.getUniqueId())) && (System.currentTimeMillis() <= ((Long) (cooldownHashMap.get(s)).get(balle.getUniqueId())).longValue());
	}
	
	public static long getCooldownBalleLong(String s, Balle balle) {
	    return ((Long)(cooldownHashMap.get(s)).get(balle.getUniqueId())).longValue() - System.currentTimeMillis();
	}
	
	

}
