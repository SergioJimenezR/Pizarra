import java.awt.EventQueue;

import Presentaci�n.EditorGrafico;

public class Principal {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorGrafico.getInstancia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}