package view.bean;

import controller.OptionController;

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

	public void setOption(boolean bgm, boolean sound, int cardNum) {
		this.option.setBgm(bgm);
		this.option.setSound(sound);
		this.option.setCardNum(cardNum);
	}
	
//	public void bgmOn() {
//		if(option == null) return;
//		option.setBgm(true);
//	}
//	public void bgmOff() {
//		if(option == null) return;
//		option.setBgm(false);
//	}
//	public void soundOn() {
//		if(option == null) return;
//		option.setSound(true);
//	}
//	public void soundOff() {
//		if(option == null) return;
//		option.setSound(false);
//	}
//	public void setCardNum(int cardNum) {
//		if(option == null) return;
//		if(cardNum < 0) return;
//		option.setCardNum(cardNum);
//	}
	
	public boolean saveOption() {
		if(opControl == null) return false;
		return opControl.saveOption();
	}
}
