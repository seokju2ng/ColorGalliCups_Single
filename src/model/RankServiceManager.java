package model;

import java.util.ArrayList;

/**
 * Rank관련 서비스(랭킹 추가,랭킹 가져오기, 랭킹 초기화 서비스)들을 관리하기 위한 클래스이다.
 * 
 * @author 김도균
 */
public class RankServiceManager {
	/** 랭킹 추가 서비스를 관리하는 멤버이다. */
	private RankAddService addService;
	/** 랭킹 초기화 서비스를 관리하는 멤버이다. */
	private RankClearService clearService;
	/** 랭킹 가져오기 서비스를 관리하는 멤버이다. */
	private RankGetService getService;

	
	/**
	 * RankServiceManager의 null-parameter생성자이다. 랭킹 추가, 랭킹 초기화, 랭킹 가져오기 서비스를 생성해준다.
	 */
	public RankServiceManager() {
		addService = new RankAddService();
		clearService = new RankClearService();
		getService = new RankGetService();
	}

	/**
	 * 랭킹 추가 서비스를 통해 플레이어의 이름과 점수를 저장하는 메소드이다.
	 * 
	 * @param name
	 *            플레이어의 이름이다.
	 * @param score
	 *            플레이어가 맞힌 정답 수(점수)이다.
	 * @return 랭킹 추가 서비스를 통해 랭킹 추가에 성공했다면 true, 실패했다면 false를 반환한다.
	 */
	public String[] insertRank(String name, int score) {
		if (addService == null) {
			System.out.println("RankServiceManager의 insert() 메소드의 addService가 null");
			return null;
		}
		if (name == null || score < 0) {
			System.out.println("RankServiceManager의 insert() 메소드의 parameter가 null");
			return null;
		}
		return addService.insertRank(name, score);
	}

	/**
	 * 랭킹 초기화 서비스를 통해 모든 플레이어의 랭킹을 초기화하는 메서드이다.
	 * 
	 * @return 랭킹 초기화 서비스를 통해 랭킹 초기화에 성공했다면 true, 실패했다면 false를 반환한다.
	 */
	public boolean clearRanks() {
		if (clearService == null) {
			System.out.println("RankServiceManager의 clearRanks() 메소드의 clearService가 null");
			return false;
		}
		return clearService.clear();
	}

	/**
	 * 랭킹 가져오기 서비스를 통해 랭킹 정보를 가져오는 메서드이다.
	 * 
	 * @return 가져오기 서비스가 null이면 null을 리턴하고, 아니라면 랭킹정보를 문자열ArrayList형태로 반환한다.
	 */
	public ArrayList<String[]> getRanks() {
		if (getService == null) {
			System.out.println("RankServiceManager의 getRanks() 메소드의 getService가 null");
			return null;
		}
		return getService.getRanks();
	}
}
