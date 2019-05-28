package controller;

import java.util.ArrayList;

import model.OptionService;
import view.bean.OptionBean;
/**
 * Model Class(OptionService)로부터 정보를 받아 View Class(Options)에서 원하는 정보로 변경하여 전달하는 Controller 클래스이다.
 * @author seokjung
 *
 */
public class OptionController {
	/**
	 * OptionController가 접근할 Model(OptionService) 객체이다.
	 */
	private OptionService optionService;
	/**
	 * 멤버 필드의 초기화 책임을 갖는 Null-Parameter 생성자이다.
	 */
	public OptionController() {
		optionService = new OptionService();
	}
	/**
	 * OptionOptionService로부터 받아온 Option 정보를 OptionBean 타입으로 변환하여 View의 Options Class에 반환해준다.
	 * @return Option 정보를 OptionBean 타입으로 변환하여 반환해준다.
	 */
	public OptionBean getOption() {
		if(optionService == null) return null;
		ArrayList<String> option = optionService.getOption();
		boolean bgm = option.get(0).equals("ON") ? true : false;
		boolean effect = option.get(1).equals("ON") ? true : false;
		int cardNum = Integer.parseInt(option.get(2));
		return new OptionBean(bgm, effect, cardNum);
	}
	/**
	 * 파라메터로 전달된 OptionBean 타입의 객체의 필드값을 복사하여 이 객체의 option 값을 설정해준다.
	 * @param option 파라메터로 전달된 객체의 필드값을 복사하여 이 객체의 option 값을 설정해준다.
	 */
	public void setOption(OptionBean option) {
		if(optionService == null || option == null) return;
		ArrayList<String> op = new ArrayList<String>();
		op.add(option.isBgm()?"ON":"OFF");
		op.add(option.isEffect()?"ON":"OFF");
		op.add(option.getCardNum()+"");
		optionService.setOption(op);
	}
	/**
	 * 파라메터로 전달된 세 개의 값으로 이 객체의 필드값을 초기화해준다.
	 * @param bgm 배경음악 ON/OFF 여부를 전달한다.
	 * @param effect 효과음 ON/OFF 여부를 전달한다.
	 * @param cardNum 시스템 카드 덱 개수를 전달한다.
	 */
	public void setOption(boolean bgm, boolean effect, int cardNum) {
		if(optionService == null) return;
		optionService.setOption(bgm, effect, cardNum);
	}
	/**
	 * Option 값을 저장하기 위해 OptionService의 saveOption() 메서드를 호출해준다.
	 * @return 저장에 성공했으면 true를, 실패했으면 false를 반환해준다.
	 */
	public boolean saveOption() {
		if(optionService == null) return false;
		return optionService.saveOption();
	}
}
