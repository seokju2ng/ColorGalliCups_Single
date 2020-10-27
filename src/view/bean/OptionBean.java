package view.bean;
/**
 * Option 설정을 위한 View Entity Bean Class이다. 멤버 필드로는 배경음악, 효과음, 카드 숫자가 있다.
 * @author seokjung
 *
 */
public class OptionBean {
	/**
	 * 배경음악의 ON / OFF를 저장할 boolean 타입 변수이다.
	 */
	private boolean bgm;
	/**
	 * 효과음의 ON / OFF를 저장할 boolean 타입 변수이다.
	 */
	private boolean effect;
	/**
	 * 듀얼 게임의 시스템 카드 덱 수를 저장할 int 타입 변수이다.
	 */
	private int cardNum;
	/**
	 * Null-Parameter 생성자이다. 
	 * 멤버 필드의 디폴트 값은 배경음악 ON, 효과음 ON, 카드덱 20장이다. 
	 */
	public OptionBean() {
		bgm = true; effect = true; cardNum = 20;
	}
	/**
	 * Copy Constructor이다.
	 * OptionBean 객체를 전달인자로 주었을 때 값을 복사해 생성해주는 복사 생성자이다.
	 * @param op 복사할 OptionBean 객체이다.
	 */
	public OptionBean(OptionBean op) {
		bgm = op.isBgm();
		effect = op.isEffect();
		cardNum = op.getCardNum();
	}
	/**
	 * Overloaded Constructor이다.
	 * 전달인자로 전달해준 세 개의 값으로 멤버 필드 초기화를 진행할 생성자이다.
	 * @param bgm 배경음악 ON/OFF 여부를 전달한다.
	 * @param effect 효과음 ON/OFF 여부를 전달한다.
	 * @param cardNum 시스템 카드 덱 개수를 전달한다.
	 */
	public OptionBean(boolean bgm, boolean effect, int cardNum) {
		this.bgm = bgm;
		this.effect = effect;
		this.cardNum = cardNum;
	}

	/**
	 * 배경음악의 ON/OFF 여부를 리턴한다.
	 * @return 배경음악이 켜져있으면 true, 꺼져있으면 false를 반환한다.
	 */
	public boolean isBgm() {
		return bgm;
	}
	/**
	 * 전달해준 bgm 값으로 배경음악을 설정한다.
	 * @param bgm 전달받은 값으로 멤버 필드를 초기화한다.
	 */
	public void setBgm(boolean bgm) {
		this.bgm = bgm;
	}
	/**
	 * 효과음의 ON/OFF 여부를 리턴한다.
	 * @return 효과음이 켜져있으면 true, 꺼져있으면 false를 반환한다.
	 */
	public boolean isEffect() {
		return effect;
	}
	/**
	 * 전달해준 effect 값으로 배경음악을 설정한다.
	 * @param effect 전달받은 값으로 멤버 필드를 초기화한다.
	 */
	public void setEffect(boolean effect) {
		this.effect = effect;
	}
	/**
	 * 옵션에 설정된 카드덱 개수가 몇 개인지 반환한다.
	 * @return 옵션에 설정된 카드덱 개수를 반환한다.
	 */
	public int getCardNum() {
		return cardNum;
	}
	/**
	 * 파라메터로 전달해준 카드 개수로 카드덱 개수를 설정한다.
	 * @param cardNum 전달해준 카드 개수로 카드덱 개수를 설정한다.
	 */
	public void setCardNum(int cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * OptionBean 클래스의 멤버 필드의 값을 모두 String으로 변환시켜 반환한다. 
	 * @return OptionBean 클래스의 멤버 필드의 값을 모두 String으로 변환시켜 반환한다.
	 */
	public String toString() {
		return "OptionBean[bgm:"+(bgm?"ON":"OFF")+
				"/effect:"+(effect?"ON":"OFF")+
				"/cardNum:"+cardNum+"]";
	}
}

