package Presentación;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JLabel;

import Dominio.*;

public class MiAreaDibujo extends JLabel {

	private static final long serialVersionUID = 1L;

	private ArrayList<ObjetoGrafico> objetosGraficos = new ArrayList<ObjetoGrafico>();

	public MiAreaDibujo() {
	}

	public void addObjetoGrafico(ObjetoGrafico objg) {
		objetosGraficos.add(objg);
		EditorGrafico.getInstancia().habilitarBtnDeshacer();
	}

	public ObjetoGrafico getUltimoObjetoGrafico() {
		return objetosGraficos.get(objetosGraficos.size() - 1);
	}

	public void deshacer() {

		objetosGraficos.remove(objetosGraficos.size() - 1);

		if (objetosGraficos.size() == 0) {
			EditorGrafico.getInstancia().deshabilitarBtnDeshacer();
		}

		paint(this.getGraphics());
	}

	public void limpiar(Graphics g) {
		objetosGraficos.clear();
		EditorGrafico.getInstancia().deshabilitarBtnDeshacer();
		paint(g);
	}

	public void paint(Graphics g) {

		super.paint(g);

		for (int i = 0; i < objetosGraficos.size(); i++) {
			ObjetoGrafico objg = objetosGraficos.get(i);
			if (objg instanceof ImagenGrafico) {
				g.drawImage(((ImagenGrafico) objg).getImagen(), objg.getX(), objg.getY(), null);
			} else if (objg instanceof RectanguloGrafico) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(((RectanguloGrafico) objg).getGrosor()));
				g2.setColor(((RectanguloGrafico) objg).getColor());
				int w = ((RectanguloGrafico) objg).getX1() - objg.getX();
				int h = ((RectanguloGrafico) objg).getY1() - objg.getY();
				g2.drawRect(objg.getX(), objg.getY(), w, h);
			} else if (objg instanceof TextoGrafico) {
				g.setFont(new Font(((TextoGrafico) objg).getFuente(), Font.PLAIN, ((TextoGrafico) objg).getTamano()));
				g.setColor(((TextoGrafico) objg).getColor());
				g.drawString(((TextoGrafico) objg).getTexto(), objg.getX(), objg.getY());
			} else if (objg instanceof RectaGrafico) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(((RectaGrafico) objg).getGrosor()));
				g2.setColor(((RectaGrafico) objg).getColor());
				int w = ((RectaGrafico) objg).getX1() - objg.getX();
				int h = ((RectaGrafico) objg).getY1() - objg.getY();
				g2.drawLine(objg.getX(), objg.getY(), objg.getX() + w, objg.getY() + h);
			} else if (objg instanceof ImagenMoldeableGrafico) {
				int w = ((ImagenMoldeableGrafico) objg).getX1() - objg.getX();
				int h = ((ImagenMoldeableGrafico) objg).getY1() - objg.getY();
				g.drawImage(((ImagenMoldeableGrafico) objg).getImage(), objg.getX(), objg.getY(), w, h, null);
			}

		}
	}
}