package model;

public class Option {
	private boolean bgm;
	private boolean sound;
	private int cardNum;
	
	public Option() {
		bgm = true; sound = true; cardNum = 20;
	}
	public Option(Option op) {
		bgm = op.isBgm();
		sound = op.isSound();
		cardNum = op.getCardNum();
	}
	public Option(boolean bgm, boolean sound, int cardNum) {
		this.bgm = bgm;
		this.sound = sound;
		this.cardNum = cardNum;
	}

	public boolean isBgm() {
		return bgm;
	}

	public void setBgm(boolean bgm) {
		this.bgm = bgm;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	
	public String toString() {
		return "Option[bgm:"+(bgm?"ON":"OFF")+
				"/sound:"+(sound?"ON":"OFF")+
				"/cardNum:"+cardNum+"]";
	}
}
