package view.bean;

import java.util.ArrayList;
import java.util.Collections;

import controller.CardController;

/**
 * view에서 사용되는 카드들에 대한 정보를 저장하는 역할을 한다. 카드의 정보로는 카드 번호, 카드그림 경로, 카드 정답이 있다. 여러개의
 * 카드에 대한 정보를 저장한다.
 * 
 * @author 김용희
 */
public class CardDeck {
   /**
    * Collection타입으로 CardBean여러개를 저장하고 있다. 게임에서 사용될 카드덱이다.
    */
   private ArrayList<CardBean> cards;
   /**
    * DualPlayMode에서 무승부가 났을 경우 사용될 골드 카드이다. 한장을 저장하고 있다.
    */
   private ArrayList<CardBean> goldCards;
   
   private static CardDeck instance;
   //private CardController controller;
   
   

   /**
    * CardDeck 클래스의 Null Parameter Constructor이다.
    */
   
   static {
      instance = new CardDeck();
   }
   private CardDeck() {
      //cards = new ArrayList<CardBean>();
      CardController controller = new CardController();
      cards = controller.getCards(); //shallow copy
      goldCards = controller.getGoldCards(); //shallow copy
   }
   
   public static CardDeck getInstance() {
      return instance;
   }

//   /**
//    * CardBean클래스의 오버로딩 생성자로 Controller를 통해 cardNum만큼의 카드덱과 골드카드를 필드로 가지는 CardBean
//    * 객체를 할당한다.
//    * 
//    * @param cardNum
//    *            원하는 카드의 개수 만큼 parameter로 전하여 사용할 수 있다.
//    */
//   public CardDeck(int cardNum) {
//      CardController controller = new CardController();
//      cards = controller.getCards(cardNum);
//      // goldCard = controller.getGoldCard();
//
//   }

   /**
    * parameter로 받은 index에 해당하는 카드에 대한 정답과 answer를 비교하여 그결과를 boolean타입으로 리턴해준다.
    * 
    * @param index
    *            카드인덱스에 해당하는 정보이다.
    * @param answer
    *            카드정답과 비교할 답의 정보이다.
    * @return 카드정답과 parameter의 answer를 비교하여 같다면 true, 다르다면 false를 리턴한다.
    */
   public boolean isCorrect(int index, String answer) {
      if (index >= cards.size()) //카드인덱스가 생성된 시스템카드덱의 수보다 크다면 틀린것.
         return false;
      if (cards.get(index).getAnswer().equals(answer))
         return true;
      else
         return false;
   }
   
   public boolean isGoldCorrect(String answer) {
      if(goldCards.get(0).getAnswer().equals(answer))
         return true;
      else
         return false;
   }

   /**
    * 무승부가 나서 goldCard가 필요할 경우 사용한다.
    * 
    * @return goldCard의 값을 리턴해준다.
    */
   public CardBean getGoldCard() {
      return goldCards.get(0);
   }
   
   //getGdImagePath

   /**
    * parameter로 받은 index에 해당하는 cards의 카드 그림 경로를 리턴한다.
    * 
    * @param index
    *            찾고 싶은 cards의 index값이다.
    * @return index에 해당하는 카드의 그림 경로를 리턴한다.
    */
   public String getImagePath(int index) {
      System.out.println(cards.get(index).getPath());
      return cards.get(index).getPath();
   }
   
   public String getGoldImagePath() {
      System.out.println(goldCards.get(0).getPath());
      return goldCards.get(0).getPath();
   }
   
   
   
   public ArrayList<CardBean> selectCards(int num) {
      if( num<0 || cards == null) return null;
      ArrayList<CardBean> selectedCards = new ArrayList<>();
      for(int i = 0 ; i < num ; i++) {
         selectedCards.add(new CardBean(cards.get(i)));
      }
      
      return cards;
      
   }
   
   
   
   public void shuffle() {
      Collections.shuffle(cards);
   }
   
   public void goldShuffle() {
      Collections.shuffle(goldCards);
   }
   
   

}