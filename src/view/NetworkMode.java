package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import etc.ChangePanelService;

public class NetworkMode extends JPanel{
	private JButton[] b;
	private int cor;
	private JLabel[] ll;
	private JLabel[] rl;
	private JLabel nickName;
	public NetworkMode() {
		//super("ColorGalli Cups");
		makeUI();
//		this.setVisible(true);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setSize(1363, 714);
	}
	private void makeUI() {
		setLayout(new BorderLayout());
		JPanel p = new JPanel();
		
		p.setBackground(new Color(251, 229, 214));
		p.setLayout(null);
		JPanel p2 = new JPanel();
		//p2.setBackground(Color.gray);
		p2.setBackground(new Color(251, 229, 214));
		p2.setLayout(new GridLayout(4,3));
		p2.setBounds(431,300,500,350);
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
		MouseHandler ml = new MouseHandler();
		Handler l = new Handler();
		//p.addKeyListener(l);
		//p2.addKeyListener(l);
		b[0] = new JButton("방만들기");
		b[1] = new JButton("입장하기");
		b[2] = new JButton("별명변경");
		b[3] = new JButton("뒤로가기");
		for(int i = 0; i < 4; i++) {
			b[i].setBorderPainted(false);
			b[i].setContentAreaFilled(false);
			b[i].setFocusPainted(false);
			b[i].setFont(font);
			b[i].setForeground(new Color(255, 80, 80));
			b[i].addActionListener(l);
			b[i].addKeyListener(l);
			b[i].addMouseListener(ml);
		}
		
		
		ImageIcon i1 = new ImageIcon("image/left.png");
		ImageIcon i2 = new ImageIcon("image/right.png");
		ll = new JLabel[4];
		rl = new JLabel[4];
		
		for(int i = 0; i < 4; i++) {
			ll[i] = new JLabel(i1);
			rl[i] = new JLabel(i2);
			p2.add(ll[i]);
			p2.add(b[i]);
			p2.add(rl[i]);
			ll[i].setVisible(false);
			//ll[i].addKeyListener(l);
			rl[i].setVisible(false);
			//rl[i].addKeyListener(l);
		}
		
		ll[0].setVisible(true);
		rl[0].setVisible(true);
		
		add(p);
		//p.setFocusable(true);
		p.addKeyListener(l);
		setFocusable(true);
		addKeyListener(l);
		
	}
	
	class Handler extends KeyAdapter implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("enter");
			ChangePanelService cps = ChangePanelService.getInstance();
			if(cor == 0) cps.changePanel("WatingRoomCrown");
			if(cor == 1) {
				String num = JOptionPane.showInputDialog("방번호를 입력해주세요.(4자리)");
				if(num != null) {
					while(num.length() != 4) num = JOptionPane.showInputDialog("방번호를 입력해주세요.(4자리)");
					cps.changePanel("WatingRoomNormal");
				}
			}
			else if(cor == 2) {
				String name = JOptionPane.showInputDialog("닉네임을 입력해주세요.\n(닉네임 유효조건 : 1~10글자)");
				if(name != null) {
					while(name.length() < 1 || name.length() >= 11) {
						name = JOptionPane.showInputDialog("닉네임을 유효조건에 어긋납니다. 다시 입력해주세요.\n(닉네임 유효조건 : 1~10글자)");
						if(name == null) return;
					}
					nickName.setText("닉네임 : "+name);
				}
			}
			else if(cor == 3) cps.changePanel("GameMode");
		}
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				System.out.println("down");
				if(cor != 2) {
					ll[cor].setVisible(false);
					rl[cor].setVisible(false);
					cor = cor+1;
					ll[cor].setVisible(true);
					rl[cor].setVisible(true);
				}
			} else if(e.getKeyCode() == KeyEvent.VK_UP) {
				System.out.println("up");
				if(cor != 0) {
					ll[cor].setVisible(false);
					rl[cor].setVisible(false);
					cor = cor-1;
					ll[cor].setVisible(true);
					rl[cor].setVisible(true);
				}
			} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
			}
		}
	}

	   class MouseHandler extends MouseAdapter{
		   public void mouseEntered(MouseEvent e) {
			   for(int i = 0; i < 4; i++) {
					if(e.getSource() == b[i]) {
						ll[cor].setVisible(false);
						rl[cor].setVisible(false);
						ll[i].setVisible(true);
						rl[i].setVisible(true);
						cor = i;
						break;
					}
				}
		   }
	   }
//	
//	public static void main(String[] args) {
//		new Help();
//	}
}
