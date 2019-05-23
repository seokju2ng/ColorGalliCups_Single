package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;

import view.etc.Board;
import view.etc.Cards;
import view.etc.ChangePanelService;
import view.etc.KeyImage;
import view.etc.RoundedPanel;
import view.etc.Sound;
import view.etc.Time1;
import view.handler.ExitBtnHandler;
import view.handler.FocusBtnHandler;
import view.handler.FocusHandler;
import view.handler.MouseBtnHandler;

public class SinglePlayMode extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	private GridBagLayout gbl;
	// Panel
	private JPanel east;
	private JPanel west;
	private JButton pauseBackground;
	// 5개의 키 버튼
	private JLabel[] controllKey;
	// ***********************************************************
	private int[] colorFlag;//
	private int gamePanelIndex;
	private int gamePanelY;
	private boolean spaceFlag;
	// 컵
	private Board board;
	// 포인트
	private JLabel[] point;

	// ***********************************************************
	// 버튼
	private JLabel bellBtn;
	private JButton exitBtn;
	private JButton pauseBtn;
	// ***********시간을 나타내는 패널(Edit by DK KIM)
	private Time1 timePanel;

	// *******************************************************
	// edit by DK Kim, first
	private JLabel correctCnt; // west에 있던거 멤버로 뺌.
	private Timer tm = new Timer(1, this);
	private int initY = 31, velY = 10; // 초기 카드위치와 카드가 움직이는 속도
	private boolean flag; // 사용자의 정답여부
	private int y; // 사용자의 카드 좌표
	int newY = -200;
	private int cnt; // 사용자의 정답개수
	private Cards systemCardDeck;
	private ArrayList<JLabel> one_Deck;
	private int cardCnt; // 현재 시스템 카드덱의 카드가 몇번째 카드인지

	// *************여기까지 에니메이션**********************////////

	public SinglePlayMode() {
		tm.start(); // Timer를 가장 먼저 실행시켜준다. (0522)
		this.setFocusTraversalKeysEnabled(false);
		this.addComponentListener(new FocusHandler());
		// 카드를 가장 먼저 붙음 Edit by DK KIM//
		systemCardDeck = new Cards();
		one_Deck = new ArrayList<>();
		////////////////////////////////////

		colorFlag = new int[5];
		spaceFlag = false;
		for (int i = 0; i < 5; i++) {
			colorFlag[i] = 0;
		}
		gamePanelIndex = 0;
		gamePanelY = 0;

		gbl = new GridBagLayout();
		// GridBagLayout에 필요한 GridBagConstraints 생성
		gbc = new GridBagConstraints();
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		paint();

		// Handler
		this.setFocusable(true);
		addKeyListener(new KeyHandler());// listener
		addKeyListener(new Key1pHandler(controllKey));
		MouseBtnHandler mbh = new MouseBtnHandler(exitBtn, pauseBtn);
		exitBtn.addMouseListener(mbh);
		exitBtn.addActionListener(new FocusBtnHandler(this));
		pauseBtn.addMouseListener(mbh);
		pauseBackground.addActionListener(new ClickHandler());
		pauseBackground.addActionListener(new FocusBtnHandler(this));
		// this.setSize(1363, 714);
		// this.setVisible(true);

	}

	private void make(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		// gbl.setConstraints(c,gbc);
		add(c, gbc);
	}

	private void paint() {
		pauseBackground = new JButton(new ImageIcon("image/pauseBackground.png"));
		pauseBackground.setBounds(0, 0, 1363, 714);
		pauseBackground.setContentAreaFilled(false);
		pauseBackground.setFocusPainted(false);
		pauseBackground.setBorderPainted(false);
		pauseBackground.setVisible(false);
		add(pauseBackground);
		westPaint();
		eastPaint();
	}

	private void eastPaint() {
		// w:545.2, w/2:272.6,
		// 컴포넌트 생성 --------------------------------------------------------------
		east = new JPanel();
		east.setLayout(null);

		// 카드 덱 -----------
		JPanel cardDeck = new RoundedPanel(null, 120, Color.WHITE);
		for (int i = 0; i < systemCardDeck.card_arr.size(); i++) {
			systemCardDeck.card_arr.get(i).setBounds(73, 31, 154, 238);
			cardDeck.add(systemCardDeck.card_arr.get(i));

		}
		cardDeck.setBounds(50, 20, 300, 300);

		east.add(cardDeck);

		// 종 버튼 --------------------------------------------------------------
		bellBtn = new JLabel(new ImageIcon("image/bell.png"));
		bellBtn.setBounds(192, 380, 165, 140);
		east.add(bellBtn);

		// 종료 버튼 --------------------------------------------------------------
		exitBtn = new JButton(new ImageIcon("image/exit.png"));
		exitBtn.setBounds(430, 20, 80, 80);
		exitBtn.setContentAreaFilled(false);
		exitBtn.setFocusPainted(false);
		exitBtn.setBorderPainted(false);
		exitBtn.addActionListener(new ClickHandler());
		exitBtn.addActionListener(new ExitBtnHandler(this, this.tm, this.timePanel.getTimer()));
		east.add(exitBtn);

		// 일시정지 버튼 --------------------------------------------------------------
		pauseBtn = new JButton(new ImageIcon("image/pause.png"));
		pauseBtn.setBounds(430, 100, 80, 80);
		pauseBtn.setContentAreaFilled(false);
		pauseBtn.setFocusPainted(false);
		pauseBtn.setBorderPainted(false);
		pauseBtn.addActionListener(new ClickHandler());

		east.add(pauseBtn);

		// 키보드 아이콘 --------------------------------------------------------------
		controllKey = KeyImage.getKey("1P", 75, 75);

		for (int i = 0; i < 5; i++) {
			controllKey[i].setBounds(65 + i * 85, 550, 75, 75);
			controllKey[i + 5].setBounds(65 + i * 85, 550, 75, 75);
			east.add(controllKey[i]);
			east.add(controllKey[i + 5]);
		}

		gbc.weightx = 0.4;
		make(east, 1, 0, 1, 1);
	}

	private void westPaint() {
		// w:817.8 , w/2:408.9, h:714

		west = new JPanel();
		west.setLayout(null);

		// 게임화면(w:787.8, h:470)
		board = new Board(5, 5);
		board.setBounds(15, 185, 785, 440);
		board.setBackground(Color.white);

		board.setCupsBounds(13, 170, 130, 140, 30);
		board.setCupsImg(0, "image/cup(red)_solo.png");
		board.setCupsImg(1, "image/cup(yellow)_solo.png");
		board.setCupsImg(2, "image/cup(green)_solo.png");
		board.setCupsImg(3, "image/cup(blue)_solo.png");
		board.setCupsImg(4, "image/cup(black)_solo.png");
		board.setCups();
		west.add(board);
		// --point
		point = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			point[i] = new JLabel(new ImageIcon("image/point.png"));
			int tmp = 157;
			point[i].setBounds(65 + i * tmp, 620, 50, 50);
			point[i].setVisible(false);
			west.add(point[i]);
		}
		point[0].setVisible(true);

		// --------------------------------------------------------------
		timePanel = new Time1(30, 15, 15, 252, 150);
		timePanel.setBorder(new LineBorder(Color.gray, 1));
		west.add(timePanel);

		// 정답개수 --------------------------------------------------------------
		JPanel correctPanel = new JPanel(new GridLayout(2, 1));
		// --correctPanel의 컴포넌트 시작
		JLabel correct = new JLabel("정답개수", SwingConstants.CENTER);
		correct.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 36));
		correctCnt = new JLabel("0", SwingConstants.CENTER);
		correctCnt.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 30));
		correctPanel.add(correct);
		correctPanel.add(correctCnt);
		// --correctPanel의 컴포넌트 끝
		correctPanel.setBounds(282, 15, 252, 150);
		correctPanel.setBorder(new LineBorder(Color.gray, 1));
		west.add(correctPanel);

		// 카드덱 --------------------------------------------------------------
		JPanel myCardPanel = new JPanel(null);
		myCardPanel.setBorder(new LineBorder(Color.red, 5));
		myCardPanel.setBounds(549, 15, 252, 150);

		gbc.weightx = 0.6;
		make(west, 0, 0, 1, 1);

	}

	private class ClickHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(pauseBtn)) {
				pauseBackground.setVisible(true);
				pauseBtn.requestFocusInWindow();
				timePanel.getTimer().stop();
			} else if (e.getSource().equals(pauseBackground)) {
				pauseBackground.setVisible(false);
				SinglePlayMode.this.requestFocusInWindow();
				timePanel.getTimer().start();
			}
		}
	}

	private class KeyHandler extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				spaceFlag = false;
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag[i] > 0) {
						setCupFlag++;
					}
				}
				// (gamePanelIndex >= 4 &&setCupFlag == 5) ||
				if (setCupFlag == 5 && spaceFlag == false) {
					bellBtn.setIcon(new ImageIcon("image/bell(push).png"));
					Sound.playSound("audio/bell.wav");
					flag = true;
					y = initY;
					tm.start();
				}

			}

		}

		@Override
		// q:r, w:y, e:g, a:blue, s:black
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				if (colorFlag[0] == 0) {
					colorFlag[0] = gamePanelIndex + 1;
					board.getCups(0, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				if (colorFlag[1] == 0) {
					colorFlag[1] = gamePanelIndex + 1;
					board.getCups(1, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				if (colorFlag[2] == 0) {
					colorFlag[2] = gamePanelIndex + 1;
					board.getCups(2, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				if (colorFlag[3] == 0) {
					colorFlag[3] = gamePanelIndex + 1;
					board.getCups(3, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				if (colorFlag[4] == 0) {
					colorFlag[4] = gamePanelIndex + 1;
					board.getCups(4, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				bellBtn.setIcon(new ImageIcon("image/bell.png"));
				if (spaceFlag == true) {
					for (int i = 0; i < 5; i++) {
						colorFlag[i] = 0;
						point[i].setVisible(false);
						for (int j = 0; j < 5; j++) {
							board.getCups(0, i, j).setVisible(false);
							board.getCups(1, i, j).setVisible(false);
							board.getCups(2, i, j).setVisible(false);
							board.getCups(3, i, j).setVisible(false);
							board.getCups(4, i, j).setVisible(false);
						}
					}
					gamePanelIndex = 0;
					gamePanelY = 0;
					point[0].setVisible(true);
					return;
				}
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag[i] > 0) {
						setCupFlag++;
					}
				}
				if (setCupFlag < 5) {
					boolean isEmptyPanel = true;
					for (int i = 0; i < 5; i++) {
						if (colorFlag[i] == gamePanelIndex + 1) {
							isEmptyPanel = false;
						}
					}
					if (gamePanelIndex < 4 && isEmptyPanel == false) {
						gamePanelIndex++;
						gamePanelY = 0;
						if (gamePanelIndex < 5) {
							for (int i = 0; i < 5; i++)
								point[i].setVisible(false);
							point[gamePanelIndex].setVisible(true);
						}

					}
				}
				spaceFlag = true;
			}

		}

	}

	public void actionPerformed(ActionEvent e) {
		// 카드갯수가 시스템 카드덱의 카드개수보다 작을때만 실행.
		if (cardCnt < systemCardDeck.card_arr.size()) {
			if (flag == true) {
				if (y > -300) {
					systemCardDeck.card_arr.get(cardCnt).setBounds(73, y, 154, 238);
					y = y - velY;
				} else if (y < -300) {
					System.out.println("뭐가");

					ImageIcon icon = (ImageIcon) systemCardDeck.card_arr.get(cardCnt).getIcon();
					one_Deck.add(new JLabel(KeyImage.resizeIcon(icon, 90, 140)));
					one_Deck.get(cnt).setBounds(549 + cnt * 30, 15, 90, 150);
					west.setBorder(new LineBorder(Color.gray, 0));
					for (int i = one_Deck.size() - 1; i >= 0; i--) {
						west.add(one_Deck.get(i));
					}

					cnt++;
					flag = false;
					cardCnt++;
					correctCnt.setText("" + cnt);
				}
			}
		}
		// System.out.println(timePanel.getTimer().isRunning());
		// 시간이 다되면 JDialog띄우고 종료
		if (!timePanel.getTimer().isRunning()) {
			tm.stop();
			JOptionPane.showMessageDialog(null, cnt + "개 맞춤", "게임 종료", JOptionPane.CANCEL_OPTION);
			ChangePanelService.getInstance().changePanel("MainView", SinglePlayMode.this);

		}
	}
}
