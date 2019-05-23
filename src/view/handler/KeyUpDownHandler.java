package view.handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;

import view.MyIndex;

public class KeyUpDownHandler extends KeyAdapter
{
	private MyIndex cor;
	private JLabel leftCursorArr[];
	private JLabel rightCursorArr[];
	private int num;
	public KeyUpDownHandler(MyIndex cor, int num,  JLabel[] leftCursorArr, JLabel[] rightCursorArr) {
		this.cor = cor;
		this.num = num;
		this.leftCursorArr = leftCursorArr;
		this.rightCursorArr = rightCursorArr; 
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			if (cor.getIndex() != num) {
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
