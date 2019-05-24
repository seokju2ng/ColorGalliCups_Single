package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.bean.RankBean;
import view.bean.Ranks;
import view.etc.ChangePanelService;

public class RankView extends JDialog {
	
	private Ranks r;
	private JLabel[][] ranks;
	/* 
	 * Edit by Seokju2ng 0525
	 * */
	public RankView() {
		super.setTitle("Rank");
		setLayout(null);

		r = new Ranks();
		
		Font cfont = new Font("배달의민족 한나체 Pro", Font.BOLD, 30);
		Font font = new Font("배달의민족 한나체 Pro", Font.PLAIN, 20);

		JPanel panel = new JPanel(new GridLayout(0, 3, 0, 0));
		panel.setBackground(new Color(250, 250, 250));
		panel.setBounds(100, 100, 400, 400);

		JLabel[] cols = new JLabel[] { new JLabel("순위", SwingConstants.CENTER), new JLabel("이름", SwingConstants.CENTER),
				new JLabel("점수", SwingConstants.CENTER) };
		
		ranks = new JLabel[r.getRanks().size()][3];
		
		panel.add(cols[0]);	panel.add(cols[1]);	panel.add(cols[2]);
		
		for(int i = 0; i < ranks.length; i++) {
			ranks[i] = new JLabel[3];
			RankBean rank = r.getRanks().get(i);
			ranks[i][0] = new JLabel(rank.getRanking()+"", SwingConstants.CENTER);
			ranks[i][1] = new JLabel(rank.getName()+"", SwingConstants.CENTER);
			ranks[i][2] = new JLabel(rank.getScore()+"", SwingConstants.CENTER);
			for(int j = 0; j < 3; j++) {
				ranks[i][j].setFont(font);
				panel.add(ranks[i][j]);
			}
		}
		panel.setBounds(40, 25, 600, 300);
		add(panel, BorderLayout.CENTER);

		// buttons: 초기화 버튼, 확인 버튼
		JButton reset = new JButton("초기화");
		JButton check = new JButton("확인");

		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
		reset.setContentAreaFilled(false);
		reset.setFocusPainted(false);
		reset.setBorderPainted(false);
		check.setContentAreaFilled(false);
		check.setFocusPainted(false);
		check.setBorderPainted(false);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "정말로 초기화하시겠습니까?", "랭크 초기화", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					if(r.clearRanking()) {
						for(int i = 0; i < ranks.length; i++) {
							ranks[i][0].setText("");
							ranks[i][1].setText("");
							ranks[i][2].setText("");
						}
					}
				}
			}
		});
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ChangePanelService.getInstance().changePanel("MainView"); //확인 버튼 누르면 메인뷰로 돌아간다.
			}
		});

		panel2.add(reset);
		panel2.add(check);

		panel2.setBounds(40, 320, 600, 75);
		add(panel2, BorderLayout.SOUTH);

		setSize(700, 450);
		setVisible(true);
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public RankView(String name,int score) {
		this();
		if(r.insert(name, score)) {
			r = new Ranks();
		}
		for(int i = 0; i < ranks.length; i++) {
			RankBean rank = r.getRanks().get(i);
			ranks[i][0].setText(rank.getRanking()+"");
			ranks[i][1].setText(rank.getName());
			ranks[i][2].setText(rank.getScore()+"");
		}
	}
//	public RankView(String name,int score) {
//		// super(f, "Rank", true);
//		//isCheckClicked = false;
//		super.setTitle("Rank");
//		setLayout(null);
//       //Edit by DK Kim 0524//
//		ArrayList<RankBean> rankList; //rankview에서 보여줄 랭크목록
//		r = new Ranks();
//		r.insert(name, score);
//		rankList =  r.getRanks();
//		//////////////////
//
//		Font cfont = new Font("배달의민족 한나체 Pro", Font.BOLD, 30);
//		Font font = new Font("배달의민족 한나체 Pro", Font.PLAIN, 20);
//
//		JPanel panel = new JPanel(new GridLayout(0, 3, 0, 0));
//		panel.setBackground(new Color(250, 250, 250));
//		panel.setBounds(100, 100, 400, 400);
//
//		JLabel[] cols = new JLabel[] { new JLabel("순위", SwingConstants.CENTER), new JLabel("이름", SwingConstants.CENTER),
//				new JLabel("점수", SwingConstants.CENTER) };
////		JLabel[] ranks = new JLabel[] { new JLabel("1", SwingConstants.CENTER), new JLabel("2", SwingConstants.CENTER),
////				new JLabel("3", SwingConstants.CENTER), new JLabel("4", SwingConstants.CENTER),
////				new JLabel("5", SwingConstants.CENTER) };
//
//		//Edit by DK Kim 0524//
//		JLabel[] names = new JLabel[5];
//		for(int i = 0 ; i < 5 ; i++) {
//			names[i] = new JLabel();
//		}
//		for(int i = 0 ;  i < rankList.size() ;i++) {
//			names[i].setText(rankList.get(i).getName());
//		}
//		
//		JLabel[] cards = new JLabel[5];
//		for(int i = 0 ; i < 5 ; i++) {
//			cards[i] = new JLabel();
//		}
//		for(int i = 0 ;  i < rankList.size() ;i++) {
//			cards[i].setText(""+rankList.get(i).getScore());
//		}
//		///////////////////////////////
//
//		for (int i = 0; i < cols.length; i++) {
//			cols[i].setFont(cfont);
//			panel.add(cols[i]);
//		}
////		for (int i = 0; i < ranks.length; i++) {
////			ranks[i].setFont(font);
////			names[i].setFont(font);
////			cards[i].setFont(font);
////			panel.add(ranks[i]);
////			panel.add(names[i]);
////			panel.add(cards[i]);
////		}
//		panel.setBounds(40, 25, 600, 300);
//		add(panel, BorderLayout.CENTER);
//
//		// buttons: 초기화 버튼, 확인 버튼
//		// JButton reset=new JButton(new ImageIcon("image\\reset.png"));
//		// JButton check=new JButton(new ImageIcon("image\\check.png"));
//		JButton reset = new JButton("초기화");
//		JButton check = new JButton("확인");
//
//		JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
//		reset.setContentAreaFilled(false);
//		reset.setFocusPainted(false);
//		reset.setBorderPainted(false);
//		check.setContentAreaFilled(false);
//		check.setFocusPainted(false);
//		check.setBorderPainted(false);
//		reset.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int result = JOptionPane.showConfirmDialog(null, "정말로 초기화하시겠습니까?", "랭크 초기화", JOptionPane.YES_NO_OPTION,
//						JOptionPane.WARNING_MESSAGE);
//				if (result == JOptionPane.YES_OPTION) {
//					for (int i = 0; i < names.length; i++) {
//						names[i].setText("");
//						cards[i].setText("");
//					}
//				}
//			}
//		});
//		check.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				ChangePanelService.getInstance().changePanel("MainView"); //확인 버튼 누르면 메인뷰로 돌아간다.
//			}
//		});
//
//		panel2.add(reset);
//		panel2.add(check);
//
//		panel2.setBounds(40, 320, 600, 75);
//		add(panel2, BorderLayout.SOUTH);
//
//		setSize(700, 450);
//		setVisible(true);
//		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//	}
//	public boolean getIsCheckClicked() {
//		return isCheckClicked;
//	}
}
