package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * model에서 사용되는 카드들에 대한 정보를 관리하는 역할을 한다. 카드의 정보로는 카드 번호, 카드그림 경로, 카드 정답이 있다.
 * 여러개의 카드에 대한 정보를 저장한다. 싱글톤 패턴으로 되어 있어 객체를 하나만 생성하고 그 객체를 사용한다.
 * 
 * @author 김용희
 */
public class CardDao  {
	/**
	 * Collection타입으로 Card여러개를 저장하고 있다.
	 */
	private ArrayList<Card> cards;
	/**
	 * Collection타입으로 goldCard여러개를 저장하고 있다.
	 */
	private ArrayList<Card> goldCards;
	/**
	 * 클래스 자기자신의 타입의 변수이다. CardDao 객체의 참조값을 저장하고 있다.
	 */
	private static CardDao cardDao;
	static {
		cardDao = new CardDao();
	}

	/**
	 * CardDao클래스의 Null Parameter Constructor이다.
	 */
	private CardDao() {
		cards = new ArrayList<Card>();
		goldCards = new ArrayList<Card>();
		loadCard();
	}

	/**
	 * cards에서 parameter로 전달받은 num에 해당하는 숫자만큼의 카드를 random하게 선택하여 Collection타입으로
	 * 리턴해준다.
	 * 
	 * @param num
	 *            원하는 카드의 개수 만큼 parameter로 전하여 사용할 수 있다.
	 * @return random하게 선택한 Card들의 모음을 리턴한다.
	 */
	public ArrayList<Card> selectCards(int num) {
		if (cards == null || goldCards == null)
			return null;
		if (num < 1 || num > cards.size())
			return null;
		ArrayList<Card> select = new ArrayList<Card>();
		Random r = new Random();
		int[] rnum = new int[num];
		for (int i = 0; i < num; i++) {
			rnum[i] = r.nextInt(cards.size());
			for (int j = 0; j < i; j++) {
				if (rnum[i] == rnum[j])
					i--;
			}
		}
		for (int i = 0; i < num; i++) {
			select.add(new Card(cards.get(rnum[i])));
		}
		return select;
	}
	
	//Edit BY DK KIM//
	public ArrayList<Card> getCards() {
		if(cards == null || goldCards == null)
			return null;
		ArrayList<Card> select = new ArrayList<>();
		for(int i = 0 ; i < cards.size(); i++) {
			select.add(new Card(cards.get(i)));
		}
		return select;
	}

	/**
	 * 현재 cards에 새로운 path, answer에 해당하는 card를 추가한다. 이때 card의 num은 현재의 카드모음중 가장큰
	 * num값에 +1 한 값으로 저장하여 card를 추가한다.
	 * 
	 * @param path
	 *            추가할 카드그림의 이미지 경로에 대한 정보이다.
	 * @param answer
	 *            추가할 카드의 정답에 대한 정보이다.
	 * @return card추가를 성공했을 경우 true, 실패했을경우 false를 리턴한다.
	 */
	public boolean add(String path, String answer) {
		if (path == null || answer == null || cards == null)
			return false;
		if (path.equals("") || answer.equals(""))
			return false;
		int num = searchLastNum();
		if (num == -1)
			return false;
		return cards.add(new Card(num + 1, path, answer));
	}

	/**
	 * 현재 cards에 새로운 parameter에 해당하는 card를 추가한다.
	 * 
	 * @param card
	 *            추가하고 싶은 Card타입의 변수이다.
	 * @return cards에 추가가 성공했을 때 true, 실패했을 때 false를 리턴한다.
	 */
	public boolean add(Card card) {
		if (card == null || cards == null)
			return false;
		if (searchCard(card.getNum()) != null)
			return false;
		return cards.add(card);
	}

	/**
	 * parameter로 받은 num에 해당하는 카드의 정답과 answer를 비교하여 그 결과를 boolean타입으로 리턴한다.
	 * 
	 * @param num
	 *            정답비교를 원하는 카드의 해당하는 카드번호를 parameter로 전하여 사용할 수 있다.
	 * @param answer
	 *            num에 해당하는 카드의 정답과 비교할 answer이다.
	 * @return num에 해당하는 카드의 정답과 answer를 비교하여 같을 경우 true, 다를 경우 false를 리턴한다.
	 */
	public boolean isCorrect(int num, String answer) {
		String sAnswer = searchAnswer(num);
		if (sAnswer == null)
			return false;
		return sAnswer.equals(answer);
	}

	/**
	 * parameter로 받은 num에 해당하는 카드의 정답을 리턴한다.
	 * 
	 * @param num
	 *            카드의 해당하는 카드번호를 parameter로 전하여 사용할 수 있다.
	 * @return num에 해당하는 카드의 정답을 리턴한다. 이 때 해당하는 card가 없을 경우 null을 리턴한다.
	 */

	public String searchAnswer(int num) {
		if (cards == null || goldCards == null)
			return null;
		int index = searchIndex(num);
		if (index == -1)
			return null;
		return cards.get(index).getAnswer();
	}

	/**
	 * parameter로 받은 num에 해당하는 card를 리턴한다.
	 * 
	 * @param num
	 *            카드의 해당하는 카드번호를 parameter로 전하여 사용할 수 있다.
	 * @return num에 해당하는 card 리턴한다. 이 때 해당하는 card가 없을 경우 null을 리턴한다.
	 */
	public Card searchCard(int num) {
		if (cards == null || goldCards == null)
			return null;
		int index = searchIndex(num);
		if (index == -1)
			return null;
		return new Card(cards.get(index));
	}

	/**
	 * parameter로 받은 num에 해당하는 카드의 index를 리턴한다.
	 * 
	 * @param num
	 *            카드번호를 parameter로 전하여 해당 카드의 index를 찾아준다.
	 * @return num에 해당하는 card 찾은 경우 index를 리턴하고, 해당하는 card가 없을 경우 -1을 리턴한다.
	 */
	public int searchIndex(int num) {
		if (cards == null || goldCards == null)
			return -1;
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getNum() == num)
				return i;
		}
		return -1;
	}

	/**
	 * cards에 element중 가장 큰 카드 번호를 리턴한다.
	 * 
	 * @return cards에 element중 가장 큰 카드 번호를 리턴한다.
	 */
	public int searchLastNum() {
		if (cards == null || goldCards == null)
			return -1;
		if (cards.size() == 0)
			return 0;
		return cards.get(cards.size() - 1).getNum();
	}

	/**
	 * parameter로 받은 num 과 같은 카드번호에 해당하는 카드의 그림과 정답을 수정한다.
	 * 
	 * @param num
	 *            path와 answer를 수정할 카드의 번호이다.
	 * @param path
	 *            수정할 카드 그림의 경로이다.
	 * @param answer
	 *            수정할 카드의 정답이다.
	 * @return parameter로 받은 num 과 같은 카드번호에 해당하는 카드의 그림과 정답을 수정한다.이 때 성공 했을 경우 true,
	 *         실패 했을 경우 false를 리턴한다.
	 */
	public boolean updateImage(int num, String path, String answer) {
		if (path == null || answer == null || cards == null || goldCards == null)
			return false;
		if (path.equals("") || answer.equals(""))
			return false;
		int index = searchIndex(num);
		if (index == -1)
			return false;
		cards.get(index).setPath(path);
		cards.get(index).setAnswer(answer);
		return true;
	}

	/**
	 * parameter로 받은 num 과 같은 카드번호에 해당하는 카드의 정답을 수정한다.
	 * 
	 * @param num
	 *            answer를 수정할 카드의 번호이다.
	 * @param answer
	 *            수정할 카드의 정답이다.
	 * @return parameter로 받은 num 과 같은 카드번호에 해당하는 카드의 정답을 수정한다.이 때 성공 했을 경우 true, 실패
	 *         했을 경우 false를 리턴한다.
	 */
	public boolean updateAnswer(int num, String answer) {
		if (answer == null || cards == null || answer.equals(""))
			return false;
		int index = searchIndex(num);
		if (index == -1)
			return false;
		cards.get(index).setAnswer(answer);
		return true;
	}

	/**
	 * parameter로 받은 num 과 같은 카드번호에 해당하는 카드를 삭제한다.
	 * 
	 * @param num
	 *            삭제할 카드의 번호이다.
	 * @return parameter로 받은 num 과 같은 카드번호에 해당하는 카드를 삭제한다.이 때 성공 했을 경우 true, 실패 했을
	 *         경우 false를 리턴한다.
	 */
	public boolean delete(int num) {
		if (cards == null || cards.size() == 0)
			return false;
		int index = searchIndex(num);
		if (index == -1)
			return false;
		cards.remove(index);
		return true;
	}

	// public int getCardNum() {
	// if(cards == null) return 0;
	// int cnt = 0;
	// for(int i = 0; i < cards.size(); i++) {
	// if(cards.get(i).getNum() > 0) cnt++;
	// }
	// return cnt;
	// }
	/**
	 * Card의 대한 정보들을 CardIO 클래스를 통해 읽기작업을 하여 cards에 저장한다.
	 * 
	 * @return CardIO클래스를 통해 읽어오기를 성공했을 때 true, 실패했을 떄 false를 리턴한다. 이때 cards는 인스턴스화
	 *         되어있어야 한다.
	 */
	public boolean loadCard() {
		if (cards == null)
			return false;
		ArrayList<String> loadCards = new CardIO().loadCard(Card.CARD_PATH);
		//ArrayList<String> loadGCards = new CardIO().loadCard(Card.GCARD_PATH);
		if (loadCards == null || loadCards.size() == 0)
			return false;
		int n = Integer.parseInt(loadCards.get(0));
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(loadCards.get(i), " ");
			int num = Integer.parseInt(st.nextToken());
			String path = st.nextToken();
			String answer = st.nextToken();
			cards.add(new Card(num, path, answer));
		}
		return true;
	}

	/**
	 * cards의 정보를 CardIO 클래스를 통해 쓰기작업을 하여 파일에 저장한다. 단, cards 정보에 수정사항이 있을경우에 사용한다.
	 * 
	 * @return CardIO클래스를 통해 쓰기 작업을 성공했을 때 true, 실패했을 떄 false를 리턴한다. 이때 cards는 인스턴스화
	 *         되어있어야 한다.
	 */
	public boolean saveCard() {
		if(cards==null)
			return false;
		String info;
		ArrayList<String> cardInfo=new ArrayList<String>();
		for(int i=0;i<cards.size();i++) {
			info=cards.get(i).getNum()+" "+cards.get(i).getPath()+" "+cards.get(i).getAnswer();
			cardInfo.add(info);
		}
		new CardIO().saveCard(cardInfo);
		return true;
	}

	/**
	 * CardDao의 객체참조값을 리턴하며 객체 인스턴스화 이전에 호출하여 사용할 수 있다.
	 * 
	 * @return CardDao의 객체 참조값을 리턴한다.
	 */
	public static CardDao getInstance() {
		return cardDao;
	}

	/**
	 * goldCards element 중 랜덤하게 하나의 element를 뽑아서 리턴한다.
	 * 
	 * @return goldCards element 중 랜덤하게 하나의 element를 뽑아서 리턴한다.
	 */
	public Card getGoldCard() {
		return new Card(goldCards.get(new Random().nextInt(goldCards.size())));
	}
}
