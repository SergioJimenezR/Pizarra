package Dominio;

import java.awt.Color;
import java.io.Serializable;

public class TextoGrafico extends ObjetoGrafico implements Serializable {

	private static final long serialVersionUID = 1L;

	private String texto;
	private Color color;
	private int tamano;
	private String fuente;

	public TextoGrafico(int x, int y, String text, Color color, int tamano) {
		super(x, y);
		this.texto = text;
		this.color = color;
		this.tamano = tamano;
		this.fuente = "Verdana";
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public String getTexto() {
		return texto;
	}

	public Color getColor() {
		return color;
	}

	public int getTamano() {
		return tamano;
	}

	public String getFuente() {
		return fuente;
	}
}