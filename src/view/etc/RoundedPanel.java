package view.etc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;
/**@author cms </br>
 * 모서리가 둥근 Panel을 생성하게 해준다. 이외의 역할은 JPanel과 동일하다.*/
public class RoundedPanel extends JPanel {
	/**panel의 배경색에 대한 정보이다.*/
   private Color backgroundColor;
   /**panel의 모서리의 둥근 정도에 대한 정보이다.*/
   private int cornerRadius = 15;
   /**RoundedPanel에 대한 생성자로 layout,radius를 parameter로 받아 객체를 할당한다.
    * @param layout panel의 layout에 대한 정보이다.
    * @param radius panel의 모서리의 둥근 정도에 대한 정보이다.*/
   public RoundedPanel(LayoutManager layout, int radius) {
      super(layout);
      cornerRadius = radius;
   }
   /**RoundedPanel에 대한 생성자로 layout,radius,bgColor를 parameter로 받아 객체를 할당한다.
    * @param layout panel의 layout에 대한 정보이다.
    * @param radius panel의 모서리의 둥근 정도에 대한 정보이다.
    * @param bgColor panel의 배경색에 대한 정보이다.*/
   public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
      super(layout);
      cornerRadius = radius;
      backgroundColor = bgColor;
   }
   /**RoundedPanel에 대한 생성자로 radius를 parameter로 받아 객체를 할당한다.
    * @param radius panel의 모서리의 둥근 정도에 대한 정보이다.*/
   public RoundedPanel(int radius) {
      super();
      cornerRadius = radius;
   }
   /**RoundedPanel에 대한 생성자로 bgCOlor,radius를 parameter로 받아 객체를 할당한다.
    * @param radius panel의 모서리의 둥근 정도에 대한 정보이다.
    * @param bgColor panel의 배경색에 대한 정보이다.*/
   public RoundedPanel(int radius, Color bgColor) {
      super();
      cornerRadius = radius;
      backgroundColor = bgColor;
   }

   @Override
   /** UI위양이null가 아닌 경우에, UI위양의 paint·메소드를 호출합니다. 위양에는Graphics오브젝트의 카피를 건네주어, 나머지의 paint·코드에 대해서 취소할 수 없는 변경을 하지 않게 보호합니다(예를 들어Graphics.translate등).
    * @param g 보호 대상의Graphics오브젝트*/
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Dimension arcs = new Dimension(cornerRadius, cornerRadius);
      int width = getWidth();
      int height = getHeight();
      Graphics2D graphics = (Graphics2D) g;
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Draws the rounded panel with borders.
      if (backgroundColor != null) {
         graphics.setColor(backgroundColor);
      } else {
         graphics.setColor(getBackground());
      }
      graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint background
      graphics.setColor(getForeground());
      graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); // paint border
   }
}
