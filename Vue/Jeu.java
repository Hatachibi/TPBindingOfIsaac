package com.projetpo.bindingofisaac.module.Vue;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Jeu {

	public static final GameWorld gameWorld = new GameWorld();
	
	public static void music(String music, boolean again) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		java.net.URL url = Jeu.class.getResource(music);
		final Clip clip = AudioSystem.getClip();
		try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
		if (again) clip.loop(Clip.LOOP_CONTINUOUSLY);    
	}
	
    public static void main(String[] args)
    {   
    	Fenetre.getInstance().init();
    	Fenetre.getInstance().create();
    	try {
			music("/com/projetpo/bindingofisaac/module/libMusic/basement.wav", true);
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			e.printStackTrace();
		} 
    	Fenetre.getInstance().setState(1);
    	Fenetre.getInstance().run();
    }

}