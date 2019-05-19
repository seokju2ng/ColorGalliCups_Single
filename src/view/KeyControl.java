package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import etc.ChangePanelService;

public class KeyControl extends JPanel {
   public KeyControl() {
      this.setLayout(null);
      this.setBackground(new Color(251,229,214));
      
//      JButton[] threeButtons=new JButton[] {new JButton(new ImageIcon("image/red(q)_k.png")), new JButton(new ImageIcon("image/green(w)_k.png")), new JButton(new ImageIcon("image/blue(e)_k.png"))};
      JButton[] fiveButtons=new JButton[] {new JButton(new ImageIcon("image/red(q)_k.png")), new JButton(new ImageIcon("image/yellow(w)_k.png")), new JButton(new ImageIcon("image/green(e)_k.png")), new JButton(new ImageIcon("image/blue(a)_k.png")), new JButton(new ImageIcon("image/black(s)_k.png"))};
      JButton functionButton = new JButton(new ImageIcon("image/spacebar.png"));
      /////////////////////////////////////////// SINGLEPLAY ////////////////////////////////////////////////
      
      JLabel singlePlay=new JLabel(new ImageIcon("image/singleplay.png"));
      singlePlay.setBounds(40, 10, 250, 150);
      add(singlePlay);
      
      
//      JButton[] singleButtons3=threeButtons;
//      for(int i=0;i<threeButtons.length;i++) {
//         singleButtons3[i].setBounds(210+100*i, 210, 100, 100);
//         singleButtons3[i].setContentAreaFilled(false);
//         singleButtons3[i].setFocusPainted(false);
//         singleButtons3[i].setBorderPainted(false);
//         add(singleButtons3[i]);
//      }
      
      JButton[] singleButtons5=fiveButtons;
      for(int i=0;i<fiveButtons.length;i++) {
         singleButtons5[i].setBounds(110+100*i, 200, 100, 100);
         singleButtons5[i].setContentAreaFilled(false);
         singleButtons5[i].setFocusPainted(false);
         singleButtons5[i].setBorderPainted(false);
         add(singleButtons5[i]);
      }
      functionButton.setBounds(190, 350, 350, 94);
      functionButton.setContentAreaFilled(false);
      functionButton.setFocusPainted(false);
      functionButton.setBorderPainted(false);
      add(functionButton);
      ///////////////////////////////////////////// SINGLEPLAY 끝////////////////////////////////////////////////////////
      
      ///////////////////////////////////////////// DOUBLEPLAY /////////////////////////////////////////////////////////
  
      JLabel doublePlay = new JLabel(new ImageIcon("image/doubleplay.png"));
    
      doublePlay.setBounds(610, 10, 250, 150);
      add(doublePlay);
      
      JLabel[] player = new JLabel[] {new JLabel("1P"), new JLabel("2P")};
      Font font = new Font("Nanum Brush Script", Font.BOLD, 45);
      for(int i = 0 ; i < 2 ; i++) {
    	  player[i].setBounds(810 + i * 280, 140, 350, 100);
    	  player[i].setFont(font);
    	  add(player[i]);
      }
//      JLabel pp=new JLabel(new ImageIcon("image/1p2p.png"));
//      pp.setBounds(790, 140, 350, 100);
//      add(pp);
      
//      JButton[] firstPlay3=new JButton[] {new JButton(new ImageIcon("image/red(q)_1p_k.png")), new JButton(new ImageIcon("image/green(w)_1p_k.png")), new JButton(new ImageIcon("image/blue(e)_1p_k.png"))};
//      for(int i=0;i<firstPlay3.length;i++) {
//         firstPlay3[i].setBounds(730+70*i, 230, 60, 60);
//         firstPlay3[i].setContentAreaFilled(false);
//         firstPlay3[i].setFocusPainted(false);
//         firstPlay3[i].setBorderPainted(false);
//         add(firstPlay3[i]);
//      }
      
      JButton[] firstPlay5=new JButton[] {new JButton(new ImageIcon("image/red(q)_1p_k.png")), new JButton(new ImageIcon("image/yellow(w)_1p_k.png")), new JButton(new ImageIcon("image/green(e)_1p_k.png")), new JButton(new ImageIcon("image/blue(a)_1p_k.png")), new JButton(new ImageIcon("image/black(s)_1p_k.png"))};
      JButton functionButton1 = new JButton(new ImageIcon("image/spacebar_1p.png"));
      
      for(int i=0;i<3;i++) {
         firstPlay5[i].setBounds(730+70*i, 230, 60, 60);
         firstPlay5[i].setContentAreaFilled(false);
         firstPlay5[i].setFocusPainted(false);
         firstPlay5[i].setBorderPainted(false);
         add(firstPlay5[i]);
      }
      for(int i=3;i<5;i++) {
         firstPlay5[i].setBounds(560+70*i, 300, 60, 60);
         firstPlay5[i].setContentAreaFilled(false);
         firstPlay5[i].setFocusPainted(false);
         firstPlay5[i].setBorderPainted(false);
         add(firstPlay5[i]);
      }
      functionButton1.setBounds(735, 400, 191, 59);
      functionButton1.setContentAreaFilled(false);
      functionButton1.setFocusPainted(false);
      functionButton1.setBorderPainted(false);
      add(functionButton1);
      
      
//      JButton[] secondPlay3=new JButton[] {new JButton(new ImageIcon("image/red(i)_2p_k.png")), new JButton(new ImageIcon("image/green(o)_2p_k.png")), new JButton(new ImageIcon("image/blue(p)_2p_k.png"))};
//      for(int i=0;i<threeButtons.length;i++) {
//         secondPlay3[i].setBounds(1010+70*i, 230, 60, 60);
//         secondPlay3[i].setContentAreaFilled(false);
//         secondPlay3[i].setFocusPainted(false);
//         secondPlay3[i].setBorderPainted(false);
//         add(secondPlay3[i]);
//      }
      JButton[] secondPlay5=new JButton[] {new JButton(new ImageIcon("image/red(i)_2p_k.png")), new JButton(new ImageIcon("image/yellow(o)_2p_k.png")), new JButton(new ImageIcon("image/green(p)_2p_k.png")), new JButton(new ImageIcon("image/blue(k)_2p_k.png")), new JButton(new ImageIcon("image/black(l)_2p_k.png"))};
      JButton functionButton2 = new JButton(new ImageIcon("image/enter_2p.png"));
      
      for(int i=0;i<3;i++) {
         secondPlay5[i].setBounds(980+70*i, 230, 125, 59);
         secondPlay5[i].setContentAreaFilled(false);
         secondPlay5[i].setFocusPainted(false);
         secondPlay5[i].setBorderPainted(false);
         add(secondPlay5[i]);
      }
      for(int i=3;i<5;i++) {
         secondPlay5[i].setBounds(835+70*i, 300, 60, 60);
         secondPlay5[i].setContentAreaFilled(false);
         secondPlay5[i].setFocusPainted(false);
         secondPlay5[i].setBorderPainted(false);
         add(secondPlay5[i]);
      }
      
      functionButton2.setBounds(1015, 400, 191, 60);
      functionButton2.setContentAreaFilled(false);
      functionButton2.setFocusPainted(false);
      functionButton2.setBorderPainted(false);
      add(functionButton2);
      
      ///////////////////////////////////////////// DOUBLEPLAY 끝 /////////////////////////////////////////////////////////
//      Font font=new Font("Nanum Brush Script", Font.BOLD, 45);
//      JLabel threeColors=new JLabel("3색");
//      add(threeColors);
//      threeColors.setBounds(630,230,100,50);
//      threeColors.setFont(font);
//      threeColors.setForeground(new Color(197, 90, 17));
//      
//      JLabel fiveColors=new JLabel("5색");
//      add(fiveColors);
//      fiveColors.setBounds(630,380,100,50);
//      fiveColors.setFont(font);
//      fiveColors.setForeground(new Color(197, 90, 17));
      
      ///////////////////////////////////////////// 뒤로가기 /////////////////////////////////////////////////////////////////
      Handler l = new Handler();
      JButton back=new JButton("뒤로가기");
      back.setFont(font);
      back.setForeground(new Color(197, 90, 17));
      back.setBounds(566, 540, 200, 100);
      back.setContentAreaFilled(false);
      back.setFocusPainted(false);
      back.setBorderPainted(false);
      back.addActionListener(l);
      back.addKeyListener(l);
      add(back);
      
      JLabel rarrow=new JLabel(new ImageIcon("image/right.png"));
      JLabel larrow=new JLabel(new ImageIcon("image/left.png"));
      larrow.setBounds(470,540,100,100);
      rarrow.setBounds(765,540,100,100);
      add(rarrow);
      add(larrow);
      ///////////////////////////////////////////// 뒤로가기 끝 /////////////////////////////////////////////////////////      
   }
   
   public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      Graphics2D g2 = (Graphics2D)g;
	      g2.setColor(Color.black);
	      g2.setStroke(new BasicStroke(3));
	      g2.drawRoundRect(80, 110, 550, 400, 50, 50);
	      g2.drawRoundRect(700, 110, 550, 400, 50, 50);
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
