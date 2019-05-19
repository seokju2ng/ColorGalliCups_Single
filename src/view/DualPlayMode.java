package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import etc.Board;
import etc.Cards;
import etc.ChangePanelService;
import etc.ImageCut;
import etc.RoundedPanel;

public class DualPlayMode extends JPanel implements ActionListener {
   public static final int WIDTH = 1363, HEIGHT = 714;

   // ********************************************************
   // edit by minseongChoi
   private JLabel redCup1p[][];
   private JLabel blueCup1p[][];
   private JLabel greenCup1p[][];
   private JLabel yellowCup1p[][];
   private JLabel blackCup1p[][];
   private JLabel redCup2p[][];
   private JLabel blueCup2p[][];
   private JLabel greenCup2p[][];
   private JLabel yellowCup2p[][];
   private JLabel blackCup2p[][];
   private int[] colorFlag1p;//
   private int[] colorFlag2p;//
   private int gamePanelIndex1p;
   private int gamePanelIndex2p;
   private int gamePanelY1p;
   private int gamePanelY2p;
   private boolean spaceFlag1p;
   private boolean spaceFlag2p;
   // **********************************************************

   private JPanel p1;
   private JPanel p2;
   private JPanel p3;
   private JLabel[] pointerOne; // 1p 화살표
   private JLabel[] pointerTwo; // 2p 화살표

   private Board board1, board2;

   private JPanel timePanel, cardPanel; // 진행시간과 카드번호를 나타내기 위한 패널
   private JLabel timer; // 진행시간 나타내기 위함
   private JLabel cardNum; // 카드번호를 나타내기 위함
   private JPanel cardDeck; // 카드가 올라갈 패널
   private JButton bell; // 종

   private JButton pause;
   private JButton exit;

   private ArrayList<JLabel> one_Deck; // 1p player가 맞춘 카드개수.
   private ArrayList<JLabel> two_Deck; // 2p player가 맞춘 카드개수.
  // private JLabel card; // 중앙 덱에 올라갈 카드

   private JButton[] one_buttons; // 1p전용 키 버튼
   private JButton[] two_buttons; // 2p전용 키 버튼

   private JButton pauseBackground;
   private boolean pauseFlag = false; // 일시정지 여부

   private class MouseHandler extends MouseAdapter {
      public void mouseEntered(MouseEvent e) {
         if (e.getSource().equals(exit)) {
            exit.setIcon(new ImageIcon("image/exit(click).png"));
         } else if (e.getSource().equals(pause)) {
            pause.setIcon(new ImageIcon("image/pause(click).png"));
         }
      }

      public void mouseExited(MouseEvent e) {
         if (e.getSource().equals(exit)) {
            exit.setIcon(new ImageIcon("image/exit.png"));
         } else if (e.getSource().equals(pause)) {
            pause.setIcon(new ImageIcon("image/pause.png"));
         }
      }
   }

   private class ActionHandler implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         // TODO Auto-generated method stub
         if (e.getSource().equals(pause)) {
            if (pauseFlag == false) {
               p2.setVisible(false);
               pauseBackground.setVisible(true);
               pauseFlag = true;
            }

         } else if (e.getSource().equals(pauseBackground)) {
            System.out.println("눌림");
            if (pauseFlag == true) {
               p2.setVisible(true);
               pauseBackground.setVisible(false);
               pauseFlag = false;
            }
         }
      }

   }

   private class KeyHandler implements KeyListener {

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
         // TODO Auto-generated method stub
         if (e.getKeyCode() == KeyEvent.VK_Q) {
            one_buttons[0].setIcon(ImageCut.one_pressed_image[0]);
            spaceFlag1p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_W) {
            one_buttons[1].setIcon(ImageCut.one_pressed_image[1]);
            spaceFlag1p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_E) {
            one_buttons[2].setIcon(ImageCut.one_pressed_image[2]);
            spaceFlag1p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_A) {
            one_buttons[3].setIcon(ImageCut.one_pressed_image[3]);
            spaceFlag1p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_S) {
            one_buttons[4].setIcon(ImageCut.one_pressed_image[4]);
            spaceFlag1p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_I) {
            two_buttons[0].setIcon(ImageCut.two_pressed_image[0]);
            spaceFlag2p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_O) {
            two_buttons[1].setIcon(ImageCut.two_pressed_image[1]);
            spaceFlag2p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_P) {
            two_buttons[2].setIcon(ImageCut.two_pressed_image[2]);
            spaceFlag2p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_K) {
            two_buttons[3].setIcon(ImageCut.two_pressed_image[3]);
            spaceFlag2p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_L) {
            two_buttons[4].setIcon(ImageCut.two_pressed_image[4]);
            spaceFlag2p = false;
         } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            int setCupFlag = 0;
            for (int i = 0; i < 5; i++) {
               if (colorFlag2p[i] > 0) {
                  setCupFlag++;
               }
            }
            if (setCupFlag == 5 && spaceFlag2p == false) {
               bell.setIcon(new ImageIcon("image/bell(push2).png"));
               playSound("audio/bell.wav");
               two_flag = true;
               two_x = initX; // 엔터누를때 2p 카드의 초기위치 재설정.
               tm.start();
              
            }
         } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            int setCupFlag = 0;
            for (int i = 0; i < 5; i++) {
               if (colorFlag1p[i] > 0) {
                  setCupFlag++;
               }
            }
            if (setCupFlag == 5 && spaceFlag1p == false) {
               bell.setIcon(new ImageIcon("image/bell(push).png"));
               playSound("audio/bell.wav");
               one_flag = true;
               one_x = initX;
               tm.start();
            }
         }

      }

      @Override
      public void keyReleased(KeyEvent e) {
         // TODO Auto-generated method stub
         if (e.getKeyCode() == KeyEvent.VK_Q) {
            one_buttons[0].setIcon(ImageCut.one_image[0]);
            if (colorFlag1p[0] == 0) {
               colorFlag1p[0] = gamePanelIndex1p + 1;
               // System.out.println(redCup1p+"," + gamePanelIndex1p +"," + gamePanelY1p);
               redCup1p[gamePanelIndex1p][4 - gamePanelY1p].setVisible(true);

               if (gamePanelY1p < 4)
                  gamePanelY1p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_W) {
            one_buttons[1].setIcon(ImageCut.one_image[1]);
            if (colorFlag1p[1] == 0) {
               colorFlag1p[1] = gamePanelIndex1p + 1;
               yellowCup1p[gamePanelIndex1p][4 - gamePanelY1p].setVisible(true);
               if (gamePanelY1p < 4)
                  gamePanelY1p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_E) {
            one_buttons[2].setIcon(ImageCut.one_image[2]);
            if (colorFlag1p[2] == 0) {
               colorFlag1p[2] = gamePanelIndex1p + 1;
               greenCup1p[gamePanelIndex1p][4 - gamePanelY1p].setVisible(true);
               if (gamePanelY1p < 4)
                  gamePanelY1p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_A) {
            one_buttons[3].setIcon(ImageCut.one_image[3]);
            if (colorFlag1p[3] == 0) {
               colorFlag1p[3] = gamePanelIndex1p + 1;
               blueCup1p[gamePanelIndex1p][4 - gamePanelY1p].setVisible(true);
               if (gamePanelY1p < 4)
                  gamePanelY1p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_S) {
            one_buttons[4].setIcon(ImageCut.one_image[4]);
            if (colorFlag1p[4] == 0) {
               colorFlag1p[4] = gamePanelIndex1p + 1;
               blackCup1p[gamePanelIndex1p][4 - gamePanelY1p].setVisible(true);
               if (gamePanelY1p < 4)
                  gamePanelY1p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            bell.setIcon(new ImageIcon("image/bell.png"));
            if (spaceFlag1p == true) {
               for (int i = 0; i < 5; i++) {
                  colorFlag1p[i] = 0;
                  pointerOne[i].setVisible(false);
                  for (int j = 0; j < 5; j++) {
                     redCup1p[i][j].setVisible(false);
                     blackCup1p[i][j].setVisible(false);
                     greenCup1p[i][j].setVisible(false);
                     yellowCup1p[i][j].setVisible(false);
                     blueCup1p[i][j].setVisible(false);
                  }
               }
               gamePanelIndex1p = 0;
               gamePanelY1p = 0;
               pointerOne[0].setVisible(true);
               return;
            }
            int setCupFlag = 0;
            for (int i = 0; i < 5; i++) {
               if (colorFlag1p[i] > 0) {
                  setCupFlag++;
               }
            }
            if (setCupFlag < 5) {
               boolean isEmptyPanel = true;
               for (int i = 0; i < 5; i++) {
                  if (colorFlag1p[i] == gamePanelIndex1p + 1) {
                     isEmptyPanel = false;
                  }
               }
               if (gamePanelIndex1p < 4 && isEmptyPanel == false) {
                  gamePanelIndex1p++;
                  gamePanelY1p = 0;
                  if (gamePanelIndex1p < 5) {
                     for (int i = 0; i < 5; i++)
                        pointerOne[i].setVisible(false);
                     pointerOne[gamePanelIndex1p].setVisible(true);
                  }

               }
            }
            spaceFlag1p = true;
         }

         // 2p
         else if (e.getKeyCode() == KeyEvent.VK_I) {
            two_buttons[0].setIcon(ImageCut.two_image[0]);
            if (colorFlag2p[0] == 0) {
               colorFlag2p[0] = gamePanelIndex2p + 1;
               redCup2p[gamePanelIndex2p][4 - gamePanelY2p].setVisible(true);
               if (gamePanelY2p < 4)
                  gamePanelY2p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_O) {
            two_buttons[1].setIcon(ImageCut.two_image[1]);
            if (colorFlag2p[1] == 0) {
               colorFlag2p[1] = gamePanelIndex2p + 1;
               yellowCup2p[gamePanelIndex2p][4 - gamePanelY2p].setVisible(true);
               if (gamePanelY2p < 4)
                  gamePanelY2p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_P) {
            two_buttons[2].setIcon(ImageCut.two_image[2]);
            if (colorFlag2p[2] == 0) {
               colorFlag2p[2] = gamePanelIndex2p + 1;
               greenCup2p[gamePanelIndex2p][4 - gamePanelY2p].setVisible(true);
               if (gamePanelY2p < 4)
                  gamePanelY2p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_K) {
            two_buttons[3].setIcon(ImageCut.two_image[3]);
            if (colorFlag2p[3] == 0) {
               colorFlag2p[3] = gamePanelIndex2p + 1;
               blueCup2p[gamePanelIndex2p][4 - gamePanelY2p].setVisible(true);
               if (gamePanelY2p < 4)
                  gamePanelY2p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_L) {
            two_buttons[4].setIcon(ImageCut.two_image[4]);
            if (colorFlag2p[4] == 0) {
               colorFlag2p[4] = gamePanelIndex2p + 1;
               blackCup2p[gamePanelIndex2p][4 - gamePanelY2p].setVisible(true);
               if (gamePanelY2p < 4)
                  gamePanelY2p++;
            }
         } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            bell.setIcon(new ImageIcon("image/bell.png"));
            if (spaceFlag2p == true) {
               for (int i = 0; i < 5; i++) {
                  colorFlag2p[i] = 0;
                  pointerTwo[i].setVisible(false);
                  for (int j = 0; j < 5; j++) {
                     redCup2p[i][j].setVisible(false);
                     blackCup2p[i][j].setVisible(false);
                     greenCup2p[i][j].setVisible(false);
                     yellowCup2p[i][j].setVisible(false);
                     blueCup2p[i][j].setVisible(false);
                  }
               }
               gamePanelIndex2p = 0;
               gamePanelY2p = 0;
               pointerTwo[0].setVisible(true);
               return;
            }
            int setCupFlag = 0;
            for (int i = 0; i < 5; i++) {
               if (colorFlag2p[i] > 0) {
                  setCupFlag++;
               }
            }
            if (setCupFlag < 5) {
               boolean isEmptyPanel = true;
               for (int i = 0; i < 5; i++) {
                  if (colorFlag2p[i] == gamePanelIndex2p + 1) {
                     isEmptyPanel = false;
                  }
               }
               if (gamePanelIndex2p < 4 && isEmptyPanel == false) {
                  gamePanelIndex2p++;
                  gamePanelY2p = 0;
                  if (gamePanelIndex2p < 5) {
                     for (int i = 0; i < 5; i++)
                        pointerTwo[i].setVisible(false);
                     pointerTwo[gamePanelIndex2p].setVisible(true);
                  }

               }
            }
            spaceFlag2p = true;
         }
      }

   }

   // 애니메이션//
   // ImageIcon icon = new ImageIcon("image/dotdanbae2.png");
   // Image img = icon.getImage();

   private Timer tm = new Timer(10, this);
   private int initX = 604, velX = 5; // 초기 카드위치와 카드가 움직이는 속도
   private boolean one_flag = false; // 1p의 정답여부
   private boolean two_flag = false; // 2p의 정답여부
   private int one_x, two_x; // 1p, 2p의 카드좌표
   private int one_cnt, two_cnt; // 1p,2p의 정답개수
   private Cards systemCardDeck;
   private int cardCnt; //현재 시스템 카드덱의 카드가 몇번째 카드인지.
   //////

   // 여기서부터 카드 애니메이션 0518//
   public void actionPerformed(ActionEvent e) {
      if (one_flag == true) {

         if (one_x > 150) {
            // velX = -velX;
        	 systemCardDeck.card_arr.get(cardCnt).setBounds(one_x, -(one_x / 7) + 128, 154, 238);
            one_x = one_x - velX;
            // card.setBounds(610,-(610/7)+45,154,238);

         }
         // x = x + velX;
         // card.setBounds(x,x/7-45,154,238);
         else if (one_x < 150) {
            try {
               Thread.sleep(1000); // 카드가 1초후 사라진다.
            } catch (InterruptedException e1) {
            }
            ImageIcon icon = (ImageIcon)  systemCardDeck.card_arr.get(cardCnt).getIcon(); // 얻은 카드의 이미지를 따온다.
            this.remove( systemCardDeck.card_arr.get(cardCnt)); // 카드를 지우고
            this.revalidate(); // 부모컨테이너를 새로고침한다
            this.repaint(); // 새로고침.

            one_Deck.add(new JLabel(ImageCut.resizeIcon(icon, 90, 140))); // 1p 사용자 카드덱에 카드추가
            one_Deck.get(one_cnt).setBounds(50 + one_cnt * 10, 190, 90, 140); // 좌표는 나중에 수정
            for (int i = one_Deck.size() - 1; i >= 0; i--) {
               p1.add(one_Deck.get(i));
            }
            one_cnt++; // 1p정답갯수를 늘려준다.
            one_flag = false;
            System.out.println("된다! one_cnt: " + one_cnt + ", arraylSize : " + one_Deck.size());
            cardCnt++; //시스템카드덱을 +1한다.
            cardNum.setText(cardCnt + "/" + systemCardDeck.card_arr.size());
         }
      } else if (two_flag == true) {
         if (two_x < 1100) {
        	 systemCardDeck.card_arr.get(cardCnt).setBounds(two_x, two_x / 7 - 45, 154, 238); // 원래
            two_x = two_x + velX;

         } else if (two_x > 1100) {
            try {
               Thread.sleep(1000); // 카드가 1초후 사라진다.
            } catch (InterruptedException e1) {
            }
            ImageIcon icon = (ImageIcon)  systemCardDeck.card_arr.get(cardCnt).getIcon(); // 얻은 카드의 이미지를 따온다.
            this.remove( systemCardDeck.card_arr.get(cardCnt)); // 카드를 지우고
            this.revalidate(); // 부모컨테이너를 새로고침한다
            this.repaint(); // 새로고침.

            two_Deck.add(new JLabel(ImageCut.resizeIcon(icon, 90, 140))); // 2p 사용자 카드덱에 카드추가
            two_Deck.get(two_cnt).setBounds(350 - two_cnt * 10, 190, 90, 140); // 좌표는 나중에 수정
            for (int i = two_Deck.size() - 1; i >= 0; i--) {
               p3.add(two_Deck.get(i)); // 겹치는 순서 지키기위해 같은 것도 다시 add한다.
            }
            two_cnt++; // 2p정답갯수를 늘려준다.

            System.out.println("된다! two_cnt: " + two_cnt + ", arraylSize : " + two_Deck.size());

            two_flag = false;
            cardCnt++;
            cardNum.setText(cardCnt + "/" + systemCardDeck.card_arr.size());
         }
      }
   }

   //////////////////////// 여기까지 카드 에니메이션/////////////

   public DualPlayMode() {

      this.setLayout(null);
      
      // setSize(WIDTH, HEIGHT);
      // ********************************************************
      // edit by minseongChoi, first
      colorFlag1p = new int[5];
      colorFlag2p = new int[5];
      gamePanelIndex1p = 0;
      gamePanelIndex2p = 0;
      gamePanelY1p = 0;
      gamePanelY2p = 0;
      spaceFlag1p = false;
      spaceFlag2p = false;
      // end
      // ********************************************************
      pauseBackground = new JButton(new ImageIcon("image/pauseBackground.png"));
      pauseBackground.setBounds(0, 0, 1363, 714);
      pauseBackground.setContentAreaFilled(false);
      pauseBackground.setFocusPainted(false);
      pauseBackground.setBorderPainted(false);
      pauseBackground.setVisible(false);
      pauseBackground.addActionListener(new ActionHandler());
      this.add(pauseBackground);
      
      // 카드를 가장 먼저 붙임)(0518애니메이션)
       systemCardDeck = new Cards();
      
//      this.card = new JLabel(new ImageIcon("image/dotdanbae2.png"));
//      card.setBounds(604, 41, 154, 238);
      for(int i = 0 ; i < systemCardDeck.card_arr.size() ; i++) {
    	  systemCardDeck.card_arr.get(i).setBounds(604,41,154,238);
    	  this.add(systemCardDeck.card_arr.get(i));
      }
      //this.add(card);
      
      showCenter();
      showWest();
      // showCenter();
      showEast();
      // setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);

      this.setFocusable(true);

      one_buttons[0].setFocusable(true);
      one_buttons[0].requestFocus(); // 초기에 버튼쪽에 focus를 요청한다.
      one_buttons[0].setFocusable(true);

   }

   public void showWest() {
      // 화살표 달아줌//
      pointerOne = new JLabel[5];
      for (int i = 0; i < pointerOne.length; i++) {
         pointerOne[i] = new JLabel(new ImageIcon("image/point.png"));
         pointerOne[i].setBounds(65 + 80 * i, 600, 40, 40);
         pointerOne[i].setVisible(false);
      }
      pointerOne[0].setVisible(true);
      ///////////////////////////////
      int width = 500;
      int height = 700;

      // 1p에 보드 생성//
      board1 = new Board(5); // 5열짜리 보드를 만듦.
      board1.setBounds(45, 350, 400, 240);
      board1.setBackground(Color.white);
      // ********************************************************
      // edit by minseongChoi, first
      redCup1p = new JLabel[5][5];
      blueCup1p = new JLabel[5][5];
      greenCup1p = new JLabel[5][5];
      yellowCup1p = new JLabel[5][5];
      blackCup1p = new JLabel[5][5];
//            JLabel redCup2p[][] = new JLabel[5][5];
//            JLabel blueCup2p[][] = new JLabel[5][5];
//            JLabel greenCup2p[][] = new JLabel[5][5];
//            JLabel yellowCup2p[][] = new JLabel[5][5];
//            JLabel blackCup2p[][] = new JLabel[5][5];                  
      // -----------------------------------------
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            redCup1p[i][j] = new JLabel(new ImageIcon("image/cup(red)_dual.png"));
            blackCup1p[i][j] = new JLabel(new ImageIcon("image/cup(black)_dual.png"));
            greenCup1p[i][j] = new JLabel(new ImageIcon("image/cup(green)_dual.png"));
            blueCup1p[i][j] = new JLabel(new ImageIcon("image/cup(blue)_dual.png"));
            yellowCup1p[i][j] = new JLabel(new ImageIcon("image/cup(yellow)_dual.png"));
            redCup1p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            blackCup1p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            greenCup1p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            blueCup1p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            yellowCup1p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            redCup1p[i][j].setVisible(false);
            blackCup1p[i][j].setVisible(false);
            greenCup1p[i][j].setVisible(false);
            yellowCup1p[i][j].setVisible(false);
            blueCup1p[i][j].setVisible(false);
            board1.getBoardPanel(i).add(redCup1p[i][j]);
            board1.getBoardPanel(i).add(blackCup1p[i][j]);
            board1.getBoardPanel(i).add(greenCup1p[i][j]);
            board1.getBoardPanel(i).add(blueCup1p[i][j]);
            board1.getBoardPanel(i).add(yellowCup1p[i][j]);
         }
      }
      // end
      // **************************************************************************
      ///////////////////////////////

      JLabel timerText = new JLabel("진행시간", SwingConstants.CENTER); // '진행시간'을 넣을 라벨
      JLabel cardText = new JLabel("카드번호", SwingConstants.CENTER); // '카드번호'를 넣을 라벨
      timerText.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
      cardText.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
      this.p1 = new JPanel(); // 왼쪽 JPanel 생성
      p1.setLayout(null);
      // p1.setBorder(new LineBorder(Color.red, 5));
      p1.setBounds(0, 0, width, height);

      timePanel = new JPanel(); // '진행시간'라벨과 실제 진행시간을 붙일 패널
      cardPanel = new JPanel(); // '카드번호'라벨과 실제 카드번호를 붙일 패널
      timePanel.setBackground(Color.white);
      cardPanel.setBackground(Color.white);
      timePanel.setLayout(null);
      cardPanel.setLayout(null);
      // cardPanel.setBorder(new LineBorder(Color.red, 5));
      timePanel.setBounds(50, 20, 180, 100);
      // timePanel.setBorder(new LineBorder(Color.red, 5));
      cardPanel.setBounds(270, 20, 180, 100);
      timer = new JLabel("00:00", SwingConstants.CENTER);
      timer.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));

      cardNum = new JLabel(cardCnt +"/"+systemCardDeck.card_arr.size(), SwingConstants.CENTER);
      cardNum.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
      // timer.setBorder(new LineBorder(Color.red, 5));
      // cardNum.setBorder(new LineBorder(Color.red, 5));
      timerText.setBounds(0, 0, 180, 50);
      cardText.setBounds(0, 0, 180, 50);
      timer.setBounds(0, 50, 180, 50);
      cardNum.setBounds(0, 50, 180, 50);

      timePanel.add(timerText);
      timePanel.add(timer);
      cardPanel.add(cardText);
      cardPanel.add(cardNum);

      p1.add(timePanel);
      p1.add(cardPanel);
      p1.add(board1);
      for (int i = 0; i < pointerOne.length; i++) {
         p1.add(pointerOne[i]);
      }
      timePanel.add(timer);
      cardPanel.add(cardNum);
      // 1p 덱에 카드추가(카드얻었을시)//
      one_Deck = new ArrayList<>();
//      one_Deck.add(new JLabel(new ImageIcon("image/card(back).png")));
//      one_Deck.add(new JLabel(new ImageIcon("image/card(back).png")));
//      one_Deck.add(new JLabel(new ImageIcon("image/card(back).png")));
//      one_Deck.add(new JLabel(new ImageIcon("image/card(back).png")));
//      one_Deck.add(new JLabel(new ImageIcon("image/card(back).png")));
//      one_Deck.get(0).setBounds(50, 190, 90, 140);
//      one_Deck.get(1).setBounds(55, 190, 90, 140);
//      one_Deck.get(2).setBounds(60, 190, 90, 140);
//      one_Deck.get(3).setBounds(65, 190, 90, 140);
//      one_Deck.get(4).setBounds(70, 190, 90, 140);
      // p1.add(one_Deck.get(0));
      // p1.add(one_Deck.get(1));
      // p1.add(one_Deck.get(2));
      // p1.add(one_Deck.get(3));
//      p1.add(one_Deck.get(4));
//      p1.add(one_Deck.get(3));
//      p1.add(one_Deck.get(2));
//      p1.add(one_Deck.get(1));
//      p1.add(one_Deck.get(0));
      ///////////////////////////////
      this.add(p1);
   }

   public void showEast() {
      // 화살표 달아줌//
      pointerTwo = new JLabel[5];
      for (int i = 0; i < pointerOne.length; i++) {
         pointerTwo[i] = new JLabel(new ImageIcon("image/point.png"));
         pointerTwo[i].setBounds(65 + 80 * i, 600, 40, 40);
         pointerTwo[i].setVisible(false);
      }
      pointerTwo[0].setVisible(true);
      ///////////////////////////////
      // 2p에 보드 추가//
      board2 = new Board(5); // 5열짜리 보드를 만듦.
      board2.setBounds(45, 350, 400, 240);
      board2.setBackground(Color.white);

      // ********************************************************
      // edit by minseongChoi, first
      redCup2p = new JLabel[5][5];
      blueCup2p = new JLabel[5][5];
      greenCup2p = new JLabel[5][5];
      yellowCup2p = new JLabel[5][5];
      blackCup2p = new JLabel[5][5];
      // -----------------------------------------
      for (int i = 0; i < 5; i++) {
         for (int j = 0; j < 5; j++) {
            redCup2p[i][j] = new JLabel(new ImageIcon("image/cup(red)_dual.png"));
            blackCup2p[i][j] = new JLabel(new ImageIcon("image/cup(black)_dual.png"));
            greenCup2p[i][j] = new JLabel(new ImageIcon("image/cup(green)_dual.png"));
            blueCup2p[i][j] = new JLabel(new ImageIcon("image/cup(blue)_dual.png"));
            yellowCup2p[i][j] = new JLabel(new ImageIcon("image/cup(yellow)_dual.png"));
            redCup2p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            blackCup2p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            greenCup2p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            blueCup2p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            yellowCup2p[i][j].setBounds(5, 100 + (15 * j), 70, 80);
            redCup2p[i][j].setVisible(false);
            blackCup2p[i][j].setVisible(false);
            greenCup2p[i][j].setVisible(false);
            yellowCup2p[i][j].setVisible(false);
            blueCup2p[i][j].setVisible(false);
            board2.getBoardPanel(i).add(redCup2p[i][j]);
            board2.getBoardPanel(i).add(blackCup2p[i][j]);
            board2.getBoardPanel(i).add(greenCup2p[i][j]);
            board2.getBoardPanel(i).add(blueCup2p[i][j]);
            board2.getBoardPanel(i).add(yellowCup2p[i][j]);
         }
      }
      // end
      // **************************************************************************
      ///////////////////////////

      this.p3 = new JPanel();
      // p3.setBackground(Color.WHITE);
      p3.setLayout(null);
      // p3.setBorder(new LineBorder(Color.red, 5));
      p3.setBounds(863, 0, 500, 700);
      this.add(p3);// 우측에 p3 패널 붙임.

      /* pause와 exit 경계선 없애서 p3패널에 붙임 */
      this.pause = new JButton(new ImageIcon("image/pause.png"));
      this.exit = new JButton(new ImageIcon("image/exit.png"));
      pause.setContentAreaFilled(false);
      pause.setFocusPainted(false);
      pause.setBorderPainted(false);
      exit.setContentAreaFilled(false);
      exit.setFocusPainted(false);
      exit.setBorderPainted(false);
      pause.setBounds(280, 30, 80, 80);
      exit.setBounds(380, 30, 80, 80);

      pause.addActionListener(new ActionHandler()); // pause버튼에 액션핸들러 추가
      pause.addMouseListener(new MouseHandler());
      exit.addActionListener(e -> {
         int res = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION,
               JOptionPane.WARNING_MESSAGE);
         if (res == JOptionPane.YES_OPTION) {
            ChangePanelService.getInstance().changePanel("MainView");
         }
      }); // exit버튼에 액션핸들러 추가
      exit.addMouseListener(new MouseHandler());
      pause.addFocusListener(new FocusListener() {

         @Override
         public void focusLost(FocusEvent e) {
            System.out.println("포커스얻음!");
            one_buttons[0].requestFocus();
            one_buttons[0].setFocusable(true);
         }

         @Override
         public void focusGained(FocusEvent e) {
            System.out.println("포커스잃음!");
            one_buttons[0].requestFocus();
            one_buttons[0].setFocusable(true);

         }
      });

      p3.add(pause);
      p3.add(exit);
      p3.add(board2);
      for (int i = 0; i < pointerTwo.length; i++) {
         p3.add(pointerTwo[i]);
      }
      ///////////////////////
      two_Deck = new ArrayList<>();

   }

   public void showCenter() {
      this.p2 = new JPanel();
      // p2.setBackground(Color.WHITE);
      p2.setLayout(null); // 가운데 패널 레이아웃을 제거
      p2.setBounds(500, 3, 360, 680);

      this.add(p2);
      this.cardDeck = new RoundedPanel(null, 120, Color.WHITE);

      ImageIcon img1 = new ImageIcon("image/dotdanbae2.png");
      ImageIcon img2 = new ImageIcon("image/bell.png");

      this.bell = new JButton(img2);
      // this.card = new JLabel(img1);
      // card.setBorder(new LineBorder(Color.red, 5));
      // bell.setBorder(new LineBorder(Color.red, 5)); // 종 버튼에 경계선 추가
      // card.setBounds(60,20,154,238);
      // card.setBounds(60, 20, 154, 238);
      bell.setBounds(90, 380, 190, 150);
      bell.setContentAreaFilled(false);
      bell.setFocusPainted(false);
      bell.setBorderPainted(false);

      // this.add(card);
      cardDeck.setLayout(null);
      cardDeck.setBounds(40, 20, 278, 278);
      p2.add(cardDeck);
      addKeyButtons();
      p2.add(bell);
      // p2.addKeyListener(new KeyHandler());

   }

   /*------------ centerpane(p2)에 버튼10개 달아주는 메소드 ---------------*/
   public void addKeyButtons() {
      p2.setLayout(null);
      one_buttons = new JButton[5];
      two_buttons = new JButton[5];

      for (int i = 0; i < one_buttons.length; i++) {
         one_buttons[i] = new JButton();
         one_buttons[i].setIcon(ImageCut.one_image[i]);
         one_buttons[i].setContentAreaFilled(false);
         one_buttons[i].setFocusPainted(false);
         one_buttons[i].setBorderPainted(false);
         if (i < 3) {
            one_buttons[i].setBounds(20 + i * 45, 550, 40, 40);
         } else {
            one_buttons[i].setBounds(i * 45 - 100, 600, 40, 40);
         }
         one_buttons[i].addKeyListener(new KeyHandler());
         one_buttons[0].setFocusable(true);
         one_buttons[0].requestFocus(); // 초기에 버튼쪽에 focus를 요청한다.
         one_buttons[0].setFocusable(true);
         p2.add(one_buttons[i]);
      }

      for (int i = 0; i < two_buttons.length; i++) {
         two_buttons[i] = new JButton();
         two_buttons[i].setIcon(ImageCut.two_image[i]);
         two_buttons[i].setContentAreaFilled(false);
         two_buttons[i].setFocusPainted(false);
         two_buttons[i].setBorderPainted(false);
         if (i < 3) {
            two_buttons[i].setBounds(210 + i * 45, 550, 40, 40);
         } else {
            two_buttons[i].setBounds(i * 45 + 90, 600, 40, 40);
         }
         two_buttons[i].addKeyListener(new KeyHandler());
         p2.add(two_buttons[i]);
      }
   }

   /*
    * public void showEast() { this.p3 = new RoundedPanel(120,Color.white);
    * p3.setLayout(null); //p3.setBorder(new LineBorder(Color.red,5));
    * p3.setBounds(863,0,500,700); this.add(p3); }
    */

   public void playSound(String fileName) {
      try {
         AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
         Clip clip = AudioSystem.getClip();
         clip.stop();
         clip.open(ais);
         clip.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
