package Dominio;

import java.awt.Color;
import java.io.Serializable;

public class RectanguloGrafico extends ObjetoGrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	private int x1, y1;
	private Color color;
	private int grosor;

	public RectanguloGrafico(int x, int y, int x1, int y1, Color color, int grosor) {
		super(x, y);
		this.x1 = x1;
		this.y1 = y1;
		this.color = color;
		this.grosor = grosor;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public void setGrosor(int grosor) {
		this.grosor = grosor;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX1() {
		return x1;
	}

	public int getY1() {
		return y1;
	}

	public Color getColor() {
		return color;
	}

	public int getGrosor() {
		return grosor;
	}
}
