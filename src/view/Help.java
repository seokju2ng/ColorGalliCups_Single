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
import javax.swing.JPanel;

import view.etc.ChangePanelService;
import view.etc.Sound;
import view.handler.FocusHandler;
import view.handler.KeyUpDownHandler;
import view.handler.MouseEnteredHandler;

/**
 * Help 메뉴를 선택하면 나오는 Help 패널를 보여주는 Help 클래스이다.
 * 
 * @author 송준희
 */
public class Help extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JButton[] b;
	/**
	 * 
	 */
	private MyIndex cor;
	/**
	 * 
	 */
	private JLabel[] ll;
	/**
	 * 
	 */
	private JLabel[] rl;

	/**
	 * 
	 */
	public Help() {
		cor = new MyIndex();
		this.makeUI();
		this.addComponentListener(new FocusHandler());
		this.addKeyListener(new KeyUpDownHandler(cor, 3, ll, rl));
		this.setSize(1363, 714);
	}

	private void makeUI() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel();
		Color bg = new Color(251, 229, 214);
		Color fg = new Color(255, 80, 80);
		p.setBackground(bg);
		p.setLayout(null);
		JPanel p2 = new JPanel();
		p2.setBackground(bg);
		p2.setLayout(new GridLayout(4, 3));
		p2.setBounds(431, 150, 500, 400);
		p.add(p2);

		b = new JButton[4];
		Font font = new Font("Nanum Brush Script", Font.BOLD, 40);

		Handler l = new Handler();
		for (int i = 0; i < 4; i++) {
			b[i] = new JButton();
			b[i].setBorderPainted(false);
			b[i].setContentAreaFilled(false);
			b[i].setFocusPainted(false);
			b[i].setFont(font);
			b[i].setForeground(fg);
			b[i].addActionListener(l);
		}
		b[0].setText("조작키");
		b[1].setText("게임설명");
		b[2].setText("게임정보");
		b[3].setText("뒤로가기");

		ImageIcon i1 = new ImageIcon("image/left.png");
		ImageIcon i2 = new ImageIcon("image/right.png");
		ll = new JLabel[4];
		rl = new JLabel[4];

		for (int i = 0; i < 4; i++) {
			ll[i] = new JLabel(i1);
			rl[i] = new JLabel(i2);
			p2.add(ll[i]);
			p2.add(b[i]);
			p2.add(rl[i]);
			ll[i].setVisible(false);
			rl[i].setVisible(false);
		}
		ll[0].setVisible(true);
		rl[0].setVisible(true);
		MouseEnteredHandler ml = new MouseEnteredHandler(b, ll, rl, cor);

		for (int i = 0; i < 4; i++) {
			b[i].addMouseListener(ml);
		}
		add(p);
		setFocusable(true);
		this.addKeyListener(l);
	}

	class Handler extends KeyAdapter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("enter");
			Sound.playEffect("audio/enter.wav");
			ChangePanelService cps = ChangePanelService.getInstance();
			if (cor.getIndex() == 0)
				cps.changePanel("KeyControl");
			else if (cor.getIndex() == 1)
				cps.changePanel("Tutorial");
			else if (cor.getIndex() == 2)
				cps.changePanel("GameInfo");
			else if (cor.getIndex() == 3)
				cps.changePanel("MainView");
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
			}
		}
	}
}
