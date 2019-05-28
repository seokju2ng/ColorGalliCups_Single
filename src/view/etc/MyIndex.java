package view.etc;


/**
 * primitive type인 int를 reference type처럼 쓸 수 있게 하는 custom wrapper class이다.<br>
 * @author 김도균 */
public class MyIndex {
	/**인덱스를 나타내는 멤버이다.*/
	private int index;
	/**MyIndex객체를 생성해주는 역할만 하는 생성자이다.*/
	public MyIndex(){}
	/**index를 반환하는  접근자이다.
	 * @return index를 반환한다.*/
	
	public int getIndex() {
		return index;
	}
	/**index를 parameter로 전해지는 index로 설정해주는 설정자이다.
	 * @param index 멤버 index를 설정하기 위한 변수이다*/
	public void setIndex(int index) {
		this.index = index;
	}
	/**index를 1 증가시킨다*/
	public void plus() {
		index++;
	}
	/**index를 1 감소시킨다.*/
	public void minus() {
		index--;
	}
}
