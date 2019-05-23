package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import view.etc.Board;
import view.etc.Cards;
import view.etc.ChangePanelService;
import view.etc.HandVanish;
import view.etc.KeyImage;
import view.etc.RoundedPanel;
import view.etc.Sound;
import view.etc.Time2;
import view.handler.ExitBtnHandler;
import view.handler.FocusBtnHandler;
import view.handler.FocusHandler;
import view.handler.MouseBtnHandler;

public class DualPlayMode extends JPanel implements ActionListener {
	public static final int WIDTH = 1363, HEIGHT = 714;

	// ********************************************************
	// edit by minseongChoi
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

	private JPanel cardPanel; // 카드번호를 나타내기 위한 패널
	private Time2 timePanel;

	private JLabel cardNum; // 카드번호를 나타내기 위함
	private JPanel cardDeck; // 카드가 올라갈 패널
	private JButton bell; // 종

	private JButton pause;
	private JButton exit;

	private ArrayList<JLabel> one_Deck; // 1p player가 맞춘 카드개수.
	private ArrayList<JLabel> two_Deck; // 2p player가 맞춘 카드개수.
	// private JLabel card; // 중앙 덱에 올라갈 카드

	private JLabel[] one_buttons; // 1p전용 키 버튼
	private JLabel[] two_buttons; // 2p전용 키 버튼

	private JButton pauseBackground;
	//////////////////////////////// 준희가 수정 //////////////////
	private JLabel[][] hands; // 손 이미지 저장하는 라벨
	private ImageIcon[] img; // 손 이미지
	private int index = 3; // 손쌓는 층 나타내는 index 0,1층만 있다
	private boolean[] handCheck; // 한사람이 연속으로 벨 못누른다 flag 역할
	//////////////////////////////// 준희가 수정 끝//////////////////

	// 애니메이션//
	private Timer tm = new Timer(10, this);
	private int initX = 604, velX = 5; // 초기 카드위치와 카드가 움직이는 속도
	private boolean one_flag = false; // 1p의 정답여부
	private boolean two_flag = false; // 2p의 정답여부
	private int one_x, two_x; // 1p, 2p의 카드좌표
	private int one_cnt, two_cnt; // 1p,2p의 정답개수
	private Cards systemCardDeck;
	private int cardCnt; // 현재 시스템 카드덱의 카드가 몇번째 카드인지.
	//////

	// 여기서부터 카드 애니메이션 0518//

	//////////////////////// 여기까지 카드 에니메이션/////////////

	public DualPlayMode() {
		this.setFocusTraversalKeysEnabled(false);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.addComponentListener(new FocusHandler());
		this.addKeyListener(new KeyHandler());
		////////////////////////////// 준희가 수정 //////////////////
		// JPanel panel = new JPanel();
		img = new ImageIcon[] { null, null, new ImageIcon("image/hand(2p)left.png"),
				new ImageIcon("image/hand(2p).png") };
		handCheck = new boolean[] { false, false, false, false };

		JLabel[] hand0 = new JLabel[] { null, null, new JLabel(img[2]), new JLabel(img[3]) };
		JLabel[] hand1 = new JLabel[] { null, null, new JLabel(img[2]), new JLabel(img[3]) };
		hands = new JLabel[][] { null, null, hand0, hand1 };
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
		pauseBackground.addActionListener(new FocusBtnHandler(this));
		this.add(pauseBackground);

		// 카드를 가장 먼저 붙임)(0518애니메이션)
		systemCardDeck = new Cards();

		for (int i = 0; i < systemCardDeck.card_arr.size(); i++) {
			systemCardDeck.card_arr.get(i).setBounds(604, 41, 154, 238);
			this.add(systemCardDeck.card_arr.get(i));
		}
		// this.add(card);

		showCenter();
		showWest();
		// showCenter();
		showEast();
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
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
		board1 = new Board(5, 5); // 5열짜리 보드를 만듦.
		board1.setBounds(45, 350, 400, 240);
		board1.setBackground(Color.white);
		// ********************************************************
		// edit by minseongChoi, first
		board1.setCupsImg(0, "image/cup(red)_dual.png");
		board1.setCupsImg(1, "image/cup(yellow)_dual.png");
		board1.setCupsImg(2, "image/cup(green)_dual.png");
		board1.setCupsImg(3, "image/cup(blue)_dual.png");
		board1.setCupsImg(4, "image/cup(black)_dual.png");
		board1.setCupsBounds(5, 100, 70, 80, 15);
		board1.setCups();

		// end
		// **************************************************************************
		///////////////////////////////

		JLabel cardText = new JLabel("카드번호", SwingConstants.CENTER); // '카드번호'를 넣을 라벨
		cardText.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
		this.p1 = new JPanel(); // 왼쪽 JPanel 생성
		p1.setBackground(Color.white);
		p1.setLayout(null);
		// p1.setBorder(new LineBorder(Color.red, 5));
		p1.setBounds(50, 0, width, height);
		timePanel = new Time2(50, 20, 180, 100);
		timePanel.setBorder(new LineBorder(Color.BLACK));
		cardPanel = new JPanel(); // '카드번호'라벨과 실제 카드번호를 붙일 패널
		cardPanel.setBorder(new LineBorder(Color.BLACK));
		timePanel.setBackground(Color.white);
		cardPanel.setBackground(Color.white);
		cardPanel.setLayout(null);

		cardPanel.setBounds(270, 20, 180, 100);

		cardNum = new JLabel(cardCnt + "/" + systemCardDeck.card_arr.size(), SwingConstants.CENTER);
		cardNum.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
		// timer.setBorder(new LineBorder(Color.red, 5));
		// cardNum.setBorder(new LineBorder(Color.red, 5));

		cardText.setBounds(0, 0, 180, 50);

		cardNum.setBounds(0, 50, 180, 50);

		cardPanel.add(cardText);
		cardPanel.add(cardNum);

		p1.add(timePanel);
		p1.add(cardPanel);
		p1.add(board1);
		for (int i = 0; i < pointerOne.length; i++) {
			p1.add(pointerOne[i]);
		}
		cardPanel.add(cardNum);
		// 1p 덱에 카드추가(카드얻었을시)//
		one_Deck = new ArrayList<>();

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
		board2 = new Board(5, 5); // 5열짜리 보드를 만듦.
		board2.setBounds(45, 350, 400, 240);
		board2.setBackground(Color.white);

		// ********************************************************
		// edit by minseongChoi, first
		board2.setCupsImg(0, "image/cup(red)_dual.png");
		board2.setCupsImg(1, "image/cup(yellow)_dual.png");
		board2.setCupsImg(2, "image/cup(green)_dual.png");
		board2.setCupsImg(3, "image/cup(blue)_dual.png");
		board2.setCupsImg(4, "image/cup(black)_dual.png");
		board2.setCupsBounds(5, 100, 70, 80, 15);
		board2.setCups();
		// end
		// **************************************************************************
		///////////////////////////

		this.p3 = new JPanel();
		p3.setBackground(Color.white);
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
		MouseBtnHandler mbh = new MouseBtnHandler(exit, pause);
		pause.addMouseListener(mbh);

		exit.addMouseListener(mbh);
		exit.addActionListener(new FocusBtnHandler(this));
		exit.addActionListener(new ExitBtnHandler(this,this.tm,this.timePanel.getTimer()));
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

		for (int i = 2; i < 4; i++) {
			for (int j = 2; j < 4; j++) {
				// panel.add(hands[i][j]);
				p2.add(hands[i][j]);
				hands[i][j].setVisible(false);
			}
		}

		for (int i = 2; i < 4; i++) {
			hands[i][2].setBounds(-70, 300, 300, 180);
			hands[i][3].setBounds(135, 295, 300, 180);
			////////// 여기 위치 변경해야 함
		}

		p2.setBackground(Color.white);
		// p2.setBackground(Color.WHITE);
		p2.setLayout(null); // 가운데 패널 레이아웃을 제거
		p2.setBounds(500, 3, 360, 680);

		this.add(p2);
		this.cardDeck = new RoundedPanel(null, 120, Color.WHITE);
		cardDeck.setBackground(Color.white);

		ImageIcon img1 = new ImageIcon("image/dotdanbae2.png");
		ImageIcon img2 = new ImageIcon("image/bell.png");

		this.bell = new JButton(img2);
		bell.setBounds(90, 380, 190, 150);
		bell.setContentAreaFilled(false);
		bell.setFocusPainted(false);
		bell.setBorderPainted(false);
		bell.addActionListener(new FocusBtnHandler(this));
		// bell.setOpaque(false);

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
		one_buttons = KeyImage.getKey("1P", 40, 40);
		two_buttons = KeyImage.getKey("2P", 40, 40);

		for (int i = 0; i < one_buttons.length / 2; i++) {
			if (i < 3) {
				one_buttons[i].setBounds(20 + i * 45, 550, 40, 40);
				one_buttons[i + 5].setBounds(20 + i * 45, 550, 40, 40);
			} else {
				one_buttons[i].setBounds(i * 45 - 100, 600, 40, 40);
				one_buttons[i + 5].setBounds(i * 45 - 100, 600, 40, 40);
			}

			p2.add(one_buttons[i]);
			p2.add(one_buttons[i + 5]);
		}

		for (int i = 0; i < two_buttons.length / 2; i++) {
			if (i < 3) {
				two_buttons[i].setBounds(210 + i * 45, 550, 40, 40);
				two_buttons[i + 5].setBounds(210 + i * 45, 550, 40, 40);
			} else {
				two_buttons[i].setBounds(i * 45 + 90, 600, 40, 40);
				two_buttons[i + 5].setBounds(i * 45 + 90, 600, 40, 40);
			}

			p2.add(two_buttons[i]);
			p2.add(two_buttons[i + 5]);
		}
		addKeyListener(new Key1pHandler(one_buttons));
		addKeyListener(new Key2pHandler(two_buttons));
	}

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
				ImageIcon icon = (ImageIcon) systemCardDeck.card_arr.get(cardCnt).getIcon(); // 얻은 카드의 이미지를 따온다.
				this.remove(systemCardDeck.card_arr.get(cardCnt)); // 카드를 지우고
				this.revalidate(); // 부모컨테이너를 새로고침한다
				this.repaint(); // 새로고침.

				one_Deck.add(new JLabel(KeyImage.resizeIcon(icon, 90, 140))); // 1p 사용자 카드덱에 카드추가
				one_Deck.get(one_cnt).setBounds(50 + one_cnt * 10, 190, 90, 140); // 좌표는 나중에 수정
				for (int i = one_Deck.size() - 1; i >= 0; i--) {
					p1.add(one_Deck.get(i));
				}
				one_cnt++; // 1p정답갯수를 늘려준다.
				one_flag = false;
				System.out.println("된다! one_cnt: " + one_cnt + ", arraylSize : " + one_Deck.size());
				cardCnt++; // 시스템카드덱을 +1한다.
				cardNum.setText(cardCnt + "/" + systemCardDeck.card_arr.size());
			}
		} else if (two_flag == true) {
			if (two_x < 1100) {
				systemCardDeck.card_arr.get(cardCnt).setBounds(two_x, two_x / 7 - 45, 154, 238); // 원래
				two_x = two_x + velX;

			} else if (two_x > 1100) {
				// try {
				// Thread.sleep(1000); // 카드가 1초후 사라진다.
				// } catch (InterruptedException e1) {
				ImageIcon icon = (ImageIcon) systemCardDeck.card_arr.get(cardCnt).getIcon(); // 얻은 카드의 이미지를 따온다.
				this.remove(systemCardDeck.card_arr.get(cardCnt)); // 카드를 지우고
				this.revalidate(); // 부모컨테이너를 새로고침한다
				this.repaint(); // 새로고침.

				two_Deck.add(new JLabel(KeyImage.resizeIcon(icon, 90, 140))); // 2p 사용자 카드덱에 카드추가
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
		if (cardCnt == systemCardDeck.card_arr.size()) {
			timePanel.getTimer().stop();
			tm.stop();
			JOptionPane.showMessageDialog(null, "xx승리", "게임 종료", JOptionPane.CANCEL_OPTION);
			ChangePanelService.getInstance().changePanel("MainView", DualPlayMode.this);
		}
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(pause)) {

				p2.setVisible(false);
				pauseBackground.setVisible(true);
				pause.setVisible(false);
				exit.setVisible(false);
				for (int i = cardCnt; i < 38; i++)
					systemCardDeck.card_arr.get(i).setVisible(false);
				pause.requestFocusInWindow();
				timePanel.getTimer().stop();

			} else if (e.getSource().equals(pauseBackground)) {
				System.out.println("눌림");
				p2.setVisible(true);
				pauseBackground.setVisible(false);
				pause.setVisible(true);
				exit.setVisible(true);
				for (int i = cardCnt; i < 38; i++)
					systemCardDeck.card_arr.get(i).setVisible(true);
				DualPlayMode.this.requestFocusInWindow();
				timePanel.getTimer().start();

			}
		}
	}

	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				spaceFlag1p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				spaceFlag1p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				spaceFlag1p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				spaceFlag1p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				spaceFlag1p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_I) {
				spaceFlag2p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				spaceFlag2p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				spaceFlag2p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_K) {
				spaceFlag2p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_L) {
				spaceFlag2p = false;
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag2p[i] > 0) {
						setCupFlag++;
					}
				}
				if (setCupFlag == 5 && spaceFlag2p == false) {
					/////////////////////////// 손 나오는 부분 ///////////////////
					if (index == 1 || handCheck[3] == true) {
						return;
					}
					bell.setIcon(new ImageIcon("image/bell(push2).png"));
					Sound.playSound("audio/bell.wav");
					new HandVanish(3, hands, handCheck).start();
					/////////////////////////// 손 나오는 부분 끝/////////////////
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
					/////////////////////////// 손 나오는 부분 ///////////////////
					if (index == 1 || handCheck[2] == true) {
						return;
					}
					bell.setIcon(new ImageIcon("image/bell(push).png"));
					Sound.playSound("audio/bell.wav");
					new HandVanish(2, hands, handCheck).start();
					// bell.setIcon(new ImageIcon("image/bell.png"));

					/////////////////////////// 손 나오는 부분 끝/////////////////
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
				if (colorFlag1p[0] == 0) {
					colorFlag1p[0] = gamePanelIndex1p + 1;
					// System.out.println(redCup1p+"," + gamePanelIndex1p +"," + gamePanelY1p);
					board1.getCups(0, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);

					if (gamePanelY1p < 4)
						gamePanelY1p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				if (colorFlag1p[1] == 0) {
					colorFlag1p[1] = gamePanelIndex1p + 1;
					board1.getCups(1, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				if (colorFlag1p[2] == 0) {
					colorFlag1p[2] = gamePanelIndex1p + 1;
					board1.getCups(2, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				if (colorFlag1p[3] == 0) {
					colorFlag1p[3] = gamePanelIndex1p + 1;
					board1.getCups(3, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				if (colorFlag1p[4] == 0) {
					colorFlag1p[4] = gamePanelIndex1p + 1;
					board1.getCups(4, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
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
							board1.getCups(0, i, j).setVisible(false);
							board1.getCups(1, i, j).setVisible(false);
							board1.getCups(2, i, j).setVisible(false);
							board1.getCups(3, i, j).setVisible(false);
							board1.getCups(4, i, j).setVisible(false);
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
				if (colorFlag2p[0] == 0) {
					colorFlag2p[0] = gamePanelIndex2p + 1;
					board2.getCups(0, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				if (colorFlag2p[1] == 0) {
					colorFlag2p[1] = gamePanelIndex2p + 1;
					board2.getCups(1, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				if (colorFlag2p[2] == 0) {
					colorFlag2p[2] = gamePanelIndex2p + 1;
					board2.getCups(2, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_K) {
				if (colorFlag2p[3] == 0) {
					colorFlag2p[3] = gamePanelIndex2p + 1;
					board2.getCups(3, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_L) {
				if (colorFlag2p[4] == 0) {
					colorFlag2p[4] = gamePanelIndex2p + 1;
					board2.getCups(4, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
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
							board2.getCups(0, i, j).setVisible(false);
							board2.getCups(1, i, j).setVisible(false);
							board2.getCups(2, i, j).setVisible(false);
							board2.getCups(3, i, j).setVisible(false);
							board2.getCups(4, i, j).setVisible(false);
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

}
