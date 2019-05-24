package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import view.etc.ChangePanelService;
import view.handler.FocusHandler;

public class WaitingRoomNormal extends JPanel {
	private int roomNum;
	private JLabel roomNumLabel;
	// private JButton startButton;
	private JButton backButton;

	public WaitingRoomNormal() {
		this.addComponentListener(new FocusHandler());
		makeUI();
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
		roomNumLabel.setText("방번호 : " + roomNum);
	}

	private void makeUI() {
		Color backgroundColor = new Color(251, 229, 214);
		Font font = new Font("BM HANNA 11yrs old", Font.BOLD, 35);
		Border border = new LineBorder(Color.BLACK);
		this.setLayout(null);

		Random r = new Random();
		// roomNum = ChangePanelService.getInstance().getNum();
		while (roomNum < 1000) {
			roomNum = r.nextInt(10000);
		}

		roomNumLabel = new JLabel("방번호 : " + roomNum, SwingConstants.CENTER);

		roomNumLabel.setFont(font);
		roomNumLabel.setBounds(530, 50, 300, 40);

		add(roomNumLabel);
		// 방번호 화면 출력부분

		JPanel playerPanel = new JPanel(new GridLayout(4, 0, 0, 10));
		JLabel[] playersLabel = new JLabel[] { new JLabel("용용죽겠지", SwingConstants.CENTER),
				new JLabel("vV민성Vv", SwingConstants.CENTER), new JLabel("준희어3세", SwingConstants.CENTER),
				new JLabel("도균했다..", SwingConstants.CENTER) };

		for (int i = 0; i < playersLabel.length; i++) {
			playersLabel[i].setFont(font);
			playersLabel[i].setBorder(border);
			playerPanel.add(playersLabel[i]);
		}
		playerPanel.setOpaque(false);
		playerPanel.setBounds(500, 150, 440, 400);
		add(playerPanel);

		backButton = new JButton(new ImageIcon("image/back.png"));
		backButton.setBounds(380, 30, 90, 70);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePanelService cps = ChangePanelService.getInstance();
				cps.changePanel("NetworkMode");
			}
		});

		// startButton = new JButton(new ImageIcon("image/startBtn.png"));
		//// startButton.setFont(font);
		// startButton.setBounds(580, 580, 211, 70);
		// startButton.setBorderPainted(false);
		// startButton.setContentAreaFilled(false);
		// startButton.setFocusPainted(false);
		//// startButton.addMouseListener(new MouseHandler());
		// startButton.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// ChangePanelService cps = ChangePanelService.getInstance();
		// cps.changePanel("NetworkPlayMode");
		// }
		// });
		// this.add(startButton);
		this.add(backButton);

		JLabel my = new JLabel(new ImageIcon("image/my.png"));
		my.setBounds(870, 235, 80, 80);
		add(my);

		JLabel[] imageLabel = new JLabel[4];

		for (int i = 0; i < imageLabel.length; i++) {
			imageLabel[i] = new JLabel(new ImageIcon("image/emoticon" + (i + 1) + ".png"));
			add(imageLabel[i]);
			imageLabel[i].setBounds(370, 150 + i * 102, 93, 93);
		}

		this.setBackground(backgroundColor);
	}
	// private class MouseHandler extends MouseAdapter {
	//
	// public void mousePressed(MouseEvent e) {
	// startButton.setIcon(new ImageIcon("image/startBtn(press).png"));
	// }
	// public void mouseReleased(MouseEvent e) {
	// startButton.setIcon(new ImageIcon("image/startBtn.png"));
	// }
	// }
}
