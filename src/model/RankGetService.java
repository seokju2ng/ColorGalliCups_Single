package model;

import java.util.ArrayList;

/**
 * RankDao를 받아오는 Service 클래스이다.
 * 
 * @author 송준희
 */
public class RankGetService {
	/**
	 * RankController에 넘겨줄 RankDao를 저장하고 있다.
	 */
	private RankDao rankDao;

	
	/**
	 * null-parameter Constructor
	 */
	public RankGetService() {
		rankDao = RankDao.getInstance();
	}

	/**
	 * RankDao를 String[] 타입으로 변환해서 Controller에 넘겨주는 메소드이다.
	 * 
	 * @return RankDao의 참조값을 리턴한다.
	 */
	public ArrayList<String[]> getRanks() {
		if (rankDao == null) {
			System.out.println("RankGetService에서 getRanks() 메소드의 rankDao가 null");
			return null;
		}
		ArrayList<String[]> ranks = new ArrayList<>();
		ArrayList<Rank> rankD = rankDao.getRanks();
		for (int i = 0; i < rankD.size(); i++) {
			String[] rank = new String[] { "" + rankD.get(i).getRanking(), rankD.get(i).getName(),
					"" + rankD.get(i).getScore() };
			ranks.add(rank);
		}
		return ranks;
	}
}