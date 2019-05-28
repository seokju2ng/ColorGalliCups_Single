package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class OptionService {
	private Option option;
	private static final String opPath = "data/option.dat";
	
	public OptionService() {
		option = new Option();
		loadOption();
		System.out.println(option);
	}
	
	public ArrayList<String> getOption() {
		if(option == null) option = new Option();
		ArrayList<String> op = new ArrayList<String>();
		op.add(option.isBgm()?"ON":"OFF");
		op.add(option.isEffect()?"ON":"OFF");
		op.add(option.getCardNum()+"");
		return op;
	}

	public void setOption(ArrayList<String> option) {
		if(this.option == null || option == null) return;
		this.option.setBgm(option.get(0).equals("ON") ? true : false);
		this.option.setEffect(option.get(1).equals("ON") ? true : false);
		this.option.setCardNum(Integer.parseInt(option.get(2)));
	}
	
	public void setOption(boolean bgm, boolean effect, int cardNum) {
		this.option.setBgm(bgm);
		this.option.setEffect(effect);
		this.option.setCardNum(cardNum);
	}
	
	public void bgmOn() {
		if(option == null) return;
		option.setBgm(true);
	}
	public void bgmOff() {
		if(option == null) return;
		option.setBgm(false);
	}
	public void effectOn() {
		if(option == null) return;
		option.setEffect(true);
	}
	public void effectOff() {
		if(option == null) return;
		option.setEffect(false);
	}
	public void setCardNum(int cardNum) {
		if(option == null) return;
		if(cardNum < 0) return;
		option.setCardNum(cardNum);
	}
	
	public boolean loadOption() {
		try(BufferedReader br = new BufferedReader(new FileReader(opPath))){
			boolean bgm = Integer.parseInt(br.readLine()) == 1 ? true:false;
			boolean effect = Integer.parseInt(br.readLine()) == 1 ? true:false;
			int cardNum = Integer.parseInt(br.readLine());
			this.option.setBgm(bgm);
			this.option.setEffect(effect);
			this.option.setCardNum(cardNum);
		} catch(Exception e) { 
			System.err.println(e); return false;
		}
		return true;
	}
	
	public boolean saveOption() {
		try(PrintWriter pw = new PrintWriter(new File(opPath));) {
			pw.println(option.isBgm()?1:0);
			pw.println(option.isEffect()?1:0);
			pw.println(option.getCardNum());
		} catch (FileNotFoundException e) {
			System.err.println(e);
			return false;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}
}
