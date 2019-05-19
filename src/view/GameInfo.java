package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import etc.ChangePanelService;

public class GameInfo extends JPanel {
   public GameInfo() {
      Color bg=new Color(197, 90, 17);
      Font font=new Font("nanum brush script", Font.BOLD, 45);
      this.setLayout(null);
      this.setBackground(new Color(251,229,214));
      

      JLabel[] labels=new JLabel[] {new JLabel("2019 (T)PerfectColor"), new JLabel("Threekimchoisong"), new JLabel("Directed by Lectopia"), new JLabel("Thanks to Hong, Jeong HK, Kim KH, Jeong KE")};
      for(int i=0;i<labels.length;i++) {
         labels[i].setFont(font);
         labels[i].setForeground(bg);
         add(labels[i]);
         labels[i].setHorizontalAlignment(SwingConstants.CENTER);
      }
      
      labels[0].setBounds(180,100,1000,80);
      labels[1].setBounds(180,180,1000,80);
      labels[2].setBounds(180,260,1000,80);
      labels[3].setBounds(180,340,1000,80);
      
      JButton back=new JButton("뒤로가기");
      back.setFont(font);
      back.setForeground(bg);
      back.setBounds(581, 500, 200, 100);
      back.setBorderPainted(false);
	  back.setContentAreaFilled(false);
	  back.setFocusPainted(false);
	  Handler l = new Handler();
      back.addActionListener(l);
      back.addKeyListener(l);
      
      JLabel rarrow=new JLabel(new ImageIcon("image/right.png"));
      JLabel larrow=new JLabel(new ImageIcon("image/left.png"));
      larrow.setBounds(460,500,100,100);
      rarrow.setBounds(800,500,100,100);
      add(back);
      add(rarrow);
      add(larrow);
   }
   private class Handler extends KeyAdapter implements ActionListener{
	   public void keyPressed(KeyEvent e) {
		   if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			   actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
		   }
	   }
	   public void actionPerformed(ActionEvent e) {
		   ChangePanelService cps = ChangePanelService.getInstance();
		   cps.changePanel("Help");
	   }
   }
}