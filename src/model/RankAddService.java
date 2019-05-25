package model;

/**
 * RankDao 정보를 추가해주는 클래스이다.
 * @author 송준희
 */
public class RankAddService {
	/**
	 * RankController에 넘겨줄 RankDao를 저장하고 있다.
	 */
	private RankDao rankDao;
	
	/**
	 * null-parameter Constructor
	 */
	public RankAddService() {
		rankDao=RankDao.getInstance();
	}
	/**
	 * RankDao에 Rank정보를 추가해주는 메소드이다.
	 * @param name 사용자가 rankDao에 추가할 name의 값이다.
	 * @param score 사용자가 rankDao에 추가할 score의 값이다.
	 * @return 추가에 성공하면 true, 아니면 false를 리턴한다.
	 */
	public boolean insert(String name, int score) {
		if(rankDao==null)
			return false;
		if(name==null||score<0) {
			return false;
		}
		return rankDao.insert(name, score);
	}
}
