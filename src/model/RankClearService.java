package model;

/**
 * RankDao 정보를 초기화하는 클래스이다.
 * @author 송준희
 */
public class RankClearService {
	/**
	 * RankController에 넘겨줄 RankDao를 저장하고 있다.
	 */
	private RankDao rankDao;
	
	/**
	 * null-parameter Constructor
	 */
	public RankClearService() {
		
	}
	/**
	 * RankDao에서 모든 Rank정보를 초기화해주는 메소드이다.
	 * @return 초기화에 성공하면 true, 아니면 false를 리턴한다.
	 */
	public boolean clear(){
		return true;
	}
}
	