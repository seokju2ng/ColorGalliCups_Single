package model;

import java.util.ArrayList;

/**
 * Rank의 정보들을 Collection 타입으로 관리하는 클래스이다. CRUD 메소드를 가지고 있으며 Rank에 관련된 Service에서 사용할 랭크정보들을 저장하는 클래스이다. 객체 생성을 한번만 해주기 위해 SingleTone으로 만들었다.
 * @author 송준희
 */
public class RankDao {
	/*
	 * Rank의 정보들을 ArrayList 타입으로 저장하고 있다.
	 */
	private ArrayList<Rank> ranks;
	/*
	 * RankDao 객체를 SingleTone으로 생성해주기 위해 접근 제어자를 static으로 설정하고 RankDao를 저장하고 있다.
	 */
	private static RankDao rankDao;
	/**
	 * RankDao 객체를 SingleTone으로 생성한다.
	 */
	static {
		rankDao = new RankDao();
	}

	/**
	 * null-parameter Constructor
	 * RankDao 클래스의 생성자로 Rank 정보들을 담고 있는 ArrayList를  default값으로 초기화한 RankDao 객체를 할당해준다.
	 */
	private RankDao() {
		ranks = new ArrayList<>();
	}
	/**
	 * 사용자로부터 Rank에 저장할 값들을 받아 Rank 객체를 생성하고 ArrayList에 삽입하는 insert() 메소드이다. 
	 * @param name ArrayList의 Rank에 저장할 사용자의 name에 해당하는 값이다.
	 * @param score ArrayList의 Rank에 저장할 사용자의 score에 해당하는 값이다.
	 * @return 삽입에 성공하면 true, 아니면 false를 반환한다.
	 */
	public boolean insert(String name, int score) {
		return true;
	}
	/**
	 * 사용자로부터 검색할 점수를 받아 RankDao에서 해당하는 점수를 찾는 search() 메소드이다.
	 * @param score 사용자가 RankDao에서 찾을 점수이다.
	 * @return 해당하는 점수가 들어있는 ArrayList의 index를 반환한다.
	 */
	public int search(int score) {
		return 0;
	}
	/**
	 * 사용자로부터 index, name, score를 받아 RankDao의 Rank 객체의 값을 수정하는 update() 메소드이다.
	 * @param index 사용자가 수정할 ArrayList의 index이다.
	 * @param name 사용자가 수정할 Rank의 name이다.
	 * @param score 사용자가 수정할 Rank의 score이다.
	 * @return ArrayList의 크기보다 index가 크거나 점수가 음수이면 false를 반환, 아니면 true를 반환한다.
	 */
	public boolean update(int index, String name, int score) {
		return true;
	}
	/**
	 * 사용자로부터 index를 받아 RankDao의 Rank 객체를 제거하는 delete() 메소드이다.
	 * @param index 사용자가 삭제할 ArrayList의 index이다. 
	 * @return ArrayList의 크기보다 index가 크면 false를 반환, 아니면 true를 반환한다.
	 */
	public boolean delete(int index) {
		return true;
	}
	/**
	 * RankDao의 ArrayList에 있는 모든 Rank 객체를 제거하는 clear() 메소드이다.
	 * @return 초기화에 성공하면 true, 실패하면 false를 반환한다.
	 */
	public boolean clear() {
		return true;
	}
	/**
	 * RankDao의 ArrayList에 있는 Rank 객체들 사이에서 사용자가 입력한 score의 순위가 몇 순위인지 알려주는 메소드이다.
	 * @param score RankDao의 Rank들의 점수를 비교할 score 값이다. 
	 * @return 사용자로부터 입력받은 score에 해당하는 순위를 반환한다.
	 */
	public int getRanking(int score) {
		return 0;
	}
	/**
	 * 파일로부터 RankDao에 저장할 Rank 정보들을 받아오는 메소드이다.
	 * @return Rank 정보들을 받아오면 true, 아니면 false를 리턴한다.
	 */
	public boolean loadRank() {
		return true;
	}
	/**
	 * 프로그램으로 부터 파일에 저장할 Rank 정보들을 업로드하는 메소드이다.
	 * @return Rank 정보들을 업로드하면 true, 아니면 false를 리턴한다.
	 */
	public boolean saveRank() {
		return true;
	}
	/**
	 * RankDao를 SingleTone으로 사용하도록 해주는 메소드이다.
	 * @return RankDao 객체인 rankDao를 반환한다. 
	 */
	public static RankDao getInstance() {
		return rankDao;
	}
	/**
	 * RankDao 클래스에서 RankDao 필드의 참조값을 리턴해주는 getter() 메소드이다.
	 * @return RankDao필드의 참조값을 리턴해준다.
	 */
	public ArrayList<Rank> getRanks() {
		return ranks;
	}
}
