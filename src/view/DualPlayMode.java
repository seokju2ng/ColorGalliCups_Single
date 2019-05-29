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

import view.bean.CardDeck;
import view.etc.Board;
import view.etc.ChangePanelService;
import view.etc.HandVanish;
import view.etc.KeyImage;
import view.etc.RoundedPanel;
import view.etc.Sound;
import view.etc.Time2;
import view.handler.ExitBtnHandler;
import view.handler.FocusBtnHandler;
import view.handler.FocusHandler;
import view.handler.Key1pHandler;
import view.handler.Key2pHandler;
import view.handler.MouseBtnHandler;

/**
 * 2인용 게임모드를 제공하는 클래스이다.
 * 
 * @author 김도균
 */
public class DualPlayMode extends JPanel implements ActionListener {
	/**
	 * 
	 */
	/** 객체직렬화를 위한 serialVersion의 ID이다. */
	private static final long serialVersionUID = 1L;
	/** 게임 화면의 너비이다. */
	public static final int WIDTH = 1363;
	/** 게임 화면의 높이이다. */
	public static final int HEIGHT = 714;
	/** 게임을 진행할때 필요한 기본카드의 갯수이다. */
	private static int cardNum;

	// ********************************************************
	// edit by minseongChoi
	/** 컵을 쌓을때 특정 색깔의 컵이 이미 쌓였는지 판별하기 위한 flag이다.(1p용) */
	private int[] colorFlag1p;
	/** 컵을 쌓을때 특정 색깔의 컵이 이미 쌓였는지 판별하기 위한 flag이다.(2p용) */
	private int[] colorFlag2p;
	/** 게임 보드에 컵을 쌓을때 몇번 째 칸에 쌓이는지를 저장하는 멤버이다.(1p용) */
	private int gamePanelIndex1p;
	/** 게임 보드에 컵을 쌓을때 몇번 째 칸에 쌓이는지를 저장하는 멤버이다.(2p용) */
	private int gamePanelIndex2p;
	/** 컵의 쌓여진 높이를 나타내는 멤버이다.(1p용) */
	private int gamePanelY1p;
	/** 컵의 쌓여진 높이를 나타내는 멤버이다.(2p용) */
	private int gamePanelY2p;
	/** 기능키(spacebar)가 연속 2번 이상 눌렸는지 판별하기 위한 flag이다. */
	private boolean spaceFlag1p;
	/** 기능키(enter)가 연속 2번 이상 눌렸는지 판별하기 위한 flag이다. */
	private boolean spaceFlag2p;
	// **********************************************************

	/** 게임 화면 좌측부에 붙인 패널이다. */
	private JPanel p1;
	/** 게임 화면 중앙부에 붙인 패널이다. */
	private JPanel p2;
	/** 게임 화면 우측부에 붙인 패널이다. */
	private JPanel p3;
	/** 컵이 쌓이는 위치를 아이콘으로 표현하기 위한 JLabel이다.(1p용) */
	private JLabel[] pointerOne; // 1p 화살표
	/** 컵이 쌓이는 위치를 아이콘으로 표현하기 위한 JLabel이다.(2p용) */
	private JLabel[] pointerTwo; // 2p 화살표

	/** 컵이 쌓이는 게임 보드를 참조하기 위한 멤버이다.(1p용) */
	private Board board1;
	/** 컵이 쌓이는 게임 보드를 참조하기 위한 멤버이다.(2p용) */
	private Board board2;
	/** 현재 카드가 몇번째 카드인지 나타내기 위한 라벨을 부착할 패널이다. */
	private JPanel cardIdxLabelPanel; // 카드번호를 나타내기 위한 패널
	/** 진행 시간을 나타내는 패널이다. */
	private Time2 timePanel;

	/** 현재 카드가 몇 번째 카드인지 나타내는 라벨이다. */
	private JLabel cardIdxLabel; // 카드번호를 나타내기 위함
	/** 시스템 카드덱이 부착될 패널이다. */
	private JPanel cardPanel; // 카드가 올라갈 패널
	/** 게임 진행 시 사용될 종 모양의 Button이다. */
	private JButton bell; // 종
	/** 일시정지 버튼이다. */
	private JButton pause;
	/** 나가기 버튼이다. */
	private JButton exit;
	/** 1p사용자 카드덱을 나타낸다. 정답을 맞출때마다 사용자 카드덱에 시스템 카드덱의 카드가 추가된다. */
	private ArrayList<JLabel> one_Deck; // 1p player가 맞춘 카드개수.
	/** 2p사용자 카드덱을 나타낸다. 정답을 맞출때마다 사용자 카드덱에 시스템 카드덱의 카드가 추가된다. */
	private ArrayList<JLabel> two_Deck; // 2p player가 맞춘 카드개수.
	// private JLabel card; // 중앙 덱에 올라갈 카드
	/** 1p 전용 키 버튼을 5색으로 나타내주는 라벨이다. */
	private JLabel[] one_buttons; // 1p전용 키 버튼
	/** 2p 전용 키 버튼을 5색으로 나타내주는 라벨이다. */
	private JLabel[] two_buttons; // 2p전용 키 버튼
	/** 일시정지 버튼 클릭 시 화면을 가득 채울 일시정지 화면 버튼이다. */
	private JButton pauseBackground;
	//////////////////////////////// 준희가 수정 //////////////////
	/** 손 모양의 이미지를 저장하는 라벨이다. */
	private JLabel[][] hands; // 손 이미지 저장하는 라벨
	/** 손의 이미지이다. */
	private ImageIcon[] img; // 손 이미지
	/** 손 쌓는 층을 나타내는 index이다. */
	private int index = 3; // 손쌓는 층 나타내는 index 0,1층만 있다
	/** 한 사람이 연속으로 벨을 못누르게 하는 flag역할을 한다. */
	private boolean[] handCheck; // 한사람이 연속으로 벨 못누른다 flag 역할
	//////////////////////////////// 준희가 수정 끝//////////////////

	// 애니메이션//
	/** 카드가 움직이는 애니메이션을 구현하기 위한 멤버이다. */
	private Timer tm = new Timer(10, this);
	/** 초기 카드의 위치를 나타내는 좌표이다. */
	private int initX = 604;
	/** 애니메이션 상에서 카드가 움직이는 속도이다. */
	private int velX = 15;
	/** 1p 사용자의 정답 여부이다. */
	private boolean one_flag = false; // 1p의 정답여부
	/** 2p 사용자의 정답 여부이다. */
	private boolean two_flag = false; // 2p의 정답여부
	/** 1p사용자의 카드 좌표이다. */
	private int one_x;
	/** 2p사용자의 카드 좌표이다. */
	private int two_x; // 1p, 2p의 카드좌표
	/** 1p사용자의 정답 갯수이다. */
	private int one_cnt;
	/** 2p사용자의 정답 갯수이다. */
	private int two_cnt; // 1p,2p의 정답개수
	/** 기본카드와 goldCard를 포함하고 있는 카드덱이다. */
	private CardDeck cardDeck;
	/** 현재 시스템 카드덱의 카드가 몇번째 카드인지 나타낸다. */
	private int cardCnt; // 현재 시스템 카드덱의 카드가 몇번째 카드인지.
	//////
	/* 게임로직 관련 멤버 추가 0526 Edit by DK KIM */
	/** 시스템 카드덱의 카드 그림을 보여주기 위한 라벨 list이다. */
	private ArrayList<JLabel> cardDeckLabels;
	/** 1인용 사용자가 입력하는 정답 문자열이다. */
	private StringBuilder pOneAnswer;
	/** 2인용 사용자가 입력하는 정답 문자열이다. */
	private StringBuilder pTwoAnswer;
	///////////////////////

	/* 무승부를 판별하기 위한 flag */
	/** 골드게임 실행여부(무승부 여부)를 판별하기 위한 flag이다. */
	private boolean goldFlag = false;
	/** 골드카드 그림을 나타내는 라벨이다. */
	private JLabel goldCardLabel;// 골드카드 그림을 나타내는 라벨
	
	/*0529추가  시스템 카드덱이 넘어갈시 뒷면을 보여주기 위한 LABEL*/
	private JLabel backLabel;
	private JLabel crownOne;
	private JLabel crownTwo;

	// 여기서부터 카드 애니메이션 0518//

	//////////////////////// 여기까지 카드 에니메이션/////////////

	/**
	 * 2인용 플레이모드가 시작될 때 처음 시작된 생성자이다. 진행 시간을 시작 및 게임에 필요한 UI들을 생성해준다.
	 */
	public DualPlayMode() {
		//backLabel = new JLabel(new ImageIcon("image/card(back).png"));
		backLabel = new JLabel(KeyImage.resizeIcon(new ImageIcon("image/card(back).png"),164, 260));
		backLabel.setVisible(false);
		//KeyImage.resizeIcon(new ImageIcon("image/card(back).png"),164, 260)
		backLabel.setBounds(595, 32, 164, 260);
		this.add(backLabel);
		//이겼을때 보여줄 왕관 0529 Edit By DK KIM//
		crownOne = new JLabel(KeyImage.resizeIcon(new ImageIcon("image/crown.png"), 268, 269));
		crownOne.setBounds(160, 320, 268, 269);
		this.add(crownOne);
		crownOne.setVisible(false);
		
		crownTwo = new JLabel(KeyImage.resizeIcon(new ImageIcon("image/crown.png"), 268, 269));
		crownTwo.setBounds(972, 320, 268, 269);
		this.add(crownTwo);
		crownTwo.setVisible(false);
		//////////////////////////////////////
		tm.start();
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
		int cardNum = 4; // 옵션에서 정해줄 시스템 카드의 장수이다.
		cardDeck = CardDeck.getInstance(); // 옵션에서 정해준 장수만큼 카드덱을 생성한다.
		cardDeck.shuffle();
		// cardDeck.goldShuffle(); // 골드카드를 섞어준다.
		cardDeckLabels = new ArrayList<JLabel>();

		// 0528 goldGame추가 Edit By DK KIM//
		goldCardLabel = new JLabel(new ImageIcon("image/abtainGCard.png"));
		goldCardLabel.setBounds(595, 32, 164, 260);
		// this.add(goldCardLabel);
		///////////////////////////

		for (int i = 0; i < cardNum; i++) {
			cardDeckLabels.add(new JLabel(new ImageIcon(cardDeck.getImagePath(i))));
			// System.out.println(cardDeck.getImagePath(i));
		}

		pOneAnswer = new StringBuilder();
		pTwoAnswer = new StringBuilder();
		showCenter();
		showWest();
		// showCenter();
		showEast();
		// setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	/** 게임화면 좌측부를 나타내주기 위한 메서드이다. (진행시간 패널, 카드번호 패널,1p 사용자용 게임보드, 1p 사용자용 카드덱 */
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
		cardIdxLabelPanel = new JPanel(); // '카드번호'라벨과 실제 카드번호를 붙일 패널
		cardIdxLabelPanel.setBorder(new LineBorder(Color.BLACK));
		timePanel.setBackground(Color.white);
		cardIdxLabelPanel.setBackground(Color.white);
		cardIdxLabelPanel.setLayout(null);

		cardIdxLabelPanel.setBounds(270, 20, 180, 100);

		cardIdxLabel = new JLabel(cardCnt + "/" + cardDeckLabels.size(), SwingConstants.CENTER);
		cardIdxLabel.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
		// timer.setBorder(new LineBorder(Color.red, 5));
		// cardIdxLabel.setBorder(new LineBorder(Color.red, 5));

		cardText.setBounds(0, 0, 180, 50);

		cardIdxLabel.setBounds(0, 50, 180, 50);

		cardIdxLabelPanel.add(cardText);
		cardIdxLabelPanel.add(cardIdxLabel);

		p1.add(timePanel);
		p1.add(cardIdxLabelPanel);
		p1.add(board1);
		for (int i = 0; i < pointerOne.length; i++) {
			p1.add(pointerOne[i]);
		}
		cardIdxLabelPanel.add(cardIdxLabel);
		// 1p 덱에 카드추가(카드얻었을시)//
		one_Deck = new ArrayList<>();

		///////////////////////////////
		this.add(p1);
	}

	/** 게임화면 우측부를 나타내주기 위한 메서드이다. (일시정지및 나가기 버튼, 2p 사용자용 게임보드, 2p 사용자용 카드덱 ) */
	public void showEast() {
		// 화살표 달아줌//
		pointerTwo = new JLabel[5];
		for (int i = 0; i < pointerTwo.length; i++) {
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
		exit.addActionListener(new ExitBtnHandler(this, this.tm, this.timePanel.getTimer()));
		p3.add(pause);
		p3.add(exit);
		p3.add(board2);
		for (int i = 0; i < pointerTwo.length; i++) {
			p3.add(pointerTwo[i]);
		}
		///////////////////////
		two_Deck = new ArrayList<>();
	}

	/**
	 * 게임화면 중앙부를 나타내주기 위한 메서드이다. (시스템 카드덱 그림, 시스템 카드덱 패널,1p 사용자용 키라벨, 2p 사용자용 키라벨
	 */
	public void showCenter() {
		this.p2 = new JPanel();
		/* 카드 먼저 cardDeckPanel에 붙여준다.0526 Edit By DK KIM */
		for (int i = 0; i < cardDeckLabels.size(); i++) {
			cardDeckLabels.get(i).setBounds(595, 32, 164, 260);
			this.add(cardDeckLabels.get(i));
		}
		this.add(goldCardLabel);
		goldCardLabel.setVisible(false);

		///////

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
		this.cardPanel = new RoundedPanel(null, 120, Color.WHITE);
		cardPanel.setBackground(Color.white);

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
		cardPanel.setLayout(null);
		cardPanel.setBounds(40, 20, 278, 278);
		p2.add(cardPanel);
		addKeyButtons();
		p2.add(bell);
		// p2.addKeyListener(new KeyHandler());

	}

	/*------------ centerpane(p2)에 버튼10개 달아주는 메소드 ---------------*/
	/** showCenter() 메서드에서 호출하는, 화면 중앙부에 1p,2p사용자용 키 라벨을 생성해주기 위한 메서드이다. */
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

	/** 옵션에서 설정한 카드 갯수 만큼 시스템 카드덱의 수를 설정해주기 위한 static 메서드이다. */
	public static void setCardNum(int cardNum) {
		DualPlayMode.cardNum = cardNum;
	}

	/**
	 * 듀얼플레이화면의 Timer가 start() 될 때 주어진 시간마다 한 번씩 실행되는 메서드이다. 카드 애니메이션을 표현한다. 무승부가
	 * 아니라면 승자를 알려주는 팝업을 띄우고, 무승부 시 골드게임을 시작하게 한다.
	 */
	public void actionPerformed(ActionEvent e) {
		// System.out.println("thread돌아감");
		ImageIcon icon = new ImageIcon("image/card(back).png"); 
		
		if (one_Deck.size() + two_Deck.size() < cardDeckLabels.size()) {
			if (one_flag == true) {
				if (one_x > 150) {
					// velX = -velX;
					spaceFlag1p = true;
					cardDeckLabels.get(cardCnt - 1).setBounds(one_x, -(one_x / 7) + 128, 154, 238);// 이미 cardCnt는
																									// 1증가되어있으므로
					board1.requestFocusInWindow(); // 이전의 cardCnt에 해당하는 카드를
					// 가져온다.
					one_x = one_x - velX;
					// card.setBounds(610,-(610/7)+45,154,238);

				}
				// x = x + velX;
				// card.setBounds(x,x/7-45,154,238);
				else if (one_x < 150) {
					//ImageIcon icon = (ImageIcon) cardDeckLabels.get(cardCnt - 1).getIcon(); // 얻은 카드의 이미지를 따온다.
					//ImageIcon icon = new ImageIcon("image/card(back).png"); 
					this.remove(cardDeckLabels.get(cardCnt - 1)); // 카드를 지우고
					this.revalidate(); // 부모컨테이너를 새로고침한다
					this.repaint(); // 새로고침.
					this.requestFocusInWindow();
					bell.setIcon(new ImageIcon("image/bell.png"));
					one_Deck.add(new JLabel(KeyImage.resizeIcon(icon, 90, 140))); // 1p 사용자 카드덱에 카드추가
					one_Deck.get(one_cnt - 1).setBounds(50 + (one_cnt - 1) * 10, 190, 90, 140); // 좌표는 나중에 수정
					for (int i = one_Deck.size() - 1; i >= 0; i--) {
						p1.add(one_Deck.get(i));
					}
					// one_cnt++; // 1p정답갯수를 늘려준다.
					// cardCnt++; // 시스템카드덱을 +1한다.
					one_flag = false;
					System.out.println("된다! one_cnt: " + one_cnt + ", arraylSize : " + one_Deck.size());
					cardIdxLabel.setText(cardCnt + "/" + cardDeckLabels.size());
					backLabel.setVisible(false);
				}
			} else if (two_flag == true) {
				if (two_x < 1100) {
					spaceFlag2p = true;
					cardDeckLabels.get(cardCnt - 1).setBounds(two_x, two_x / 7 - 45, 154, 238); // 원래
					two_x = two_x + velX;
					board1.requestFocusInWindow();
				} else if (two_x > 1100) {
					// try {
					// Thread.sleep(1000); // 카드가 1초후 사라진다.
					// } catch (InterruptedException e1) {
					//ImageIcon icon = (ImageIcon) cardDeckLabels.get(cardCnt - 1).getIcon(); // 얻은 카드의 이미지를 따온다.
					this.remove(cardDeckLabels.get(cardCnt - 1)); // 카드를 지우고
					this.revalidate(); // 부모컨테이너를 새로고침한다
					this.repaint(); // 새로고침.
					this.requestFocusInWindow();
					bell.setIcon(new ImageIcon("image/bell.png"));
					two_Deck.add(new JLabel(KeyImage.resizeIcon(icon, 90, 140))); // 2p 사용자 카드덱에 카드추가
					two_Deck.get(two_cnt - 1).setBounds(350 - (two_cnt - 1) * 10, 190, 90, 140); // 좌표는 나중에 수정
					for (int i = two_Deck.size() - 1; i >= 0; i--) {
						p3.add(two_Deck.get(i)); // 겹치는 순서 지키기위해 같은 것도 다시 add한다.
					}
					// two_cnt++; // 2p정답갯수를 늘려준다.

					System.out.println("된다! two_cnt: " + two_cnt + ", arraylSize : " + two_Deck.size());

					two_flag = false;
					// cardCnt++;
					cardIdxLabel.setText(cardCnt + "/" + cardDeckLabels.size());
					backLabel.setVisible(false);
				}
			}
		}
		if (one_Deck.size() + two_Deck.size() >= cardDeckLabels.size() && goldFlag == false) {
			// timePanel.getTimer().stop();
			String winner;
			if (one_cnt == two_cnt) {
				goldCardLabel.setVisible(true);
				cardDeck.goldShuffle(); // 골드카드를 섞어준다.
				JOptionPane.showMessageDialog(null, "무승부네요~ 골드게임 시작합니다!", "무승부", JOptionPane.OK_CANCEL_OPTION);
				goldFlag = true;
				
				goldCardLabel.setIcon(new ImageIcon(cardDeck.getGoldImagePath()));
			} else {
				if (one_cnt > two_cnt) {
					winner = "1p";
				} else {
					winner = "2p";
				}
				
				if(winner.equals("1p")) {
					crownOne.setVisible(true);
				}else {
					crownTwo.setVisible(true);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				timePanel.getTimer().stop();

//				try {
//					Thread.sleep(2000);
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				JOptionPane.showMessageDialog(null, one_cnt + ":" + two_cnt + ", " + winner + "승리(게임 진행시간 : "
						+ timePanel.getTimeFlow().getText() + ")", "게임 종료", JOptionPane.CANCEL_OPTION);
				tm.stop();
				timePanel.getTimer().stop();
				ChangePanelService.getInstance().changePanel("MainView", DualPlayMode.this);
			}
		}
	}

	/**
	 * 일시정지 버튼을 클릭하고 다시 화면을 클릭했을 때 각각의 효과를 발생시킨다. 1. 일시정지 버튼을 클릭한 경우 - 일시정지 화면으로 전환,
	 * Time2을 통한 진행시간 정지, 조작키 비활성화한다. 2. 일시정지 화면을 클릭한 경우 - 게임화면으로 전환, Time2을 통한 진행
	 * 시간 재개, 조작키 활성화한다.
	 */
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(pause)) {

				p2.setVisible(false);
				pauseBackground.setVisible(true);
				pause.setVisible(false);
				exit.setVisible(false);
				for (int i = cardCnt; i < cardDeckLabels.size(); i++)
					cardDeckLabels.get(i).setVisible(false);
				pause.requestFocusInWindow();
				timePanel.getTimer().stop();

			} else if (e.getSource().equals(pauseBackground)) {
				System.out.println("눌림");
				p2.setVisible(true);
				pauseBackground.setVisible(false);
				pause.setVisible(true);
				exit.setVisible(true);
				for (int i = cardCnt; i < cardDeckLabels.size(); i++)
					cardDeckLabels.get(i).setVisible(true);
				DualPlayMode.this.requestFocusInWindow();
				timePanel.getTimer().start();

			}
		}
	}

	/**
	 * 키를 누르고 뗐을 때, 각각의 조건에 따른 효과를 발생시킨다. 1. 색상키를 누르면 board에 해당 색상 컵을 쌓는다. 2-1. 컵이
	 * 5개 쌓이기 전 기능키를 한번 누른 경우: 다음 칸으로 이동한다. 2-2 컵이 5개 쌓이기 전 기능키를2번 연속 누른 경우: 배치된 컵을
	 * 초기화한다. 3. 컵이 5개가 쌓인 뒤 기능키를 누른 경우 정답을 제출하고 Sound 클래스의 playSound() 메서드를 통해 벨소리를
	 * 울린다. 4. 제출한 답이 정답인 경우 문제카드를 맞힌 사용자에게 이동하고 맞힌 문제 수를 증가시킨다.
	 */
	private class KeyHandler extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(spaceFlag1p);
			System.out.println(spaceFlag2p);
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_Q) {
				spaceFlag1p = false;
				if (colorFlag1p[0] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag1p[0] = gamePanelIndex1p + 1;
					// System.out.println(redCup1p+"," + gamePanelIndex1p +"," + gamePanelY1p);
					board1.getCups(0, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
					pOneAnswer.append("1");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				spaceFlag1p = false;
				if (colorFlag1p[1] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag1p[1] = gamePanelIndex1p + 1;
					board1.getCups(1, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
					pOneAnswer.append("2");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				spaceFlag1p = false;
				if (colorFlag1p[2] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag1p[2] = gamePanelIndex1p + 1;
					board1.getCups(2, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
					pOneAnswer.append("3");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				spaceFlag1p = false;
				if (colorFlag1p[3] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag1p[3] = gamePanelIndex1p + 1;
					board1.getCups(3, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
					pOneAnswer.append("4");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				spaceFlag1p = false;
				if (colorFlag1p[4] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag1p[4] = gamePanelIndex1p + 1;
					board1.getCups(4, gamePanelIndex1p, 4 - gamePanelY1p).setVisible(true);
					if (gamePanelY1p < 4)
						gamePanelY1p++;
					pOneAnswer.append("5");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_I) {
				spaceFlag2p = false;
				if (colorFlag2p[0] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag2p[0] = gamePanelIndex2p + 1;
					board2.getCups(0, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
					pTwoAnswer.append("1");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_O) {
				spaceFlag2p = false;
				if (colorFlag2p[1] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag2p[1] = gamePanelIndex2p + 1;
					board2.getCups(1, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
					pTwoAnswer.append("2");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				spaceFlag2p = false;
				if (colorFlag2p[2] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag2p[2] = gamePanelIndex2p + 1;
					board2.getCups(2, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
					pTwoAnswer.append("3");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_K) {
				spaceFlag2p = false;
				if (colorFlag2p[3] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag2p[3] = gamePanelIndex2p + 1;
					board2.getCups(3, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
					pTwoAnswer.append("4");
				}
			} else if (e.getKeyCode() == KeyEvent.VK_L) {
				spaceFlag2p = false;
				if (colorFlag2p[4] == 0) {
					Sound.playEffect("audio/stack.wav");
					colorFlag2p[4] = gamePanelIndex2p + 1;
					board2.getCups(4, gamePanelIndex2p, 4 - gamePanelY2p).setVisible(true);
					if (gamePanelY2p < 4)
						gamePanelY2p++;
					pTwoAnswer.append("5");
				}

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
					// if(cardCnt < cardDeckLabels.size())
					bell.setIcon(new ImageIcon("image/bell(push_right).png"));
					Sound.playEffect("audio/bell.wav");
					new HandVanish(3, hands, handCheck).start();
					/////////////////////////// 손 나오는 부분 끝/////////////////
					System.out.println("2p 답 :" + pTwoAnswer);
					if (goldFlag == false) {
						if ((cardDeck.isCorrect(cardCnt, new String(pTwoAnswer)) == true) && one_flag == false) {
							backLabel.setVisible(true);
							two_flag = true;
							cardCnt++; // 시스템카드덱을 +1한다.
							two_cnt++; // 2p 정답수를 +1한다.
							two_x = initX; // 엔터누를때 2p 카드의 초기위치 재설정.
							// tm.start();
						}
					} else {
						if ((cardDeck.isGoldCorrect(new String(pTwoAnswer)) == true) && one_flag == false) {
							System.out.println("2p골드카드 맞춤");
							two_flag = true;
							// cardCnt++;
							two_cnt++;
							goldFlag = false;

						}
					}
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
					bell.setIcon(new ImageIcon("image/bell(push_left).png"));
					Sound.playEffect("audio/bell.wav");
					new HandVanish(2, hands, handCheck).start();
					// bell.setIcon(new ImageIcon("image/bell.png"));

					/////////////////////////// 손 나오는 부분 끝/////////////////

					// 여기 다시 해봐라!(if문 추가)
					// 1p,2p 동싴 ㅣ입력시 버그 해결을 위해 && 이용함
					if (goldFlag == false) {
						if ((cardDeck.isCorrect(cardCnt, new String(pOneAnswer)) == true) && (two_flag == false)) {
							backLabel.setVisible(true);
							one_flag = true;
							cardCnt++; // 시스템카드덱을 +1한다.
							one_cnt++; // 1p 정답수를 +1한다.
							one_x = initX;
							// tm.start();
						}
					} else {
						System.out.println("1p골드카드 맞춤들어옴");
						System.out.println("사용자 :" + pOneAnswer + "/ 정답 : " + cardDeck.getGoldCard().getAnswer() + "진짜경로"
								+ cardDeck.getGoldCard().getPath());
						if ((cardDeck.isGoldCorrect(new String(pOneAnswer)) == true) && two_flag == false) {
							//backLabel.setVisible(true);
							System.out.println("1p골드카드 진짜맞춤");
							one_flag = true;
							// cardCnt++;
							one_cnt++;
							goldFlag = false; // 골드게임을 끝나게하고 최종승부 반영.

						}
					}
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_Q) {

			} else if (e.getKeyCode() == KeyEvent.VK_W) {

			} else if (e.getKeyCode() == KeyEvent.VK_E) {

			} else if (e.getKeyCode() == KeyEvent.VK_A) {

			} else if (e.getKeyCode() == KeyEvent.VK_S) {

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
					pOneAnswer.delete(0, pOneAnswer.length()); // 스페이스 2번 누를 시 1P 정답 스트링 초기화
					return;
				}
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag1p[i] > 0) {
						setCupFlag++;
					}
				}
				if (setCupFlag > 0 && setCupFlag < 5) {
					if (pOneAnswer.charAt(pOneAnswer.length() - 1) != '/')
						pOneAnswer.append("/");
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

			} else if (e.getKeyCode() == KeyEvent.VK_O) {

			} else if (e.getKeyCode() == KeyEvent.VK_P) {

			} else if (e.getKeyCode() == KeyEvent.VK_K) {

			} else if (e.getKeyCode() == KeyEvent.VK_L) {

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
					pTwoAnswer.delete(0, pTwoAnswer.length()); // 스페이스 2번 누를 시 2P 정답 스트링 초기화
					return;
				}
				int setCupFlag = 0;
				for (int i = 0; i < 5; i++) {
					if (colorFlag2p[i] > 0) {
						setCupFlag++;
					}
				}
				if (setCupFlag > 0 && setCupFlag < 5) {
					if (pTwoAnswer.charAt(pTwoAnswer.length() - 1) != '/')
						pTwoAnswer.append("/");
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
