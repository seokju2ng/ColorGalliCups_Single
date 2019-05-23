package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class RankView extends JDialog {
   public RankView() {
	   //super(f, "Rank", true);
      super.setTitle("Rank");
      setLayout(null);
      
      Font cfont=new Font("배달의민족 한나체 Pro", Font.BOLD, 30);
      Font font=new Font("배달의민족 한나체 Pro", Font.PLAIN, 20);
      
      JPanel panel=new JPanel(new GridLayout(0,3,0,0));
      panel.setBackground(new Color(250,250,250));
      panel.setBounds(100,100,400,400);
      
      JLabel[] cols=new JLabel[] {new JLabel("순위", SwingConstants.CENTER), new JLabel("이름", SwingConstants.CENTER), new JLabel("점수", SwingConstants.CENTER)};
      JLabel[] ranks=new JLabel[] {new JLabel("1", SwingConstants.CENTER), new JLabel("2", SwingConstants.CENTER), new JLabel("3", SwingConstants.CENTER), new JLabel("4", SwingConstants.CENTER), new JLabel("5", SwingConstants.CENTER)};
      JLabel[] names=new JLabel[] {new JLabel("김도균", SwingConstants.CENTER), new JLabel("김용희", SwingConstants.CENTER), new JLabel("최민성", SwingConstants.CENTER), new JLabel("김석중", SwingConstants.CENTER), new JLabel("송준희", SwingConstants.CENTER)};
      JLabel[] cards=new JLabel[] {new JLabel("13", SwingConstants.CENTER), new JLabel("12", SwingConstants.CENTER), new JLabel("10", SwingConstants.CENTER), new JLabel("7", SwingConstants.CENTER), new JLabel("5", SwingConstants.CENTER)};
      
      for(int i=0;i<cols.length;i++) {
         cols[i].setFont(cfont);
         panel.add(cols[i]);
      }
      for(int i=0;i<ranks.length;i++) {
         ranks[i].setFont(font);
         names[i].setFont(font);
         cards[i].setFont(font);
         panel.add(ranks[i]);
         panel.add(names[i]);
         panel.add(cards[i]);
      }
      panel.setBounds(40, 25, 600, 300);
      add(panel, BorderLayout.CENTER);
      
      // buttons: 초기화 버튼, 확인 버튼      
      //JButton reset=new JButton(new ImageIcon("image\\reset.png"));
      //JButton check=new JButton(new ImageIcon("image\\check.png"));
      JButton reset=new JButton("초기화");
      JButton check=new JButton("확인");
      
      JPanel panel2=new JPanel(new FlowLayout(FlowLayout.CENTER, 80, 20));
      reset.setContentAreaFilled(false);
      reset.setFocusPainted(false);
      reset.setBorderPainted(false);
      check.setContentAreaFilled(false);
      check.setFocusPainted(false);
      check.setBorderPainted(false);
      reset.addActionListener(new ActionListener() {
    	  	public void actionPerformed(ActionEvent e) {
	         int result = JOptionPane.showConfirmDialog(null, "정말로 초기화하시겠습니까?", "랭크 초기화", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	         if(result == JOptionPane.YES_OPTION) {
	            for(int i = 0;i<names.length;i++) {
	               names[i].setText("");
	               cards[i].setText("");
	            }
	         }
    	  	 }
      });
      check.addActionListener(new ActionListener() {
	    	  public void actionPerformed(ActionEvent e){
	    	  	dispose();
	    	  }
	  });
      
      panel2.add(reset);
      panel2.add(check);
      
      panel2.setBounds(40, 320, 600, 75);
      add(panel2, BorderLayout.SOUTH);
      
      setSize(700,450);
      //setVisible(true);
      //setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }
}
