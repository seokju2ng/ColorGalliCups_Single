package view.etc;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * 게임 플레이 UI(SinglePlayMode, DualPlayMode, NetworkPlayMode)에 있는 Board에 사용되는 컵의 이미지,개수를 정해주어 Board에 cup의 이미지를 배치해준다.
 * @author cms<br>*/
public class Cups {
	//ColorCups
	/**게임에 사용될 컵들에 대한 정보이다.*/
	private JLabel cups[][][];
	/**게임에 사용될 컵의 개수에 대한 정보이다.*/
	private int cupCnt;
	/**게임판의 개수에 대한 정보이다.*/
	private int panelCnt;
	/**Cups의 생성자로 cupCnt,panelCnt를 parameter로 받아 객체를 할당한다.
	 * @param cupCnt 게임에 사용될 컵의 개수에 대한 정보이다.
	 * @param panelCnt 게임판의 개수에 대한 정보이다.*/
	public Cups(int cupCnt, int panelCnt) {
		this.cupCnt = cupCnt;
		this.panelCnt = panelCnt;
		cups = new JLabel[cupCnt][panelCnt][panelCnt];
		for(int i = 0; i < panelCnt; i++) {
			for(int j = 0; j < panelCnt; j++) {
				for(int k= 0; k < cupCnt; k++) {
					cups[k][i][j] = new JLabel();
				}
			}
		}
	}
	/**컵에 이미지를 입혀주는 메소드이다.
	 * @param cupIndex 이미지를 입힐 컵의 인덱스
	 * @param imgPath 컵에 입힐 이미지 경로*/
	public void setImg(int cupIndex, String imgPath) {
		for(int i = 0; i < panelCnt; i++) {
			for(int j = 0; j < panelCnt; j++) {
				cups[cupIndex][i][j].setIcon(new ImageIcon(imgPath));
			}
		}
	}
	/**모든 컵에 대하여 위치정보 및 크기를 설정하는 메소드이다.
	 * @param x 컵의 x좌표이다.
	 * @param y 컵의 y좌표이다.
	 * @param area 컵의 넓이이다.
	 * @param height 컵의 높이이다.
	 * @param interval 쌓여지는 컵과 컵사이의 높이 간격이다.
	 * */
	public void setAllBounds(int x, int y, int area, int height, int interval) {
		for(int i = 0; i < panelCnt; i++) {
			for(int j = 0; j < panelCnt; j++) {
				for(int k= 0; k < cupCnt; k++) {
					cups[k][i][j].setBounds(x, y + j * interval, area, height);
					cups[k][i][j].setVisible(false);
				}
			}
		}		
	}
	/**원하는 컵에 대한 정보를 얻는 메소드이다.
	 * @param colorIndex 원하는 컵의 인덱스 정보이다
	 * @param col 원하는 컵의 게임판 가로 위치이다.
	 * @param row 원하는 컵의 게임판 세로 위치이다.
	 * @return 컵에 대한 정보*/
	public JLabel getCups(int colorIndex, int col, int row) {
		return cups[colorIndex][col][row];
	}
}
