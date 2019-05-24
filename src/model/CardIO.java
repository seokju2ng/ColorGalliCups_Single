package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * 저장된 파일로부터 카드정보를 읽어와 CardDao에 전달해준다.
 * 카드에 대한 번호, 그림, 정답이 변경되었을 때 CardDao의 정보를 파일에 쓰기작업을 한다. 
 * @author 김용희
 */
public class CardIO {
	/**
	 * card의 정보들을 읽고 쓰기 작업을 할 파일의 경로값이다.
	 */
	private final String cardPath = "data/cards.txt";
	/**
	 * 카드에 대한 번호, 그림, 정답이 변경되었을 때 CardDao의 정보를 파일에 쓰기작업을 한다. 
	 * @return 쓰기작업을 성공했을 때 true, 실패했을 때 false를 리턴한다.
	 */
	public boolean saveCard(ArrayList<String> cardInfo) {
		try(PrintWriter pw = new PrintWriter(new File(cardPath));) {
			for(int i = 0; i < cardInfo.size(); i++) {
				pw.println(cardInfo.get(i));
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			return false;
		}catch(Exception e) {
			System.err.println(e);
			return false;
		}

		return true;
	}
	/**
	 * 저장된 파일로부터 카드정보를 읽어와 CardDao에 전달해준다.
	 * @param rankInfo 파일에 저장 할 카드정보이다.
	 * @return 저장된 파일로부터 카드정보들을 읽어오고, 읽어온 값을 리턴한다.
	 */
	public ArrayList<String> loadCard() {
		ArrayList<String> load = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(cardPath))){
			String str;
			while((str = br.readLine()) != null) {
				load.add(str);
			}
		} catch(FileNotFoundException e) { System.err.println(e); return null;
		} catch(IOException e) { System.err.println(e); return null;
		} catch(Exception e) { System.err.println(e); return null;
		}
		return load;
	}
}
