package view.bean;

/**
 * 랭크정보 뷰전용 클래스이다. 기본적인 메소드만 가지고 있으며 Ranks에서 사용할 랭크정보(순위, 이름, 점수)를 저장하는 클래스이다.
 * @author 송준희
 */
public class RankBean {
	/**
	 * 사용자의 순위를 저장하고 있다.
	 */
	private int ranking;
	/**
	 * 사용자의 이름을 저장하고 있다.
	 */
	private String name;
	/**
	 * 사용자의 점수를 저장하고 있다.
	 */
	private int score;
	
	
	/**
	 * null-parameter Constructor
	 * RankBean 클래스의 생성자로 사용자의 순위, 이름, 점수를 default값으로 초기화한 랭크정보 객체를 할당해준다.
	 */
	public RankBean() {
		
	}
	/**
	 * RankBean 클래스의 생성자로 해당 순위, 이름, 점수에 해당하는 새로운 성적정보 객체를 할당해준다.
	 * @param ranking 랭크정보 클래스에서 사용자의 순위에 해당하는 값이다.
	 * @param name 랭크정보 클래스 사용자의 이름에 해당하는 값이다.
	 * @param score 랭크정보 클래스에서 사용자의 점수에 해당하는 값이다.
	 */
	public RankBean(int ranking, String name, int score) {
		
	}
	/**
	 * 랭크정보 클래스에서 ranking 필드의 참조값을 리턴해주는 getter() 메소드이다.
	 * @return 랭크정보에서 ranking필드의 참조값을 리턴해준다.
	 */
	public int getRanking() {
		return ranking;
	}
	/**
	 * 랭크정보 클래스에서 ranking 필드의 값을 변경하는 setter() 메소드이다.
	 * @param ranking 원래의 ranking값에서 변경시키고 싶은 값이다.
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}
	/**
	 * 랭크정보 클래스에서 name 필드의 참조값을 리턴해주는 getter() 메소드이다.
	 * @return 랭크정보에서 name필드의 참조값을 리턴해준다.
	 */
	public String getName() {
		return name;
	}
	/**
	 * 랭크정보 클래스에서 name 필드의 값을 변경하는 setter() 메소드이다.
	 * @param name 원래의 name값에서 변경시키고 싶은 값이다.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 랭크정보 클래스에서 score 필드의 참조값을 리턴해주는 getter() 메소드이다.
	 * @return 랭크정보에서 score필드의 참조값을 리턴해준다.
	 */
	public int getScore() {
		return score;
	}
	/**
	 * 랭크정보 클래스에서 score 필드의 값을 변경하는 setter() 메소드이다.
	 * @param score 원래의 score값에서 변경시키고 싶은 값이다.
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * 랭크정보 객체의 데이터를 반환하는 toString() 메소드이다.
	 * @return 랭크정보 객체의 사용자의 순위, 이름, 점수를 String 타입으로 반환하여 리턴해준다.
	 */
	public String toString() {
		return "Rank [ranking=" + ranking + ", name=" + name + ", score=" + score + "]";
	}	
}
