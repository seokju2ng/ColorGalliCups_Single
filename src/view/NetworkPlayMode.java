package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import etc.ChangePanelService;
import etc.RoundedPanel;

public class NetworkPlayMode extends JPanel {
	private JPanel panel;
	private JPanel[] everyPanelArr;
	private JPanel[] panelArr;
	private JLabel[] userId;
	private JButton[] keyBoard;
	private JLabel bell;
	private int pointIndex = 0;
	private JLabel[] currentPointArr;
	private JButton exitButton;
	private JButton pauseButton;
	private JButton pauseBackground;
	//***********************************************************
	//choims
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
	   
	   private JPanel mainPanel;
	   private JPanel[] mainPanelArr;
	  //**************************************************
	/////////////////////// 추가 ////////////////////
	private JLabel[][] hands;
	private ImageIcon[] img;
	private int index = 3;
	private boolean[] handCheck;

	class HandVanish0 extends Thread {
		public void run() {
			int zero = index;
			hands[index--][0].setVisible(true);
			handCheck[0] = true;
			try {
				System.out.println("0번 1초 휴식중..");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hands[zero][0].setVisible(false);
			index++;
			handCheck[0] = false;
			System.out.println("0번 끝");
		}
	}
	class HandVanish1 extends Thread {
		public void run() {
			int one = index;
			hands[index--][1].setVisible(true);
			handCheck[1] = true;
			try {
				System.out.println("1번 1초 휴식중..");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hands[one][1].setVisible(false);
			index++;
			handCheck[1] = false;
			System.out.println("1번 끝");
		}
	}
	class HandVanish2 extends Thread {
		public void run() {
			int two = index;
			hands[index--][2].setVisible(true);
			handCheck[2] = true;
			try {
				System.out.println("2번 1초 휴식중..");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hands[two][2].setVisible(false);
			index++;
			handCheck[2] = false;
			System.out.println("2번 끝");
		}
	}
	class HandVanish3 extends Thread {
		public void run() {
			int three = index;
			hands[index--][3].setVisible(true);
			handCheck[3] = true;
			try {
				System.out.println("3번 1초 휴식중..");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hands[three][3].setVisible(false);
			index++;
			handCheck[3] = false;
			System.out.println("3번 끝");
		}
	}
	
	
	
	public NetworkPlayMode() {
		panel = new JPanel();
		
		
		
		//**********************************
		//cms,start
		colorFlag = new int[5];
		  spaceFlag = false;
		  for(int i = 0; i < 5; i++) {
			  colorFlag[i] = 0;
		  }
		  gamePanelIndex = 0;
		  gamePanelY = 0;
		//cms,end
		//**********************************
		  
		  
		/////////////////////// 추가 ////////////////////
		img = new ImageIcon[] { new ImageIcon("image/hand0.png"), new ImageIcon("image/hand1.png"),
				new ImageIcon("image/hand2.png"), new ImageIcon("image/hand3.png") };
		handCheck = new boolean[] { false, false, false, false };

		JLabel[] hand0 = new JLabel[] { new JLabel(img[0]), new JLabel(img[1]), new JLabel(img[2]),
				new JLabel(img[3]) };
		JLabel[] hand1 = new JLabel[] { new JLabel(img[0]), new JLabel(img[1]), new JLabel(img[2]),
				new JLabel(img[3]) };
		JLabel[] hand2 = new JLabel[] { new JLabel(img[0]), new JLabel(img[1]), new JLabel(img[2]),
				new JLabel(img[3]) };
		JLabel[] hand3 = new JLabel[] { new JLabel(img[0]), new JLabel(img[1]), new JLabel(img[2]),
				new JLabel(img[3]) };
		hands = new JLabel[][] { hand0, hand1, hand2, hand3 };

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				panel.add(hands[i][j]);
				hands[i][j].setVisible(false);
			}
		}

		for (int i = 0; i < 4; i++) {
			hands[i][0].setBounds(650, 180, 300, 180);
			hands[i][1].setBounds(500, 60, 300, 300);
			hands[i][2].setBounds(440, 170, 300, 200);
			hands[i][3].setBounds(460, 200, 300, 350);
		}
		/////////////////////// 추가 끝 ////////////////////

		// super("ColorGalli Cups");
		setLayout(new BorderLayout());
		panelArr = new JPanel[3];
		everyPanelArr = new JPanel[15];
		Font font = new Font("배달의민족 한나는 열한살", Font.PLAIN, 25);
		LineBorder lineBorder = new LineBorder(Color.BLACK);

		pauseBackground = new JButton(new ImageIcon("Image/pauseBackground.png"));
		pauseBackground.setBounds(0, 0, 1363, 714);
		pauseBackground.setContentAreaFilled(false);
		pauseBackground.setFocusPainted(false);
		pauseBackground.setBorderPainted(false);
		pauseBackground.setVisible(false);
		pauseBackground.addActionListener(new ButtonHandler());
		panel.add(pauseBackground);

		panel.setBorder(new LineBorder(Color.BLACK));
		panel.setBackground(Color.white);

		panelArr[0] = new JPanel(new GridLayout(0, 5));
		panelArr[1] = new JPanel(new GridLayout(0, 5));
		panelArr[2] = new JPanel(new GridLayout(0, 5));

		for (int i = 0; i < 5; i++) {
			everyPanelArr[3 * i] = new JPanel();
			everyPanelArr[3 * i].setBorder(lineBorder);
			everyPanelArr[3 * i].setBackground(Color.WHITE);
			panelArr[0].add(everyPanelArr[3 * i]);

			everyPanelArr[3 * i + 1] = new JPanel();
			everyPanelArr[3 * i + 1].setBorder(lineBorder);
			everyPanelArr[3 * i + 1].setBackground(Color.WHITE);
			panelArr[1].add(everyPanelArr[3 * i + 1]);

			everyPanelArr[3 * i + 2] = new JPanel();
			everyPanelArr[3 * i + 2].setBorder(lineBorder);
			everyPanelArr[3 * i + 2].setBackground(Color.WHITE);
			panelArr[2].add(everyPanelArr[3 * i + 2]);
		}

		userId = new JLabel[3];

		userId[0] = new JLabel("vV민성Vv", SwingConstants.CENTER);
		userId[1] = new JLabel("준희어3세", SwingConstants.CENTER);
		userId[2] = new JLabel("도균했다", SwingConstants.CENTER);

		for (JLabel a : userId) {
			a.setForeground(Color.white);
			a.setOpaque(true);
			a.setBackground(new Color(89, 89, 89));
		}

		for (int i = 0; i < 3; i++) {
			userId[i].setBounds(70, 10 + i * 220, 300, 40);
			userId[i].setBorder(lineBorder);
			userId[i].setFont(font);
			panelArr[i].setBounds(70, 60 + 220 * i, 300, 150);
		}

		panel.add(userId[0]);
		panel.add(panelArr[0]);

		panel.add(userId[1]);
		panel.add(panelArr[1]);

		panel.add(userId[2]);
		panel.add(panelArr[2]);
		// 여기까지 왼쪽 View
		exitButton = new JButton(new ImageIcon("image/exit.png"));
		pauseButton = new JButton(new ImageIcon("image/pause.png"));

		exitButton.setBounds(600, 10, 80, 80);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addKeyListener(new KeyHandler());
		exitButton.addActionListener(new ButtonHandler());
		exitButton.addMouseListener(new MouseHandler());

		pauseButton.setBounds(700, 10, 80, 80);
		pauseButton.setBorderPainted(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setFocusPainted(false);
		pauseButton.addKeyListener(new KeyHandler());
		pauseButton.addActionListener(new ButtonHandler());
		pauseButton.addMouseListener(new MouseHandler());
		
		
		
		panel.add(exitButton);
		panel.add(pauseButton);
		// 여기까지가 전원, 일시정지 버튼

		bell = new JLabel(new ImageIcon("image/bell(Network).png"));
		bell.setBounds(590, 180, 200, 200);
		bell.setBackground(Color.white);

		panel.add(bell);
		// 여기까지가 벨

		RoundedPanel problemCardPanel = new RoundedPanel(null, 120, Color.WHITE);
		problemCardPanel.setBounds(900, 10, 250, 250);
		problemCardPanel.setBackground(Color.white);

		JLabel problemCard = new JLabel(new ImageIcon("image/sampleCard.png"));
		problemCard.setBounds(50, 5, 154, 238);

		problemCardPanel.add(problemCard);
		panel.add(problemCardPanel);
		// 문제그림

		RoundedPanel timePanel = new RoundedPanel(null, 20, Color.white);
		timePanel.setBounds(1170, 20, 170, 100);

		JLabel timeLabel = new JLabel("진행시간", SwingConstants.CENTER);
		timeLabel.setFont(font);
		timeLabel.setBounds(10, 10, 150, 30);
		timePanel.add(timeLabel);

		JLabel time = new JLabel("00:00", SwingConstants.CENTER);
		time.setFont(font);
		time.setBounds(10, 50, 150, 30);
		timePanel.add(time);

		panel.add(timePanel);
		// 진행시간
		RoundedPanel cardNumPanel = new RoundedPanel(null, 20, Color.white);
		cardNumPanel.setBounds(1170, 145, 170, 100);

		JLabel cardNumLabel = new JLabel("카드번호", SwingConstants.CENTER);
		cardNumLabel.setFont(font);
		cardNumLabel.setBounds(10, 10, 150, 30);
		cardNumPanel.add(cardNumLabel);

		JLabel cardNum = new JLabel("08/10", SwingConstants.CENTER);
		cardNum.setFont(font);
		cardNum.setBounds(10, 50, 150, 30);
		cardNumPanel.add(cardNum);

		panel.add(cardNumPanel);
		// 카드번호

		mainPanel = new JPanel(new GridLayout(0, 5));
		mainPanelArr = new JPanel[5];

		for (int i = 0; i < 5; i++) {
			mainPanelArr[i] = new JPanel(null);
			mainPanelArr[i].setBorder(lineBorder);
			mainPanelArr[i].setBackground(Color.WHITE);
			mainPanel.add(mainPanelArr[i]);
		}
		mainPanel.setBounds(900, 350, 420, 270);
		panel.add(mainPanel);
		// 사용자의 게임화면
		currentPointArr = new JLabel[5];

		for (int i = 0; i < 5; i++) {
			currentPointArr[i] = new JLabel(new ImageIcon("image/point.png"));
			currentPointArr[i].setBounds(890 + i * 85, 600, 100, 100);
			currentPointArr[i].setVisible(false);
			panel.add(currentPointArr[i]);
		}
		currentPointArr[0].setVisible(true);
		// Point

		// JLabel[]
		keyBoard = new JButton[] { new JButton(new ImageIcon("image/red(q).png")),
				new JButton(new ImageIcon("image/yellow(w).png")), new JButton(new ImageIcon("image/green(e).png")),
				new JButton(new ImageIcon("image/blue(a).png")), new JButton(new ImageIcon("image/black(s).png")) };
		for(int i = 0; i < 5; i ++) {
			keyBoard[i].addKeyListener(new KeyHandler());
			keyBoard[i].setBorderPainted(false);
			keyBoard[i].setContentAreaFilled(false);
			keyBoard[i].setFocusPainted(false);
			
		}
		for (int i = 0; i < 3; i++) {
			keyBoard[i].setBounds(550 + i * 95, 480, 80, 80);
			panel.add(keyBoard[i]);
		}
		for (int i = 3; i < 5; i++) {
			keyBoard[i].setBounds(600 + 90 * (i - 3), 570, 80, 80);
			panel.add(keyBoard[i]);
		}

		// 색상에 해당하는 키

		JLabel[] abtainCard = new JLabel[4];

		for (int i = 0; i < 4; i++) {
			abtainCard[i] = new JLabel(new ImageIcon("image/abtainCard.png"));
			panel.add(abtainCard[i]);
		}

		abtainCard[0].setBounds(380, 110, 63, 98);
		abtainCard[1].setBounds(380, 330, 63, 98);
		abtainCard[2].setBounds(1245, 250, 63, 98);
		abtainCard[3].setBounds(1250, 250, 63, 98);
		// 자신이 딴 카드 덱
		
		
		
/////////////////////// 추가 끝 ////////////////////
//************************************************
//cms,start
redCup = new JLabel[5][5];
blackCup = new JLabel[5][5];
greenCup = new JLabel[5][5];
blueCup = new JLabel[5][5];
yellowCup = new JLabel[5][5];
for(int i = 0; i < 5; i++) {
for(int j = 0; j < 5; j++) {
	  redCup[i][j] = new JLabel(new ImageIcon("image/cup(red)_dual.png"));
	  blackCup[i][j] = new JLabel(new ImageIcon("image/cup(black)_dual.png"));
	  greenCup[i][j] = new JLabel(new ImageIcon("image/cup(green)_dual.png"));
	  blueCup[i][j] = new JLabel(new ImageIcon("image/cup(blue)_dual.png"));
    yellowCup[i][j] = new JLabel(new ImageIcon("image/cup(yellow)_dual.png"));
	  redCup[i][j].setBounds(7,130 + j*15,70,80);
	  blackCup[i][j].setBounds(7,130 + j*15,70,80);
	  greenCup[i][j].setBounds(7,130 + j*15,70,80);
	  blueCup[i][j].setBounds(7,130 + j*15,70,80);
	  yellowCup[i][j].setBounds(7,130 + j*15,70,80);
	  redCup[i][j].setVisible(false);
	  blackCup[i][j].setVisible(false);
	  greenCup[i][j].setVisible(false);
	  yellowCup[i][j].setVisible(false);
	  blueCup[i][j].setVisible(false);
	  System.out.println(mainPanelArr[i]);
	  mainPanelArr[i].add(redCup[i][j]);
	  mainPanelArr[i].add(blackCup[i][j]);
	  mainPanelArr[i].add(greenCup[i][j]);
	  mainPanelArr[i].add(blueCup[i][j]);
	  mainPanelArr[i].add(yellowCup[i][j]);
}
}
//cms,end
//************************************************
		

		panel.setLayout(null);
		this.addKeyListener(new KeyHandler());
		this.add(panel);
		this.setSize(1363, 714);
		// this.setVisible(true);
		this.setFocusable(true);
		// this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

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

	private class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(exitButton)) {
				int res = JOptionPane.showConfirmDialog(null,
						"         네트워크 대전에서의 게임 종료는\n" + "다른 플레이어들에게 악영향을 끼칠 수 있습니다.\n" + "             게임을 종료하시겠습니까?",
						"게임 종료", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if (res == JOptionPane.YES_OPTION) {
					ChangePanelService.getInstance().changePanel("MainView");
				}
			} else if (e.getSource().equals(pauseButton)) {
				pauseBackground.setVisible(true);
			} else if (e.getSource().equals(pauseBackground)) {
				pauseBackground.setVisible(false);
			}
		}
	}

	private class MouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(exitButton)) {
				exitButton.setIcon(new ImageIcon("image/exit(click).png"));
			} else if (e.getSource().equals(pauseButton)) {
				pauseButton.setIcon(new ImageIcon("image/pause(click).png"));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(exitButton)) {
				exitButton.setIcon(new ImageIcon("image/exit.png"));
			} else if (e.getSource().equals(pauseButton)) {
				pauseButton.setIcon(new ImageIcon("image/pause.png"));
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
			System.out.println(e.getKeyChar());

			if (e.getKeyCode() == KeyEvent.VK_Q) {
				keyBoard[0].setIcon(new ImageIcon("image/red(q)_press.png"));
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				keyBoard[1].setIcon(new ImageIcon("image/yellow(w)_press.png"));
				spaceFlag = false;
			} else if (e.getKeyCode()  == KeyEvent.VK_E) {
				keyBoard[2].setIcon(new ImageIcon("image/green(e)_press.png"));
				spaceFlag = false;
			} else if (e.getKeyCode()  == KeyEvent.VK_A) {
				keyBoard[3].setIcon(new ImageIcon("image/blue(a)_press.png"));
				spaceFlag = false;
			} else if (e.getKeyCode()  == KeyEvent.VK_S) {
				keyBoard[4].setIcon(new ImageIcon("image/black(s)_press.png"));
				spaceFlag = false;
			} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				////////////////////////// 추가 //////////////////////
				 int setCupFlag=0;
	             for(int i = 0; i< 5; i++) {
	             	if(colorFlag[i] > 0) {
	             		setCupFlag++;
	             	}
	             }
	        	 if( setCupFlag == 5 && spaceFlag == false) {
	        		 Thread zero=new HandVanish0();
	 				zero.start();
	             	bell.setIcon(new ImageIcon("image/bell(Network).png"));
	             	playSound("audio/bell.wav");
	             }
				if (index == -1 || handCheck[0] == true) {
					return;
				}
			}
			
			else if (e.getKeyChar() == '1') {
				if (index == -1 || handCheck[1] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				playSound("audio/bell.wav");
				Thread one=new HandVanish1();
				one.start();
			} else if (e.getKeyChar() == '2') {
				if (index == -1 || handCheck[2] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				playSound("audio/bell.wav");
				Thread two=new HandVanish2();
				two.start();
			} else if (e.getKeyChar() == '3') {
				if (index == -1 || handCheck[3] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				playSound("audio/bell.wav");
				Thread three=new HandVanish3();
				three.start();
			}
			/////////////////////// 추가 끝 ////////////////////
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
	         if (e.getKeyCode() == KeyEvent.VK_Q) {
	            keyBoard[0].setIcon(new ImageIcon("image/red(q).png"));
	            if(colorFlag[0] == 0) {
	            	colorFlag[0] = gamePanelIndex+1;
	            	redCup[gamePanelIndex][4-gamePanelY].setVisible(true);
	            	if(gamePanelY < 4)
	            		gamePanelY++;
	            }
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_W) {
	            keyBoard[1].setIcon(new ImageIcon("image/yellow(w).png"));
	            if(colorFlag[1] == 0) {
	            	colorFlag[1] = gamePanelIndex+1;
	            	yellowCup[gamePanelIndex][4-gamePanelY].setVisible(true);
	            	if(gamePanelY < 4)
	            		gamePanelY++;
	            }
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_E) {
	            keyBoard[2].setIcon(new ImageIcon("image/green(e).png"));
	            if(colorFlag[2] == 0) {
	            	colorFlag[2] = gamePanelIndex+1;
	            	greenCup[gamePanelIndex][4-gamePanelY].setVisible(true);
	            	if(gamePanelY < 4)
	            		gamePanelY++;
	            }
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_A) {
	            keyBoard[3].setIcon(new ImageIcon("image/blue(a).png"));
	            if(colorFlag[3] == 0) {
	            	colorFlag[3] = gamePanelIndex+1;
	            	blueCup[gamePanelIndex][4-gamePanelY].setVisible(true);
	            	if(gamePanelY < 4)
	            		gamePanelY++;
	            }
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_S) {
	            keyBoard[4].setIcon(new ImageIcon("image/black(s).png"));
	            if(colorFlag[4] == 0) {
	            	colorFlag[4] = gamePanelIndex+1;
	            	blackCup[gamePanelIndex][4-gamePanelY].setVisible(true);
	            	if(gamePanelY < 4)
	            		gamePanelY++;
	            }
	         }
	         else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	            bell.setIcon(new ImageIcon("image/bell(Network).png"));
	            if(spaceFlag == true) {
	            	for(int i = 0; i < 5; i++) {
	            		colorFlag[i] = 0;
	            		currentPointArr[i].setVisible(false);
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
	            	currentPointArr[0].setVisible(true);
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
	                    		currentPointArr[i].setVisible(false);   
	                    	currentPointArr[gamePanelIndex].setVisible(true);
	                    }
	                    
	                }
	            }  
	           
			else if (e.getKeyChar() == '1') {
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
			}
			else if (e.getKeyChar() == '2') {
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
			}
			else if (e.getKeyChar() == '3') {
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
			}
	        spaceFlag = true;
		}
		}
		}
}
