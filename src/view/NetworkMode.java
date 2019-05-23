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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.etc.ChangePanelService;
import view.handler.FocusHandler;
import view.handler.KeyUpDownHandler;
import view.handler.MouseEnteredHandler;

public class NetworkMode extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton[] b;
	private MyIndex cor;
	private JLabel[] ll;
	private JLabel[] rl;
	private JLabel nickName;

	public NetworkMode() {
		cor = new MyIndex();
		this.addComponentListener(new FocusHandler());
		makeUI();
		this.addKeyListener(new KeyUpDownHandler(cor, 3, ll, rl));
		this.setSize(1363, 714);
	}

	private void makeUI() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel();

		p.setBackground(new Color(251, 229, 214));
		p.setLayout(null);
		JPanel p2 = new JPanel();
		// p2.setBackground(Color.gray);
		p2.setBackground(new Color(251, 229, 214));
		p2.setLayout(new GridLayout(4, 3));
		p2.setBounds(431, 300, 500, 350);
		p.add(p2);

		JLabel info = new JLabel(new ImageIcon("image/net_info.png"));
		info.setBounds(421, 20, 520, 200);
		p.add(info);

		nickName = new JLabel("닉네임 : nickname", SwingConstants.CENTER);
		nickName.setFont(new Font("BM HANNA 11yrs old", Font.BOLD, 30));
		nickName.setBounds(421, 200, 520, 100);
		p.add(nickName);

		b = new JButton[4];
		Font font = new Font("Nanum Brush Script", Font.BOLD, 40);
		Handler l = new Handler();
		// p.addKeyListener(l);
		// p2.addKeyListener(l);
		b[0] = new JButton("방만들기");
		b[1] = new JButton("입장하기");
		b[2] = new JButton("별명변경");
		b[3] = new JButton("뒤로가기");
		for (int i = 0; i < 4; i++) {
			b[i].setBorderPainted(false);
			b[i].setContentAreaFilled(false);
			b[i].setFocusPainted(false);
			b[i].setFont(font);
			b[i].setForeground(new Color(255, 80, 80));
			b[i].addActionListener(l);
			b[i].addKeyListener(l);
		}

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
			// ll[i].addKeyListener(l);
			rl[i].setVisible(false);
			// rl[i].addKeyListener(l);
		}
		ll[0].setVisible(true);
		rl[0].setVisible(true);

		MouseEnteredHandler ml = new MouseEnteredHandler(b, ll, rl, cor);
		for (int i = 0; i < 4; i++) {
			b[i].addMouseListener(ml);
		}

		add(p);
		addKeyListener(l);
	}

	class Handler extends KeyAdapter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("enter");
			if (cor.getIndex() == 0) {
				ChangePanelService cps = ChangePanelService.getInstance();
				cps.changePanel("WaitingRoomCrown");
			}
			if (cor.getIndex() == 1) {
				String num = JOptionPane.showInputDialog("방번호를 입력해주세요.(4자리)");
				if (num != null) {
					while (num.length() != 4) {
						num = JOptionPane.showInputDialog("방번호를 입력해주세요.(4자리)");
						if (num == null)
							return;
					}
					ChangePanelService cps = ChangePanelService.getInstance();
					WaitingRoomNormal wrn = new WaitingRoomNormal();
					wrn.setRoomNum(Integer.parseInt(num));
					cps.addPanel("WaitingRoomNormal", wrn);
					cps.changePanel("WaitingRoomNormal");
				}
			} else if (cor.getIndex() == 2) {
				String name = JOptionPane.showInputDialog("닉네임을 입력해주세요.\n(닉네임 유효조건 : 1~10글자)");
				if (name != null) {
					while (name.length() < 1 || name.length() >= 11) {
						name = JOptionPane.showInputDialog("닉네임을 유효조건에 어긋납니다. 다시 입력해주세요.\n(닉네임 유효조건 : 1~10글자)");
						if (name == null)
							return;
					}
					nickName.setText("닉네임 : " + name);
				}
			} else if (cor.getIndex() == 3) {
				ChangePanelService cps = ChangePanelService.getInstance();
				cps.changePanel("GameMode");
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
			}
		}
	}
}
