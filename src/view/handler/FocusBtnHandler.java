package view.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
/**@author cms</br>
 * 게임 플레이 UI(SinglePlayMode, DualPlayMode, NetworkPlayMode)에서 버튼 클릭 시 버튼으로 옮겨진 포커스를 현재 패널로 옮겨준다.
 * */
public class FocusBtnHandler implements ActionListener{
	/**게임 플레이 UI(SinglePlayMode, DualPlayMode, NetworkPlayMode) 중 하나의 JPanel이다.*/
	private JPanel panel;
	/**FocusBtnHandler의 생성자로 panel를 parameter로 받아 객체를 생성한다.
	 * @param panel 게임 플레이 UI(SinglePlayMode, DualPlayMode, NetworkPlayMode) 중 하나의 JPanel이다.*/
	public FocusBtnHandler(JPanel panel) {
		this.panel = panel;
	}
	/**이벤트 발생시 멤버 필드인 panel에게 focus를 주는 메소드이다.
	 * @param e 컴퍼넌트가 정의하는 액션이 발생하는 이벤트이다.*/
	public void actionPerformed(ActionEvent e) {
		panel.requestFocusInWindow();
	}
}
