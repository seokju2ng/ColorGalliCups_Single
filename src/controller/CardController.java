package controller;

import java.util.ArrayList;

import model.CardSelectService;
import view.bean.CardBean;

public class CardController {
	private CardSelectService cardService;
	
	public CardController() {
		
	}
	public ArrayList<CardBean> getCards(){
		return null;
	}
	public boolean isCorrect(int num, String answer) {
		return true;
	}
	public CardBean getGoldCard() {
		return null;
	}
}
