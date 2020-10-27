package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.handler.BackHelpHandler;
import view.handler.FocusHandler;

/**
 * 게임정보 메뉴를 선택하면 나오는 GameInfo 패널를 보여주는 GameInfo 클래스이다.
 * 
 * @author 송준희
 */
public class GameInfo extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * null parameter constructor로 GameInfo 패널을 만들어 사용자에게 보여준다.
	 */
	public GameInfo() {
		this.setLayout(null);
		this.setBackground(new Color(251, 229, 214));
		Color bg = new Color(197, 90, 17);
		Font font = new Font("nanum brush script", Font.BOLD, 45);
		JLabel[] labels = new JLabel[] { new JLabel("2019 (T)PerfectColor", SwingConstants.CENTER),
				new JLabel("Threekimchoisong", SwingConstants.CENTER),
				new JLabel("Directed by Lectopia", SwingConstants.CENTER),
				new JLabel("Thanks to Hong, Jeong HK, Kim KH, Jeong KE", SwingConstants.CENTER) };
		for (int i = 0; i < labels.length; i++) {
			labels[i].setFont(font);
			labels[i].setForeground(bg);
			labels[i].setBounds(180, 100 + i * 80, 1000, 80);
			add(labels[i]);
		}

		JButton back = new JButton("뒤로가기");
		back.setFont(font);
		back.setForeground(bg);
		back.setBounds(581, 500, 200, 100);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);

		BackHelpHandler l = new BackHelpHandler();
		back.addActionListener(l);
		this.addKeyListener(l);
		this.addComponentListener(new FocusHandler());

		JLabel rarrow = new JLabel(new ImageIcon("image/right.png"));
		JLabel larrow = new JLabel(new ImageIcon("image/left.png"));
		larrow.setBounds(460, 500, 100, 100);
		rarrow.setBounds(800, 500, 100, 100);
		add(back);
		add(rarrow);
		add(larrow);
	}
}
