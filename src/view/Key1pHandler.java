package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

public class Key1pHandler extends KeyAdapter{
	private JLabel[] labels;
	
	public Key1pHandler(JLabel[] labels) {
		this.labels = labels;
	}
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_Q:
			labels[0].setVisible(false);
			break;
		case KeyEvent.VK_W:
			labels[1].setVisible(false);
			break;
		case KeyEvent.VK_E:
			labels[2].setVisible(false);
			break;
		case KeyEvent.VK_A:
			labels[3].setVisible(false);
			break;
		case KeyEvent.VK_S:
			labels[4].setVisible(false);
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_Q:
			labels[0].setVisible(true);
			break;
		case KeyEvent.VK_W:
			labels[1].setVisible(true);
			break;
		case KeyEvent.VK_E:
			labels[2].setVisible(true);
			break;
		case KeyEvent.VK_A:
			labels[3].setVisible(true);
			break;
		case KeyEvent.VK_S:
			labels[4].setVisible(true);
			break;
		}
	}
}
