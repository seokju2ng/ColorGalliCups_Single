package view.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class FocusBtnHandler implements ActionListener{
	private JPanel panel;
	
	public FocusBtnHandler(JPanel panel) {
		this.panel = panel;
	}
	public void actionPerformed(ActionEvent e) {
		panel.requestFocusInWindow();
	}
}
