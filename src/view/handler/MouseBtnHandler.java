package view.handler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MouseBtnHandler extends MouseAdapter {
		private JButton exitBtn;
		private JButton pauseBtn;
		
		public MouseBtnHandler(JButton exitBtn, JButton pauseBtn) {
			this.exitBtn = exitBtn;
			this.pauseBtn = pauseBtn;
		}
		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(exitBtn)) {
				exitBtn.setIcon(new ImageIcon("image/exit(click).png"));
			} else if (e.getSource().equals(pauseBtn)) {
				pauseBtn.setIcon(new ImageIcon("image/pause(click).png"));
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(exitBtn)) {
				exitBtn.setIcon(new ImageIcon("image/exit.png"));
			} else if (e.getSource().equals(pauseBtn)) {
				pauseBtn.setIcon(new ImageIcon("image/pause.png"));
			}
		}
	}