package view.bean;

import java.util.ArrayList;

import controller.RankController;

/**
 * View RankBean의 객체들을 Collection 타입으로 관리하는 클래스이다.
 * RankBean에 관련된 Service에서 사용할 랭크정보들을 저장하는 클래스이다.
 * 
 * @author seokjung
 */
public class Ranks {
	/**
	 * 랭크 정보를 리스트로 가지고 있다.
	 */
	private ArrayList<RankBean> ranks;
	/**
	 * Ranks의 서비스로부터 받은 랭킹정보들을 SinglePlayMode 객체가 사용할 수 있는 형태로 가공하여 전달해주는 컨트롤러 객체이다.
	 */
	private RankController rankController;

	/**
	 * Null-Parameter Constructor : RankController를 통해 랭킹 정보를 담고 있는 File에서 랭킹정보를 읽어와 저장한다.
	 */
	public Ranks() {
		// ranks = new ArrayList<>();
		rankController = new RankController();
		ranks = rankController.getRanks();
	}

	/**
	 * 랭킹에 저장할 정보를 전달 받아 RankController를 통해 랭킹 삽입을 하고 파일에 저장한다.
	 * 
	 * @param name 랭킹에 삽입될 이름이다.
	 * @param score 랭킹에 삽입될 점수이다.
	 * @return 삽입에 성공하면 true, 아니면 false를 반환한다.
	 */
	public boolean insert(String name, int score) {
		if (isRanker(score) == false) { // 해당 점수가 순위권 안에 없으면 insert 하지 않는다.
			return false;
		}
		// RankBean rank = new RankBean(6,name,score);

		//if (ranks.size() < 5) {
			boolean res = rankController.insert(name, score);
			// if(res == true) {
			// ranks.add(new RankBean(6,name,score));
			// }
			System.out.println("Ranks res ="+res);
			if (res == true) {
				ranks = rankController.getRanks();
			}
			return res;
	//	}
	}

	/**
	 * 사용자로부터 검색할 점수를 받아 Ranks에서 해당하는 점수를 찾는 search() 메소드이다.
	 * 
	 * @param score 사용자가 Ranks에서 찾을 점수이다.
	 * @return 해당하는 점수가 들어있는 ArrayList의 index를 반환한다. 실패시 -1반환.
	 */
	public int search(int score) {
		int idx = -1;
		for (int i = 0; i < ranks.size(); i++) {
			if (score == ranks.get(i).getScore()) {
				idx = i;
				return idx;
			}
		}
		return idx;
	}

	/**
	 * Ranks의 ArrayList에 있는 모든 RankBean 객체를 제거하는 메소드이다.
	 * 
	 * @return 초기화에 성공하면 true, 실패하면 false를 반환한다.
	 */
	public boolean clearRanking() {
//		if (ranks == null) {
//			System.out.println("ranks가 null입니다!");
//			return false;
//		}
//		ranks.clear();
		boolean res = rankController.clearRanking();
		return res;
	}

	/**
	 * Ranks 클래스에서 Ranks 필드의 참조값을 리턴해주는 getter() 메소드이다.
	 * 
	 * @return Ranks필드의 참조값을 리턴해준다.
	 */
	public ArrayList<RankBean> getRanks() {
		ranks = rankController.getRanks();
		return ranks;
	}
	/**
	 * 점수를 전달 받아 해당 점수가 5위 안에 드는지 체크해주는 메서드이다. 
	 * @param score 5위 안에 드는지 체크할 점수이다.
	 * @return 전달받은 점수가 5위 안에 드는 점수이면 true, 아니면 false를 반환한다.
	 */
	public boolean isRanker(int score) {
		if (ranks == null || score == 0) {
			return false;
		}
		if (ranks.size() < 5) // 기존 랭커가 5명미만 이면 무조건 랭커가 된다.
			return true;

		if (this.ranks.get(4).getScore() < score) {
			return true;
		}
		return false;
	}
}
