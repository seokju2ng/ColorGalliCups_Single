package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.bean.RankBean;
import view.bean.Ranks;
import view.etc.ChangePanelService;
/**
 * 랭크 리스트를 팝업으로 보여주는 RankView 클래스이다. 랭크 리스트에는 순위와 이름, 점수가 등록되어 있다.
 * @author seokjung
 *
 */
public class RankView extends JDialog {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 랭크 리스트 정보를 RankController를 통해 받아와 저장하는 view bean의 컬렉션 객체이다.
	 */
	private Ranks r;
	/**
	 * 랭크 정보를 view에서 보여줄 JLabel 배열이다.
	 * 한 행에는 순위, 이름, 점수가 JLabel 텍스트로 가지고 있다.
	 */
	private JLabel[][] ranks;
	/**
	 * bgm을 바꿔야하는지 안바꿔도 되는지 체크할 flag 변수이다.
	 */
	private boolean bgmFlag;
	
	/* 
	 * Edit by Seokju2ng 0525
	 * */
	
	/**
	 * Null-Parameter Constructor : MainView에서 RankView에 접근했을 때 팝업창을 보여주는 생성자이다.
	 * Ranks 객체로 RankController를 통해 파일에 저장된 랭크 정보를 불러온다. 
	 */
	public RankView() {
		super.setTitle("Rank");
		setLayout(null);

		r = Ranks.getInstance();
		bgmFlag = false;
		
//		Font cfont = new Font("배달의민족 한나체 Pro", Font.BOLD, 30);
		Font font = new Font("배달의민족 한나체 Pro", Font.PLAIN, 20);

		JPanel panel = new JPanel(new GridLayout(0, 3, 0, 0));
		panel.setBackground(new Color(250, 250, 250));
		panel.setBounds(100, 100, 400, 400);

		JLabel[] cols = new JLabel[] { new JLabel("순위", SwingConstants.CENTER), new JLabel("이름", SwingConstants.CENTER),
				new JLabel("점수", SwingConstants.CENTER) };
		
		ranks = new JLabel[5][3];
		
		panel.add(cols[0]);	panel.add(cols[1]);	panel.add(cols[2]);
		for(int i = 0; i < ranks.length; i++) {
			ranks[i][0] = new JLabel("", SwingConstants.CENTER);
			ranks[i][1] = new JLabel("", SwingConstants.CENTER);
			ranks[i][2] = new JLabel("", SwingConstants.CENTER);
			for(int j = 0; j < 3; j++) {
				ranks[i][j].setFont(font);
				panel.add(ranks[i][j]);
			}
		}
		
		for(int i = 0; i < r.getRanks().size(); i++) {
			RankBean rank = r.getRanks().get(i);
			ranks[i][0].setText(rank.getRanking()+"");
			ranks[i][1].setText(rank.getName());
			ranks[i][2].setText(rank.getScore()+"");
			
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
				if(bgmFlag)
					ChangePanelService.getInstance().changePanel("MainView", null); //확인 버튼 누르면 메인뷰로 돌아간다.
				else ChangePanelService.getInstance().changePanel("MainView");
			}
		});
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(bgmFlag)
					ChangePanelService.getInstance().changePanel("MainView", null); //확인 버튼 누르면 메인뷰로 돌아간다.
				else ChangePanelService.getInstance().changePanel("MainView");
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
	/**
	 * Overloaded Constructor : SinglePlayMode에서 RankView에 접근했을 때 팝업창을 보여주는 생성자이다.
	 * 싱글 게임이 종료했을 때 랭크 5위 안에 들었다면, 파라메터를 통해 삽입할 랭킹정보를 받아와 랭킹에 삽입한다.
	 * 삽입을 마친 후 Ranks 객체로 RankController를 통해 파일에 저장된 랭크 정보를 불러온다.
	 * @param name 랭킹 정보에 삽입될 이름이다.
	 * @param score 랭킹 정보에 삽입될 점수이다.
	 */
	public RankView(String name,int score) {
		this();
		bgmFlag = true;
		r.insertRank(name, score);
//		if(r.insertRank(name, score)) {
//			r = Ranks.getInstance();
//		}
		int n = r.getRanks().size();
		for(int i = 0; i < n; i++) {
			RankBean rank = r.getRanks().get(i);
			ranks[i][0].setText(rank.getRanking()+"");
			ranks[i][1].setText(rank.getName());
			ranks[i][2].setText(rank.getScore()+"");
		}
	}
}
