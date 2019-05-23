package view.etc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

	   public static void playSound(String fileName) {
	      try {
	         AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	         Clip clip = AudioSystem.getClip();
	         clip.stop();
	         clip.open(ais);
	         clip.start();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
}
