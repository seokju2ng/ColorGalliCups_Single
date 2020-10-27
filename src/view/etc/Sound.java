package view.etc;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 게임에서 사용되는 모든 소리를 관리한다.
 * 
 * @author 김용희
 */
public class Sound {
	/**
	 * 효과음 ON/OFF 설정에 대한 정보를 저장한다.
	 */
	private static boolean effect;
	/**
	 * 배경음악 ON/OFF 설정에 대한 정보를 저장한다.
	 */
	private static boolean bgm;
	/**
	 * 효과음 및 배경음을 재생하기 위한 AudioInputStream 객체이다.
	 */
	private static AudioInputStream ais;
	/**
	 * 효과음 및 배경음을 재생하기 위한 Clip 객체이다.
	 */
	private static Clip bgmClip;
	/**
	 * 파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다. 효과음에 해당하는 소리를 재생한다.
	 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.
	 */
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
	/**
	 * 파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다. 배경음악에 해당하는 소리를 재생한다.
	 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.
	 */
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
	/**
	 * 재생되는 소리를 중단하는 메소드이다.
	 */
	public static void stop() {
		if(bgmClip == null) return;
		try {
			bgmClip.stop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 파일의 경로를 받아 해당 파일의 소리를 재생해주는 메소드이다. 배경음악을 ON으로 만들고 해당소리를 재생한다.
	 * @param fileName 게임에서 사용 하고싶은 소리의 파일 경로이다.
	 */
	public static void bgmOn(String fileName) {
		if(bgm) return;
		bgm = true;
		playBgm(fileName);
	}
	/**
	 * 멤버 필드인 bgm을 true로 만들어 주는 메소드이다.
	 */
	public static void bgmOn() {
		bgm = true;
	}
	/**
	 * 멤버 필드인 bgm을 false로 만들어 주는 메소드이다.
	 */
	public static void bgmOff() {
		if(bgm) {
			bgm = false;
			stop();
		}
	}
	/**
	 * 멤버 필드인 effect을 true로 만들어 주는 메소드이다.
	 */
	public static void effectOn() {
		effect = true;
	}
	/**
	 * 멤버 필드인 effect을 false로 만들어 주는 메소드이다.
	 */
	public static void effectOff() {
		effect = false;
	}

}
