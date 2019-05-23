package view.handler;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

public class FocusHandler extends ComponentAdapter {
	public void componentShown(ComponentEvent e) {
		((JPanel)e.getSource()).requestFocusInWindow();
	}
}
