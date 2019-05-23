package view.etc;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageCut {
	public static ImageIcon[] one_image = new ImageIcon[5];
	public static ImageIcon[] two_image = new ImageIcon[5];
	public static ImageIcon[] one_pressed_image = new ImageIcon[5];
	public static ImageIcon[] two_pressed_image = new ImageIcon[5];

	//////////////////////////
	static {
		one_image[0] = new ImageIcon("image/red(q).png");
		one_image[1] = new ImageIcon("image/yellow(w).png");
		one_image[2] = new ImageIcon("image/green(e).png");
		one_image[3] = new ImageIcon("image/blue(a).png");
		one_image[4] = new ImageIcon("image/black(s).png");
		two_image[0] = new ImageIcon("image/red(i)_2p.png");
		two_image[1] = new ImageIcon("image/yellow(o)_2p.png");
		two_image[2] = new ImageIcon("image/green(p)_2p.png");
		two_image[3] = new ImageIcon("image/blue(k)_2p.png");
		two_image[4] = new ImageIcon("image/black(l)_2p.png");
		
		one_pressed_image[0] = new ImageIcon("image/red(q)_press.png");
		one_pressed_image[1] = new ImageIcon("image/yellow(w)_press.png");
		one_pressed_image[2] = new ImageIcon("image/green(e)_press.png");
		one_pressed_image[3] = new ImageIcon("image/blue(a)_press.png");
		one_pressed_image[4] = new ImageIcon("image/black(s)_press.png");
		two_pressed_image[0] = new ImageIcon("image/red(i)_2p_press.png");
		two_pressed_image[1] = new ImageIcon("image/yellow(o)_2p_press.png");
		two_pressed_image[2] = new ImageIcon("image/green(p)_2p_press.png");
		two_pressed_image[3] = new ImageIcon("image/blue(k)_2p_press.png");
		two_pressed_image[4] = new ImageIcon("image/black(l)_2p_press.png");

		for (int i = 0; i < 5; i++) {
			one_image[i] = (ImageIcon) resizeIcon(one_image[i], 40, 40);
			two_image[i] = (ImageIcon) resizeIcon(two_image[i], 40, 40);
			one_pressed_image[i] = (ImageIcon) resizeIcon(one_pressed_image[i], 40, 40);
			two_pressed_image[i] = (ImageIcon) resizeIcon(two_pressed_image[i], 40, 40);

		}
	}
	
	/*------------ 이미지를 원하는 크기로 조절해주는 메소드 ---------------*/
	public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}


}
