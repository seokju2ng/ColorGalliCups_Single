package view.bean;

import controller.OptionController;
import view.etc.Sound;

public class Options {
	private OptionBean option;
	private OptionController opControl;
	
	public Options() {
		opControl = new OptionController();
		option = opControl.getOption();
		System.out.println(option);
	}
	
	public OptionBean getOption() {
		if(opControl == null) opControl = new OptionController();
		if(option == null) option = opControl.getOption();
		return option;
	}
	
	public void setOption(boolean bgm, boolean effect, int cardNum) {
		this.option.setBgm(bgm);
		this.option.setEffect(effect);
		this.option.setCardNum(cardNum);
		if(bgm) Sound.bgmOn(); else Sound.bgmOff();
		if(effect) Sound.effectOn(); else Sound.effectOff();
//		DualPlayMode.setCardNum(cardNum);
	}
	public void setOption(OptionBean option) {
		if(option == null) return;
		this.setOption(option.isBgm(), option.isEffect(), option.getCardNum());
	}
	
	public boolean saveOption() {
		if(opControl == null) return false;
		opControl.setOption(option);
		return opControl.saveOption();
	}
}
