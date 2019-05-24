package view.handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.etc.ChangePanelService;
/**@author cms</br>
 * 도움말 하위 UI(GameInfo, KeyControl, Tutorial)에서 뒤로가기 버튼 클릭 시 현재 화면을 이전 화면인 Help로 변경한다.
 * */
public class BackHelpHandler extends KeyAdapter implements ActionListener{
	/**Emter키를 입력 받을 시 actionPerformed 메소드를 실행한다.
	 * @param e 키 입력시 발생하는 이벤트이다.*/
	public void keyPressed(KeyEvent e) {
		   if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			   actionPerformed(new ActionEvent(e.getSource(), e.getID(), Character.toString(e.getKeyChar())));
		   }
	}
	/**{@link ChangepanelService} 를 사용하여 ChangepanelService안에서의 패널을 Help 패널로 바꾸어주는 메소드이다.
	 * @param e 컴퍼넌트가 정의하는 액션이 발생하는 이벤트이다.*/
	public void actionPerformed(ActionEvent e) {
		   ChangePanelService cps = ChangePanelService.getInstance();
		   cps.changePanel("Help");
	}
}
