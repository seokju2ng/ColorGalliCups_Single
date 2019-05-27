package view.bean;

public class OptionBean {
	private boolean bgm;
	private boolean effect;
	private int cardNum;
	
	public OptionBean() {
		bgm = true; effect = true; cardNum = 20;
	}
	public OptionBean(OptionBean op) {
		this();
		if(op != null) {
			bgm = op.isBgm();
			effect = op.isEffect();
			cardNum = op.getCardNum();
		}
	}
	public OptionBean(boolean bgm, boolean effect, int cardNum) {
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
		return "OptionBean[bgm:"+(bgm?"ON":"OFF")+
				"/effect:"+(effect?"ON":"OFF")+
				"/cardNum:"+cardNum+"]";
	}
}

