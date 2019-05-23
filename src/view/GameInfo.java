package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.handler.BackHelpHandler;
import view.handler.FocusHandler;

public class GameInfo extends JPanel {
   public GameInfo() {
	   
      Color bg=new Color(197, 90, 17);
      Font font=new Font("nanum brush script", Font.BOLD, 45);
      this.setLayout(null);
      this.setBackground(new Color(251,229,214));
      

      JLabel[] labels=new JLabel[] {new JLabel("2019 (T)PerfectColor",SwingConstants.CENTER), new JLabel("Threekimchoisong",SwingConstants.CENTER), new JLabel("Directed by Lectopia",SwingConstants.CENTER), new JLabel("Thanks to Hong, Jeong HK, Kim KH, Jeong KE",SwingConstants.CENTER)};
      for(int i=0;i<labels.length;i++) {
         labels[i].setFont(font);
         labels[i].setForeground(bg);
         labels[i].setBounds(180,100 + i * 80, 1000, 80);
         add(labels[i]);
      }
      
      JButton back=new JButton("뒤로가기");
      back.setFont(font);
      back.setForeground(bg);
      back.setBounds(581, 500, 200, 100);
      back.setBorderPainted(false);
	  back.setContentAreaFilled(false);
	  back.setFocusPainted(false);
	  
	  BackHelpHandler l = new BackHelpHandler();
      back.addActionListener(l);
      this.addComponentListener(new FocusHandler());
	  this.addKeyListener(l);
//      back.addKeyListener(l);
      
      JLabel rarrow=new JLabel(new ImageIcon("image/right.png"));
      JLabel larrow=new JLabel(new ImageIcon("image/left.png"));
      larrow.setBounds(460,500,100,100);
      rarrow.setBounds(800,500,100,100);
      add(back);
      add(rarrow);
      add(larrow);
   }
}
