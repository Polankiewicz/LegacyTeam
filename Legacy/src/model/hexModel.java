package model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class hexModel {
	int x;
	int y;
	int index;
	ImageView image;
	ImageView hover;
	Rectangle hex;
	Label hexLabel;
	//na sprawdzenie czy to przeciwnik
	boolean isEnemy;
	//na sprawdzenie czy dane pole jest zamkiem czy regularnym wojem
	boolean isCastle;
	//iloœæ wojska
	int ilosc;
	
	public hexModel(int x, int y, int index, boolean isCastle, Rectangle hex, Label hexLabel){
		this.x = x;
		this.y = y;
		this.index = index;
		this.isCastle = isCastle;
		this.hex = hex;
		this.hexLabel = hexLabel;
	}

	public Rectangle getHex() {
		return hex;
	}

	public void setHex(Rectangle hex) {
		this.hex = hex;
	}

	public Label getHexLabel() {
		return hexLabel;
	}

	public void setHexLabel(Label hexLabel) {
		this.hexLabel = hexLabel;
	}

	public boolean isEnemy() {
		return isEnemy;
	}

	public void setEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}

	public boolean isCastle() {
		return isCastle;
	}

	public void setCastle(boolean isCastle) {
		this.isCastle = isCastle;
	}
	
	
}
