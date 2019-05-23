package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class CardDao {
	private ArrayList<Card> cards;
	private ArrayList<Card> goldCards;
	private static CardDao cardDao;
	static {
		cardDao = new CardDao();
	}
	
	private CardDao() {
		cards = new ArrayList<Card>();
		goldCards = new ArrayList<Card>();
		loadCard();
	}
	
	public ArrayList<Card> selectCards(int num){
		if(cards == null) return null;
		if(num < 1 || num > cards.size()) return null;
		ArrayList<Card> select = new ArrayList<Card>();
		Random r = new Random();
		int[] rnum = new int[num];
		for(int i = 0; i < num; i++) {
			rnum[i] = r.nextInt(cards.size());
			for(int j = 0; j < i; j++) {
				if(rnum[i] == rnum[j]) i--;
			}
		}
		for(int i = 0; i < num; i++) {
			select.add(new Card(cards.get(rnum[i])));
		}
		return select;
	}
	
	public boolean add(String path, String answer) {
		if(path == null || answer == null || cards == null)
			return false;
		if(path.equals("") || answer.equals(""))
			return false;
		int num = searchLastNum();
		if(num == -1) return false;
		return cards.add(new Card(num+1, path, answer));
	}
	public boolean add(Card card) {
		if(card == null || cards == null) return false;
		if(searchCard(card.getNum()) != null) return false;
		return cards.add(card);
	}
	
	public boolean isCorrect(int num, String answer) {
		String sAnswer = searchAnswer(num);
		if(sAnswer == null) return false;
		return sAnswer.equals(answer);
	}
	
	public String searchAnswer(int num) {
		if(cards == null) return null;
		int index = searchIndex(num);
		if(index == -1) return null;
		return cards.get(index).getAnswer();
	}
	
	public Card searchCard(int num) {
		if(cards == null) return null;
		int index = searchIndex(num);
		if(index == -1) return null;
		return new Card(cards.get(index));
	}
	
	public int searchIndex(int num) {
		if(cards == null) return -1;
		for(int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getNum() == num)	return i;
		}
		return -1;
	}
	
	public int searchLastNum() {
		if(cards == null) return -1;
		if(cards.size() == 0) return 0;
		return cards.get(cards.size()-1).getNum();
	}
	
	public boolean updateImage(int num, String path, String answer) {
		if(path == null || answer == null || cards == null)
			return false;
		if(path.equals("") || answer.equals(""))
			return false;
		int index = searchIndex(num);
		if(index == -1)
			return false;
		cards.get(index).setPath(path);
		cards.get(index).setAnswer(answer);
		return true;
	}
	
	public boolean updateAnswer(int num, String answer) {
		if(answer == null || cards == null || answer.equals(""))
			return false;
		int index = searchIndex(num);
		if(index == -1)
			return false;
		cards.get(index).setAnswer(answer);
		return true;
	}
	
	public boolean delete(int num) {
		if(cards == null || cards.size() == 0) return false;
		int index = searchIndex(num);
		if(index == -1) return false;
		cards.remove(index);
		return true;
	}
	
//	public int getCardNum() {
//		if(cards == null) return 0;
//		int cnt = 0;
//		for(int i = 0; i < cards.size(); i++) {
//			if(cards.get(i).getNum() > 0) cnt++;
//		}
//		return cnt;
//	}
	
	public boolean loadCard() {
		if(cards == null) return false;
		ArrayList<String> load = new CardIO().loadCard();
		if(load.size() == 0) return false;
		int n = Integer.parseInt(load.get(0));
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(load.get(i), " ");
			int num = Integer.parseInt(st.nextToken());
			String path = st.nextToken();
			String answer = st.nextToken();
			cards.add(new Card(num, path, answer));
		}
		return true;
	}
	
	public boolean saveCard() {return true;}
	public static CardDao getInstance() {
		return cardDao;
	}
	public Card getGoldCard() {
		return new Card(goldCards.get(new Random().nextInt(goldCards.size())));
	}
}
