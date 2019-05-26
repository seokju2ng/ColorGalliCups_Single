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
import view.handler.FocusHandler;
import view.handler.KeyUpDownHandler;
import view.handler.MouseEnteredHandler;

public class MainView extends JPanel {
	private static final long serialVersionUID = 1L;
	//private JButton menuArr[];
	//private JLabel leftCursorArr[];
	//private JLabel rightCursorArr[];
	private MyIndex cor;
	//private RankView rank;

	public MainView() {
		cor = new MyIndex();
		this.addComponentListener(new FocusHandler());
		this.addKeyListener(new Handler());
		setLayout(new BorderLayout());
		makeUI();
		Sound.playBgm("audio/mainBGM.wav");
		//this.setSize(1363, 714);
	}

	private void makeUI() {
		JButton[] menuArr = new JButton[5];
		menuArr[0] = new JButton("Game Start");
		menuArr[1] = new JButton("Single Rank");
		menuArr[2] = new JButton("Option");
		menuArr[3] = new JButton("Help");
		menuArr[4] = new JButton("Exit");
		//RankView rank = new RankView();

		ImageIcon leftCursorImage = new ImageIcon("image/LeftCursor.png");
		ImageIcon rightCursorImage = new ImageIcon("image/RightCursor.png");

		JLabel[] leftCursorArr = new JLabel[] { new JLabel(leftCursorImage), new JLabel(leftCursorImage),
				new JLabel(leftCursorImage), new JLabel(leftCursorImage), new JLabel(leftCursorImage) };
		JLabel[] rightCursorArr = new JLabel[] { new JLabel(rightCursorImage), new JLabel(rightCursorImage),
				new JLabel(rightCursorImage), new JLabel(rightCursorImage), new JLabel(rightCursorImage) };

		for (int i = 0; i < 5; i++) {
			leftCursorArr[i].setBounds(450, 280 + 60 * i, 100, 100);
			leftCursorArr[i].setVisible(false);
			add(leftCursorArr[i]);
		}
		for (int i = 0; i < 5; i++) {
			rightCursorArr[i].setBounds(790, 280 + 60 * i, 100, 100);
			rightCursorArr[i].setVisible(false);
			add(rightCursorArr[i]);
		}
		Font font = new Font("Nanum Brush Script", Font.PLAIN, 60);
		JPanel panel = new JPanel(new GridLayout(5, 0));
		Handler l = new Handler();
		MouseEnteredHandler ml = new MouseEnteredHandler(menuArr, leftCursorArr, rightCursorArr, cor);
		for (JButton b : menuArr) {
			b.setBorderPainted(false);
			b.setContentAreaFilled(false);
			b.setFocusPainted(false);
			b.setFont(font);
			b.setForeground(new Color(80, 80, 180));
			b.addActionListener(l);
			b.addKeyListener(l);
			b.addMouseListener(ml);
			b.addKeyListener(new KeyUpDownHandler(cor, 4, leftCursorArr, rightCursorArr));
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

		// for(JLabel a : labelArr) {
		// panel.add(a);
		// //a.addKeyListener(new Handler());
		// }
		panel.setOpaque(false);
		background.add(panel);
		background.setLayout(null);
		panel.setBounds(500, 300, 350, 300);
		background.setOpaque(false);
		//panel.addKeyListener(new Handler());
		//panel.addKeyListener(new KeyUpDownHandler(cor, 4, leftCursorArr, rightCursorArr));
		this.addKeyListener(new KeyUpDownHandler(cor, 4, leftCursorArr, rightCursorArr));
		this.add(background);
	}

	class Handler extends KeyAdapter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ChangePanelService cps = ChangePanelService.getInstance();
			if (cor.getIndex() == 0)
				cps.changePanel("GameMode");
			else if (cor.getIndex() == 1)
				//rank.setVisible(true);
				new RankView();
			else if (cor.getIndex() == 2)
				cps.changePanel("Option");
			else if (cor.getIndex() == 3)
				cps.changePanel("Help");
			else if (cor.getIndex() == 4)
				System.exit(0);
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
			}
		}
	}

}
