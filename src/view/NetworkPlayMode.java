package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.etc.Board;
import view.etc.ChangePanelService;
import view.etc.HandVanish;
import view.etc.KeyImage;
import view.etc.RoundedPanel;
import view.etc.Sound;
import view.handler.FocusBtnHandler;
import view.handler.FocusHandler;
import view.handler.MouseBtnHandler;

public class NetworkPlayMode extends JPanel {
	private JPanel panel;
	private JPanel[] everyPanelArr;
	private JPanel[] panelArr;
	private JLabel[] userId;
	private JLabel[] keyBoard;
	private JLabel bell;
	private JLabel[] currentPointArr;
	private JButton exitButton;
	private JButton pauseButton;
	private JButton pauseBackground;

	// ***********************************************************
	// choims
	private int[] colorFlag;//
	private int gamePanelIndex;
	private int gamePanelY;
	private boolean spaceFlag;
	// 컵
	private Board mainBoard;
	// **************************************************
	/////////////////////// 추가 ////////////////////
	private JLabel[][] hands;
	private ImageIcon[] img;
	private int index = 3;
	private boolean[] handCheck;
	JLabel problemCard;

	public NetworkPlayMode() {
		this.setFocusTraversalKeysEnabled(false);
		panel = new JPanel();
		this.addComponentListener(new FocusHandler());

		// hello

		// **********************************
		// cms,start
		colorFlag = new int[5];
		spaceFlag = false;
		for (int i = 0; i < 5; i++) {
			colorFlag[i] = 0;
		}
		gamePanelIndex = 0;
		gamePanelY = 0;
		// cms,end
		// **********************************

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
		pauseBackground.addActionListener(new FocusBtnHandler(this));
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
		MouseBtnHandler mbh = new MouseBtnHandler(exitButton, pauseButton);
		exitButton.addMouseListener(mbh);
		pauseButton.addMouseListener(mbh);
		exitButton.addActionListener(new FocusBtnHandler(this));
		pauseButton.setBounds(700, 10, 80, 80);
		pauseButton.setBorderPainted(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setFocusPainted(false);
		pauseButton.addActionListener(new ButtonHandler());
		pauseButton.addActionListener(new FocusBtnHandler(this));

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

		problemCard = new JLabel(new ImageIcon("image/sampleCard.png"));
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
		// ************************************************
		// cms,start
		mainBoard = new Board(5, 5);
		mainBoard.setBounds(900, 350, 420, 270);
		mainBoard.setBackground(Color.white);
		mainBoard.setCupsBounds(7, 130, 70, 80, 15);
		mainBoard.setCupsImg(0, "image/cup(red)_dual.png");
		mainBoard.setCupsImg(1, "image/cup(yellow)_dual.png");
		mainBoard.setCupsImg(2, "image/cup(green)_dual.png");
		mainBoard.setCupsImg(3, "image/cup(blue)_dual.png");
		mainBoard.setCupsImg(4, "image/cup(black)_dual.png");
		mainBoard.setCups();
		panel.add(mainBoard);
		// cms,end
		// ************************************************
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
		keyBoard = KeyImage.getKey("1P", 80, 80);

		for (int i = 0; i < 3; i++) {
			keyBoard[i].setBounds(550 + i * 95, 480, 80, 80);
			keyBoard[i + 5].setBounds(550 + i * 95, 480, 80, 80);
			panel.add(keyBoard[i]);
			panel.add(keyBoard[i + 5]);
		}

		for (int i = 3; i < 5; i++) {
			keyBoard[i].setBounds(600 + 90 * (i - 3), 570, 80, 80);
			keyBoard[i + 5].setBounds(600 + 90 * (i - 3), 570, 80, 80);
			panel.add(keyBoard[i]);
			panel.add(keyBoard[i + 5]);
		}

		addKeyListener(new Key1pHandler(keyBoard));

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

		panel.setLayout(null);
		this.addKeyListener(new KeyHandler());
		this.add(panel);
		this.setSize(1363, 714);
		// this.setVisible(true);
		this.setFocusable(true);
		// this.setResizable(false);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				pauseButton.setVisible(false);
				exitButton.setVisible(false);
				for (int i = 0; i < 10; i++)
					keyBoard[i].setVisible(false);
				problemCard.setVisible(false);

			} else if (e.getSource().equals(pauseBackground)) {
				pauseBackground.setVisible(false);
				pauseButton.setVisible(true);
				exitButton.setVisible(true);
				for (int i = 0; i < 10; i++)
					keyBoard[i].setVisible(true);
				problemCard.setVisible(true);
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
			} else if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				////////////////////////// 추가 //////////////////////
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag[i] > 0) {
						setCupFlag++;
					}
				}
				if (setCupFlag == 5 && spaceFlag == false) {
					if (index == -1 || handCheck[0] == true) {
						return;
					}
					bell.setIcon(new ImageIcon("image/bell(Network).png"));
					Sound.playEffect("audio/bell.wav");
					new HandVanish(0, hands, handCheck).start();
				}
			}

			//// 없어도 되는 부분. 이건 손 나오는지 확인만 하는 용도(송준희)
			else if (e.getKeyChar() == KeyEvent.VK_1) {
				if (index == -1 || handCheck[1] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				Sound.playEffect("audio/bell.wav");
				new HandVanish(1, hands, handCheck).start();
			} else if (e.getKeyChar() == KeyEvent.VK_2) {
				if (index == -1 || handCheck[2] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				Sound.playEffect("audio/bell.wav");
				new HandVanish(2, hands, handCheck).start();
			} else if (e.getKeyChar() == KeyEvent.VK_3) {
				if (index == -1 || handCheck[3] == true) {
					return;
				}
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				Sound.playEffect("audio/bell.wav");
				new HandVanish(3, hands, handCheck).start();
			}
			/////////////////////// 추가 끝 ////////////////////
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				if (colorFlag[0] == 0) {
					colorFlag[0] = gamePanelIndex + 1;
					mainBoard.getCups(0, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				if (colorFlag[1] == 0) {
					colorFlag[1] = gamePanelIndex + 1;
					mainBoard.getCups(1, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				if (colorFlag[2] == 0) {
					colorFlag[2] = gamePanelIndex + 1;
					mainBoard.getCups(2, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				if (colorFlag[3] == 0) {
					colorFlag[3] = gamePanelIndex + 1;
					mainBoard.getCups(3, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				if (colorFlag[4] == 0) {
					colorFlag[4] = gamePanelIndex + 1;
					mainBoard.getCups(4, gamePanelIndex, 4 - gamePanelY).setVisible(true);
					if (gamePanelY < 4)
						gamePanelY++;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				bell.setIcon(new ImageIcon("image/bell(Network).png"));
				if (spaceFlag == true) {
					for (int i = 0; i < 5; i++) {
						colorFlag[i] = 0;
						currentPointArr[i].setVisible(false);
						for (int j = 0; j < 5; j++) {
							mainBoard.getCups(0, i, j).setVisible(false);
							mainBoard.getCups(1, i, j).setVisible(false);
							mainBoard.getCups(2, i, j).setVisible(false);
							mainBoard.getCups(3, i, j).setVisible(false);
							mainBoard.getCups(4, i, j).setVisible(false);
						}
					}
					gamePanelIndex = 0;
					gamePanelY = 0;
					currentPointArr[0].setVisible(true);
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
								currentPointArr[i].setVisible(false);
							currentPointArr[gamePanelIndex].setVisible(true);
						}

					}
				}

				else if (e.getKeyChar() == '1') {
					bell.setIcon(new ImageIcon("image/bell(Network).png"));
				} else if (e.getKeyChar() == '2') {
					bell.setIcon(new ImageIcon("image/bell(Network).png"));
				} else if (e.getKeyChar() == '3') {
					bell.setIcon(new ImageIcon("image/bell(Network).png"));
				}
				spaceFlag = true;
			}
		}
	}
}
