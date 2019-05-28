package view.handler;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
/**
 * 패널이 있는 UI(SinglePlayMode, DualPlayMode, GameMode, OptionView, 
 * MainView, Help, GameInfo, KeyControl, Tutorial)
 * 의 패널이 변경되었을 때 변경된 패널에 초점을 맞춘다.
 * @author cms<br>*/
public class FocusHandler extends ComponentAdapter {
	/**이벤트 발생시 변경된 패널에 focus를 주는 메소드이다.
	 * @param e 컴퍼넌트가 이동한 것, 사이즈를 변경한 것, 또는 가시성을 변경한 것을 나타내는 저레벨 이벤트이다.*/
	public void componentShown(ComponentEvent e) {
		((JPanel)e.getSource()).requestFocusInWindow();
	}
}
