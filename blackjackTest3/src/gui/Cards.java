package gui;

import javax.swing.ImageIcon;

public class Cards {

	public ImageIcon front = new ImageIcon();
	public ImageIcon spade[] = new ImageIcon[14];
	public ImageIcon heart[] = new ImageIcon[14];
	public ImageIcon clover[] = new ImageIcon[14];
	public ImageIcon diamond[] = new ImageIcon[14];

	public Cards() {

		front = new ImageIcon(getClass().getResource("front.png"));
		
		spade[1] = new ImageIcon(getClass().getResource("1S.png"));
		spade[2] = new ImageIcon(getClass().getResource("2S.png"));
		spade[3] = new ImageIcon(getClass().getResource("3S.png"));
		spade[4] = new ImageIcon(getClass().getResource("4S.png"));
		spade[5] = new ImageIcon(getClass().getResource("5S.png"));
		spade[6] = new ImageIcon(getClass().getResource("6S.png"));
		spade[7] = new ImageIcon(getClass().getResource("7S.png"));	
		spade[8] = new ImageIcon(getClass().getResource("8S.png"));
		spade[9] = new ImageIcon(getClass().getResource("9S.png"));
		spade[10] = new ImageIcon(getClass().getResource("10S.png"));
		spade[11] = new ImageIcon(getClass().getResource("11S.png"));
		spade[12] = new ImageIcon(getClass().getResource("12S.png"));
		spade[13] = new ImageIcon(getClass().getResource("13S.png"));
		
		heart[1] = new ImageIcon(getClass().getResource("1H.png"));
		heart[2] = new ImageIcon(getClass().getResource("2H.png"));
		heart[3] = new ImageIcon(getClass().getResource("3H.png"));
		heart[4] = new ImageIcon(getClass().getResource("4H.png"));
		heart[5] = new ImageIcon(getClass().getResource("5H.png"));
		heart[6] = new ImageIcon(getClass().getResource("6H.png"));
		heart[7] = new ImageIcon(getClass().getResource("7H.png"));
		heart[8] = new ImageIcon(getClass().getResource("8H.png"));
		heart[9] = new ImageIcon(getClass().getResource("9H.png"));
		heart[10] = new ImageIcon(getClass().getResource("10H.png"));
		heart[11] = new ImageIcon(getClass().getResource("11H.png"));
		heart[12] = new ImageIcon(getClass().getResource("12H.png"));
		heart[13] = new ImageIcon(getClass().getResource("13H.png"));
		
		clover[1] = new ImageIcon(getClass().getResource("1C.png"));
		clover[2] = new ImageIcon(getClass().getResource("2C.png"));
		clover[3] = new ImageIcon(getClass().getResource("3C.png"));
		clover[4] = new ImageIcon(getClass().getResource("4C.png"));
		clover[5] = new ImageIcon(getClass().getResource("5C.png"));
		clover[6] = new ImageIcon(getClass().getResource("6C.png"));
		clover[7] = new ImageIcon(getClass().getResource("7C.png"));
		clover[8] = new ImageIcon(getClass().getResource("8C.png"));
		clover[9] = new ImageIcon(getClass().getResource("9C.png"));
		clover[10] = new ImageIcon(getClass().getResource("10C.png"));
		clover[11] = new ImageIcon(getClass().getResource("11C.png"));
		clover[12] = new ImageIcon(getClass().getResource("12C.png"));
		clover[13] = new ImageIcon(getClass().getResource("13C.png"));
		
		diamond[1] = new ImageIcon(getClass().getResource("1D.png"));
		diamond[2] = new ImageIcon(getClass().getResource("2D.png"));
		diamond[3] = new ImageIcon(getClass().getResource("3D.png"));
		diamond[4] = new ImageIcon(getClass().getResource("4D.png"));
		diamond[5] = new ImageIcon(getClass().getResource("5D.png"));
		diamond[6] = new ImageIcon(getClass().getResource("6D.png"));
		diamond[7] = new ImageIcon(getClass().getResource("7D.png"));
		diamond[8] = new ImageIcon(getClass().getResource("8D.png"));
		diamond[9] = new ImageIcon(getClass().getResource("9D.png"));
		diamond[10] = new ImageIcon(getClass().getResource("10D.png"));
		diamond[11] = new ImageIcon(getClass().getResource("11D.png"));
		diamond[12] = new ImageIcon(getClass().getResource("12D.png"));
		diamond[13] = new ImageIcon(getClass().getResource("13D.png"));
		
	}
}
