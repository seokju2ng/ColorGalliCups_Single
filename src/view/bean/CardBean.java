package view.bean;

public class CardBean {
	private int num;
	private String path;
	private String answer;
	
	public CardBean() {}
	public CardBean(String path, String answer) {
		this.path = path; this.answer = answer;
	}
	public CardBean(int num, String path, String answer) {
		this.num = num; this.path = path; this.answer = answer;
	}
	public CardBean(CardBean card) {
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
