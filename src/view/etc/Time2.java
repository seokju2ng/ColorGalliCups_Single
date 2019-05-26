package view.etc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Time2 extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel time, timeflow; // 시간진행
	private Timer timer;
	private int min, sec;

	public Time2(int x, int y, int width, int height) {
		this.setLayout(null);

		time = new JLabel("진행시간", SwingConstants.CENTER);
		timeflow = new JLabel("00:00", SwingConstants.CENTER);
		this.setBounds(x, y, width, height);
		time.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));
		timeflow.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 20));

		timer = new Timer(1000, this);
		this.add(time);
		this.add(timeflow);

		timer.start();
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);

		time.setBounds(0, 0, width, height / 2);
		timeflow.setBounds(0, height / 2, width, height / 2);
	}

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

	public Timer getTimer() {
		return this.timer;
	}
	public JLabel getTimeFlow() {
		return this.timeflow;
	}

	// public static void main(String[] args) {
	// JFrame f = new JFrame();
	// f.setLayout(null);
	// Time time = new Time(50,20,180,100);
	// time.setBorder(new LineBorder(Color.RED, 5));
	// //time.setBounds(50, 20, 180, 100);
	// f.setSize(1363, 714);
	// f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
	// f.setVisible(true);
	// f.add(time);
	// }
}
