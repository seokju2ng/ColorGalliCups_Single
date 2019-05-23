package etc;

import javax.swing.JFrame;

import view.*;

public class TempView extends JFrame{
	TempView(){
		//Option o = new Option();
		//Description o = new Description();
		//GameInfo o = new GameInfo();
		//TwoPlay o = new TwoPlay();
		//NetworkMode o = new NetworkMode();
		//WatingRoomCrown o = new WatingRoomCrown();
		WaitingRoomNormal o = new WaitingRoomNormal();
		add(o);
		this.setVisible(true);
		setSize(1363, 714);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new TempView();
	}
}

