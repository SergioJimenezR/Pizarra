package Dominio;

import java.awt.Image;
import java.io.Serializable;

public class ImagenMoldeableGrafico extends ObjetoGrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x1, y1;
	private Image imagen;

	public ImagenMoldeableGrafico(int x, int y, int x1, int y1, Image imagen) {
		super(x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.imagen = imagen;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public void setImage(Image imagen) {
		this.imagen = imagen;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public Image getImage() {
		return imagen;
	}
}
