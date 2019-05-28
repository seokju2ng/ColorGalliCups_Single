package model;

public class Option {
	private boolean bgm;
	private boolean effect;
	private int cardNum;
	
	public Option() {
		bgm = true; effect = true; cardNum = 20;
	}
	public Option(Option op) {
		bgm = op.isBgm();
		effect = op.isEffect();
		cardNum = op.getCardNum();
	}
	public Option(boolean bgm, boolean effect, int cardNum) {
		this.bgm = bgm;
		this.effect = effect;
		this.cardNum = cardNum;
	}

	public boolean isBgm() {
		return bgm;
	}

	public void setBgm(boolean bgm) {
		this.bgm = bgm;
	}

	public boolean isEffect() {
		return effect;
	}

	public void setEffect(boolean effect) {
		this.effect = effect;
	}

	public int getCardNum() {
		return cardNum;
	}

	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	
	public String toString() {
		return "Option[bgm:"+(bgm?"ON":"OFF")+
				"/effect:"+(effect?"ON":"OFF")+
				"/cardNum:"+cardNum+"]";
	}
}
