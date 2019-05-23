package view.etc;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Board extends JPanel {
	//<<Member>>
	private static final long serialVersionUID = 1L;
	private JPanel [] cols; 
	private int colNum;
	private Cups cups;
	private int cupCnt;
	//<<Constructor>>
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
	//<<Method>>
	public void setCupsBounds(int x, int y, int area, int height, int interval) {
		cups.setAllBounds(x, y, area, height, interval);
	}
	public JLabel getCups(int colorIndex, int col, int row) {
		return cups.getCups(colorIndex, col, row);
	}
	public JPanel getBoardPanel(int i) {
		return cols[i];
	}
	public void setCupsImg(int cupIndex,String imgPath) {
		cups.setImg(cupIndex, imgPath);
	}
	public void setCups() {
		for(int i = 0; i < colNum; i++){
			for(int j = 0; j < colNum; j++) {
				for(int k = 0; k < cupCnt; k++) {
					cols[i].add(cups.getCups(k,i,j));
				}
			}
		}
	}
	public void setBackground(Color bg) {
		for(int i = 0 ; i < colNum ; i++) {
			cols[i].setBackground(bg);
		}
		
	}
	
}
