package view;

public class MyIndex {
	private int index;
	public MyIndex(){}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public void plus() {
		index++;
	}
	public void minus() {
		index--;
	}
}