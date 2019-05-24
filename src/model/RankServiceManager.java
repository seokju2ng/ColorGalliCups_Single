package model;

import java.util.ArrayList;

public class RankServiceManager {
	private RankAddService addService;
	private RankClearService clearService;
	private RankGetService getService;

	public RankServiceManager() {
		addService = new RankAddService();
		clearService = new RankClearService();
		getService = new RankGetService();
	}

	public boolean getAddService(String name, int score) {
		if (addService == null || name == null || score < 0)
			return false;
		return addService.insert(name, score);
	}

	public boolean getClearService() {
		if(clearService==null)
			return false;
		return clearService.clear();
	}

	public ArrayList<String[]> getGetService() {
		if(getService==null)
			return null;
		return getService.getRanks();
	}
}
