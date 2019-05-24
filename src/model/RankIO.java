package model;

import java.util.ArrayList;

/**
 * 파일로부터 Rank 정보를 읽고, 파일에 Rank 정보를 쓰는 클래스이다.
 * @author 송준희
 */
public class RankIO {
	/**
	 * 읽고 쓸 파일의 위치를 저장하고 있다.
	 */
	private final String rankPath = "data/ranks.txt";
	
	/**
	 * 파일로부터 Rank정보를 저장하는 메소드이다.
	 * @return 파일로부터 Rank정보를 저장하면 true, 아니면 false를 리턴한다.
	 */
	public boolean saveRank() {
		return true;
	}
	/**
	 * 프로그램에서 파일에 Rank정보를 쓰는 메소드이다.
	 * @return 파일에 Rank정보를 쓰면 true, 아니면 false를 리턴한다.
	 */
	public ArrayList<String> loadRank() {
		return null;
	}
}
