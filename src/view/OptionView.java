package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.bean.OptionBean;
import view.bean.Options;
import view.etc.ChangePanelService;
import view.etc.Sound;
import view.handler.FocusHandler;

public class OptionView extends JPanel {
	// private JButton[] buttons;
	private JLabel[] rarrows;
	private JLabel[] larrows;
	private int index;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private int[] optionValue;
	private JButton[][] btn;
	private JButton[] name;
	private JButton back;
	private JButton[] slash;
	private Options option;

	public OptionView() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		option = new Options();

		Font font = new Font("Nanum Brush Script", Font.BOLD, 50);
		Color bg = new Color(251, 229, 214); // 배경 색
		Color fg = new Color(255, 80, 80);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		setLayout(gbl);
		setFont(font);
		setBackground(bg); // 판넬의 배경화면 색 설정
		this.addComponentListener(new FocusHandler());
		this.addKeyListener(new ArrowsHandler());

		ImageIcon rightArrow = new ImageIcon("image/left_o.png"); // 오른쪽을 가리키는 화살표
		ImageIcon leftArrow = new ImageIcon("image/right_o.png"); // 왼쪽을 가리키는 화살표

		rarrows = new JLabel[] { new JLabel(rightArrow), new JLabel(rightArrow), new JLabel(rightArrow),
				new JLabel(rightArrow), new JLabel(rightArrow) };
		larrows = new JLabel[] { new JLabel(leftArrow), new JLabel(leftArrow), new JLabel(leftArrow),
				new JLabel(leftArrow), new JLabel(leftArrow) };

		for (int i = 0; i < rarrows.length; i++) {
			rarrows[i].setVisible(false);
			larrows[i].setVisible(false);
		}
		rarrows[0].setVisible(true);
		larrows[0].setVisible(true);

		ArrowsHandler l = new ArrowsHandler();
		MouseHandler ml = new MouseHandler();

		name = new JButton[] { new JButton("배경음"), new JButton("효과음"), new JButton("카드덱") };
		for (int i = 0; i < 3; i++) {
			name[i].setFont(font);
			name[i].addMouseListener(ml);
			name[i].addKeyListener(l);
			name[i].setContentAreaFilled(false);
			name[i].setFocusPainted(false);
			name[i].setBorderPainted(false);
		}

		back = new JButton("뒤로 가기");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setFont(font);
		back.addMouseListener(ml);
		back.addActionListener(l);
		back.addKeyListener(l);

		// buttons=new JButton[] {new JButton("ON"), new JButton("/"), new
		// JButton("OFF"), new JButton("ON"), new JButton("/"), new JButton("OFF"), new
		// JButton("3색"), new JButton("/"), new JButton("5색"), new JButton("10장"), new
		// JButton("20장"), new JButton("40장")};
		btn = new JButton[][] { new JButton[2], new JButton[2], new JButton[3] };
		slash = new JButton[] { new JButton("/"), new JButton("/"), new JButton("/"), new JButton("/") };
		for (int i = 0; i < slash.length; i++) {
			slash[i].setFont(font);
			slash[i].setContentAreaFilled(false);
			slash[i].setFocusPainted(false);
			slash[i].setBorderPainted(false);
			slash[i].addMouseListener(ml);
			slash[i].addKeyListener(l);
		}

		for (int i = 0; i < 2; i++) {
			btn[i][0] = new JButton("ON");
			btn[i][1] = new JButton("OFF");
		}
		btn[2][0] = new JButton("10장");
		btn[2][1] = new JButton("20장");
		btn[2][2] = new JButton("40장");

		for (int i = 0; i < btn.length; i++) {
			for (int j = 0; j < btn[i].length; j++) {
				btn[i][j].setContentAreaFilled(false);
				btn[i][j].setFocusPainted(false);
				btn[i][j].setBorderPainted(false);
				btn[i][j].setFont(font);
				// btn[i][j].setForeground(fg);
				btn[i][j].addKeyListener(l);
				// btn[i][j].setSize(200, 200);
				btn[i][j].addMouseListener(ml);
				btn[i][j].addActionListener(l);
			}
		}

		OptionBean ob = option.getOption();
		optionValue = new int[3];
		optionValue[0] = ob.isBgm() ? 0 : 1;
		optionValue[1] = ob.isEffect() ? 0 : 1;
		optionValue[2] = ob.getCardNum() == 10 ? 0 : ob.getCardNum() == 20 ? 1 : 2;
		option.setOption(ob.isBgm(), ob.isEffect(), ob.getCardNum());

		btn[0][optionValue[0]].setForeground(fg);
		btn[1][optionValue[1]].setForeground(fg);
		btn[2][optionValue[2]].setForeground(fg);

		addGrid(rarrows[0], 0, 0, 1, 1);
		addGrid(name[0], 1, 0, 1, 1);
		addGrid(btn[0][0], 2, 0, 2, 1);
		addGrid(slash[0], 4, 0, 1, 1);
		addGrid(btn[0][1], 5, 0, 2, 1);
		addGrid(larrows[0], 7, 0, 1, 1);

		addGrid(rarrows[1], 0, 1, 1, 1);
		addGrid(name[1], 1, 1, 1, 1);
		addGrid(btn[1][0], 2, 1, 2, 1);
		addGrid(slash[1], 4, 1, 1, 1);
		addGrid(btn[1][1], 5, 1, 2, 1);
		addGrid(larrows[1], 7, 1, 1, 1);

		addGrid(rarrows[2], 0, 2, 1, 1);
		addGrid(name[2], 1, 2, 1, 1);
		addGrid(btn[2][0], 2, 2, 1, 1);
		addGrid(slash[2], 3, 2, 1, 1);
		addGrid(btn[2][1], 4, 2, 1, 1);
		addGrid(slash[3], 5, 2, 1, 1);
		addGrid(btn[2][2], 6, 2, 1, 1);
		addGrid(larrows[2], 7, 2, 1, 1);

		addGrid(rarrows[3], 0, 3, 1, 1);
		addGrid(back, 1, 3, 6, 1);
		addGrid(larrows[3], 7, 3, 1, 1);

		// setSize(1364, 714); // 판넬의 크기 설정
		// setVisible(true); // 판넬의 시각화 설정
	}

	private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	class ArrowsHandler extends KeyAdapter implements ActionListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (index == 3) {
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index = 0;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				} else if (index != 3) {
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index += 1;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (index == 0) {
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index = 3;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				} else if (index != 0) {
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index -= 1;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (index != 3 && optionValue[index] != 0) {
					btn[index][optionValue[index]].setForeground(new Color(0, 0, 0));
					optionValue[index] -= 1;
					btn[index][optionValue[index]].setForeground(new Color(255, 80, 80));
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (index != 3 && optionValue[index] != btn[index].length - 1) {
					btn[index][optionValue[index]].setForeground(new Color(0, 0, 0));
					optionValue[index] += 1;
					btn[index][optionValue[index]].setForeground(new Color(255, 80, 80));
				}
			} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				if (index == 3) {
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index = 0;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
					actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
				}
			}
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("\n") || index == 3) {
				boolean bgm = optionValue[0] == 0 ? true : false;
				boolean effect = optionValue[1] == 0 ? true : false;
				int cardNum = optionValue[2] == 0 ? 10 : optionValue[2] == 1 ? 20 : 40;

				if (bgm)
					Sound.bgmOn("audio/mainBGM.wav");
				else
					Sound.bgmOff();

				option.setOption(bgm, effect, cardNum);
				option.saveOption();

				ChangePanelService cps = ChangePanelService.getInstance();
				cps.changePanel("MainView");
				Sound.playEffect("audio/enter.wav");
			} else {
				for (int i = 0; i < btn.length; i++) {
					for (int j = 0; j < btn[i].length; j++) {
						if (e.getSource() == btn[i][j]) {
							btn[i][optionValue[i]].setForeground(new Color(0, 0, 0));
							btn[i][j].setForeground(new Color(255, 80, 80));
							optionValue[i] = j;
						}
					}
				}
			}
		}
	}

	class MouseHandler extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			Sound.playEffect("audio/touch2.wav");
			for (int i = 0; i < btn.length; i++) {
				if (e.getSource() == name[i]) {
					changeIndex(i);
					return;
				}
				for (int j = 0; j < btn[i].length; j++) {
					if (e.getSource() == btn[i][j]) {
						changeIndex(i);
						return;
					}
				}
			}
			for (int i = 0; i < slash.length; i++) {
				if (e.getSource() == slash[i]) {
					if (i == slash.length - 1)
						i -= 1;
					changeIndex(i);
					return;
				}
			}
			changeIndex(3);
		}

		private void changeIndex(int i) {
			larrows[index].setVisible(false);
			rarrows[index].setVisible(false);
			larrows[i].setVisible(true);
			rarrows[i].setVisible(true);
			index = i;
		}
	}
}
