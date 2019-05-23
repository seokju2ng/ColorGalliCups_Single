package view.etc;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ChangePanelService {
	static private ChangePanelService obj;
	private CardLayout layout;
	private JPanel mainPanel;
//	private int num;

	static {
		obj = new ChangePanelService();
	}

	private ChangePanelService() {
	}

	public static ChangePanelService getInstance() {
		return obj;
	}

	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	
	public void changePanel(String panelName) {
		layout.show(mainPanel, panelName);
		mainPanel.setFocusable(true);
	}
	
	public void changePanel(String panelName, JPanel panel) {
		removePanel(panel);
		layout.show(mainPanel, panelName);
		mainPanel.setFocusable(true);
	}
	
	public void addPanel(String panelName, JPanel panel) {
		mainPanel.add(panelName, panel);
	}
	
	public void removePanel(JPanel panel) {
		try { mainPanel.remove(panel); }
		catch (Exception e) { System.err.println("지울 패널 없음");}
	}
	
//	public void changePanel(String panelName, int num) {
//		layout.show(mainPanel, panelName);
//		mainPanel.setFocusable(true);
//		this.num = num;
//	}
//	public int getNum() {
//		return num;
//	}

}
