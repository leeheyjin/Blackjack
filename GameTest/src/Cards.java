
import javax.swing.ImageIcon;

public class Cards {

	ImageIcon front = new ImageIcon();
	ImageIcon spade[] = new ImageIcon[14];
	ImageIcon heart[] = new ImageIcon[14];
	ImageIcon clover[] = new ImageIcon[14];
	ImageIcon diamond[] = new ImageIcon[14];

	public Cards() {

		front = new ImageIcon(getClass().getClassLoader().getResource("front.png"));
		
		spade[1] = new ImageIcon(getClass().getClassLoader().getResource("1S.png"));
		spade[2] = new ImageIcon(getClass().getClassLoader().getResource("2S.png"));
		spade[3] = new ImageIcon(getClass().getClassLoader().getResource("3S.png"));
		spade[4] = new ImageIcon(getClass().getClassLoader().getResource("4S.png"));
		spade[5] = new ImageIcon(getClass().getClassLoader().getResource("5S.png"));
		spade[6] = new ImageIcon(getClass().getClassLoader().getResource("6S.png"));
		spade[7] = new ImageIcon(getClass().getClassLoader().getResource("7S.png"));
		spade[8] = new ImageIcon(getClass().getClassLoader().getResource("8S.png"));
		spade[9] = new ImageIcon(getClass().getClassLoader().getResource("9S.png"));
		spade[10] = new ImageIcon(getClass().getClassLoader().getResource("10S.png"));
		spade[11] = new ImageIcon(getClass().getClassLoader().getResource("11S.png"));
		spade[12] = new ImageIcon(getClass().getClassLoader().getResource("12S.png"));
		spade[13] = new ImageIcon(getClass().getClassLoader().getResource("13S.png"));
		
		heart[1] = new ImageIcon(getClass().getClassLoader().getResource("1H.png"));
		heart[2] = new ImageIcon(getClass().getClassLoader().getResource("2H.png"));
		heart[3] = new ImageIcon(getClass().getClassLoader().getResource("3H.png"));
		heart[4] = new ImageIcon(getClass().getClassLoader().getResource("4H.png"));
		heart[5] = new ImageIcon(getClass().getClassLoader().getResource("5H.png"));
		heart[6] = new ImageIcon(getClass().getClassLoader().getResource("6H.png"));
		heart[7] = new ImageIcon(getClass().getClassLoader().getResource("7H.png"));
		heart[8] = new ImageIcon(getClass().getClassLoader().getResource("8H.png"));
		heart[9] = new ImageIcon(getClass().getClassLoader().getResource("9H.png"));
		heart[10] = new ImageIcon(getClass().getClassLoader().getResource("10H.png"));
		heart[11] = new ImageIcon(getClass().getClassLoader().getResource("11H.png"));
		heart[12] = new ImageIcon(getClass().getClassLoader().getResource("12H.png"));
		heart[13] = new ImageIcon(getClass().getClassLoader().getResource("13H.png"));
		
		clover[1] = new ImageIcon(getClass().getClassLoader().getResource("1C.png"));
		clover[2] = new ImageIcon(getClass().getClassLoader().getResource("2C.png"));
		clover[3] = new ImageIcon(getClass().getClassLoader().getResource("3C.png"));
		clover[4] = new ImageIcon(getClass().getClassLoader().getResource("4C.png"));
		clover[5] = new ImageIcon(getClass().getClassLoader().getResource("5C.png"));
		clover[6] = new ImageIcon(getClass().getClassLoader().getResource("6C.png"));
		clover[7] = new ImageIcon(getClass().getClassLoader().getResource("7C.png"));
		clover[8] = new ImageIcon(getClass().getClassLoader().getResource("8C.png"));
		clover[9] = new ImageIcon(getClass().getClassLoader().getResource("9C.png"));
		clover[10] = new ImageIcon(getClass().getClassLoader().getResource("10C.png"));
		clover[11] = new ImageIcon(getClass().getClassLoader().getResource("11C.png"));
		clover[12] = new ImageIcon(getClass().getClassLoader().getResource("12C.png"));
		clover[13] = new ImageIcon(getClass().getClassLoader().getResource("13C.png"));
		
		diamond[1] = new ImageIcon(getClass().getClassLoader().getResource("1D.png"));
		diamond[2] = new ImageIcon(getClass().getClassLoader().getResource("2D.png"));
		diamond[3] = new ImageIcon(getClass().getClassLoader().getResource("3D.png"));
		diamond[4] = new ImageIcon(getClass().getClassLoader().getResource("4D.png"));
		diamond[5] = new ImageIcon(getClass().getClassLoader().getResource("5D.png"));
		diamond[6] = new ImageIcon(getClass().getClassLoader().getResource("6D.png"));
		diamond[7] = new ImageIcon(getClass().getClassLoader().getResource("7D.png"));
		diamond[8] = new ImageIcon(getClass().getClassLoader().getResource("8D.png"));
		diamond[9] = new ImageIcon(getClass().getClassLoader().getResource("9D.png"));
		diamond[10] = new ImageIcon(getClass().getClassLoader().getResource("10D.png"));
		diamond[11] = new ImageIcon(getClass().getClassLoader().getResource("11D.png"));
		diamond[12] = new ImageIcon(getClass().getClassLoader().getResource("12D.png"));
		diamond[13] = new ImageIcon(getClass().getClassLoader().getResource("13D.png"));
		
	}
}
