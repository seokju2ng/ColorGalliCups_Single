package model;

import java.util.ArrayList;

public interface CardSelectable {
	ArrayList<String[]> selectCards(int num);
	String[] getGoldCard();
}