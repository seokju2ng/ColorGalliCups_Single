package view.etc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 게임에서 사용되는 모든 소리를 관리한다.
 * 
 * @author cms<br>
 */
public class Sound {
	/**
	 * 파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다.
	 * 
	 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.
	 */
	private static boolean effect;
	private static boolean bgm;
	private static AudioInputStream ais;
	private static Clip bgmClip;

	public static void playEffect(String fileName) {
		if(effect) {
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

	public static void playBgm(String fileName) {
		if(bgm) {
			try {
				ais = AudioSystem.getAudioInputStream(new File(fileName));
				bgmClip = AudioSystem.getClip();
				bgmClip.stop();
				bgmClip.open(ais);
				bgmClip.start();
				bgmClip.loop(-1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void stop() {
		try {
			bgmClip.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void bgmOn() {
		bgm = true;
	}
	public static void bgmOff() {
		bgm = false;
	}
	public static void effectOn() {
		effect = true;
	}
	public static void effectOff() {
		effect = false;
	}

}
