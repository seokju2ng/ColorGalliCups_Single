package etc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
	/*
	public static void main(String[] args) {
		OptionService os = new OptionService();
//		os.setOption(true, false, 10);
		System.out.println(os.getOption());
		if(os.saveOption()) System.out.println("성공");
		else System.out.println("실패");
	}
	*/
	/*
	public static void main(String[] args) {
		ArrayList<String> card = new ArrayList<String>();
		card.add("3");
		card.add("6 image/6.png 53142");
		card.add("12 image/12.png 53142");
		card.add("17 image/17.png 53142");
		new CardIO().saveCard(card);
	}*/
	
	public static void main(String[] args) {
		ArrayList<String> cardInfo = new ArrayList<>();
		cardInfo.add("1");
		cardInfo.add("101 image/101.png 41/2/53");
		try(PrintWriter pw = new PrintWriter(new File("data/gcards.dat"))) {
				for(int i = 0; i < cardInfo.size(); i++) {
					pw.println(cardInfo.get(i));
				}
				System.out.println("성공");
			} catch (FileNotFoundException e) {
				System.err.println(e);
			}catch(Exception e) {
				System.err.println(e);
			}
	}
}
