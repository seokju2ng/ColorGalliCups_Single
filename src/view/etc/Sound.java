package view.etc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**@author cms </br> 
 * 게임에서 사용되는 모든 소리를 관리한다.
 * */
public class Sound {
		/**파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다.
		 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.*/
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
