package view;

import java.awt.Color;
import java.awt.Component;
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

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

import view.bean.CardDeck;
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

/**
 *   1인용 게임모드를 제공하는 클래스이다.
 *
 */
public class SinglePlayMode extends JPanel implements ActionListener {
	/** 객체직렬화를 위한 serialVersion의 ID이다. */
	private static final long serialVersionUID = 1L;
	/** GridBagConstraints를 적용하기 위한 멤버이다. */
	private GridBagConstraints gbc;
	/** {@link GridBagLayout}을 적용하기 위한 멤버이다. */
	private GridBagLayout gbl;
	// Panel
	/** 게임화면의 왼쪽 부분을 구성하는 패널이다. */
	private JPanel east;
	/** 게임화면의 오른쪽 부분을 구성하는 패널이다. */
	private JPanel west;
	/** 일시정지 버튼 클릭시 화면을 가득 채울 일시정지 화면 버튼이다. */
	private JButton pauseBackground;
	// 5개의 키 버튼
	/** 조작키(색상키)를 화면에 나타내기 위한 {@link JLabel}이다. 5개를 배열로 묶었다. */
	private JLabel[] controllKey;
	// ***********************************************************
	/** 컵을 쌓을때 특정 색깔의 컵이 이미 쌓였는지 판별하기 위한 flag이다. */
	private int[] colorFlag;//
	/** 게임 보드에 컵을 쌓을때 몇번 째 칸에 쌓이는지를 저장하는 멤버이다. */
	private int gamePanelIndex;
	/** 컵의 쌓여진 높이를 나타내는 멤버이다. */
	private int gamePanelY;
	/** 기능키(spacebar)가 연속 2번 이상 눌렸는지 판별하기 위한 flag이다. */
	private boolean spaceFlag;
	// 컵
	/** 컵이 쌓이는 게임 보드를 참조하기 위한 멤버이다. */
	private Board board;
	// 포인트
	/** 컵이 쌓이는 위치를 아이콘으로 표기하기 위한 JLabel이다. */
	private JLabel[] point;

	// ***********************************************************
	// 버튼
	/** 벨 모양을 나타내기 위한 버튼이다. */
	private JLabel bellBtn;
	/** 게임을 종료시키는 버튼이다. */
	private JButton exitBtn;
	/** 게임을 일시정지 시키는 버튼이다. */
	private JButton pauseBtn;
	// ***********시간을 나타내는 패널(Edit by DK KIM)
	/** 게임시간이 얼마나 남았는지 나타내기 위한 타이머이다. */
	private Time1 timePanel;

	// *******************************************************
	// edit by DK Kim, first
	/** 플레이어가 맞춘 정답의 갯수를 표시해주는 JLabel이다. */
	private JLabel correctCnt; // west에 있던거 멤버로 뺌.
	/**
	 * 카드가 움직이는 애니메이션을 구현하기 위한 멤버이다. 첫번째 인자로 ms단위의 수를 입력받아 해당 시간에 1번씩
	 * actionPerformed를 발생시킨다.
	 */
	private Timer tm = new Timer(1, this);
	/** 초기 카드의 위치와 카드 에니메이션이 움직이는 속도를 나타내기 위한 멤버이다. */
	private int initY = 31, velY = 10; // 초기 카드위치와 카드가 움직이는 속도
	/** 사용자가 제출한 답이 정답인지 오답인지 나타내기 위한 멤버이다.(정답일 경우:true,오답일경우:false) */
	private boolean flag; // 사용자의 정답여부
	/** 시간에 따라 변화하는 카드의 좌표이다. */
	private int y; // 사용자의 카드 좌표
	/** 사용자가 현재까지 맞힌 정답의 갯수이다. */
	private int cnt; // 사용자의 정답개수
	/** 시스템 카드덱에 카드를 깔아주기 위한 멤버이다. */
	private Cards systemCardDeck;
	/** 사용자 카드덱을 나타낸다. 정답을 맞출때마다 사용자 카드덱에 시스템 카드덱의 카드가 추가된다. */
	private ArrayList<JLabel> one_Deck;
	/** 현재 플레이어에게 보여지는 카드가 시스템 카드덱의 몇 번째 카드인지 알려주기 위한 멤버이다. */
	private int cardCnt; // 현재 시스템 카드덱의 카드가 몇번째 카드인지

	// *************여기까지 에니메이션**********************////////
	/**
	 * 1인용 플레이 모드가 시작될때 처음 시작되는 생성자이다. 남은 시간 카운트다운을 시작하며, 시스템 카드덱과 사용자 카드덱을 생성해주며,게임
	 * 화면에 {@link Component}를 표기하기 위한 Panel들을 생성해준다
	 */
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

	/** 게임 화면을 구성하는 {@link Component}들을 지정된 비율대로 배치하기 위한 메서드이다.
	 * @param c 싱글플레이 화면에 부착할 Component이다.
	 * @param x 부착할 Component의 x좌표이다.
	 * @param y 부착할 Component의 y좌표이다.
	 * @param w 부착할 Component의 폭이다.
	 * @param h 부착할 Component의 높이이다. */
	private void make(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		// gbl.setConstraints(c,gbc);
		add(c, gbc);
	}

	/** 일시정지 버튼 클릭시 나타날 일시정지 화면 버튼과 왼쪽패널,오른쪽 패널을 화면에 나타내주기 위한 메서드이다. */
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

	/** 게임화면 왼쪽부분을 나타내주기 위한 메서드이다. */
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

	/** 게임화면 오른쪽 부분을 나타내주기 위한 메서드이다. */
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

	/**
	 * 일시정지 버튼을 클릭하고 다시 화면을 클릭했을 때의 효과를 발생시기 위한 {@link InnerClass}이다. 1. 일시정지 버튼을
	 * 클릭한 경우:일시정지 화면으로 전환, Timer1을 통한 남은시간 정지, 조작키 비활성화한다. 2. 일시정지 화면을 클릭한 경우-
	 * 게임화면으로 전환, Timer1을 통한 남은시간 재개, 조작키 활성화한다.
	 */
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

	/**
	 * 키를 누르고 뗐을 때, 각각의 조건에 따른 효과를 발생시키위한 {@link InnerClass}이다. 1. 색상키를 누르면 board에 해당 색상 컵을 쌓는다. 2-1. 컵이
	 * 5개 쌓이기 전 기능키를 한번 누른 경우: 다음 칸으로 이동한다. 2-2 컵이 5개 쌓이기 전 기능키를2번 연속 누른 경우: 배치된 컵을
	 * 초기화한다. 3. 컵이 5개가 쌓인 뒤 기능키를 누른 경우 정답을 제출하고 Sound 클래스의 playSound() 메서드를 통해 벨소리를
	 * 울린다. 4. 제출한 답이 정답인 경우 문제카드를 맞힌 사용자에게 이동하고 맞힌 문제 수를 증가시킨다.
	 */
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

	/**싱글플레이화면의 Timer가 start()될 때 주어진 시간마다 한 번씩 실행되는 메서드이다. 카드가 움직이는 애니메이션을 표현하고, 남은시간이 0초가 되면 팝업을 띄워 정답 갯수를
	 * 알려준다. 상위 5위안에 들었을 시 닉네임을 입력 받아 랭킹 등록을 할 수 있다.*/
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
