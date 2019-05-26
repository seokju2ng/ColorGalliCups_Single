package controller;

import java.util.ArrayList;

import model.OptionService;
import view.bean.OptionBean;

public class OptionController {
	private OptionService optionService;
	public OptionController() {
		optionService = new OptionService();
	}
	public OptionBean getOption() {
		if(optionService == null) return null;
		ArrayList<String> option = optionService.getOption();
		boolean bgm = option.get(0).equals("ON") ? true : false;
		boolean sound = option.get(1).equals("ON") ? true : false;
		int cardNum = Integer.parseInt(option.get(2));
		return new OptionBean(bgm, sound, cardNum);
	}

	public void setOption(OptionBean option) {
		if(optionService == null || option == null) return;
		ArrayList<String> op = new ArrayList<String>();
		op.add(option.isBgm()?"ON":"OFF");
		op.add(option.isSound()?"ON":"OFF");
		op.add(option.getCardNum()+"");
		optionService.setOption(op);
	}
	
	public void setOption(boolean bgm, boolean sound, int cardNum) {
		if(optionService == null) return;
		optionService.setOption(bgm, sound, cardNum);
	}
	
//	public void bgmOn() {
//		if(optionService == null) return;
//		optionService.bgmOn();
//	}
//	public void bgmOff() {
//		if(optionService == null) return;
//		optionService.bgmOff();
//	}
//	public void soundOn() {
//		if(optionService == null) return;
//		optionService.soundOn();
//	}
//	public void soundOff() {
//		if(optionService == null) return;
//		optionService.soundOff();
//	}
//	public void setCardNum(int cardNum) {
//		if(optionService == null) return;
//		optionService.setCardNum(cardNum);
//	}
	
//	public boolean loadOption() {
//		return optionService.loadOption();
//	}
	
	public boolean saveOption() {
		return optionService.saveOption();
	}
}
