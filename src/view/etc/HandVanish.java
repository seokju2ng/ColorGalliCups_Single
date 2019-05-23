package view.etc;

import javax.swing.JLabel;

public class HandVanish extends Thread{
   private static int index=3;
   private int order;
   private JLabel[][] hands;
   private boolean[] handCheck;
   public HandVanish() {
      
   }
   public HandVanish(int order, JLabel[][] hands, boolean[] handCheck) {
      this.order=order;
      this.hands=hands;
      this.handCheck=handCheck;
   }
   public void run() {
      int num=index;
      hands[index--][order].setVisible(true);
      handCheck[order] = true;
      try {
         System.out.println(order+"번 1초 휴식중..");
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      hands[num][order].setVisible(false);
      index++;
      handCheck[order] = false;
      System.out.println(order+"번 끝");
   }
}