package view;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import etc.ChangePanelService;
import etc.RoundedPanel;
public class SinglePlayMode extends JPanel
{
   private static final long serialVersionUID = 1L;
   private GridBagConstraints gbc;
   private GridBagLayout gbl;
   //Panel
   private JPanel east;
   private JPanel west;
   private JButton pauseBackground;
   //5개의 키 버튼
   private JButton qKey;
   private JButton wKey;
   private JButton eKey;
   private JButton aKey;
   private JButton sKey;
   //***********************************************************
   private int[] colorFlag;//
   private int gamePanelIndex;
   private int gamePanelY;
   private boolean spaceFlag;
   //컵
   private JLabel redCup[][];
   private JLabel blueCup[][];
   private JLabel greenCup[][];
   private JLabel yellowCup[][];
   private JLabel blackCup[][];
   //포인트
   private JLabel[] point;
   //게임 패널
   private JPanel[] line;
 //***********************************************************
   //버튼
   private JButton bellBtn;
   private JButton exitBtn;
   private JButton pauseBtn;
   private class MouseHandler extends MouseAdapter {
         public void mouseEntered(MouseEvent e) {
            if (e.getSource().equals(exitBtn)) {
               exitBtn.setIcon(new ImageIcon("image/exit(click).png"));
            } else if (e.getSource().equals(pauseBtn)) {
               pauseBtn.setIcon(new ImageIcon("image/pause(click).png"));
            }
         }

         public void mouseExited(MouseEvent e) {
            if (e.getSource().equals(exitBtn)) {
               exitBtn.setIcon(new ImageIcon("image/exit.png"));
            } else if (e.getSource().equals(pauseBtn)) {
               pauseBtn.setIcon(new ImageIcon("image/pause.png"));
            }
         }
      }
   
   private class ButtonHandler implements ActionListener{
       public void actionPerformed(ActionEvent e) {
          
         }
      }
   private class ClickHandler implements ActionListener{

      @Override
      public void actionPerformed(ActionEvent e) {
         if(e.getSource().equals(pauseBtn)) {
            pauseBackground.setVisible(true);
         }
         else if(e.getSource().equals(pauseBackground)) {
               pauseBackground.setVisible(false);
         }
      }
      
   }
   private class KeyHandler implements KeyListener{
      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_Q) {
            spaceFlag = false;
            qKey.setIcon(new ImageIcon("image/red(q)_press.png"));
            
         }
         else if(e.getKeyCode() == KeyEvent.VK_W) {
            wKey.setIcon(new ImageIcon("image/yellow(w)_press.png"));
            spaceFlag = false;
         }
         else if(e.getKeyCode() == KeyEvent.VK_E) {
            eKey.setIcon(new ImageIcon("image/green(e)_press.png"));
            spaceFlag = false;
         }
         else if(e.getKeyCode() == KeyEvent.VK_A) {
            aKey.setIcon(new ImageIcon("image/blue(a)_press.png"));
            spaceFlag = false;
         }
         else if(e.getKeyCode() == KeyEvent.VK_S) {
            sKey.setIcon(new ImageIcon("image/black(s)_press.png"));
            spaceFlag = false;
         } 
         else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            int setCupFlag=0;
             for(int i = 0; i< 5; i++) {
                if(colorFlag[i] > 0) {
                   setCupFlag++;
                }
             }
             //(gamePanelIndex >= 4 &&setCupFlag == 5) ||
            if( setCupFlag == 5 && spaceFlag == false) {
                bellBtn.setIcon(new ImageIcon("image/bell(push).png"));
                playSound("audio/bell.wav");
             }
            
         }
         
      }
      

      @Override
      //q:r, w:y, e:g, a:blue, s:black
      public void keyReleased(KeyEvent e) {
         // TODO Auto-generated method stub
         if (e.getKeyCode() == KeyEvent.VK_Q) {
            qKey.setIcon(new ImageIcon("image/red(q).png"));
            if(colorFlag[0] == 0) {
               colorFlag[0] = gamePanelIndex+1;
               redCup[gamePanelIndex][4-gamePanelY].setVisible(true);
               if(gamePanelY < 4)
                  gamePanelY++;
            }
         }
         else if(e.getKeyCode() == KeyEvent.VK_W) {
            wKey.setIcon(new ImageIcon("image/yellow(w).png"));
            if(colorFlag[1] == 0) {
               colorFlag[1] = gamePanelIndex+1;
               yellowCup[gamePanelIndex][4-gamePanelY].setVisible(true);
               if(gamePanelY < 4)
                  gamePanelY++;
            }
         }
         else if(e.getKeyCode() == KeyEvent.VK_E) {
            eKey.setIcon(new ImageIcon("image/green(e).png"));
            if(colorFlag[2] == 0) {
               colorFlag[2] = gamePanelIndex+1;
               greenCup[gamePanelIndex][4-gamePanelY].setVisible(true);
               if(gamePanelY < 4)
                  gamePanelY++;
            }
         }
         else if(e.getKeyCode() == KeyEvent.VK_A) {
            aKey.setIcon(new ImageIcon("image/blue(a).png"));
            if(colorFlag[3] == 0) {
               colorFlag[3] = gamePanelIndex+1;
               blueCup[gamePanelIndex][4-gamePanelY].setVisible(true);
               if(gamePanelY < 4)
                  gamePanelY++;
            }
         }
         else if(e.getKeyCode() == KeyEvent.VK_S) {
            sKey.setIcon(new ImageIcon("image/black(s).png"));
            if(colorFlag[4] == 0) {
               colorFlag[4] = gamePanelIndex+1;
               blackCup[gamePanelIndex][4-gamePanelY].setVisible(true);
               if(gamePanelY < 4)
                  gamePanelY++;
            }
         }
         else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            bellBtn.setIcon(new ImageIcon("image/bell.png"));
            if(spaceFlag == true) {
               for(int i = 0; i < 5; i++) {
                  colorFlag[i] = 0;
                  point[i].setVisible(false);
                  for(int j = 0; j <5; j++) {
                     redCup[i][j].setVisible(false);
                     blackCup[i][j].setVisible(false);
                     greenCup[i][j].setVisible(false);
                     yellowCup[i][j].setVisible(false);
                         blueCup[i][j].setVisible(false);
                  }
               }
               gamePanelIndex = 0;
               gamePanelY = 0;
               point[0].setVisible(true);
               return;
            }
            int setCupFlag=0;
            for(int i = 0; i< 5; i++) {
               if(colorFlag[i] > 0) {
                  setCupFlag++;
               }
            }
            if(setCupFlag < 5) {
               boolean isEmptyPanel = true;
               for(int i = 0; i < 5; i++) {
                  if(colorFlag[i] == gamePanelIndex + 1 ) {
                     isEmptyPanel = false;
                  }
               }
               if( gamePanelIndex < 4 && isEmptyPanel == false) {
                   gamePanelIndex++;
                    gamePanelY = 0;
                    if(gamePanelIndex < 5) {
                       for(int i = 0; i <5; i++)
                           point[i].setVisible(false);   
                           point[gamePanelIndex].setVisible(true);
                    }
                    
                }
            }  
            spaceFlag = true;
         }
         
      }
      
   }
   
   public SinglePlayMode()
   {   

     colorFlag = new int[5];
     spaceFlag = false;
     for(int i = 0; i < 5; i++) {
        colorFlag[i] = 0;
     }
     gamePanelIndex = 0;
     gamePanelY = 0;
     
      gbl = new GridBagLayout();
      //GridBagLayout에 필요한 GridBagConstraints 생성
      gbc = new GridBagConstraints();
      gbc.weighty = 1.0;
      gbc.fill=GridBagConstraints.BOTH;
      setLayout(gbl);
      paint();
      
      //Handler   
      this.setFocusable(true);
      addKeyListener(new KeyHandler());//listener
      bellBtn.addKeyListener(new KeyHandler());//listener
      exitBtn.addKeyListener(new KeyHandler());//listener
      pauseBtn.addKeyListener(new KeyHandler());//listener
      exitBtn.addMouseListener(new MouseHandler());
      pauseBtn.addMouseListener(new MouseHandler());
      pauseBackground.addActionListener(new ClickHandler());
      this.setSize(1363, 714);
      this.setVisible(true);
      
   }
   private void make(JComponent c, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        //gbl.setConstraints(c,gbc);
        add(c, gbc);
   }
   private void paint() {   
      pauseBackground = new JButton(new ImageIcon("image/pauseBackground.png"));
      pauseBackground.setBounds(0,0,1363,714);
      pauseBackground.setContentAreaFilled(false);
      pauseBackground.setFocusPainted(false);
      pauseBackground.setBorderPainted(false);
      pauseBackground.setVisible(false);
      add(pauseBackground);
      eastPaint();
      westPaint();
      
   } 
   private void eastPaint() {
      //w:545.2, w/2:272.6, 
      //컴포넌트 생성 --------------------------------------------------------------
      east = new JPanel();
      east.setLayout(null);
      
      //카드 덱 -----------
      JPanel cardDeck = new RoundedPanel(null, 120, Color.WHITE);
      JLabel sampleCard = new JLabel(new ImageIcon("image/sampleCard.png"));
      sampleCard.setBounds(10,10,280,280);
      cardDeck.add(sampleCard);
      cardDeck.setBounds(50,20,300,300);
      
      east.add(cardDeck);
      
      //종 버튼 --------------------------------------------------------------
      bellBtn = new JButton(new ImageIcon("image/bell.png"));
      bellBtn.setBounds(192,380,165,140);
      bellBtn.setContentAreaFilled(false);
      bellBtn.setFocusPainted(false);
      bellBtn.setBorderPainted(false);
      bellBtn.addActionListener(new ClickHandler());
      east.add(bellBtn);
      
      //종료 버튼 --------------------------------------------------------------
      exitBtn = new JButton(new ImageIcon("image/exit.png"));
      exitBtn.setBounds(430,20,80,80);
      exitBtn.setContentAreaFilled(false);
      exitBtn.setFocusPainted(false);
      exitBtn.setBorderPainted(false);
      exitBtn.addActionListener(new ClickHandler());
      exitBtn.addActionListener(e->{
          int res = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION,
                  JOptionPane.WARNING_MESSAGE);
      if(res == JOptionPane.YES_OPTION) {
        ChangePanelService.getInstance().changePanel("MainView");}
        
      });
      
      east.add(exitBtn);
      
      //일시정지 버튼 --------------------------------------------------------------
      pauseBtn = new JButton(new ImageIcon("image/pause.png"));
      pauseBtn.setBounds(430,100,80,80);
      pauseBtn.setContentAreaFilled(false);
      pauseBtn.setFocusPainted(false);
      pauseBtn.setBorderPainted(false);
      pauseBtn.addActionListener(new ClickHandler());
      
      east.add(pauseBtn);
      
      //키보드 아이콘 --------------------------------------------------------------
      //q
      qKey = new JButton(new ImageIcon("image/red(q).png"));
      qKey.setBounds(65,550,75,75);
      qKey.setContentAreaFilled(false);
      qKey.setFocusPainted(false);
      qKey.setBorderPainted(false);
      //qKey.addKeyListener(new KeyHandler());//listener
      east.add(qKey);
      //w
      wKey = new JButton(new ImageIcon("image/yellow(w).png"));
      wKey.setBounds(150,550,75,75);
      wKey.setContentAreaFilled(false);
      wKey.setFocusPainted(false);
      wKey.setBorderPainted(false);
      east.add(wKey);
      //e
      eKey = new JButton(new ImageIcon("image/green(e).png"));
      eKey.setBounds(235,550,75,75);
      eKey.setContentAreaFilled(false);
      eKey.setFocusPainted(false);
      eKey.setBorderPainted(false);
      east.add(eKey);
      //a
      aKey = new JButton(new ImageIcon("image/blue(a).png"));
      aKey.setBounds(320,550,75,75);
      aKey.setContentAreaFilled(false);
      aKey.setFocusPainted(false);
      aKey.setBorderPainted(false);
      east.add(aKey);      
      //s
      sKey = new JButton(new ImageIcon("image/black(s).png"));
      sKey.setBounds(405,550,75,75);
      sKey.setContentAreaFilled(false);
      sKey.setFocusPainted(false);
      sKey.setBorderPainted(false);
      east.add(sKey);   
      
      //메인 JPanel에 추가
      gbc.weightx = 0.4;
      make(east,1,0,1,1);
   }
   private void westPaint() {
      //w:817.8 , w/2:408.9, h:714
      
      west = new JPanel();
      west.setLayout(null);
         
      //게임화면(w:787.8, h:470)
      
      JPanel line[] = new JPanel[5];
      for(int i = 0; i < 5; i++)
         line[i] = new JPanel(null);
      //--cup
   
      line[0].setBorder(new LineBorder(Color.gray,2));
      line[0].setBounds(15,185,157,440);
      line[1].setBorder(new LineBorder(Color.gray,2));
      line[1].setBounds(170,185,157,440);
      line[2].setBorder(new LineBorder(Color.gray,2));
      line[2].setBounds(325,185,157,440);
      line[3].setBorder(new LineBorder(Color.gray,2));
      line[3].setBounds(480,185,157,440);
      line[4].setBorder(new LineBorder(Color.gray,2));
      line[4].setBounds(635,185,157,440);
      
      redCup = new JLabel[5][5];
      blackCup = new JLabel[5][5];
      greenCup = new JLabel[5][5];
      blueCup = new JLabel[5][5];
      yellowCup = new JLabel[5][5];
      int y = 30;
      for(int i = 0; i < 5; i++) {
         for(int j = 0; j < 5; j++) {
            redCup[i][j] = new JLabel(new ImageIcon("image/cup(red)_solo.png"));
            blackCup[i][j] = new JLabel(new ImageIcon("image/cup(black)_solo.png"));
            greenCup[i][j] = new JLabel(new ImageIcon("image/cup(green)_solo.png"));
            blueCup[i][j] = new JLabel(new ImageIcon("image/cup(blue)_solo.png"));
             yellowCup[i][j] = new JLabel(new ImageIcon("image/cup(yellow)_solo.png"));
            redCup[i][j].setBounds(13,170 + j*y,130,140);
            blackCup[i][j].setBounds(13,170 + j*y,130,140);
            greenCup[i][j].setBounds(13,170 + j*y,130,140);
            blueCup[i][j].setBounds(13,170 + j*y,130,140);
            yellowCup[i][j].setBounds(13,170 + j*y,130,140);
            redCup[i][j].setVisible(false);
            blackCup[i][j].setVisible(false);
            greenCup[i][j].setVisible(false);
            yellowCup[i][j].setVisible(false);
            blueCup[i][j].setVisible(false);
            line[i].add(redCup[i][j]);
            line[i].add(blackCup[i][j]);
            line[i].add(greenCup[i][j]);
            line[i].add(blueCup[i][j]);
            line[i].add(yellowCup[i][j]);
         }
      }
      
      
      west.add(line[0]);
      west.add(line[1]);
      west.add(line[2]);
      west.add(line[3]);
      west.add(line[4]);
      //--point
      point = new JLabel[5];
      for(int i = 0; i < 5; i++) {
         point[i] = new JLabel(new ImageIcon("image/point.png"));
         int tmp = 157;
         point[i].setBounds(65+i*tmp,620,50,50);
         point[i].setVisible(false);
         west.add(point[i]);
      }
      point[0].setVisible(true);
      
      //남은시간 w:272.6 60 --------------------------------------------------------------
      JPanel timePanel = new JPanel(new GridLayout(2,1));   
      timePanel.setBorder(new LineBorder(Color.gray,1));
      timePanel.setBounds(15,15,252,150);
      //--timePanel의 컴포넌트 시작
      JLabel timeRemaining = new JLabel("남은시간",SwingConstants.CENTER);
      timeRemaining.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 36));
      timePanel.add(timeRemaining);
      JLabel time = new JLabel("00:00",SwingConstants.CENTER);
      time.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 30));
      timePanel.add(time); 
      //--timePanel의 컴포넌트 끝
      west.add(timePanel);
      
      //정답개수 --------------------------------------------------------------
      JPanel correctPanel = new JPanel(new GridLayout(2,1));
      //--correctPanel의 컴포넌트 시작
      JLabel correct = new JLabel("정답개수",SwingConstants.CENTER);
      correct.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 36));
      JLabel correctCnt = new JLabel("0",SwingConstants.CENTER);
      correctCnt.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 30));
      correctPanel.add(correct);
      correctPanel.add(correctCnt);
      //--correctPanel의 컴포넌트 끝
      correctPanel.setBounds(282,15,252,150);
      correctPanel.setBorder(new LineBorder(Color.gray,1));
      west.add(correctPanel);   
      
      //카드덱 --------------------------------------------------------------
      JPanel myCardPanel = new JPanel(null);
      //--myCardPanel의 컴포넌트 시작
      JLabel card1 = new JLabel(new ImageIcon("image/card(back).png"));
      card1.setBounds(549,15,90,150);
      west.add(card1);   
      //--myCardPanel의 컴포넌트 끝
      myCardPanel.setBounds(549,15,252,150);
      
      
            
      gbc.weightx = 0.6;
      make(west,0,0,1,1);
      
   }
   public void playSound(String fileName) { 
      try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileName));
            Clip clip = AudioSystem.getClip();
            clip.stop();
            clip.open(ais);
            clip.start();
        }
        catch (Exception e)
        {
           e.printStackTrace();
        }
    }
   public int resultY(int y) {
      
      if(y == 0) return 290;
      else if(y == 1) return 260;
      else if(y == 2) return 230;
      else if(y == 3) return 200;
      else return 170;
   }

}