package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import etc.ChangePanelService;

public class ColorGalliCups extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	CardLayout layout;
	
	MainView mainView;
	GameMode gameMode;
	SinglePlayMode singleMode;
	DualPlayMode dualMode;
	NetworkMode networkMode;
	NetworkPlayMode networkPlayMode;
	Option option;
	Help help;
	KeyControl keyControl;
	Tutorial tutorial;
	GameInfo gameInfo;
	WaitingRoomCrown waitingRoomC;
	WaitingRoomNormal waitingRoomN;
	
	public ColorGalliCups() {
		super("ColorGalli Cups");
		
		mainPanel = new JPanel();
		layout = new CardLayout();
		mainPanel.setLayout(layout);
		
		
		mainView = new MainView();
		option = new Option();
		help = new Help();
		tutorial = new Tutorial();
		gameMode = new GameMode();
		gameInfo = new GameInfo();
		keyControl = new KeyControl();
		networkPlayMode = new NetworkPlayMode();
		networkMode = new NetworkMode();
		singleMode = new SinglePlayMode();
		dualMode = new DualPlayMode();
		waitingRoomC = new WaitingRoomCrown();
		waitingRoomN = new WaitingRoomNormal();
		
		mainPanel.add("MainView", mainView);
		mainPanel.add("Option", option);
		mainPanel.add("Help", help);
		mainPanel.add("Tutorial", tutorial);
		mainPanel.add("GameMode", gameMode);
		mainPanel.add("GameInfo", gameInfo);
		mainPanel.add("KeyControl", keyControl);
		mainPanel.add("NetworkMode", networkMode);
		mainPanel.add("NetworkPlayMode", networkPlayMode);
		mainPanel.add("SingleMode", singleMode);
		mainPanel.add("DualMode", dualMode);
		mainPanel.add("WatingRoomNormal", waitingRoomN);
		mainPanel.add("WatingRoomCrown", waitingRoomC);
		
		ChangePanelService changePanel = ChangePanelService.getInstance();
		changePanel.setLayout(layout);
		changePanel.setMainPanel(mainPanel);
		
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
