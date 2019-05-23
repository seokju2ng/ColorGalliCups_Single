package view.etc;

import java.util.ArrayList;

import model.Card;
import model.CardDao;

public class CardDeck {
	private ArrayList<Card> cards;
	private CardDao cd = CardDao.getInstance();
	
	public CardDeck() {
		cards = new ArrayList<Card>();
	}
	public CardDeck(int cardNum) {
		cards =  cd.selectCards(cardNum);
//		for(int i = 0 ; i < cardNum ;i++) {
//			Card cv = new Card
//			cards.add(
//		}  //깊은 복사 안해도 되나?
		
	}
	public Card get(int index) {
		return cards.get(index);
	}
	public String getImage(int index) {
		return cards.get(index).getPath();
	}
	public int size() {
		return cards.size();
	}
	public boolean isCorrect(int index, StringBuffer userAns) {
		if(userAns == null) return false;
		String str = new String(userAns);
		if(str.equals(cards.get(0))) {
			return true;
		}
		return false;
	}
	
	
}
