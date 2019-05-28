package view.handler;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 * 게임 플레이 UI(SinglePlayMode, DualPlayMode)에서 일시정지, 게임종료 버튼에 마우스 진입 시 
 * 버튼의 이미지를 어둡게 변경하고 진출 시 이미지를 원상태로 변경한다.
 * @author cms<br>*/
public class MouseBtnHandler extends MouseAdapter {
		/**게임 플레이 UI에서 게임종료 버튼에 대한 정보이다.*/
		private JButton exitBtn;
		/**게임 플레이 UI에서 일시정지 버튼에 대한 정보이다.*/
		private JButton pauseBtn;
		/**MouseBtnHandler의 생성자로 exitBtn,pauseBtn을 parameter로 받아 객체를 할당한다.
		 * @param exitBtn 마우스 진입/진출 시 이미지가 변경될 버튼 정보이다.
		 * @param pauseBtn 마우스 진입/진출 시 이미지가 변경될 버튼 정보이다.*/
		public MouseBtnHandler(JButton exitBtn, JButton pauseBtn) {
			this.exitBtn = exitBtn;
			this.pauseBtn = pauseBtn;
		}
		/**mouse가 exitBtn이나 pauseBtn에 진입시 이미지를 바꾸어주는 메소드이다.
		 * @param e 컴퍼넌트 내에서 마우스 액션이 발생한 것을 나타내는 이벤트이다. */
		public void mouseEntered(MouseEvent e) {
			if (e.getSource().equals(exitBtn)) {
				exitBtn.setIcon(new ImageIcon("image/exit(click).png"));
			} else if (e.getSource().equals(pauseBtn)) {
				pauseBtn.setIcon(new ImageIcon("image/pause(click).png"));
			}
		}
		/**mouse가 exitBtn이나 pauseBtn에서 진출시 이미지를 바꾸어주는 메소드이다.
		 * @param e 컴퍼넌트 내에서 마우스 액션이 발생한 것을 나타내는 이벤트이다. */
		public void mouseExited(MouseEvent e) {
			if (e.getSource().equals(exitBtn)) {
				exitBtn.setIcon(new ImageIcon("image/exit.png"));
			} else if (e.getSource().equals(pauseBtn)) {
				pauseBtn.setIcon(new ImageIcon("image/pause.png"));
			}
		}
	}
