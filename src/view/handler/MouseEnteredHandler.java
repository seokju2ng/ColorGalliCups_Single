package view.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import view.MyIndex;

public class MouseEnteredHandler extends MouseAdapter {
	private JButton[] buttons;
	private JLabel[] ll;
	private JLabel[] rl;
	private MyIndex cor;

	public MouseEnteredHandler(JButton[] buttons, JLabel[] ll, JLabel[] rl, MyIndex cor) {
		// cor=0;
		this.buttons = buttons;
		this.ll = ll;
		this.rl = rl;
		this.cor = cor;
	}

	public void mouseEntered(MouseEvent e) {
		System.out.println("mouse.cor전 = " + cor);
		for (int i = 0; i < buttons.length; i++) {
			if (e.getSource() == buttons[i]) {
				ll[cor.getIndex()].setVisible(false);
				rl[cor.getIndex()].setVisible(false);
				ll[i].setVisible(true);
				rl[i].setVisible(true);
				cor.setIndex(i);
				break;
			}
		}
		System.out.println("mouse.cor후 = " + cor);
	}
}
