package controller;

import java.util.ArrayList;

import model.RankAddService;
import model.RankClearService;
import model.RankGetService;
import view.bean.RankBean;

public class RankController {
	private RankAddService rankAddService;
	private RankClearService rankClearService;
	private RankGetService rankGetService;
	
	public RankController() {
		
	}
	
	public boolean insert(String name, int score) {
		return true;
	}
	
	public boolean clearRanking() {
		return true;
	}
	
	public ArrayList<RankBean> getRanks(){
		return null;
	}
}
