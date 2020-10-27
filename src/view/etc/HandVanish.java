package view.etc;

import javax.swing.JLabel;

/**
 * 게임 플레이 UI(DualPlayMode)에서 정답을 제출할 때 사용되는 손이미지 조작을 위한 클래스이다. Thread Class를
 * 상속하고 있다.
 * 
 * @author 김용희
 *
 */
public class HandVanish extends Thread {
	/** 손 이미지가 겹쳐서 나올 수 있게 하기위한 index 변수이다. */
	private static int index = 3;
	/**
	 * 어떤 사용자의 손인지 판별하기 위한 변수이다.
	 */
	private int order;
	/**
	 * 손 이미지를 위한 변수이다.
	 */
	private JLabel[][] hands;
	/**
	 * 손이미지가 이미 나왔으면 손을 나오지 않게 하기 위한 변수이다.
	 */
	private boolean[] handCheck;

	/**
	 * HandVanish 클래스의 Null Parameter Contructor이다.
	 */
	public HandVanish() {

	}

	/**
	 * HandVanish 클래스의 overloaded Contructor이다.
	 * parameter로 받은 정보에 맞게 HandVanish 객체를 할당한다.
	 * @param order 채점을 요청한 사용자를 식별하는 정보이다.
	 * @param hands 손 이미지를 저장하고 있는 변수이다.
	 * @param handCheck 손이미지가 이미 나왔으면 손을 나오지 않게 하기 위한 변수이다. true이면 화면에 손이 보이는 상태이고 false이면 보이지 않는 상태이다.
	 */
	public HandVanish(int order, JLabel[][] hands, boolean[] handCheck) {
		this.order = order;
		this.hands = hands;
		this.handCheck = handCheck;
	}
	/**
	 * Thread Class의 run()메소드를 오버라이딩 한 메소드이다.
	 * 1초간 손이 나오고 사라지면 메소드도 같이 종료된다.
	 */
	public void run() {
		int num = index;
		hands[index--][order].setVisible(true);
		handCheck[order] = true;
		try {
			System.out.println(order + "번 1초 휴식중..");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hands[num][order].setVisible(false);
		index++;
		handCheck[order] = false;
		System.out.println(order + "번 끝");
	}
}