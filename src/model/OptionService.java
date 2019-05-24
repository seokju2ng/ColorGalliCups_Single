package model;

import java.io.BufferedReader;
import java.io.FileReader;

public class OptionService {
	private Option option;
	private final String opPath = "data/option.txt";
	
	public OptionService() {
		setOption(new Option());
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option.setBgm(option.isBgm());
		this.option.setSound(option.isSound());
		this.option.setCardNum(option.getCardNum());
	}
	
	public void setOption(boolean bgm, boolean sound, int cardNum) {
		this.option.setBgm(bgm);
		this.option.setSound(sound);
		this.option.setCardNum(cardNum);
	}
	
	public boolean loadOption() {
		try(BufferedReader br = new BufferedReader(new FileReader(opPath))){
			boolean bgm = Integer.parseInt(br.readLine()) == 1 ? true:false;
			boolean sound = Integer.parseInt(br.readLine()) == 1 ? true:false;
			int cardNum = Integer.parseInt(br.readLine());
			this.option.setBgm(bgm);
			this.option.setSound(sound);
			this.option.setCardNum(cardNum);
		} catch(Exception e) { 
			System.err.println(e); return false;
		}
		return true;
	}
	
	public boolean saveOption() {
		return true;
	}
}
