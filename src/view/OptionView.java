package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.etc.ChangePanelService;
import view.handler.FocusHandler;

public class OptionView extends JPanel {
   private JButton[] buttons;
   private JLabel[] rarrows;
   private JLabel[] larrows;
   private int index;
   private GridBagLayout gbl;
   private GridBagConstraints gbc;
   private int[] option;
   private JButton[][] btn;
   private JButton[] name;
   private JButton back;
   private JButton[] slash;
   
   public OptionView() {
      gbl = new GridBagLayout();
      gbc = new GridBagConstraints();
      
      Font font=new Font("Nanum Brush Script", Font.BOLD, 50);
      Color bg = new Color(251,229,214);      // 배경 색
      Color fg = new Color(255, 80, 80);
      gbc.fill=GridBagConstraints.HORIZONTAL;
      setLayout(gbl);
      setFont(font);
      setBackground(bg);         // 판넬의 배경화면 색 설정
      this.addComponentListener(new FocusHandler());
      this.addKeyListener(new ArrowsHandler());
      
      
      ImageIcon rightArrow=new ImageIcon("image/left_o.png");      // 오른쪽을 가리키는 화살표
      ImageIcon leftArrow=new ImageIcon("image/right_o.png");         // 왼쪽을 가리키는 화살표
      
      rarrows=new JLabel[] {new JLabel(rightArrow),new JLabel(rightArrow),new JLabel(rightArrow),new JLabel(rightArrow),new JLabel(rightArrow)};
      larrows=new JLabel[] {new JLabel(leftArrow),new JLabel(leftArrow),new JLabel(leftArrow),new JLabel(leftArrow),new JLabel(leftArrow)};
      
      for(int i = 0; i < rarrows.length; i++) {
         rarrows[i].setVisible(false);
         larrows[i].setVisible(false);
      }
      rarrows[0].setVisible(true);
      larrows[0].setVisible(true);

      ArrowsHandler l = new ArrowsHandler();
      MouseHandler ml = new MouseHandler();
      
      name = new JButton[] {new JButton("배경음"), new JButton("효과음"), new JButton("카드덱")};
      for(int i = 0; i < 3; i++) {
          name[i].setFont(font);
          name[i].addMouseListener(ml);
          name[i].addKeyListener(l);
        name[i].setContentAreaFilled(false);
        name[i].setFocusPainted(false);
        name[i].setBorderPainted(false);
      }
      
      back = new JButton("뒤로 가기");
      back.setContentAreaFilled(false);
      back.setFocusPainted(false);
      back.setBorderPainted(false);
      back.setFont(font);
      back.addMouseListener(ml);
      back.addActionListener(l);
      back.addKeyListener(l);
      
      //buttons=new JButton[] {new JButton("ON"), new JButton("/"), new JButton("OFF"), new JButton("ON"), new JButton("/"), new JButton("OFF"), new JButton("3색"), new JButton("/"), new JButton("5색"), new JButton("10장"), new JButton("20장"), new JButton("40장")}; 
      btn = new JButton[][] {new JButton[2], new JButton[2], new JButton[3]};
      slash = new JButton[] {new JButton("/"), new JButton("/"), new JButton("/"), new JButton("/")};
      for(int i = 0; i < slash.length; i++) {
            slash[i].setFont(font);
            slash[i].setContentAreaFilled(false);
            slash[i].setFocusPainted(false);
            slash[i].setBorderPainted(false);
            slash[i].addMouseListener(ml);
            slash[i].addKeyListener(l);
      }
      
      for(int i = 0; i < 2; i++) {
            btn[i][0] = new JButton("ON");
            btn[i][1] = new JButton("OFF");
      }
      btn[2][0] = new JButton("10장");
      btn[2][1] = new JButton("20장");
      btn[2][2] = new JButton("40장");
      
      
      
      
      for(int i = 0; i < btn.length; i++) {
            for(int j = 0; j < btn[i].length; j++) {
               btn[i][j].setContentAreaFilled(false);
               btn[i][j].setFocusPainted(false);
               btn[i][j].setBorderPainted(false);
               btn[i][j].setFont(font);
               //btn[i][j].setForeground(fg);
               btn[i][j].addKeyListener(l);
               //btn[i][j].setSize(200, 200);
               btn[i][j].addMouseListener(ml);
               btn[i][j].addActionListener(l);
            }
      }
      option = new int[3];
      option[2] = 1;
      
      btn[0][0].setForeground(fg);
      btn[1][0].setForeground(fg);
      btn[2][1].setForeground(fg);
      
//      for(int i = 0; i < buttons.length; i++) {
//         buttons[i].setContentAreaFilled(false);
//         buttons[i].setFocusPainted(false);
//         buttons[i].setBorderPainted(false);
//         buttons[i].setFont(font);
//         buttons[i].addKeyListener(l);
//         if(i==1 || i==4 || i==7) {
//            continue;
//         }
//         else {
//            buttons[i].addMouseListener(new MouseAdapter() {
//               public void mousePressed(MouseEvent e) {
//                  //e.getButton();
//               }
//            });
//         }
//      }
      
//      JLabel label0 = new JLabel("배경음");
//      JLabel label1 = new JLabel("효과음");
//      JLabel label2 = new JLabel("컵색깔");
//      JLabel label3 = new JLabel("덱개수");
      
//      
//      label0.setFont(font);
//      label1.setFont(font);
//      label2.setFont(font);
//      label3.setFont(font);
      
      
      addGrid(rarrows[0], 0, 0, 1, 1);
      addGrid(name[0], 1, 0, 1, 1);
      addGrid(btn[0][0], 2, 0, 2, 1);
      addGrid(slash[0], 4, 0, 1, 1);
      addGrid(btn[0][1], 5, 0, 2, 1);
      addGrid(larrows[0], 7, 0, 1, 1);
        
      addGrid(rarrows[1], 0, 1, 1, 1);
      addGrid(name[1], 1, 1, 1, 1);
      addGrid(btn[1][0], 2, 1, 2, 1);
      addGrid(slash[1], 4, 1, 1, 1);
      addGrid(btn[1][1], 5, 1, 2, 1);
      addGrid(larrows[1], 7, 1, 1, 1);
        
      addGrid(rarrows[2], 0, 2, 1, 1);
      addGrid(name[2], 1, 2, 1, 1);
      addGrid(btn[2][0], 2, 2, 1, 1);
      addGrid(slash[2], 3, 2, 1, 1);
      addGrid(btn[2][1], 4, 2, 1, 1);
      addGrid(slash[3], 5, 2, 1, 1);
      addGrid(btn[2][2], 6, 2, 1, 1);
      addGrid(larrows[2], 7, 2, 1, 1);
      
      addGrid(rarrows[3], 0, 3, 1, 1);
      addGrid(back, 1, 3, 6, 1);
      addGrid(larrows[3], 7, 3, 1, 1);
      
//      setSize(1364, 714);         // 판넬의 크기 설정
//      setVisible(true);            // 판넬의 시각화 설정
   }
   
   private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight) {
      gbc.gridx = gridx;
      gbc.gridy = gridy;
      gbc.gridwidth = gridwidth;
      gbc.gridheight = gridheight;
      gbl.setConstraints(c, gbc);
      add(c);
   }
   
   class ArrowsHandler extends KeyAdapter implements ActionListener{
      public void keyPressed(KeyEvent e) {
         if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(index != 3) {
               larrows[index].setVisible(false);
               rarrows[index].setVisible(false);
               index += 1;
               larrows[index].setVisible(true);
               rarrows[index].setVisible(true);
            }
         } else if(e.getKeyCode() == KeyEvent.VK_UP) {
            if(index != 0) {
               larrows[index].setVisible(false);
               rarrows[index].setVisible(false);
               index -= 1;
               larrows[index].setVisible(true);
               rarrows[index].setVisible(true);
            }
         } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
               //System.out.println("left, option[index]="+option[index]);
               if(index != 3 && option[index] != 0) {
//                  System.out.println("left");
                  btn[index][option[index]].setForeground(new Color(0, 0, 0));
                  option[index] -= 1;
                  btn[index][option[index]].setForeground(new Color(255, 80, 80));
//                  Option.this.validate();
               }
         } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
//               System.out.println("right, option[index]=" + option[index]);
//               System.out.println("btn[index].length-1 = " + (btn[index].length -1));
               if(index != 3 && option[index] != btn[index].length - 1) {
//                  System.out.println("right");
               btn[index][option[index]].setForeground(new Color(0, 0, 0));
               option[index] += 1;
               btn[index][option[index]].setForeground(new Color(255, 80, 80));
//               Option.this.validate();
            }
         } else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
               if(index == 3)
               actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
         }
      }
      public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("\n") || index == 3){
               ChangePanelService cps = ChangePanelService.getInstance();
              cps.changePanel("MainView");
            } else {
               for(int i = 0; i < btn.length; i++) {
                  for(int j = 0; j < btn[i].length; j++) {
                     if(e.getSource() == btn[i][j]) {
//                        System.out.printf("btn[%d][%d]\n", i, j);
//                        System.out.printf("option[%d]:%d", i, option[i]);
                        btn[i][option[i]].setForeground(new Color(0, 0, 0));
                        btn[i][j].setForeground(new Color(255, 80, 80));
                        option[i] = j;
//                        Option.this.validate();
                     }
                  }
               }
            }
      }
   }
   class MouseHandler extends MouseAdapter{
      public void mouseEntered(MouseEvent e) {
         for(int i = 0; i < btn.length; i++) {
            if(e.getSource() == name[i]) {
               changeIndex(i); return;
            }
            for(int j = 0; j < btn[i].length; j++) {
               if(e.getSource() == btn[i][j]) {
                  changeIndex(i);   return;
               }
            }
         }
         for(int i = 0; i < slash.length; i++) {
            if(e.getSource() == slash[i]) {
               if(i == slash.length-1) i -= 1;
               changeIndex(i); return;
            }
         }
         changeIndex(3);
      }
      private void changeIndex(int i) {
         larrows[index].setVisible(false);
         rarrows[index].setVisible(false);
         larrows[i].setVisible(true);
         rarrows[i].setVisible(true);
         index = i;
      }
   }
}
