package view.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.etc.ChangePanelService;

public class ExitBtnHandler implements ActionListener {
	private JPanel prevPanel;
	private Timer tm;
	private Timer tm2;
	public ExitBtnHandler(JPanel prevPanel,Timer tm,Timer tm2) {
		this.prevPanel=prevPanel;
		this.tm = tm;
		this.tm2 = tm2;
	}
	public void actionPerformed(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			ChangePanelService.getInstance().changePanel("MainView", prevPanel);
			tm.stop();
			tm2.stop();
		}
	}
	// exit버튼에 액션핸들러 추가

}
