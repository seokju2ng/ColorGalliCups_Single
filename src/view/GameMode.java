package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.etc.ChangePanelService;
import view.etc.MyIndex;
import view.etc.Sound;
import view.handler.FocusHandler;
import view.handler.KeyUpDownHandler;
import view.handler.MouseEnteredHandler;

/**
 * GameStart 메뉴를 선택하면 나오는 GameMode 패널를 보여주는 GameMode 클래스이다.
 * 
 * @author 송준희
 */
public class GameMode extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 선택한 메뉴에 따른 index값을 저장한다.
	 */
	private MyIndex cor;

	/**
	 * null parameter constructor로 GameMode 패널을 만들어 사용자에게 보여준다.
	 */
	public GameMode() {
		cor = new MyIndex();
		this.addComponentListener(new FocusHandler());
		this.setLayout(new BorderLayout());
		this.makeUI();
	}

	/**
	 * GameMode 패널에 배경, 메뉴 텍스트 등을 넣어 패널을 꾸며준다.
	 */
	private void makeUI() {
		JButton[] menuArr = new JButton[3];
		menuArr[0] = new JButton("1p Mode");
		menuArr[1] = new JButton("2p Mode");
		// menuArr[2] = new JButton("Network Mode");
		menuArr[2] = new JButton("Back");

		ImageIcon leftCursorImage = new ImageIcon("image/LeftCursor.png");
		ImageIcon rightCursorImage = new ImageIcon("image/RightCursor.png");
		JLabel[] leftCursorArr = new JLabel[] { new JLabel(leftCursorImage), new JLabel(leftCursorImage),
				new JLabel(leftCursorImage) };
		JLabel[] rightCursorArr = new JLabel[] { new JLabel(rightCursorImage), new JLabel(rightCursorImage),
				new JLabel(rightCursorImage) };

		for (int i = 0; i < leftCursorArr.length; i++) {
			leftCursorArr[i].setBounds(450, 340 + 60 * i, 100, 100);
			leftCursorArr[i].setVisible(false);
			add(leftCursorArr[i]);
		}
		for (int i = 0; i < leftCursorArr.length; i++) {
			rightCursorArr[i].setBounds(790, 340 + 60 * i, 100, 100);
			rightCursorArr[i].setVisible(false);
			add(rightCursorArr[i]);
		}

		Font font = new Font("Nanum Brush Script", Font.PLAIN, 60);
		JPanel panel = new JPanel(new GridLayout(5, 0));
		Handler l = new Handler();
		KeyUpDownHandler kudh = new KeyUpDownHandler(cor, leftCursorArr.length - 1, leftCursorArr, rightCursorArr);
		for (JButton b : menuArr) {
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			b.setFocusPainted(false);
			b.setFont(font);
			b.setForeground(new Color(80, 80, 180));
			b.addActionListener(l);
			b.addMouseListener(new MouseEnteredHandler(menuArr, leftCursorArr, rightCursorArr, cor));
			panel.add(b);
		}

		ImageIcon img = new ImageIcon("image/MainBackground.png");
		JPanel background = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, null);
				setOpaque(false);
			}
		};

		leftCursorArr[0].setVisible(true);
		rightCursorArr[0].setVisible(true);

		panel.setOpaque(false);
		background.add(panel);
		background.setLayout(null);
		panel.setBounds(500, 360, 350, 300);
		background.setOpaque(false);
		panel.addKeyListener(kudh);
		this.add(background);
		this.addKeyListener(kudh);
		this.addKeyListener(l);
	}

	/**
	 * GameMode의 Inner Class로 패널에 이벤트가 발생했을 때, 그 이벤트를 처리해주는 Handler 클래스이다. *
	 * 
	 * @author 송준희
	 */
	class Handler extends KeyAdapter implements ActionListener {
		/**
		 * 사용자가 메뉴를 선택하면, 선택한 메뉴로 패널을 교체한다.
		 */
		public void actionPerformed(ActionEvent e) {
			ChangePanelService cps = ChangePanelService.getInstance();
			Sound.playEffect("audio/enter.wav");
			if (cor.getIndex() == 0) {
				cps.addPanel("SingleMode", new SinglePlayMode());
				cps.changePanel("SingleMode");
			} else if (cor.getIndex() == 1) {
				cps.addPanel("DualMode", new DualPlayMode());
				cps.changePanel("DualMode");
			} else if (cor.getIndex() == 2)
				cps.changePanel("MainView");
			// else if (cor.getIndex() == 3)
			// cps.changePanel("MainView");
		}

		/**
		 * 사용자가 엔터키를 입력했을 때 actionPerformed 메소드를 발생시킨다.
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
			}
		}
	}
}
