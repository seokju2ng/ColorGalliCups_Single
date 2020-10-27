package view.etc;

import java.awt.CardLayout;

import javax.swing.JPanel;
/**
 * (Singleton)한 화면에서 다른 화면으로 전환할 수 있게 해주는 클래스이다. <br>
 * @author 김도균*/
public class ChangePanelService {
	/**단 한번만 생성되는 ChangePanelService 객체를 참조하기위한 멤버이다.*/
	static private ChangePanelService obj;
	/**전환 될 화면들의 전반적인 layout을 설정해주는 멤버이다.*/
	private CardLayout layout;
	/**게임이 진행되면서 나오게 될 모든 화면을 add하기 위한 최 상단 부모패널이다.*/
	private JPanel mainPanel;

	static {
		obj = new ChangePanelService();
	}

	/**ChangePanelService의 객체를 생성하는 생성자이다.*/
	private ChangePanelService() {
	}

	/**외부에서 ChangePanelService 객체를 사용할 수 있도록 단 한번만 생성된 객체를 참조하는 멤버를 반환한다. 
	 * @return 단 한번만 생성된 인스턴스 객체를 참조하는 변수.*/
	public static ChangePanelService getInstance() {
		return obj;
	}

	/**전달받은 매개변수로 게임 전반적인 layout을 설정하기 위한 설정자이다.
	 * @param layout 게임에 적용될 전반적인 layout이다.*/
	public void setLayout(CardLayout layout) {
		this.layout = layout;
	}

	/**전달받은 매개변수로 모든 게임화면을 부착할 최 상단 패널을 설정해주는 설정자이다.
	 * @param mainPanel 모든 게임화면을 부착할 최 상단 패널이다.*/
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	/**전달받은 매개변수의 이름에 해당하는 Component로 화면을 전환시켜주는 메서드이다.
	 * @param panelName 전환할 Component가 갖는 name이다.*/
	public void changePanel(String panelName) {
		layout.show(mainPanel, panelName);
		mainPanel.setFocusable(true);
		Sound.playEffect("audio/enter.wav");
//		if(panelName.equals("MainView")) {
//			Sound.stop();
//			Sound.playBgm("audio/mainBGM.wav");
//		}
		if(panelName.equals("SingleMode") || panelName.equals("DualMode") ||panelName.equals("NetworkPlayMode")){
			Sound.stop();
			Sound.playBgm("audio/gameModeBGM.wav");
		}
	}
	/**전달받은 매개변수의 JPanel을 삭제하고 전달받은 이름에 해당하는 Component로 화면을 전환시켜주는 메서드이다.
	 * @param panelName 전환할 Component가 갖는 name이다.
	 * @param panel 게임화면에서 삭제할 JPanel이다.*/
	public void changePanel(String panelName, JPanel panel) {
		removePanel(panel);
		layout.show(mainPanel, panelName);
		mainPanel.setFocusable(true);
		Sound.playEffect("audio/enter.wav");
		if(panelName.equals("MainView")) {
			Sound.stop();
			Sound.playBgm("audio/mainBGM.wav");
		}
	}
	
	/**패널과 그 패널의 name을 전달받아 메인화면에 add해주는 메서드이다
	 * @param panelName 추가될 JPanel이 갖는 name이다.
	 * @param panel 추가될 JPanel이다.*/
	public void addPanel(String panelName, JPanel panel) {
		mainPanel.add(panelName, panel);
	}
	/**전달 받은 패널을 메인 패널에서 삭제해주는 메서드이다.
	 * @param panel 메인패널에서 삭제할 JPanel이다.*/
	public void removePanel(JPanel panel) {
		try { mainPanel.remove(panel); }
		catch (Exception e) { System.err.println("지울 패널 없음");}
	}
	
}
