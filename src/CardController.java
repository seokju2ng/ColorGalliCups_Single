package controller;

import java.util.ArrayList;

import model.CardSelectService;
import view.bean.CardBean;
/**
 * 
 * @author 김용희
 * CardSelectService 혹은 CardCorrectService 로부터 정보를 받아 CardDeck이 원하는 정보로 변경하여 전달한다.
 */
public class CardController {
	/**
	 * CardSelectService 타입으로 Card의 service 클래스를 가지고 있다.
	 */
	private CardSelectService cardService;
	/**
	 * CardController의 Null Parameter Contructor 이다.
	 */
	public CardController() {
		
	}
	/**
	 * CardDeck으로부터 요청을 받아 num만큼의 카드를 CardSelectService에 요청하여 전달 받은 정보를 가공하여 CardDeck으로 리턴한다.
	 * @param num 메소드 호출하는 측에서 리턴받고 싶어하는 카드의 장수에 해당되는 정보이다.
	 * @return  CardSelectService로 부터 전달 받은 정보를 가공하여 CardDeck으로 리턴한다.
	 */
	public ArrayList<CardBean> getCards(int num){
		return null;
	}
	/**
	 * CardDeck으로부터 요청을 받아 num과 같은 카드번호값을 가진 카드의 정답과 answer이 일치하는지 CardCorrectService에 요청하여 
	 * 같은 경우 true, 다를 경우 false를 CardDeck으로 리턴한다.
	 * @param num 메소드 호출하는 측에서 확인하고 싶은 카드의 번호 정보이다.
	 * @param answer 메소드 호출하는 측에서 확인하고 싶은 제출한 답의 정보이다.
	 * @return CardCorrectService로 부터 전달 받은 정보를 CardDeck으로 리턴한다. 답과 정답이 같을 경우 true, 다를 경우 false를 리턴한다.
	 */
	public boolean isCorrect(int num, String answer) {
		return true;
	}
	/**
	 * CardDeck으로부터 요청을 받아 goldCard를 CardSelectService에 요청하여 전달 받은 정보를 가공하여 CardDeck으로 리턴한다.
	 * @return CardSelectService로 부터 전달 받은 정보를 가공하여 CardDeck으로 리턴한다.
	 */
	public CardBean getGoldCard() {
		return null;
	}
}