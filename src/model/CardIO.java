package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CardIO {
	private final String cardPath = "data/cards.txt";
	
	public boolean saveCard() {
		return true;
	}
	
	public ArrayList<String> loadCard() {
		ArrayList<String> load = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(cardPath))){
			String str;
			while((str = br.readLine()) != null) {
				load.add(str);
			}
		} catch(FileNotFoundException e) { System.err.println(e);
		} catch(IOException e) { System.err.println(e);
		} catch(Exception e) { System.err.println(e);
		}
		return load;
	}
}
