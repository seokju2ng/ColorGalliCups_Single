package view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.etc.ChangePanelService;
import view.etc.Sound;

/**
 * 게임 시작 시, 게임 화면(Panel)들을 붙인 패널들을 보여주는 JFrame이다.
 * 
 * @author 송준희
 */
public class ColorGalliCups extends JFrame {
	/**
	 * 객체 직렬화를 위한 serialVersion의 ID이다.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * null parameter constructor로 ColorGalliCups 패널을 만들어 사용자에게 보여준다.
	 */
	public ColorGalliCups() {
		super("ColorGalli Cups");

		JPanel mainPanel = new JPanel();
		CardLayout layout = new CardLayout();
		mainPanel.setLayout(layout);

		ChangePanelService changePanel = ChangePanelService.getInstance();
		changePanel.setLayout(layout);
		changePanel.setMainPanel(mainPanel);
		changePanel.addPanel("MainView", new MainView());
		changePanel.addPanel("GameMode", new GameMode());
		changePanel.addPanel("Option", new OptionView());
		changePanel.addPanel("Help", new Help());
		changePanel.addPanel("Tutorial", new Tutorial());
		changePanel.addPanel("GameInfo", new GameInfo());
		changePanel.addPanel("KeyControl", new KeyControl());

		add(mainPanel);
		layout.show(mainPanel, "MainView");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1363, 714);
		setVisible(true);
		setResizable(false);
		Sound.playBgm("audio/mainBGM.wav");
	}
	
	public static void main(String[] args) {
		new ColorGalliCups();
	}
}
