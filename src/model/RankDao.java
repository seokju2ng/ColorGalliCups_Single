package model;

import java.util.ArrayList;

public class RankDao {
	
	private ArrayList<Rank> ranks;
	private static RankDao rankDao;
	static {
		rankDao = new RankDao();
	}
	
	private RankDao() {
		ranks = new ArrayList<>();
	}
	public boolean insert(String name, int score) {
		return true;
	}
	public int search(int score) {
		return 0;
	}
	public boolean update(int index, String name, int score) {
		return true;
	}
	public boolean delete(int index) {
		return true;
	}
	public boolean clear() {
		return true;
	}
	public int getRanking(int score) {
		return 0;
	}
	public boolean loadRank() {
		return true;
	}
	public boolean saveRank() {
		return true;
	}
	public static RankDao getInstance() {
		return rankDao;
	}
	public ArrayList<Rank> getRanks(){
		return ranks;
	}
	
}
