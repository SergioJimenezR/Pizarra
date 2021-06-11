package Presentación;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;

import Dominio.*;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class EditorGrafico {

	private static EditorGrafico mInstanciaSingleton = null;

	private JFrame frmEditorGrafico;

	private JToolBar tbBarraDibujo;

	private JButton btnCargarMapa;

	private JButton btnWarning;
	private JButton btnCircles;
	private JButton btnBed;
	private JButton btnBus;
	private JButton btnLocation;

	private JButton btnUp;
	private JButton btnRight;
	private JButton btnDown;
	private JButton btnLeft;

	private JButton btnTrazado;
	private JButton btnRectangulo;
	private JButton btnRecta;
	private JButton btnAnotacion;

	private JButton btnDeshacer;
	private JButton btnLimpiar;

	private JScrollPane scrollPane;

	int modo = -1;

	private final int WARNING = 1;
	private final int CIRCLES = 2;
	private final int BED = 3;
	private final int BUS = 4;
	private final int LOCATION = 5;
	private final int UP = 6;
	private final int RIGHT = 7;
	private final int DOWN = 8;
	private final int LEFT = 9;
	private final int TRAZADO = 10;
	private final int RECTANGULO = 11;
	private final int RECTA = 12;
	private final int TEXTO = 13;

	private Toolkit toolkit;

	private Image imagWarning;
	private Image imagCircles;
	private Image imagBed;
	private Image imagBus;
	private Image imagLocation;

	private Image imagUp;
	private Image imagRight;
	private Image imagDown;
	private Image imagLeft;

	private Image imagPixel;
	private Image imagPencil;
	private Image imagRectangulo;
	private Image imagRecta;
	private Image imagTexto;

	private Cursor cursorWarning;
	private Cursor cursorCircles;
	private Cursor cursorBed;
	private Cursor cursorBus;
	private Cursor cursorLocation;

	private Cursor cursorUp;
	private Cursor cursorRight;
	private Cursor cursorDown;
	private Cursor cursorLeft;

	private Cursor cursorPencil;
	private Cursor cursorRectangulo;
	private Cursor cursorRecta;
	private Cursor cursorTexto;

	private int x, y;

	private JTextField txtTexto = new JTextField();

	private MiAreaDibujo miAreaDibujo;

	private ImageIcon imagenFondo;
	private JSlider slider;
	private JLabel lblSlider;

	public static EditorGrafico getInstancia() {
		if (mInstanciaSingleton == null) {
			mInstanciaSingleton = new EditorGrafico();
			mInstanciaSingleton.frmEditorGrafico.setVisible(true);
		}
		return mInstanciaSingleton;
	}

	private EditorGrafico() {
		initialize();
	}

	private void initialize() {
		frmEditorGrafico = new JFrame();
		frmEditorGrafico.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmEditorGrafico.setTitle("Editor gráfico - Sergio Jiménez Roncero");
		frmEditorGrafico.setBounds(15, 15, 1200, 800);
		{
			tbBarraDibujo = new JToolBar();
			frmEditorGrafico.getContentPane().add(tbBarraDibujo, BorderLayout.NORTH);
			{
				btnCargarMapa = new JButton("");
				btnCargarMapa.addActionListener(new BtnCargarMapaActionListener());
				btnCargarMapa.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/cargarMapa.png")));
				tbBarraDibujo.add(btnCargarMapa);
			}
			{
				btnWarning = new JButton("");
				btnWarning.setEnabled(false);
				btnWarning.addActionListener(new BtnWarningActionListener());
				btnWarning.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/warning.png")));
				tbBarraDibujo.add(btnWarning);
			}
			{
				btnCircles = new JButton("");
				btnCircles.setEnabled(false);
				btnCircles.addActionListener(new BtnCirclesActionListener());
				btnCircles.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/circles.png")));
				tbBarraDibujo.add(btnCircles);
			}
			{
				{
					btnBed = new JButton("");
					btnBed.setEnabled(false);
					btnBed.addActionListener(new BtnBedActionListener());
					btnBed.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/bed.png")));
					tbBarraDibujo.add(btnBed);
				}
				{
					btnBus = new JButton("");
					btnBus.setEnabled(false);
					btnBus.addActionListener(new BtnBusActionListener());
					btnBus.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/bus.png")));
					tbBarraDibujo.add(btnBus);
				}
				{
					btnLocation = new JButton("");
					btnLocation.setEnabled(false);
					btnLocation.addActionListener(new BtnLocationActionListener());
					btnLocation.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/location.png")));
					tbBarraDibujo.add(btnLocation);
				}
			}
			{
			}
			{
				btnUp = new JButton("");
				btnUp.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/up.png")));
				btnUp.setEnabled(false);
				btnUp.addActionListener(new BtnUpActionListener());
				tbBarraDibujo.add(btnUp);
			}
			{
				{
					btnRight = new JButton("");
					btnRight.addActionListener(new BtnRightActionListener());
					btnRight.setEnabled(false);
					btnRight.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/right.png")));
					tbBarraDibujo.add(btnRight);
				}
				{
					btnDown = new JButton("");
					btnDown.addActionListener(new BtnDownActionListener());
					btnDown.setEnabled(false);
					btnDown.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/down.png")));
					tbBarraDibujo.add(btnDown);
				}
				{
					btnLeft = new JButton("");
					btnLeft.addActionListener(new BtnLeftActionListener());
					btnLeft.setEnabled(false);
					btnLeft.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/left.png")));
					tbBarraDibujo.add(btnLeft);
				}
			}
			{
				slider = new JSlider();
				slider.setEnabled(false);
				slider.setValue(10);
				slider.setMaximum(20);
				slider.setMinimum(1);
				slider.addChangeListener(new SliderChangeListener());
				btnRectangulo = new JButton("");
				btnRectangulo.setEnabled(false);
				btnRectangulo.addActionListener(new BtnRectanguloActionListener());
				{
					btnTrazado = new JButton("");
					btnTrazado.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/dibujar.png")));
					btnTrazado.setEnabled(false);
					btnTrazado.addActionListener(new BtnTrazadoActionListener());
					tbBarraDibujo.add(btnTrazado);
				}
				btnRectangulo.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/rectangulo.png")));
				tbBarraDibujo.add(btnRectangulo);
				{
					btnRecta = new JButton("");
					btnRecta.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/recta.png")));
					btnRecta.setEnabled(false);
					btnRecta.addActionListener(new BtnRectaActionListener());
					tbBarraDibujo.add(btnRecta);
				}
				{
					btnAnotacion = new JButton("");
					btnAnotacion.setEnabled(false);
					btnAnotacion.addActionListener(new BtnAnotacionActionListener());
					btnAnotacion
							.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/aniadirAnotacion.png")));
					tbBarraDibujo.add(btnAnotacion);
				}
				btnLimpiar = new JButton("");
				btnLimpiar.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/eraser.png")));
				btnLimpiar.addActionListener(new BtnLimpiarActionListener());
				btnDeshacer = new JButton("");
				btnDeshacer.setIcon(new ImageIcon(EditorGrafico.class.getResource("/Recursos/undo.png")));
				btnDeshacer.setEnabled(false);
				btnDeshacer.addActionListener(new BtnDeshacerActionListener());
				tbBarraDibujo.add(btnDeshacer);
				btnLimpiar.setEnabled(false);
				tbBarraDibujo.add(btnLimpiar);
				tbBarraDibujo.add(slider);
			}
			{
				lblSlider = new JLabel("10");
				tbBarraDibujo.add(lblSlider);
			}
		}
		{
			scrollPane = new JScrollPane();
			frmEditorGrafico.getContentPane().add(scrollPane, BorderLayout.CENTER);
		}

		miAreaDibujo = new MiAreaDibujo();
		miAreaDibujo.setHorizontalAlignment(SwingConstants.CENTER);
		miAreaDibujo.addMouseMotionListener(new MiAreaDibujoMouseMotionListener());
		miAreaDibujo.addMouseListener(new MiAreaDibujoMouseListener());
		miAreaDibujo.setIcon(null);
		scrollPane.setViewportView(miAreaDibujo);

		toolkit = Toolkit.getDefaultToolkit();

		imagWarning = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/warning.png"));
		imagCircles = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/circles.png"));
		imagBed = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/bed.png"));
		imagBus = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/bus.png"));
		imagLocation = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/location.png"));

		imagUp = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/up.png"));
		imagRight = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/right.png"));
		imagDown = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/down.png"));
		imagLeft = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/left.png"));

		imagPixel = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/pixel.png"));
		imagPencil = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/dibujar.png"));
		imagRectangulo = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/rectangulo.png"));
		imagRecta = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/recta.png"));
		imagTexto = toolkit.getImage(getClass().getClassLoader().getResource("Recursos/aniadirAnotacion.png"));

		cursorWarning = toolkit.createCustomCursor(imagWarning, new Point(0, 0), "CURSOR_WARNING");
		cursorCircles = toolkit.createCustomCursor(imagCircles, new Point(0, 0), "CURSOR_CIRCLES");
		cursorBed = toolkit.createCustomCursor(imagBed, new Point(0, 0), "CURSOR_BED");
		cursorBus = toolkit.createCustomCursor(imagBus, new Point(0, 0), "CURSOR_BUS");
		cursorLocation = toolkit.createCustomCursor(imagLocation, new Point(0, 0), "CURSOR_LOCATION");

		cursorUp = toolkit.createCustomCursor(imagUp, new Point(0, 0), "CURSOR_UP");
		cursorRight = toolkit.createCustomCursor(imagRight, new Point(0, 0), "CURSOR_RIGHT");
		cursorDown = toolkit.createCustomCursor(imagDown, new Point(0, 0), "CURSOR_DOWN");
		cursorLeft = toolkit.createCustomCursor(imagLeft, new Point(0, 0), "CURSOR_LEFT");

		cursorPencil = toolkit.createCustomCursor(imagPencil, new Point(0, 0), "CURSOR_PIXEL");
		cursorRectangulo = toolkit.createCustomCursor(imagRectangulo, new Point(0, 0), "CURSOR_RECTANGULO");
		cursorRecta = toolkit.createCustomCursor(imagRecta, new Point(0, 0), "CURSOR_RECTA");
		cursorTexto = toolkit.createCustomCursor(imagTexto, new Point(0, 0), "CURSOR_TEXTO");
	}

	private class BtnCargarMapaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser fcAbrir = new JFileChooser();
			int valorDevuelto = fcAbrir.showOpenDialog(frmEditorGrafico);
			if (valorDevuelto == JFileChooser.APPROVE_OPTION) {
				File file = fcAbrir.getSelectedFile();
				imagenFondo = new ImageIcon(file.getAbsolutePath());
				miAreaDibujo.setIcon(imagenFondo);

				btnWarning.setEnabled(true);
				btnCircles.setEnabled(true);
				btnBed.setEnabled(true);
				btnBus.setEnabled(true);
				btnLocation.setEnabled(true);

				btnUp.setEnabled(true);
				btnRight.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);

				btnTrazado.setEnabled(true);
				btnRectangulo.setEnabled(true);
				btnRecta.setEnabled(true);
				btnAnotacion.setEnabled(true);

				slider.setEnabled(true);
			}
		}
	}

	private class BtnWarningActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = WARNING;
			frmEditorGrafico.setCursor(cursorWarning);
		}
	}

	private class BtnCirclesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = CIRCLES;
			frmEditorGrafico.setCursor(cursorCircles);
		}
	}

	private class BtnBedActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = BED;
			frmEditorGrafico.setCursor(cursorBed);
		}
	}

	private class BtnBusActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = BUS;
			frmEditorGrafico.setCursor(cursorBus);
		}
	}

	private class BtnLocationActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = LOCATION;
			frmEditorGrafico.setCursor(cursorLocation);
		}
	}

	private class BtnTrazadoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = TRAZADO;
			frmEditorGrafico.setCursor(cursorPencil);
		}
	}

	private class BtnRectanguloActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = RECTANGULO;
			frmEditorGrafico.setCursor(cursorRectangulo);
		}
	}

	private class BtnRectaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = RECTA;
			frmEditorGrafico.setCursor(cursorRecta);
		}
	}

	private class BtnAnotacionActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = TEXTO;
			frmEditorGrafico.setCursor(cursorTexto);
		}
	}

	private class BtnUpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = UP;
			frmEditorGrafico.setCursor(cursorUp);
		}
	}

	private class BtnRightActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = RIGHT;
			frmEditorGrafico.setCursor(cursorRight);
		}
	}

	private class BtnDownActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = DOWN;
			frmEditorGrafico.setCursor(cursorDown);
		}
	}

	private class BtnLeftActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			modo = LEFT;
			frmEditorGrafico.setCursor(cursorLeft);
		}
	}

	private class MiAreaDibujoMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			if (imagenFondo != null) {
				switch (modo) {
				case WARNING:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagWarning));
					miAreaDibujo.paint(miAreaDibujo.getGraphics());
					break;
				case CIRCLES:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagCircles));
					miAreaDibujo.repaint();
					break;
				case BED:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagBed));
					miAreaDibujo.repaint();
					break;
				case BUS:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagBus));
					miAreaDibujo.repaint();
					break;
				case LOCATION:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagLocation));
					miAreaDibujo.repaint();
					break;
				case TRAZADO:
					miAreaDibujo.addObjetoGrafico(new ImagenGrafico(x, y, imagPixel));
					miAreaDibujo.repaint();
					break;
				case RECTANGULO:
					miAreaDibujo.addObjetoGrafico(new RectanguloGrafico(x, y, x, y, Color.RED, slider.getValue()));
					break;
				case RECTA:
					miAreaDibujo.addObjetoGrafico(new RectaGrafico(x, y, x, y, Color.RED, slider.getValue()));
					break;
				case TEXTO:
					txtTexto.setBounds(x, y, 200, 30);
					txtTexto.setVisible(true);
					txtTexto.requestFocus();
					txtTexto.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							if (!txtTexto.getText().equals(""))
								miAreaDibujo.addObjetoGrafico(new TextoGrafico(x, y + slider.getValue() + 5,
										txtTexto.getText(), Color.BLUE, slider.getValue()));
							txtTexto.setText("");
							txtTexto.setVisible(false);
							miAreaDibujo.repaint();
						}
					});
					miAreaDibujo.add(txtTexto);
					break;
				case UP:
					miAreaDibujo.addObjetoGrafico(new ImagenMoldeableGrafico(x, y, x, y, imagUp));
					break;
				case RIGHT:
					miAreaDibujo.addObjetoGrafico(new ImagenMoldeableGrafico(x, y, x, y, imagRight));
					break;
				case DOWN:
					miAreaDibujo.addObjetoGrafico(new ImagenMoldeableGrafico(x, y, x, y, imagDown));
					break;
				case LEFT:
					miAreaDibujo.addObjetoGrafico(new ImagenMoldeableGrafico(x, y, x, y, imagLeft));
					break;
				}
			}

		}
	}

	private class MiAreaDibujoMouseMotionListener extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			if ((modo == UP || modo == RIGHT || modo == DOWN || modo == LEFT) && imagenFondo != null) {
				((ImagenMoldeableGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setX1(e.getX());
				((ImagenMoldeableGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setY1(e.getY());
				miAreaDibujo.repaint();
			} else if (modo == TRAZADO && imagenFondo != null) {
				miAreaDibujo.addObjetoGrafico(new ImagenGrafico(e.getX(), e.getY(), imagPixel));
				miAreaDibujo.repaint();
			} else if (modo == RECTANGULO && imagenFondo != null) {
				((RectanguloGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setX1(e.getX());
				((RectanguloGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setY1(e.getY());
				miAreaDibujo.repaint();
			} else if (modo == RECTA && imagenFondo != null) {
				((RectaGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setX1(e.getX());
				((RectaGrafico) miAreaDibujo.getUltimoObjetoGrafico()).setY1(e.getY());
				miAreaDibujo.repaint();
			}
		}
	}

	private class BtnDeshacerActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			miAreaDibujo.deshacer();
		}
	}

	public void habilitarBtnDeshacer() {
		btnDeshacer.setEnabled(true);
		btnLimpiar.setEnabled(true);
	}

	public void deshabilitarBtnDeshacer() {
		btnDeshacer.setEnabled(false);
		btnLimpiar.setEnabled(false);
	}

	private class BtnLimpiarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			miAreaDibujo.limpiar(miAreaDibujo.getGraphics());
		}
	}

	private class SliderChangeListener implements ChangeListener {
		public void stateChanged(ChangeEvent arg0) {
			lblSlider.setText(slider.getValue() + "");
		}
	}

}
