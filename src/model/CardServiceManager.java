package model;

import java.util.ArrayList;
/**
 * CardController에서 CardModel 계층에 접근하기 위해 거쳐야하는 CardServiceManager 클래스이다.
 * CardService는 여러 서비스 클래스로 나뉘는데, CardServiceManager가 CardService를 통합 및 관리한다.  
 * @author seokjung
 *
 */
public class CardServiceManager {
	/**
	 * CardSelectService 객체이다.
	 */
	private CardSelectService cardSelectService;
	/**
	 * Null-Parameter Constructor. 멤버 필드의 초기화를 책임진다.
	 */
	public CardServiceManager() {
		cardSelectService = new CardSelectService();
	}
	/**
	 * parameter로 전달받은 num에 해당하는 숫자만큼의 카드를 random하게 선택하여 Collection 타입으로 리턴해준다.
	 * 
	 * @param num 뽑아올 카드 개수를 전달인자로 받는다.
	 * @return num 개수만큼 랜덤하게 선택된 Cards로 시스템 카드 덱을 만들어 리턴한다.
	 */
	public ArrayList<String[]> selectCards(int num){
		if(num < 0 || cardSelectService == null) return null;
		return cardSelectService.selectCards(num);
	}
	/**
	 * 무승부시 승부를 볼 골드카드를 한장 뽑아온다.
	 * @return 랜덤하게 뽑은 골드카드 한장을 리턴한다.
	 */
	public ArrayList<String[]> getGoldCards() {
		if(cardSelectService == null) return null;
		return cardSelectService.getGoldCards();
	}
	
	//0527 Edit By DK KIM//
	public ArrayList<String[]> getCards(){
		if(cardSelectService ==null) return null;
		return cardSelectService.getCards();
	}
}
