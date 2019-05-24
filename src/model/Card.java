package model;
/**
 * model에서 사용되는 CardDao 중 하나의 카드에 대한 정보를 저장하는 역할을 한다.
 * 카드의 정보로는 카드 번호, 카드그림 경로, 카드 정답이 있다.
 * @author 김용희
 *
 */
public class Card {
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
	 * Card 클래스의 Null Parameter Constructor이다.
	 */
	public Card() {}
	/**
	 * Card클래스의 오버로딩 생성자로 카드그림 경로, 카드 정답에 해당하는 새로운 Card 객체를 할당해준다.
	 * 이때 카드 번호는 생성자에서 자동 생성해준다.
	 * 
	 * @param path Card클래스 에서 카드그림 경로에 해당하는 값이다.
	 * @param answer Card클래스 에서 카드 정답에 해당하는 값이다.
	 */
	public Card(String path, String answer) {
		this.path = path; this.answer = answer;
	}
	/**
	 * Card클래스의 오버로딩 생성자로 카드 번호, 카드그림 경로, 카드 정답에 해당하는 새로운 Card 객체를 할당해준다.
	 * @param num Card클래스 에서 카드 번호에 해당하는 값이다.
	 * @param path Card클래스 에서 카드그림 경로에 해당하는 값이다.
	 * @param answer Card클래스 에서 카드 정답에 해당하는 값이다.
	 */
	public Card(int num, String path, String answer) {
		this.num = num; this.path = path; this.answer = answer;
	}
	/**
	 * Card클래스의 오버로딩 생성자로 parameter에 해당하는 새로운 Card 객체를 할당해준다.
	 * @param card 생성하고 싶은 Card타입의 변수이다.
	 */
	public Card(Card card) {
		this.num = card.getNum();
		this.path = card.getPath();
		this.answer = card.getAnswer();
	}
	/**
	 * Card 클래스에서 num필드의 값을 리턴해주는 getter()메소드이다.
	 * @return Card에서 num필드의 값을 리턴해준다.
	 */
	public int getNum() {
		return num;
	}
	/**
	 * Card 클래스에서 num필드의 값을 변경하는 setter()메소드이다.
	 * @param num 원래의 num값에서 변경시키고 싶은 값이다.
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * Card 클래스에서 path필드의 값을 리턴해주는 getter()메소드이다.
	 * @return Card에서 path필드의 값을 리턴해준다.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * Card 클래스에서 path필드의 값을 변경하는 setter()메소드이다.
	 * @param path 원래의 path값에서 변경시키고 싶은 값이다.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Card 클래스에서 answer필드의 값을 리턴해주는 getter()메소드이다.
	 * @return Card에서 answer필드의 값을 리턴해준다.
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * Card 클래스에서 answer필드의 값을 변경하는 setter()메소드이다.
	 * @param answer 원래의 answer값에서 변경시키고 싶은 값이다.
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
