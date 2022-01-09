package com.projetpo.bindingofisaac.module.Model;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import com.projetpo.bindingofisaac.module.Controler.Input;
import com.projetpo.bindingofisaac.module.Vue.Fenetre;

public class Jeu {

	public static final GameWorld gameWorld = new GameWorld();
	
	public static void music(String test, boolean again) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
	/*	java.net.URL url = Jeu.class.getResource(test);
		final Clip clip = AudioSystem.getClip();
		try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
		if (again) clip.loop(Clip.LOOP_CONTINUOUSLY);   */
	}
	
    public static void main(String[] args)
    {   
    	Fenetre.getInstance().init();
    	Fenetre.getInstance().create();
    	Input.getInstance().init(Fenetre.getInstance().getWindow());
    	try {
			music("/com/projetpo/bindingofisaac/module/libMusic/basement.wav", true);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} 
    	Fenetre.getInstance().setState(1);
    	Fenetre.getInstance().run();
    }

}