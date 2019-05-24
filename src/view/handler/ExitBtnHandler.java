package view.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.etc.ChangePanelService;
/**
 * 게임 플레이 UI(SinglePlayMode, DualPlayMode, NetworkPlayMode)에서  게임 종료 버튼 클릭 시 게임 종료 여부를 묻는 팝업창을 생성해준다. 
 * @author cms<br>*/
public class ExitBtnHandler implements ActionListener {
	/**게임 종료가 될 게임 플레이 UI이다.*/
	private JPanel prevPanel;
	/**게임 플레이 UI에 남는 시간 혹은 진행 시간을 나타내는 시간 정보이다.*/
	private Timer tm;
	/** tm2 게임 플레이 UI에 적용되는 애니메이션에 사용되는 시간 정보이다./
	private Timer tm2;
	/**ExitBtnHandler의 생성자로 prevPanel,tm,tm2를 Parameter로 받아 객체를 할당해준다.
	 * @param prevPanel 게임 종료가 될 게임 플레이 UI이다.
	 * @param tm 게임 플레이 UI에 남는 시간 혹은 진행 시간을 나타내는 시간 정보이다.
	 * @param tm2 게임 플레이 UI에 적용되는 애니메이션에 사용되는 시간 정보이다.*/
	public ExitBtnHandler(JPanel prevPanel,Timer tm,Timer tm2) {
		this.prevPanel=prevPanel;
		this.tm = tm;
	}
	/**게임 종료 팝업창을 띄어주는 역할을 하고, 게임 종료시 게임 플레이 UI에 있는 모든 시간 정보들을 멈추어주고 MainView화면으로 전화시켜준다.
	 * @param e 컴퍼넌트가 정의하는 액션이 발생하는 이벤트이다.
	 * */
	public void actionPerformed(ActionEvent e) {
		int res = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (res == JOptionPane.YES_OPTION) {
			ChangePanelService.getInstance().changePanel("MainView", prevPanel);
			tm.stop();
		}
	}
	// exit버튼에 액션핸들러 추가

}
