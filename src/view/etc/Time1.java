package view.etc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Time1 extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel time, timeflow; // 시간진행
	private Timer timer;
	private int sec;

	public Time1(int sec, int x, int y, int width, int height) {
		this.sec = sec;
		this.setLayout(null);
		
		time = new JLabel("남은시간", SwingConstants.CENTER);
		timeflow = new JLabel("00:30", SwingConstants.CENTER);
		this.setBounds(x, y, width, height);

		time.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 30));
		timeflow.setFont(new Font("배달의민족 한나는 열한살", Font.BOLD, 36));

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
		sec--;
		if (sec >= 0) {
			if (sec >= 10)
				timeflow.setText("00:" + sec);
			else {
				timeflow.setText("00:0" + sec);
				//JOptionPane.showConfirmDialog(null, ")
			}
		}
		else {
			timer.stop();
		}
	}

	public Timer getTimer() {
		return this.timer;
	}
	public int getSec() {
		return sec;
	}

}
