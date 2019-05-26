package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.etc.ChangePanelService;
import view.etc.Sound;

public class ColorGalliCups extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	                
	public ColorGalliCups() {
		super("ColorGalli Cups");
		
		JPanel mainPanel = new JPanel();
		CardLayout layout = new CardLayout();
		mainPanel.setLayout(layout);
		
		
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
		changePanel.addPanel("WaitingRoomCrown", new WaitingRoomCrown());
		
		add(mainPanel);
		layout.show(mainPanel, "MainView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1363, 714);
		setVisible(true);
		Sound.playBgm("audio/mainBGM.wav");
	}
	public static void main(String[] args) {
		new ColorGalliCups();
	}
}
