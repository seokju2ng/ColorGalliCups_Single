package model;

import java.util.ArrayList;
/**
 * CardDao에게 요청한 카드 개수만큼 카드덱을 받아 CardController에게 전달해준다.
 * @author 김용희
 */
public class CardSelectService {
	/**
	 * CardSelectable 타입의 변수이다.
	 */
	private CardSelectable cards;
	/**
	 * CardSelectService클래스의 Null Parameter Constructor이다.
	 */
	public CardSelectService() {
		
	}
	/**
	 * CardController로부터 요청을 받아 num만큼의 카드를 CardSelectable을 구현한 클래스에 요청하고 전달 받은 정보를 가공하여 CardController으로 리턴한다.
	 * @param num 메소드 호출하는 측에서 리턴받고 싶어하는 카드의 장수에 해당되는 정보이다.
	 * @return CardSelectable을 구현한 클래스로 부터 전달 받은 정보를 가공하여 CardController로 리턴한다.
	 */
	public ArrayList<String[]> selectCards(int num){
		return null;
	}
	/**
	 * CardController로부터 요청을 받아 goldCard를 CardSelectable을 구현한 클래스에 요청하여 전달 받은 정보를 가공하여 CardController로 리턴한다.
	 * @return CardSelectable을 구현한 클래스로 부터 전달 받은 정보를 가공하여 CardController로 리턴한다.
	 */
	public String[] getGoldCard() {
		return null;
	}
}