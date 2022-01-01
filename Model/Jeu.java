package com.projetpo.bindingofisaac.module.Model;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Shaders.Vector2;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Jeu {
	
	public static Personnage Isaac = new Personnage(10, 25, 25, new Vector2(100, 100), new Vector2(1, 1), "libImg/Isaac.png");
	public static final GameWorld gameWorld = new GameWorld(Isaac);
	
	public static void music(String test, boolean again) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	/*	java.net.URL url = Jeu.class.getResource(test);
		final Clip clip = AudioSystem.getClip();
		try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
		if (again) clip.loop(Clip.LOOP_CONTINUOUSLY); */
	}
	
    public static void main(String[] args)
    {   
    	Fenetre.getInstance().init();
    	Fenetre.getInstance().create();
    	Input.getInstance().init(Fenetre.getInstance().getWindow());
    /*	try {
			music("/libMusic/basement.wav", true);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} */
    	Fenetre.getInstance().run();
    }

}