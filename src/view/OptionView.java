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

/**
 * Option 메뉴들을 보여주는 OptionView 클래스이다.
 * 
 * @author 송준희
 */
public class OptionView extends JPanel {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 선택한 메뉴를 오른쪽에서 가리키는 화살표를 저장한다.
	 */
	private JLabel[] rarrows;
	/**
	 * 선택한 메뉴를 왼쪽에서 가리키는 화살표를 저장한다.
	 */
	private JLabel[] larrows;
	/**
	 * 사용자가 어떤 메뉴를 선택했는지에 대한 index를 저장한다.
	 */
	private int index;
	/**
	 * GridBagLayout을 사용하기 위한 변수이다.
	 */
	private GridBagLayout gbl;
	/**
	 * GridBagConstraints을 사용하기 위한 변수이다.
	 */
	private GridBagConstraints gbc;
	/**
	 * 사용자가 설정한 Option값들을 저장한다.
	 */
	private int[] optionValue;
	/**
	 * 사용자가 선택할 Option 사항을 버튼 형태로 저장한다.
	 */
	private JButton[][] btn;
	/**
	 * 사용자가 선택하는 Option 사항의 이름들을 버튼 형태로 저장한다.
	 */
	private JButton[] name;
	/**
	 * 사용자가 선택할 Option 사항을 구분해주는 '/'를 저장한다.
	 */
	private JButton[] slash;
	/**
	 * 사용자가 선택한 Option 사항을 저장한다.
	 */
	private Options option;

	/**
	 * null parameter constructor로 OptionView 패널을 만들어 사용자에게 보여준다.
	 */
	public OptionView() {
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		option = new Options();
		Font font = new Font("Nanum Brush Script", Font.BOLD, 50);
		Color bg = new Color(251, 229, 214);
		Color fg = new Color(255, 80, 80);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		setLayout(gbl);
		setFont(font);
		setBackground(bg);

		this.addComponentListener(new FocusHandler());
		this.addKeyListener(new ArrowsHandler());

		ImageIcon rightArrow = new ImageIcon("image/left_o.png");
		ImageIcon leftArrow = new ImageIcon("image/right_o.png");
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

		JButton back = new JButton("뒤로 가기");
		back.setContentAreaFilled(false);
		back.setFocusPainted(false);
		back.setBorderPainted(false);
		back.setFont(font);
		back.addMouseListener(ml);
		back.addActionListener(l);
		back.addKeyListener(l);

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
				btn[i][j].addKeyListener(l);
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
	}

	/**
	 * 사용자가 패널에 컴포넌트를 넣는 메소드이다.
	 * 
	 * @param c
	 *            사용자가 패널에 넣을 컴포넌트를 저장한다.
	 * @param gridx
	 *            사용자가 넣을 컴포넌트의 x축 좌표를 저장한다.
	 * @param gridy
	 *            사용자가 넣을 컴포넌트의 y축 좌표를 저장한다.
	 * @param gridwidth
	 *            사용자가 넣을 컴포넌트의 너비를 저장한다.
	 * @param gridheight
	 *            사용자가 넣을 컴포넌트의 높이를 저장한다.
	 */
	private void addGrid(Component c, int gridx, int gridy, int gridwidth, int gridheight) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	/**
	 * OptionView의 Inner Class로 패널에 이벤트가 발생했을 때, 그 이벤트를 처리해주는 ArrowsHandler 클래스이다.
	 * 
	 * @author 송준희
	 */
	class ArrowsHandler extends KeyAdapter implements ActionListener {
		/**
		 * 사용자가 위아래 화살표 키를 입력했을 때, 선택한 메뉴를 화살표로 가리킨다.
		 */
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (index == 3) {
					Sound.playEffect("audio/touch2.wav");
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index = 0;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				} else if (index != 3) {
					Sound.playEffect("audio/touch2.wav");
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index += 1;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_UP) {
				if (index == 0) {
					Sound.playEffect("audio/touch2.wav");
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index = 3;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				} else if (index != 0) {
					Sound.playEffect("audio/touch2.wav");
					larrows[index].setVisible(false);
					rarrows[index].setVisible(false);
					index -= 1;
					larrows[index].setVisible(true);
					rarrows[index].setVisible(true);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (index != 3 && optionValue[index] != 0) {
					Sound.playEffect("audio/touch2.wav");
					btn[index][optionValue[index]].setForeground(new Color(0, 0, 0));
					optionValue[index] -= 1;
					btn[index][optionValue[index]].setForeground(new Color(255, 80, 80));
				}
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (index != 3 && optionValue[index] != btn[index].length - 1) {
					Sound.playEffect("audio/touch2.wav");
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

		/**
		 * 이벤트가 발생했을 때, Option 사항에 따라 사용자에게 Option 정보를 적용한다.
		 */
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

	/**
	 * OptionView의 Inner Class로 패널에 이벤트가 발생했을 때, 그 이벤트를 처리해주는 MouseHandler 클래스이다.
	 * 
	 * @author 송준희
	 *
	 */
	class MouseHandler extends MouseAdapter {
		/**
		 * 마우스가 선택한 메뉴로 들어가서 이벤트가 발생했을 때, 그 이벤트를 처리한다.
		 */
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

		/**
		 * 현재 보이는 라벨을 가리고 파라미터로 받은 값에 해당하는 순서의 라벨을 사용자에게 보여준다.
		 * @param i 입력받은 값을 저장한다.
		 */
		private void changeIndex(int i) {
			larrows[index].setVisible(false);
			rarrows[index].setVisible(false);
			larrows[i].setVisible(true);
			rarrows[i].setVisible(true);
			index = i;
		}
	}
}
