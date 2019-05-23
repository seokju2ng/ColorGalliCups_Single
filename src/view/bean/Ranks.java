package view.bean;

import java.util.ArrayList;

import controller.RankController;

public class Ranks {
	private ArrayList<RankBean> ranks; 
	private RankController rankController;
	
	public Ranks() {
		
	}
	
	public boolean insert(String name, int score) {
		return true;
	}
	
	public int search(int score) {
		return 0;
	}
	
	public boolean clearRanking() {
		return true;
	}
	
	public ArrayList<RankBean> getRanks(){
		return null;
	}
}
