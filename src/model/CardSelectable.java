package model;

import java.util.ArrayList;
/**
 * parameter로 받은 num만큼 카드를 뽑아 리턴 할 때, goldCard 한장을 뽑아 리턴할 때 implements하여 구현하여 사용 할 수 있다.
 * @author 김용희
 */
public interface CardSelectable {
	/**
	 * parameter로 받은 num만큼 카드를  뽑아 리턴 한다. interface를 구현한 클래스에서 메소드 바디부를 작성하여 사용한다.
	 * @param num 카드의 개수정보이다. num만큼 카드를 뽑아야 할 때 사용한다.
	 * @return parameter로 받은 num만큼 카드를  뽑아 리턴 한다.
	 */
	ArrayList<Card> selectCards(int num);
	/**
	 * goldCard 한장을 뽑아 리턴한다. interface를 구현한 클래스에서 메소드 바디부를 작성하여 사용한다.
	 * @return goldCard 한장을 뽑아 리턴한다.
	 */
	Card getGoldCard();
}