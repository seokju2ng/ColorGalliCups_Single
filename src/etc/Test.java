package etc;

import model.OptionService;

public class Test {
//	public static void main(String[] args) {
//		ArrayList<String> as = new ArrayList<>();
//		as.add("5");
//		as.add("1\t김도균\t5");
//		as.add("2\t김블랑\t3");
//		as.add("2\t김광산\t3");
//		as.add("4\t송준희\t2");
//		as.add("5\t김똥똥\t1");
//		if(new RankIO().saveRank(as)) System.out.println("성공");
//		else System.out.println("실패");
//	}
	
	
//	public static void main(String[] args) {
//		ArrayList<String> card = new CardIO().loadCard("data/cards.txt");
//		try(PrintWriter pw = new PrintWriter(new FileWriter("data/cards.dat"))) {
//			for(String str : card) {
//				pw.println(str);
//			}
//			pw.flush();
//			System.out.println("성공");
//		} catch(Exception e) {
//			System.err.println("실패");
//		}
//	}
	
	public static void main(String[] args) {
		OptionService os = new OptionService();
//		os.setOption(true, false, 10);
		System.out.println(os.getOption());
		if(os.saveOption()) System.out.println("성공");
		else System.out.println("실패");
	}
}
