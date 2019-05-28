package view.etc;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
/**
 * 게임 플레이 UI(SinglePlayMode, DualPlayMode)에서 사용되는 게임판에 대한 정보를 관리한다.
 *@author cms<br> */
public class Board extends JPanel {
	/**게임플레이 UI에서 사용되는 게임판이다.*/
	private JPanel [] cols; 
	/**게임판의 칸 개수에 대한 정보이다.*/
	private int colNum;
	/**게임판에서 사용되는 컵에 대한 정보이다.*/
	private Cups cups;
	/**게임판에서 사용되는 컵의 개수에 대한 정보이다.*/
	private int cupCnt;
	/**Board의 생성자로 cupCnt,colNum을 parameter로 받아 객체를 할당한다.
	 * @param cupCnt 게임판에서 사용되는 컵의 개수에 대한 정보이다.
	 * @param colNum 게임판의 칸 개수에 대한 정보이다.*/
	public Board(int cupCnt, int colNum) {
		this.setLayout(new GridLayout(0, 5));
		this.colNum = colNum;
		this.cupCnt = cupCnt;
		cols = new JPanel[colNum];
		for(int i = 0 ; i <cols.length ;i++) {
			cols[i] = new JPanel(null);
			cols[i].setBorder(new LineBorder(Color.BLACK,1));
			this.add(cols[i]);
		}
		cups =new Cups(cupCnt,colNum);
	}
	/**게임판에 사용되는 모든 컵에 대하여 위치정보 및 크기를 설정한다.
	 * @param x 컵의 x좌표이다.
	 * @param y 컵의 y좌표이다.
	 * @param area 컵의 너비이다.
	 * @param height 컵의 높이이다.
	 * @param interval 쌓여지는 컵과 컵사이의 높이 간격이다.
	 * */
	public void setCupsBounds(int x, int y, int area, int height, int interval) {
		cups.setAllBounds(x, y, area, height, interval);
	}
	/**게임판에 사용되는 컵들 중에 원하는 컵에 대한 정보를 얻는다.
	 * @param colorIndex 원하는 컵의 인덱스 정보이다
	 * @param col 원하는 컵의 게임판 가로 위치이다.
	 * @param row 원하는 컵의 게임판 세로 위치이다.
	 * @return 컵에 대한 정보*/
	public JLabel getCups(int colorIndex, int col, int row) {
		return cups.getCups(colorIndex, col, row);
	}
	/**게임판에서 원하는 칸에 대한 정보를 얻는다.
	 * @param i 게임판의 칸에 대한 인덱스이다.
	 * @return 게임판에서 원하는 칸이다.*/
	public JPanel getBoardPanel(int i) {
		return cols[i];
	}
	/**게임판에서 사용하는 컵에 이미지를 입혀준다.
	 * @param cupIndex 이미지를 입힐 컵의 인덱스
	 * @param imgPath 컵에 입힐 이미지 경로*/
	public void setCupsImg(int cupIndex,String imgPath) {
		cups.setImg(cupIndex, imgPath);
	}
	/**게임판에서 사용되는 컵을 설정해준다.
	 * */
	public void setCups() {
		for(int i = 0; i < colNum; i++){
			for(int j = 0; j < colNum; j++) {
				for(int k = 0; k < cupCnt; k++) {
					cols[i].add(cups.getCups(k,i,j));
				}
			}
		}
	}
	/**게임판의 배경색을 설정해준다
	 * @param bg 게임판의 배경색에 대한 정보이다.*/
	public void setBackground(Color bg) {
		for(int i = 0 ; i < colNum ; i++) {
			cols[i].setBackground(bg);
		}
		
	}
	
}
