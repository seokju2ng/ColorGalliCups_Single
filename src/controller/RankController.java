package controller;

import java.util.ArrayList;

import model.RankServiceManager;
import view.bean.RankBean;

/**
 * Rank의 Service들과 Ranks 사이에서 Rank 정보들을 가공하여 서로 주고받을 수 있게 하는 Contorller 클래스이다.
 * @author 송준희
 */
public class RankController {
	/**
	 * RankAddService를 저장하고 있다.
	 */
	private RankServiceManager rankServiceManager;

	/**
	 * null-parameter Constructor
	 */
	public RankController() {
		rankServiceManager = new RankServiceManager();
	}
	/**
	 * 
	 * RankDao의 Rank 정보를 가공하여 Ranks의 RankBean에 추가하는 메소드이다.
	 * @param name Ranks에서 받은 name을 저장하고 있다.
	 * @param score Ranks에서 받은 score을 저장하고 있다.
	 * @return 삽입에 성공하면 true, 아니면 false를 리턴한다.
	 */
	public String[] insertRank(String name, int score) {
		if(rankServiceManager == null) return null;
		if(name == null || name.equals("") || score < 0) {
			return null;
		}
		
		return rankServiceManager.insertRank(name, score);
	}
	/**
	 * Ranks의 모든 RankBean를 초기화하는 메소드이다.
	 * @return 초기화에 성공하면 true, 아니면 false를 리턴한다.
	 */
	public boolean clearRanking() {
		if(rankServiceManager == null) return false;
		return rankServiceManager.clearRanks();
	}
	/**
	 * RankDao의 Rank 모든 정보를 가공하여 Ranks에 전달해주는 메소드이다.
	 * @return RankBean의 ArrayList타입을 리턴한다.
	 */
	public ArrayList<RankBean> getRanks(){
		if(rankServiceManager == null) return null;
		ArrayList<String[]> serviceRank = rankServiceManager.getRanks();
		ArrayList<RankBean> ranks= new ArrayList<RankBean>();
		
		for(int i = 0; i < serviceRank.size(); i++) {		
			String[] rankInfo = serviceRank.get(i);
			int ranking = Integer.valueOf(rankInfo[0]);
			String name = rankInfo[1];
			int score = Integer.valueOf(rankInfo[2]);
			ranks.add(new RankBean(ranking,name,score));
		}
		return ranks;
	}
}
