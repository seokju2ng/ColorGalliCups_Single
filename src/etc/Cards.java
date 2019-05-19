package etc;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Cards  {
	public ArrayList<JLabel> card_arr;
	
	public Cards() {
		card_arr = new ArrayList<>();
	
		for(int i = 0 ; i  < 38 ; i++) {
			card_arr.add(new JLabel(new ImageIcon("image/"+(i+1)+".png")));
		}
		Collections.shuffle(card_arr);
	}

//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.setLayout(new FlowLayout());
//		Cards c = new Cards();
//		for(int i = 0;  i < c.card_arr.size(); i++) {
//			f.add(c.card_arr.get(i));
//		}
//		f.setTitle("Ä«µå Å×½ºÆ®");
//		f.setSize(1363,714);
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//	}

}
