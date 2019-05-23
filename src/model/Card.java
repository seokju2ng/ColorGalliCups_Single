package model;

public class Card {
	private int num;
	private String path;
	private String answer;
	
	public Card() {}
	public Card(String path, String answer) {
		this.path = path; this.answer = answer;
	}
	public Card(int num, String path, String answer) {
		this.num = num; this.path = path; this.answer = answer;
	}
	public Card(Card card) {
		this.num = card.getNum();
		this.path = card.getPath();
		this.answer = card.getAnswer();
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String toString() {
		return "Card[card_num:"+num+"/img_path:"+path+"/answer:"+answer+"]";
	}
}
