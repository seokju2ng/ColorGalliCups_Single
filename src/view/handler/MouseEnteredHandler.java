package view.handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

import view.MyIndex;
import view.etc.Sound;
/**
 * 메뉴 선택 UI(MainView, GameMode, Help)에서 메뉴 버튼에 마우스 진입 시 현재 메뉴를 표시하는 아이콘의 위치를 마우스 위치에 해당하는 메뉴로 변경한다.
 * @author cms<br>*/
public class MouseEnteredHandler extends MouseAdapter {
	/**메뉴 선택 UI에서 메뉴 버튼에 대한 정보이다.*/
	private JButton[] buttons;
	/**메뉴 선택 UI의 메뉴 왼쪽에 있는 JLabel에 대한 정보이다.*/
	private JLabel[] ll;
	/**메뉴 선택 UI의 메뉴 오른쪽에 있는 JLabel에 대한 정보이다.*/
	private JLabel[] rl;
	/**메뉴 선택 UI의 메뉴 인덱스 정보이다.*/
	private MyIndex cor;
	/** MouseEnteredHandler의 생성자로 button
	 * @param buttons 메뉴 선택 UI에서 메뉴 버튼에 대한 정보이다.
	 * @param ll 메뉴 선택 UI의 메뉴 왼쪽에 있는 JLabel에 대한 정보이다.
	 * @param rl 메뉴 선택 UI의 메뉴 오른쪽에 있는 JLabel에 대한 정보이다.
	 * @param cor 메뉴 선택 UI의 메뉴 인덱스 정보이다.
	 * */
	public MouseEnteredHandler(JButton[] buttons, JLabel[] ll, JLabel[] rl, MyIndex cor) {
		// cor=0;
		this.buttons = buttons;
		this.ll = ll;
		this.rl = rl;
		this.cor = cor;
	}
	/**mouse가 메뉴 선택UI의 메뉴 버튼에 진입시 메뉴 버튼 양 옆에 아이콘을 보여준다.
	 * @param e 컴퍼넌트 내에서 마우스 액션이 발생한 것을 나타내는 이벤트이다. */
	public void mouseEntered(MouseEvent e) {
//		System.out.println("mouse.cor전 = " + cor);
		for (int i = 0; i < buttons.length; i++) {
			if (e.getSource() == buttons[i]) {
				Sound.playEffect("audio/touch2.wav");
				ll[cor.getIndex()].setVisible(false);
				rl[cor.getIndex()].setVisible(false);
				ll[i].setVisible(true);
				rl[i].setVisible(true);
				cor.setIndex(i);
				break;
			}
		}
//		System.out.println("mouse.cor후 = " + cor);
	}
}
