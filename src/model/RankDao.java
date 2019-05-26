package model;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Rank의 정보들을 Collection 타입으로 관리하는 클래스이다. CRUD 메소드를 가지고 있으며 Rank에 관련된 Service에서
 * 사용할 랭크정보들을 저장하는 클래스이다. 객체 생성을 한번만 해주기 위해 SingleTone으로 만들었다.
 * 
 * @author 송준희
 */
public class RankDao {
   /**
    * Rank의 정보들을 ArrayList 타입으로 저장하고 있다.
    */
   private ArrayList<Rank> ranks;
   /**
    * RankDao 객체를 SingleTone으로 생성해주기 위해 접근 제어자를 static으로 설정하고 RankDao를 저장하고 있다.
    */
   private static RankDao rankDao;
   /**
    * Ranking 정보를 파일에 읽고 쓸 RankIO 객체이다.
    */
   private RankIO rankio;
   static {
      rankDao = new RankDao();
   }

   /**
    * null-parameter Constructor RankDao 클래스의 생성자로 Rank 정보들을 담고 있는 ArrayList를
    * default값으로 초기화한 RankDao 객체를 할당해준다.
    */
   private RankDao() {
      ranks = new ArrayList<Rank>();
      rankio = new RankIO();
      loadRank();
   }

   /**
    * 랭킹 정보가 추가될 때 불리는 메서드이다. 랭킹에 추가될 이름과 점수가 파라메터로 전달되면 자동으로 순위를 찾아 삽입하고 정렬한다.
    * @param name 랭킹에 추가될 이름을 전달하는 변수이다.
    * @param score 랭킹에 추가될 점수를 전달하는 변수이다.
    * @return 삽입에 성공하면 true, 아니면 false를 반환한다.
    */
   	public boolean insert(String name, int score) {
   		if(name == null) return false;
   		int ranking = this.getRanking(score);
	    int index = this.searchInsertIndex(score);
	    System.out.println("ranking:"+ranking+", index:"+index);
	    if(ranking == -1 || index == -1) return false;
	    ranks.add(index, new Rank(ranking, name, score));
	    if(ranks.size() == 6) ranks.remove(5);
	    for(int i = index + 1; i < ranks.size(); i++) {
		   update(i, ranks.get(i).getRanking()+1);
	    }
	    saveRank();
	    return true;
   	}

   /**
    * 사용자로부터 검색할 점수를 받아 RankDao에서 해당하는 점수를 찾는 search() 메소드이다.
    * 
    * @param score
    *            사용자가 RankDao에서 찾을 점수이다.
    * @return 해당하는 점수가 들어있는 ArrayList의 index를 반환한다. 해당 점수가 없는경우 -1을 리턴한다.
    */
   public int search(int score) {
      for (int i = 0; i < ranks.size(); i++) {
         if (score == ranks.get(i).getScore())
            return i;
      }
      return -1;
   }
   /**
    * 파라메터로 전달 받은 점수가 몇번 인덱스에 삽입되어야 하는지 체크해 리턴해준다.
    * 
    * @param score 사용자가 RankDao에서 찾을 점수이다.
    * @return index 파라메터로 전달 받은 점수가 몇번 인덱스에 삽입되어야 하는지 리턴해준다.
    */
   private int searchInsertIndex(int score) {
	   if(ranks == null) return -1;
	   if(ranks.size() == 0) return 0;
	   for(int i = 0; i < ranks.size(); i++) {
		   if (score == ranks.get(i).getScore()) {	// 넣을 점수가 현재 인덱스의 점수와 같다면, 
			   if (i+1 == ranks.size()) {	// 현재 인덱스가 마지막 인덱스라면 그 다음 인덱스에 insert한다.
				   return i+1;
			   }
//			   else if (score != ranks.get(i+1).getScore()) {
//				   return i+1;
//			   }
		   }
		   if (score > ranks.get(i).getScore()) {
			   return i;
		   }
	   }		// 반복문이 끝나면 랭킹에 있는 점수보다 score가 낮은 상태이다.
	   if(ranks.size() < 5)	// 랭크가 5개보다 작을 때는 마지막 랭크로 삽입한다.
		   return ranks.size(); 
	   else return -1;
   }

   /**
    * 사용자로부터 index, ranking을 받아 RankDao의 Rank 객체의 값을 수정하는 update() 메소드이다.
    * 
    * @param index
    *            사용자가 수정할 ArrayList의 index이다.
    * @param ranking
    *            사용자가 수정할 Rank의 ranking이다.
    * @return ArrayList의 크기보다 index가 크거나 점수가 음수이면 false를 반환, 아니면 true를 반환한다.
    */
   public boolean update(int index, int ranking) {
	   if(index >= ranks.size() || index < 0) return false;
	   ranks.get(index).setRanking(ranking);
	   return true;
   }
   /**
    * 사용자로부터 index, name을 받아 RankDao의 Rank 객체의 값을 수정하는 update() 메소드이다.
    * @param index 사용자가 수정할 ArrayList의 index이다.
    * @param name 사용자가 수정할 Rank의 name이다.
    * @return ArrayList의 크기보다 index가 크거나 점수가 음수이면 false를 반환, 아니면 true를 반환한다.
    */
   public boolean update(int index, String name) {
	   if(index >= ranks.size() || index < 0) return false;
	   if(name == null) return false;
	   ranks.get(index).setName(name);
	   return true;
   }

   /**
    * 사용자로부터 index를 받아 RankDao의 Rank 객체를 제거하는 delete() 메소드이다.
    * 
    * @param index
    *            사용자가 삭제할 ArrayList의 index이다.
    * @return ArrayList의 크기보다 index가 크면 false를 반환, 아니면 true를 반환한다.
    */
   public boolean delete(int index) {
	   if(ranks == null) return false;
	   if(ranks.remove(index) != null) {
		   for(int i = index; i < ranks.size(); i++) {
			   update(i, ranks.get(i).getRanking()-1);
		   }
		   return true;
	   }
	   return false;
   }

   /**
    * RankDao의 ArrayList에 있는 모든 Rank 객체를 제거하는 clear() 메소드이다.
    * 
    * @return 초기화에 성공하면 true, 실패하면 false를 반환한다.
    */
   public boolean clear() {
	   if(ranks == null) return false;
	   ArrayList<String> clear = new ArrayList<String>();
	   clear.add("0");
	   if(rankio.saveRank(clear)) {
		   ranks.clear();
		   return true;
	   }
	   return false;
   }

   /**
    * RankDao의 ArrayList에 있는 Rank 객체들 사이에서 사용자가 입력한 score의 순위가 몇 순위인지 알려주는 메소드이다.
    * 
    * @param score RankDao의 Rank들의 점수를 비교할 score 값이다.
    * @return 사용자로부터 입력받은 score에 해당하는 순위를 반환한다.
    */
   public int getRanking(int score) {
	   if(ranks == null) return -1;
	   if(ranks.size() == 0) return 1;
	   for (int i = 0; i < ranks.size(); i++) {
		   if (score >= ranks.get(i).getScore()) {
			   return ranks.get(i).getRanking();
		   }
	   }
	   if(ranks.size() < 5)	// 랭크가 5개보다 작을 때는 마지막 순위로 삽입한다.
		   return ranks.size()+1; 
	   else return -1;
   }

   /**
    * 파일로부터 RankDao에 저장할 Rank 정보들을 받아오는 메소드이다.
    * 
    * @return Rank 정보들을 받아오면 true, 아니면 false를 리턴한다.
    */
   public boolean loadRank() {
	   if (ranks == null) return false;
	   ArrayList<String> load = rankio.loadRank();
	   if (load == null || load.size() == 0) return false;
	   int n = Integer.parseInt(load.get(0));
	   for(int i = 1; i <= n; i++) {
		   StringTokenizer st = new StringTokenizer(load.get(i), "\t");
		   int rank = Integer.parseInt(st.nextToken());
		   String name = st.nextToken();
		   int score = Integer.parseInt(st.nextToken());
		   ranks.add(new Rank(rank, name, score));
	   }
	   return true;
   }
   /**
    * 프로그램으로 부터 파일에 저장할 Rank 정보들을 업로드하는 메소드이다.
    * 
    * @return Rank 정보들을 업로드하면 true, 아니면 false를 리턴한다.
    */
   public boolean saveRank() {
	   if(ranks == null) return false;
	   ArrayList<String> save = new ArrayList<>();
	   int n = ranks.size();
	   save.add(n+"");
	   for(int i = 0; i < n; i++) {
		   StringBuilder sb = new StringBuilder();
		   sb.append(ranks.get(i).getRanking()+"\t");
		   sb.append(ranks.get(i).getName()+"\t");
		   sb.append(ranks.get(i).getScore());
		   save.add(sb.toString());
	   }
	   return rankio.saveRank(save);
   }

   /**
    * RankDao를 SingleTone으로 사용하도록 해주는 메소드이다.
    * 
    * @return RankDao 객체인 rankDao를 반환한다.
    */
   public static RankDao getInstance() {
      return rankDao;
   }

   /**
    * RankDao 클래스에서 RankDao 필드의 참조값을 리턴해주는 getter() 메소드이다.
    * 
    * @return RankDao필드의 참조값을 리턴해준다.
    */
   public ArrayList<Rank> getRanks() {
	   if(ranks == null) return null;
	   ArrayList<Rank> clone = new ArrayList<Rank>();
	   for(int i = 0; i < ranks.size(); i++) {
		   clone.add(new Rank(ranks.get(i)));
	   }
      return clone;
   }
}