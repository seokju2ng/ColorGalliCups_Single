package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.etc.KeyImage;
import view.handler.BackHelpHandler;
import view.handler.FocusHandler;

/**
 * Help 메뉴의 조작키 메뉴를 선택했을 때 사용자에게 조작키를 보여주는 KeyControl 클래스이다.
 * 
 * @author 송준희
 */
public class KeyControl extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * SingleMode 게임을 할 때, 사용자가 사용하는 색상키와 입력키를 저장한다.
	 */
	private JLabel[] soloKey;
	/**
	 * DualMode 게임을 할 때, 1P 사용자가 사용하는 색상키와 입력키를 저장한다.
	 */
	private JLabel[] dualKey1;
	/**
	 * DualMode 게임을 할 때, 2P 사용자가 사용하는 색상키와 입력키를 저장한다.
	 */
	private JLabel[] dualKey2;

	/**
	 * null parameter constructor로 KeyControl 패널을 만들어 사용자에게 보여준다.
	 */
	public KeyControl() {
		this.setLayout(null);
		this.setBackground(new Color(251, 229, 214));
		this.addComponentListener(new FocusHandler());
		BackHelpHandler l = new BackHelpHandler();
		this.addKeyListener(l);

		soloKey = KeyImage.getKey("1P", 90, 90);
		JLabel functionButton = new JLabel(new ImageIcon("image/spacebar.png"));
		JLabel singlePlay = new JLabel(new ImageIcon("image/singleplay.png"));
		singlePlay.setBounds(40, 10, 250, 150);
		add(singlePlay);
		for (int i = 0; i < soloKey.length / 2; i++) {
			soloKey[i].setBounds(110 + 100 * i, 200, 90, 90);
			soloKey[i + 5].setBounds(110 + 100 * i, 200, 90, 90);
			add(soloKey[i]);
			add(soloKey[i + 5]);
		}
		functionButton.setBounds(190, 350, 350, 94);
		add(functionButton);
		
		JLabel doublePlay = new JLabel(new ImageIcon("image/doubleplay.png"));
		doublePlay.setBounds(610, 10, 250, 150);
		add(doublePlay);
		JLabel[] player = new JLabel[] { new JLabel("1P"), new JLabel("2P") };
		Font font = new Font("Nanum Brush Script", Font.BOLD, 45);
		for (int i = 0; i < 2; i++) {
			player[i].setBounds(810 + i * 280, 140, 350, 100);
			player[i].setFont(font);
			add(player[i]);
		}

		dualKey1 = KeyImage.getKey("1P", 60, 60);
		JLabel functionButton1 = new JLabel(new ImageIcon("image/spacebar_1p.png"));
		for (int i = 0; i < 3; i++) {
			dualKey1[i].setBounds(730 + 70 * i, 230, 60, 60);
			dualKey1[i + 5].setBounds(730 + 70 * i, 230, 60, 60);
			add(dualKey1[i]);
			add(dualKey1[i + 5]);
		}
		for (int i = 3; i < 5; i++) {
			dualKey1[i].setBounds(560 + 70 * i, 300, 60, 60);
			dualKey1[i + 5].setBounds(560 + 70 * i, 300, 60, 60);
			add(dualKey1[i]);
			add(dualKey1[i + 5]);
		}
		functionButton1.setBounds(735, 400, 191, 59);
		add(functionButton1);

		dualKey2 = KeyImage.getKey("2P", 60, 60);
		JLabel functionButton2 = new JLabel(new ImageIcon("image/enter_2p.png"));
		for (int i = 0; i < 3; i++) {
			dualKey2[i].setBounds(1010 + 70 * i, 230, 60, 60);
			dualKey2[i + 5].setBounds(1010 + 70 * i, 230, 60, 60);
			add(dualKey2[i]);
			add(dualKey2[i + 5]);
		}
		for (int i = 3; i < 5; i++) {
			dualKey2[i].setBounds(835 + 70 * i, 300, 60, 60);
			dualKey2[i + 5].setBounds(835 + 70 * i, 300, 60, 60);
			add(dualKey2[i]);
			add(dualKey2[i + 5]);
		}
		functionButton2.setBounds(1015, 400, 191, 60);
		add(functionButton2);

		JButton back = new JButton("뒤로가기");
		back.setFont(font);
		back.setForeground(new Color(197, 90, 17));
		back.setBounds(566, 540, 200, 100);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.addActionListener(l);
		add(back);

		this.addKeyListener(new Key1pHandler(soloKey));
		this.addKeyListener(new Key1pHandler(dualKey1));
		this.addKeyListener(new Key2pHandler(dualKey2));

		JLabel rarrow = new JLabel(new ImageIcon("image/right.png"));
		JLabel larrow = new JLabel(new ImageIcon("image/left.png"));
		larrow.setBounds(470, 540, 100, 100);
		rarrow.setBounds(765, 540, 100, 100);
		add(rarrow);
		add(larrow);
	}

	/**
	 * 패널에 모서리가 둥근 직사각형을 그려주는 메소드이다.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(80, 110, 550, 400, 50, 50);
		g2.drawRoundRect(700, 110, 550, 400, 50, 50);
	}
}
