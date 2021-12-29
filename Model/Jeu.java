package Model;

import Vue.Fenetre;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import Controler.Input;
import Shaders.Vector2;

public class Jeu {
	
	public static Personnage Isaac = new Personnage(10, 25, 25, new Vector2(100, 100), new Vector2(1, 1), "libImg/Isaac.png");
	public static final Room room = new Room(Isaac);
	
	public static void music(String test, boolean again) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		java.net.URL url = Jeu.class.getResource(test);
		final Clip clip = AudioSystem.getClip();
		try(AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
			clip.open(audioIn);
		}
		clip.start();
		clip.stop();
		if (again) clip.loop(Clip.LOOP_CONTINUOUSLY); 
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