package model;

import java.util.ArrayList;

/**
 * CardDao에게 요청한 카드 개수만큼 카드덱을 받아 CardController에게 전달해준다.
 * 
 * @author 김용희
 */
public class CardSelectService implements CardSelectable {
   /**
    * CardSelectable 타입의 변수이다.
    */
   private CardDao cards;

   /**
    * CardSelectService클래스의 Null Parameter Constructor이다.
    */
   public CardSelectService() {
      cards = CardDao.getInstance();
   }

   /**
    * CardController로부터 요청을 받아 num만큼의 카드를 CardSelectable을 구현한 클래스에 요청하고 전달 받은 정보를
    * 가공하여 CardController으로 리턴한다.
    * 
    * @param num
    *            메소드 호출하는 측에서 리턴받고 싶어하는 카드의 장수에 해당되는 정보이다.
    * @return CardSelectable 을 구현한 클래스로 부터 전달 받은 정보를 가공하여 CardController로 리턴한다.
    */
   @Override
   public ArrayList<String[]> selectCards(int num) {
      if (num < 0 || cards == null)
         return null;

      ArrayList<Card> originalCards = cards.selectCards(num);
      ArrayList<String[]> selectCards = new ArrayList<String[]>();

      for (int i = 0; i < originalCards.size(); i++) {
         String[] str = new String[3];
         str[0] = originalCards.get(i).getNum() + "";
         str[1] = originalCards.get(i).getPath();
         str[2] = originalCards.get(i).getAnswer();
         selectCards.add(str);
      }
      return selectCards;
   }

   /**
    * CardController로부터 요청을 받아 goldCard를 CardSelectable을 구현한 클래스에 요청하여 전달 받은 정보를
    * 가공하여 CardController로 리턴한다.
    * 
    * @return CardSelectable을 구현한 클래스로 부터 전달 받은 정보를 가공하여 CardController로 리턴한다.
    */
   @Override
   public ArrayList<String[]> getGoldCards() {
      if (cards == null)
         return null;

      ArrayList<Card> gCards = cards.getGoldCards();
      ArrayList<String[]> selectedGCards = new ArrayList<String[]>();
      for (int i = 0; i < gCards.size(); i++) {
         String[] str = new String[3];
         str[0] = gCards.get(i).getNum() + "";
         str[1] = gCards.get(i).getPath();
         str[2] = gCards.get(i).getAnswer();
         selectedGCards.add(str);
      }
      return selectedGCards;
   }
   
   // 0527 Edit By DK KIM//
   /**
    * Card DAO Class로부터 받은 ArrayList&lt;Card&gt; 타입의 카드덱을 ArrayList&lt;String[]&gt; 타입으로 변환하여 반환한다. 
    * @return ArrayList&lt;String[]&gt; 타입으로 변환한 카드덱을 리턴한다.
    */
   @Override
   public ArrayList<String[]> getCards() {
      if (cards == null)
         return null;
      ArrayList<Card> originalCards = cards.getCards();
      ArrayList<String[]> selectCards = new ArrayList<String[]>();

      for (int i = 0; i < originalCards.size(); i++) {
         String[] str = new String[3];
         str[0] = originalCards.get(i).getNum() + "";
         str[1] = originalCards.get(i).getPath();
         str[2] = originalCards.get(i).getAnswer();
         selectCards.add(str);
      }
      return selectCards;

   }

}