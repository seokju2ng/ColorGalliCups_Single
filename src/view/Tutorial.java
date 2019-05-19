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

import etc.ChangePanelService;

public class Tutorial extends JPanel {
   public Tutorial() {
      setLayout(null);
      setBackground(new Color(251,229,214));
      
      JLabel picture=new JLabel(new ImageIcon("image/tutorial.gif"));
      picture.setBounds(230, 50, 900, 500);
      add(picture);
      
      JButton back=new JButton("뒤로가기");
      back.setFont(new Font("nanum brush script", Font.BOLD, 45));
      back.setForeground(new Color(197, 90, 17));
      back.setBounds(581, 550, 200, 100);
      back.setBorderPainted(false);
	  back.setContentAreaFilled(false);
	  back.setFocusPainted(false);
      JLabel rarrow=new JLabel(new ImageIcon("image/right.png"));
      JLabel larrow=new JLabel(new ImageIcon("image/left.png"));
      larrow.setBounds(460,550,100,100);
      rarrow.setBounds(800,550,100,100);
      
      Handler l = new Handler();
      this.addKeyListener(l);
      back.addKeyListener(l);
      back.addActionListener(l);
      rarrow.addKeyListener(l);
      larrow.addKeyListener(l);
      picture.addKeyListener(l);
      this.setFocusable(true);
      //back.setFocusable(true);
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