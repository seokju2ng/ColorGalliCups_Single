package view.etc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
/**입력받는 제한시간 만큼 시간을 재주는 SinglePlayMode 전용 타이머이다.<br>
 * @author 김도균 
 *  */
public class Time1 extends JPanel implements ActionListener {
	/**"남은시간"에 해당하는 글을 표시해줄 JLabel이다.*/
	private JLabel time;
	/**실제 남은 시간을 표시해줄 JLabel이다.*/
	private JLabel timeflow; 
	/**특정시간마다 남은 시간을 감소시켜주기 위한 멤버이다.*/
	private Timer timer;
	/**남은시간을 나타내는 멤버이다.*/
	private int sec;
	/**전달받은 시간만큼 시간을 재주는 타이머를 생성한다. 전달받는 좌표와 크기로 Time1객체를 배치한다.
	 * @param sec 타임어택 시간을 설정할 변수이다.
	 * @param x Time1의 x 좌표이다.
	 * @param y Time1의 y 좌표이다.
	 * @param width Time1의 너비이다.
	 * @param height Time1의 높이이다.*/
	public Time1(int sec, int x, int y, int width, int height) {
		this.sec = sec;
		this.setLayout(null);
		
		time = new JLabel("남은시간", SwingConstants.CENTER);
		timeflow = new JLabel(""+sec, SwingConstants.CENTER);
		this.setBounds(x, y, width, height);

		time.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 30));
		timeflow.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 36));

		timer = new Timer(1000, this);
		this.add(time);
		this.add(timeflow);
		
		timer.start();
	}

	/**전달받은 좌표와 크기로 Time1객체를 배치하기 위한 메서드이다.
	 * @param x Time1의 x 좌표이다.
	 * @param y Time1의 y 좌표이다.
	 * @param width Time1의 너비이다.
	 * @param height Time1의 높이이다.*/
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		time.setBounds(0, 0, width, height / 2);
		timeflow.setBounds(0, height / 2, width, height / 2);
	}

	/**1초에 1번 씩 남은 시간을 갱신해주는 메서드이다. 시간이 0이되면 타이머를 중지시킨다.*/
	@Override
	public void actionPerformed(ActionEvent e) {
		sec--;
		
		if (sec >= 0) {
			timeflow.setText(sec+"");
		}
		else {
			timer.stop();
		}
	}

	 /**timer를 반환해주는 getter()메소드이다.
     * @return 필드 timer를 리턴한다. */
	public Timer getTimer() {
		return this.timer;
	}
	 /**timeflow를 반환해주는 getter()메소드이다.
     * @return 필드 timeflow를 리턴한다. */
	public int getSec() {
		return sec;
	}

}
