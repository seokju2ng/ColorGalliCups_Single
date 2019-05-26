package view.etc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 * 게임에서 사용되는 모든 소리를 관리한다.
 * @author cms<br>*/
public class Sound {
		/**파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다.
		 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.*/
		private boolean effectFlag;
		private boolean bgmFlag;
		public static AudioInputStream ais;
		private static Clip BgmClip;
		
		public static void playEffect(String fileName) {
		//if(effectFlag == true)
	      try {
	    	 AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
	         Clip clip = AudioSystem.getClip();
	         clip.stop();
	         clip.open(ais);
	         clip.start();

	      }  catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
		public static void playBgm(String fileName) {
			//if(bgmFlag == true) 
		      try {
		    	 ais = AudioSystem.getAudioInputStream(new File(fileName));
		    	 BgmClip = AudioSystem.getClip();
		    	 BgmClip.stop();
		    	 BgmClip.open(ais);
		    	 BgmClip.start();
		    	 BgmClip.loop(-1);
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
	   public static void stop() {
		      try {
		    	  BgmClip.stop();

		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
}
