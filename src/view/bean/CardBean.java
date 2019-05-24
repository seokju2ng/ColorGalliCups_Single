package view.bean;
/**
 * view에서 사용되는 CardDeck 중 하나의 카드에 대한 정보를 저장하는 역할을 한다.
 * 카드의 정보로는 카드 번호, 카드그림 경로, 카드 정답이 있다.
 * @author 김용희
 */
public class CardBean {
	/**
	 * 카드를 식별할 수 있는 번호를 저장하고 있다.
	 */
	private int num;
	/**
	 * 카드의 이미지의 경로를 저장하고 있다.
	 */
	private String path;
	/**
	 * 카드에 해당하는 정답 컵 배치를 String타입으로 변환하여 저장하고 있다.
	 */
	private String answer;
	/**
	 * CardBean 클래스의 Null Parameter Constructor이다.
	 */
	public CardBean() {}
	/**
	 * CardBean클래스의 오버로딩 생성자로 카드그림 경로, 카드 정답에 해당하는 새로운 CardBean 객체를 할당해준다.
	 * 이때 카드 번호는 생성자에서 자동 생성해준다.
	 * 
	 * @param path CardBean클래스 에서 카드그림 경로에 해당하는 값이다.
	 * @param answer CardBean클래스 에서 카드 정답에 해당하는 값이다.
	 */
	public CardBean(String path, String answer) {
		this.path = path; this.answer = answer;
	}
	/**
	 * CardBean클래스의 오버로딩 생성자로 카드 번호, 카드그림 경로, 카드 정답에 해당하는 새로운 CardBean 객체를 할당해준다.
	 * @param num CardBean클래스 에서 카드 번호에 해당하는 값이다.
	 * @param path CardBean클래스 에서 카드그림 경로에 해당하는 값이다.
	 * @param answer CardBean클래스 에서 카드 정답에 해당하는 값이다.
	 */
	public CardBean(int num, String path, String answer) {
		this.num = num; this.path = path; this.answer = answer;
	}
	/**
	 * CardBean클래스의 오버로딩 생성자로 parameter에 해당하는 새로운 CardBean 객체를 할당해준다.
	 * @param card 생성하고 싶은 CardBean타입의 변수이다.
	 */
	public CardBean(CardBean card) {
		this.num = card.getNum();
		this.path = card.getPath();
		this.answer = card.getAnswer();
	}
	/**
	 * CardBean 클래스에서 num필드의 값을 리턴해주는 getter()메소드이다.
	 * @return CardBean에서 num필드의 값을 리턴해준다.
	 */
	public int getNum() {
		return num;
	}
	/**
	 * CardBean 클래스에서 num필드의 값을 변경하는 setter()메소드이다.
	 * @param num 원래의 num값에서 변경시키고 싶은 값이다.
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * CardBean 클래스에서 path필드의 참조값을 리턴해주는 getter()메소드이다.
	 * @return CardBean에서 path필드의 참조값을 리턴해준다.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * CardBean 클래스에서 path필드의 참조값을 변경하는 setter()메소드이다.
	 * @param path 원래의 path참조값에서 변경시키고 싶은 값이다.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * CardBean 클래스에서 answer필드의 참조값을 리턴해주는 getter()메소드이다.
	 * @return CardBean에서 answer필드의 참조값을 리턴해준다.
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * CardBean 클래스에서 answer필드의 참조값을 변경하는 setter()메소드이다.
	 * @param answer 원래의 answer참조값에서 변경시키고 싶은 값이다.
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * card에 대한 정보(카드번호, 카드그림 경로, 카드 정답)을 리턴해주는 toString()메소드이다.
	 * @return 카드번호, 카드그림 경로, 카드 정답의 값을 리턴한다.
	 */
	public String toString() {
		return "Card[card_num:"+num+"/img_path:"+path+"/answer:"+answer+"]";
	}
}
