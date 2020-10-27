package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Option 정보를 Controller와 Option이 주고받을 수 있도록 해주는 OptionService 클래스이다.
 * 
 * @author 송준희
 *
 */
public class OptionService {
	/**
	 * Option 정보를 저장한다.
	 */
	private Option option;
	/**
	 * Option 정보를 저장하고 불러오는 파일의 경로를 저장한다.
	 */
	private static final String opPath = "data/option.dat";

	/**
	 * null parameter constructor. 파일로부터 Option 정보를 읽어 저장한다.
	 */
	public OptionService() {
		option = new Option();
		loadOption();
		System.out.println(option);
	}

	/**
	 * Option 정보를 Controller에 전달한다.
	 * 
	 * @return Option 정보를 ArrayList&lt;String&gt; 타입으로 변환하여 Controller에 반환한다.
	 */
	public ArrayList<String> getOption() {
		if (option == null)
			option = new Option();
		ArrayList<String> op = new ArrayList<String>();
		op.add(option.isBgm() ? "ON" : "OFF");
		op.add(option.isEffect() ? "ON" : "OFF");
		op.add(option.getCardNum() + "");
		return op;
	}

	/**
	 * 파라미터로 받은 Option 정보를 기존의 Option 정보에 저장한다.
	 * 
	 * @param option
	 *            정보 ArrayList&lt;String&gt;타입으로 저장한다.
	 */
	public void setOption(ArrayList<String> option) {
		if (this.option == null || option == null)
			return;
		this.option.setBgm(option.get(0).equals("ON") ? true : false);
		this.option.setEffect(option.get(1).equals("ON") ? true : false);
		this.option.setCardNum(Integer.parseInt(option.get(2)));
	}

	/**
	 * 파라미터로 받은 Option 정보를 기존의 Option 정보에 저장한다.
	 * 
	 * @param bgm
	 *            배경음 상태를 저장한다.
	 * @param effect
	 *            효과음 상태를 저장한다.
	 * @param cardNum
	 *            게임할 때 사용할 카드의 개수를 저장한다.
	 */
	public void setOption(boolean bgm, boolean effect, int cardNum) {
		this.option.setBgm(bgm);
		this.option.setEffect(effect);
		this.option.setCardNum(cardNum);
	}

	/**
	 * 배경음을 On으로 설정한다.
	 */
	public void bgmOn() {
		if (option == null)
			return;
		option.setBgm(true);
	}

	/**
	 * 배경음을 Off로 설정한다.
	 */
	public void bgmOff() {
		if (option == null)
			return;
		option.setBgm(false);
	}

	/**
	 * 효과음을 On으로 설정한다.
	 */
	public void effectOn() {
		if (option == null)
			return;
		option.setEffect(true);
	}

	/**
	 * 효과음을 Off로 설정한다.
	 */
	public void effectOff() {
		if (option == null)
			return;
		option.setEffect(false);
	}

	/**
	 * 입력받은 값에 따라 게임할 때 사용할 카드의 개수를 설정한다.
	 * 
	 * @param cardNum
	 *            게임할 때 사용할 카드의 개수를 저장한다.
	 */
	public void setCardNum(int cardNum) {
		if (option == null)
			return;
		if (cardNum < 0)
			return;
		option.setCardNum(cardNum);
	}

	/**
	 * 파일로부터 Option 정보를 읽어와 기존의 Option 정보를 변경한다.
	 * 
	 * @return 파일로부터 정보를 받아오는데 성공하면 true, 실패하면 false를 반환한다.
	 */
	public boolean loadOption() {
		try (BufferedReader br = new BufferedReader(new FileReader(opPath))) {
			boolean bgm = Integer.parseInt(br.readLine()) == 1 ? true : false;
			boolean effect = Integer.parseInt(br.readLine()) == 1 ? true : false;
			int cardNum = Integer.parseInt(br.readLine());
			this.option.setBgm(bgm);
			this.option.setEffect(effect);
			this.option.setCardNum(cardNum);
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	/**
	 * 파일에 Option 정보를 저장한다.
	 * 
	 * @return 파일에 정보를 쓰는데 성공하면 true, 실패하면 false를 반환한다.
	 */
	public boolean saveOption() {
		try (PrintWriter pw = new PrintWriter(new File(opPath));) {
			pw.println(option.isBgm() ? 1 : 0);
			pw.println(option.isEffect() ? 1 : 0);
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
