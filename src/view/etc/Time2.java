package view.etc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
/**게임진행시간을 나타내는 DualPlayMode 전용 타이머이다.<br>
 * @author 김용희 
 *  */
public class Time2 extends JPanel implements ActionListener {
	/**"진행시간"에 해당하는 글을 표시해줄 JLabel이다.*/
	private JLabel time; // 시간진행
	/**실제 진행시간을 표시해줄 JLabel이다.*/
	private JLabel timeflow;
	/**특정시간마다 진행 시간을 증가시켜주기 위한 멤버이다.*/
	private Timer timer;
	/**진행시간의 분을 나타내는 멤버이다.*/
	private int min;
	/**진행시간의 초를 나타내는 멤버이다.*/
	private int sec;
	
	/**진행시간을 계산 할 타이머를 생성한다. 전달받는 좌표와 크기로 타이머를 배치한다.
	 * @param x Time1의 x 좌표이다.
	 * @param y Time1의 y 좌표이다.
	 * @param width Time2의 너비이다.
	 * @param height Time2의 높이이다.*/
	public Time2(int x, int y, int width, int height) {
		this.setLayout(null);
		
		Font font = new Font("배달의민족 한나는 열한살", Font.BOLD, 20);
		time = new JLabel("진행시간", SwingConstants.CENTER);
		timeflow = new JLabel("00:00", SwingConstants.CENTER);
		this.setBounds(x, y, width, height);
		time.setFont(font);
		timeflow.setFont(font);

		timer = new Timer(1000, this);
		this.add(time);
		this.add(timeflow);

		timer.start();
	}

	/**전달받은 좌표와 크기로 타이머를 배치하기 위한 메서드이다.
	 * @param x 타이머의 x 좌표이다.
	 * @param y 타이머의의 y 좌표이다.
	 * @param width 타이머의 너비이다.
	 * @param height 타이머의 높이이다.*/
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		time.setBounds(0, 0, width, height / 2);
		timeflow.setBounds(0, height / 2, width, height / 2);
	}
	/**진행 시간을 1초마다 1번 씩 진행 시간을 갱신해주는 메서드이다.*/
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// System.out.println(chk_time++);
		// timeflow.setText(sdf1.format(chk_time++));
		sec++;
		if (sec / 60 == 1)
			min++;
		sec = sec % 60;
		if (min < 10) {
			if (sec < 10) {
				timeflow.setText("0" + min + ":0" + sec);
			} else {
				timeflow.setText("0" + min + ":" + sec);
			}
		} else {
			if (sec < 10) {
				timeflow.setText(min + ":0" + sec);
			} else {
				timeflow.setText(min + ":" + sec);
			}
		}

	}
	 /**timer를 반환해주는 getter()메소드이다.
     * @return 필드 timer를 리턴한다. */
	public Timer getTimer() {
		return this.timer;
	}
	 /**timeflow를 반환해주는 getter()메소드이다.
     * @return 필드 timeflow를 리턴한다. */
	public JLabel getTimeFlow() {
		return this.timeflow;
	}
}
