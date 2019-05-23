package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class Key2pHandler extends KeyAdapter{
	private JLabel[] labels;
	
	public Key2pHandler(JLabel[] labels) {
		this.labels = labels;
	}
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_I:
			labels[0].setVisible(false);
			break;
		case KeyEvent.VK_O:
			labels[1].setVisible(false);
			break;
		case KeyEvent.VK_P:
			labels[2].setVisible(false);
			break;
		case KeyEvent.VK_K:
			labels[3].setVisible(false);
			break;
		case KeyEvent.VK_L:
			labels[4].setVisible(false);
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_I:
			labels[0].setVisible(true);
			break;
		case KeyEvent.VK_O:
			labels[1].setVisible(true);
			break;
		case KeyEvent.VK_P:
			labels[2].setVisible(true);
			break;
		case KeyEvent.VK_K:
			labels[3].setVisible(true);
			break;
		case KeyEvent.VK_L:
			labels[4].setVisible(true);
			break;
		}
	}
}
