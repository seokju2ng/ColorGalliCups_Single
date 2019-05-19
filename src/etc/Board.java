package etc;

import java.awt.Color;
import java.awt.GridLayout;


import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Board extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel [] cols; 
	private int colNum;
	

	public Board(int colNum) {
		this.setLayout(new GridLayout(0, 5));
		this.colNum = colNum;
		cols = new JPanel[colNum];
		for(int i = 0 ; i <cols.length ;i++) {
			cols[i] = new JPanel(null);
			cols[i].setBorder(new LineBorder(Color.BLACK,1));
			this.add(cols[i]);
			
		}
	}
	public JPanel getBoardPanel(int i) {
		return cols[i];
	}
	
	public void setBackground(Color bg) {
		for(int i = 0 ; i < colNum ; i++) {
			cols[i].setBackground(bg);
		}
		
	}
	
}
