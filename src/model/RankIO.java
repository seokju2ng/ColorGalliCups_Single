package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 파일로부터 Rank 정보를 읽고, 파일에 Rank 정보를 쓰는 클래스이다.
 * @author 송준희
 */
public class RankIO {
	/**
	 * 읽고 쓸 파일의 경로를 dat 파일 형식으로 저장하고 있다.
	 */
	private final String rankPath = "data/ranks.dat";
	
	/**
	 * 프로그램에서 파일에 Rank정보를 저장하는 메소드이다.
	 * @param rankInfo 파일에 저장할 랭킹정보이다.
	 * @return 파일에 Rank정보를 저장하면 true, 실패하면 false를 리턴한다.
	 */
	public boolean saveRank(ArrayList<String> rankInfo) {
		try(PrintWriter pw = new PrintWriter(new File(rankPath));) {
			for(int i = 0; i < rankInfo.size(); i++) {
				pw.println(rankInfo.get(i));
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
	 * 파일로부터 프로그램에 Rank정보를 저장하는 메소드이다.
	 * @return Rank정보를 저장한 ArrayList를 반환한다.
	 */
	public ArrayList<String> loadRank() {
		ArrayList<String> load = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(rankPath))){
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
