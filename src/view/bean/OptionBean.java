package view.bean;

public class OptionBean {
	private boolean bgm;
	private boolean sound;
	private int cardNum;
	
	public OptionBean() {
		bgm = true; sound = true; cardNum = 20;
	}
	public OptionBean(OptionBean op) {
		bgm = op.isBgm();
		sound = op.isSound();
		cardNum = op.getCardNum();
	}
	public OptionBean(boolean bgm, boolean sound, int cardNum) {
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
		return "OptionBean[bgm:"+(bgm?"ON":"OFF")+
				"/sound:"+(sound?"ON":"OFF")+
				"/cardNum:"+cardNum+"]";
	}
}

