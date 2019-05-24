package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
/**
 *2P 색상키에 적용된다.
 * 색상키를 보여주는 UI(KeyControll, SinglePlayMode, DualPlayMode, NetworkPlayMode)에서 색상키에 해당하는 키(I, O, P, K, L) Press시 색상 키 이미지를 눌린 이미지로 변경하고 키 release시 색상 키 이미지를 원상태로 변경한다.
 * @author cms <br>*/
public class Key2pHandler extends KeyAdapter{
	/**색상키를 보여주는 UI에서 색상키에 해당하는 정보이다.*/
	private JLabel[] labels;
	/**Key2pHandler의 생성자로 labels를 parameter로 받아 객체를 할당한다.
	 * @param labels 색상키를 보여주는 UI에서 색상키에 해당하는 JLabel[]이다.
	 * */
	public Key2pHandler(JLabel[] labels) {
		this.labels = labels;
	}
	/**색상키에 해당하는 키(I, O, P, K, L)가 눌려질 때 이미지를 바꾸어주는 메소드
	 * @param e 키 입력시 발생하는 이벤트이다.
	 * */
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_I:
			labels[0].setVisible(false);
			break;
		case KeyEvent.VK_O:
			labels[1].setVisible(false);
			break;
		case KeyEvent.VK_P:
			labels[2].setVisible(false);
			break;
		case KeyEvent.VK_K:
			labels[3].setVisible(false);
			break;
		case KeyEvent.VK_L:
			labels[4].setVisible(false);
			break;
		}
	}
	/**색상키에 해당하는 키(I, O, P, K, L)가 떼질 때 이미지를 바꾸어주는 메소드
	 * @param e 키 입력시 발생하는 이벤트이다.
	 * */
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		switch (code) {
		case KeyEvent.VK_I:
			labels[0].setVisible(true);
			break;
		case KeyEvent.VK_O:
			labels[1].setVisible(true);
			break;
		case KeyEvent.VK_P:
			labels[2].setVisible(true);
			break;
		case KeyEvent.VK_K:
			labels[3].setVisible(true);
			break;
		case KeyEvent.VK_L:
			labels[4].setVisible(true);
			break;
		}
	}
}
