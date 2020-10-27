package view.etc;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * 조작키 이미지에 대한 정보를 저장하고 있는 클래스이며, 메소드를 이용하여 조작키 이미지 크기를 조정하여 사용할 수 있다.
 * @author 김용희
 *
 */
public class KeyImage {
	/**
	 * 1P 조작키이미지에 대한 정보를 저장하고 있다.
	 */
	private static ImageIcon[] one_image = new ImageIcon[5];
	/**
	 * 2P 조작키이미지에 대한 정보를 저장하고 있다.
	 */
	private static ImageIcon[] two_image = new ImageIcon[5];
	/**
	 * 1P 조작키가 눌렸을 때의 이미지 정보를 저장하고 있다.
	 */
	private static ImageIcon[] one_pressed_image = new ImageIcon[5];
	/**
	 * 2P 조작키가 눌렸을 때의 이미지 정보를 저장하고 있다.
	 */
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
	/**
	 * parameter player에 따라 해당하는 조작키 이미지를  xSize, ySize에 따라 이미지를 담은 JLabel배열을 리턴한다.
	 * @param player "1P" 혹은 "2P"의 값이 들어올 경우 그 조작키 이미지에 맞는 데이터를 리턴한다.
	 * @param xSize 리턴 할 JLabel의 가로 사이즈이다.
	 * @param ySize 리턴 할 JLabel의 가로 사이즈이다.
	 * @return player가 "1P"일 경우 Q, W, E, A, S "2P"일 경우 I, O, P, K ,L에 해당하는 조작키이미지를 xSize, ySize 값에 맞는 이미지를 JLabel타입에 담아 리턴한다.
	 */
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

	/**
	 * 원하는 크기로 ImageIcon을 변경해주는 메소드이다.
	 * @param icon 사이즈 변경할 이미지아이콘 변수이다.
	 * @param resizedWidth 변경할 너비 변수이다. 
	 * @param resizedHeight 변경할 높이 변수이다.
	 * @return 변경된 사이즈의 이미지를 리턴한다.
	 */
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}

}
