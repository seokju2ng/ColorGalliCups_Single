package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.etc.ChangePanelService;

public class ColorGalliCups extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JPanel mainPanel;
//	private CardLayout layout;
//	
//	private MainView mainView;
//	private GameMode gameMode;
//	private SinglePlayMode singleMode;
//	private DualPlayMode dualMode;
//	private NetworkMode networkMode;
//	private NetworkPlayMode networkPlayMode;
//	private OptionView option;
//	private Help help;
//	private KeyControl keyControl;
//	private Tutorial tutorial;
//	private GameInfo gameInfo;
//	private WaitingRoomCrown waitingRoomC;
//	private WaitingRoomNormal waitingRoomN;
	                
	public ColorGalliCups() {
		super("ColorGalli Cups");
		
		JPanel mainPanel = new JPanel();
		CardLayout layout = new CardLayout();
		mainPanel.setLayout(layout);
		
		
//		MainView mainView = new MainView();
//		OptionView option = new OptionView();
//		Help help = new Help();
//		Tutorial tutorial = new Tutorial();
//		GameMode gameMode = new GameMode();
//		GameInfo gameInfo = new GameInfo();
//		KeyControl keyControl = new KeyControl();
//		NetworkPlayMode networkPlayMode = new NetworkPlayMode();
//		NetworkMode networkMode = new NetworkMode();
//		SinglePlayMode singleMode = new SinglePlayMode();
//		DualPlayMode dualMode = new DualPlayMode();
//		WaitingRoomCrown waitingRoomC = new WaitingRoomCrown();
//		WaitingRoomNormal waitingRoomN = new WaitingRoomNormal();
		
//		mainPanel.add("MainView", mainView);
//		mainPanel.add("Option", option);
//		mainPanel.add("Help", help);
//		mainPanel.add("Tutorial", tutorial);
//		mainPanel.add("GameMode", gameMode);
//		mainPanel.add("GameInfo", gameInfo);
//		mainPanel.add("KeyControl", keyControl);
//		mainPanel.add("NetworkMode", networkMode);
//		mainPanel.add("NetworkPlayMode", networkPlayMode);
//		mainPanel.add("SingleMode", singleMode);
//		mainPanel.add("DualMode", dualMode);
//		mainPanel.add("WatingRoomNormal", waitingRoomN);
//		mainPanel.add("WatingRoomCrown", waitingRoomC);
		
		ChangePanelService changePanel = ChangePanelService.getInstance();
		changePanel.setLayout(layout);
		changePanel.setMainPanel(mainPanel);
		changePanel.addPanel("MainView", new MainView());
		changePanel.addPanel("Option", new OptionView());
		changePanel.addPanel("Help", new Help());
		changePanel.addPanel("Tutorial", new Tutorial());
		changePanel.addPanel("GameMode", new GameMode());
		changePanel.addPanel("GameInfo", new GameInfo());
		changePanel.addPanel("KeyControl", new KeyControl());
		changePanel.addPanel("NetworkMode", new NetworkMode());
		changePanel.addPanel("NetworkPlayMode", new NetworkPlayMode());
//		changePanel.addPanel("SingleMode", new SinglePlayMode());
//		changePanel.addPanel("DualMode", new DualPlayMode());
		changePanel.addPanel("WaitingRoomCrown", new WaitingRoomCrown());
		
		add(mainPanel);
		layout.show(mainPanel, "MainView");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1363, 714);
		setVisible(true);

	}
	public static void main(String[] args) {
		new ColorGalliCups();
	}
}
