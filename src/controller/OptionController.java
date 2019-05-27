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
		boolean effect = option.get(1).equals("ON") ? true : false;
		int cardNum = Integer.parseInt(option.get(2));
		return new OptionBean(bgm, effect, cardNum);
	}

	public void setOption(OptionBean option) {
		if(optionService == null || option == null) return;
		ArrayList<String> op = new ArrayList<String>();
		op.add(option.isBgm()?"ON":"OFF");
		op.add(option.isEffect()?"ON":"OFF");
		op.add(option.getCardNum()+"");
		optionService.setOption(op);
	}
	
	public void setOption(boolean bgm, boolean effect, int cardNum) {
		if(optionService == null) return;
		optionService.setOption(bgm, effect, cardNum);
	}
	
	public boolean saveOption() {
		if(optionService == null) return false;
		return optionService.saveOption();
	}
}
