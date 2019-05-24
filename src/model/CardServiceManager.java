package model;

import java.util.ArrayList;

public class CardServiceManager {
	private CardSelectService cardSelectService;
	
	public CardServiceManager() {
		cardSelectService = new CardSelectService();
	}
	
	public ArrayList<String[]> selectCards(int num){
		if(num < 0 || cardSelectService == null) return null;
		return cardSelectService.selectCards(num);
	}
	public String[] getGoldCard() {
		if(cardSelectService == null) return null;
		return cardSelectService.getGoldCard();
	}
}
