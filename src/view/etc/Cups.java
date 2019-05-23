package view.etc;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Cups {
	//ColorCups
	private JLabel cups[][][];
	private int cupCnt;
	private int panelCnt;
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
	public void setImg(int cupIndex, String imgPath) {
		for(int i = 0; i < panelCnt; i++) {
			for(int j = 0; j < panelCnt; j++) {
				cups[cupIndex][i][j].setIcon(new ImageIcon(imgPath));
			}
		}
	}
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
	public JLabel getCups(int colorIndex, int col, int row) {
		return cups[colorIndex][col][row];
	}
}
