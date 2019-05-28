package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.handler.BackHelpHandler;
import view.handler.FocusHandler;

/**
 * Tutorial을 보여주는 Tutorial 클래스이다.
 * 
 * @author 송준희
 *
 */
public class Tutorial extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * null parameter constructor로 Tutorial 패널을 만들어 사용자에게 보여준다.
	 */
	public Tutorial() {
		setLayout(null);
		setBackground(new Color(251, 229, 214));
		JLabel picture = new JLabel(new ImageIcon("image/tutorial.gif"));
		picture.setBounds(230, 50, 900, 500);
		add(picture);

		JButton back = new JButton("뒤로가기");
		back.setFont(new Font("nanum brush script", Font.BOLD, 45));
		back.setForeground(new Color(197, 90, 17));
		back.setBounds(581, 550, 200, 100);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);

		JLabel rarrow = new JLabel(new ImageIcon("image/right.png"));
		JLabel larrow = new JLabel(new ImageIcon("image/left.png"));
		larrow.setBounds(460, 550, 100, 100);
		rarrow.setBounds(800, 550, 100, 100);

		BackHelpHandler l = new BackHelpHandler();
		back.addActionListener(l);
		this.addKeyListener(l);
		this.addComponentListener(new FocusHandler());

		add(back);
		add(rarrow);
		add(larrow);
	}
}
