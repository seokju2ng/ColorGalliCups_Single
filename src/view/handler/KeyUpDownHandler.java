package view.handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import view.etc.MyIndex;
import view.etc.Sound;
/**
 * 메뉴 선택 UI(MainView, GameMode, Help)에서 키보드의 위, 아래 키 입력 시 화면에서 현재 메뉴를 표시하는 아이콘의 위치를 위, 아래로 변경한다.
 * @author cms<br>*/
public class KeyUpDownHandler extends KeyAdapter
{
	/**메뉴 선택 UI의 메뉴 인덱스 정보를 저장한다.*/
	private MyIndex cor;
	/**메뉴 선택 UI의 메뉴 왼쪽에 있는 JLabel에 대한 정보이다.*/
	private JLabel leftCursorArr[];
	/**메뉴 선택 UI의 메뉴 오른쪽에 있는 JLabel에 대한 정보이다.*/
	private JLabel rightCursorArr[];
	/**메뉴 선택 UI에 있는 메뉴 개수에 대한 정보이다.*/
	private int num;
	/**
	 * KeyUpDownHandler의 생성자로 cor, num, leftCursorArr, rightCursorArr를 Parameter로 받아 객체를 할당해준다.
	 * @param cor 메뉴 선택 UI의 메뉴 인덱스 정보를 저장한다.
	 * @param num 메뉴 선택 UI에 있는 메뉴 개수에 대한 정보이다.
	 * @param leftCursorArr 메뉴 선택 UI의 메뉴 왼쪽에 있는 JLabel에 대한 정보이다.
	 * @param rightCursorArr 메뉴 선택 UI의 메뉴 오른쪽에 있는 JLabel에 대한 정보이다.
	 * */
	public KeyUpDownHandler(MyIndex cor, int num,  JLabel[] leftCursorArr, JLabel[] rightCursorArr) {
		this.cor = cor;
		this.num = num;
		this.leftCursorArr = leftCursorArr;
		this.rightCursorArr = rightCursorArr; 
		
	}
	@Override
	/** 위/아래 키 입력시 메뉴 선택 UI의 현재 메뉴 양 옆에 CursorArr 아이콘을 보여준다.
	 * @param e 키 입력시 발생하는 이벤트이다.
	 * */
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if (cor.getIndex() != num) {
				Sound.playEffect("audio/touch2.wav");
				leftCursorArr[cor.getIndex()].setVisible(false);
				rightCursorArr[cor.getIndex()].setVisible(false);
				cor.plus();
				leftCursorArr[cor.getIndex()].setVisible(true);
				rightCursorArr[cor.getIndex()].setVisible(true);
			}
			System.out.println("down"+cor);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			
			System.out.println("up");
			if (cor.getIndex() != 0) {
				Sound.playEffect("audio/touch2.wav");
				leftCursorArr[cor.getIndex()].setVisible(false);
				rightCursorArr[cor.getIndex()].setVisible(false);
				cor.minus();
				leftCursorArr[cor.getIndex()].setVisible(true);
				rightCursorArr[cor.getIndex()].setVisible(true);
			}
			System.out.println("up" + cor);
		} 
	}

}
