package view.bean;

import controller.OptionController;
import view.DualPlayMode;
import view.etc.Sound;
/**
 * View OptionBean의 객체를 관리하는 클래스이다. OptionBean에 관련된 Service에서 사용할 옵션정보를 저장하는 클래스이다.
 * @author 김용희
 */
public class Options {
   /**
    * 옵션 정보를 OptionBean타입으로 저장하고 있다..
    */
   private OptionBean option;
   /**
    * Options에서 옵션정보를 사용할 수 있는 형태로 가공하여 전달해주는 컨트롤러 객체이다.
    */
   private OptionController opControl;
   /**
    * Options 클래스의 Null Parameter Contructor이다.OptionController로부터 파일에 저장된 옵션정보를 받아 Option을 초기화한다.
    */
   public Options() {
      opControl = new OptionController();
      option = opControl.getOption();
      DualPlayMode.setCardNum(option.getCardNum());
   }
   /**
    * 옵션정보를 반환해주는 메소드이다.
    * @return 옵션정보를 OptionBean타입으로 반환한다. 이때 옵션정보가 null일 경우 파일로부터 옵션정보를 읽어와 초기화시킨 후 반환한다.
    */
   public OptionBean getOption() {
      if(opControl == null) opControl = new OptionController();
      if(option == null) option = opControl.getOption();
      return option;
   }
   /**
    * parameter로 전달받은 값으로 옵션정보를 변경해주고 이에 따라 소리를 ON/OFF한다.
    * @param bgm 배경음악 재생 여부를 저장한다.
    * @param effect 효과음 재생 여부를 저장한다.
    * @param cardNum DualPlayMode에 적용되는 카드의 장수를 저장한다.
    */
   public void setOption(boolean bgm, boolean effect, int cardNum) {
      this.option.setBgm(bgm);
      this.option.setEffect(effect);
      this.option.setCardNum(cardNum);
      if(bgm) Sound.bgmOn(); else Sound.bgmOff();
      if(effect) Sound.effectOn(); else Sound.effectOff();
      DualPlayMode.setCardNum(cardNum);
   }
   /**
    * parameter로 전달받은 값으로 옵션정보를 변경해주고 이에 따라 소리를 ON/OFF한다.
    * @param option 설정할 옵션정보이다.
    */
   public void setOption(OptionBean option) {
      if(option == null) return;
      this.setOption(option.isBgm(), option.isEffect(), option.getCardNum());
   }
   /**
    * 현재의 옵션정보들을 파일에 저장한다.
    * @return 파일에 저장이 성공하면 true, 실패하면 false를 리턴한다.
    */
   public boolean saveOption() {
      if(opControl == null) return false;
      opControl.setOption(option);
      return opControl.saveOption();
   }
}