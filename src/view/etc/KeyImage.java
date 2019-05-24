package view.etc;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class KeyImage {
	private static ImageIcon[] one_image = new ImageIcon[5];
	private static ImageIcon[] two_image = new ImageIcon[5];
	private static ImageIcon[] one_pressed_image = new ImageIcon[5];
	private static ImageIcon[] two_pressed_image = new ImageIcon[5];

	static {
		one_image[0] = new ImageIcon("image/red(q)_1p.png");
		one_image[1] = new ImageIcon("image/yellow(w)_1p.png");
		one_image[2] = new ImageIcon("image/green(e)_1p.png");
		one_image[3] = new ImageIcon("image/blue(a)_1p.png");
		one_image[4] = new ImageIcon("image/black(s)_1p.png");
		two_image[0] = new ImageIcon("image/red(i)_2p.png");
		two_image[1] = new ImageIcon("image/yellow(o)_2p.png");
		two_image[2] = new ImageIcon("image/green(p)_2p.png");
		two_image[3] = new ImageIcon("image/blue(k)_2p.png");
		two_image[4] = new ImageIcon("image/black(l)_2p.png");

		one_pressed_image[0] = new ImageIcon("image/red(q)_1p_press.png");
		one_pressed_image[1] = new ImageIcon("image/yellow(w)_1p_press.png");
		one_pressed_image[2] = new ImageIcon("image/green(e)_1p_press.png");
		one_pressed_image[3] = new ImageIcon("image/blue(a)_1p_press.png");
		one_pressed_image[4] = new ImageIcon("image/black(s)_1p_press.png");
		two_pressed_image[0] = new ImageIcon("image/red(i)_2p_press.png");
		two_pressed_image[1] = new ImageIcon("image/yellow(o)_2p_press.png");
		two_pressed_image[2] = new ImageIcon("image/green(p)_2p_press.png");
		two_pressed_image[3] = new ImageIcon("image/blue(k)_2p_press.png");
		two_pressed_image[4] = new ImageIcon("image/black(l)_2p_press.png");
	}

	public static JLabel[] getKey(String player, int xSize, int ySize) {
		JLabel[] labelArr = new JLabel[10];

		if (player.equals("1P")) {
			for (int i = 0; i < 5; i++) {
				labelArr[i] = new JLabel((ImageIcon) resizeIcon(one_image[i], xSize, ySize));
				labelArr[i + 5] = new JLabel((ImageIcon) resizeIcon(one_pressed_image[i], xSize, ySize));
			}
		} else if (player.equals("2P")) {
			for (int i = 0; i < 5; i++) {
				labelArr[i] = new JLabel((ImageIcon) resizeIcon(two_image[i], xSize, ySize));
				labelArr[i + 5] = new JLabel((ImageIcon) resizeIcon(two_pressed_image[i], xSize, ySize));
			}
		} else
			return null;

		return labelArr;
	}

	/*------------ 이미지를 원하는 크기로 조절해주는 메소드 ---------------*/
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

}
