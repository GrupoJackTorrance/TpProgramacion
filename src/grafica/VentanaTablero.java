package grafica;

import javax.swing.JFrame;

import logica.Tablero;

public class VentanaTablero extends JFrame {
	
	private PanelVentanaTablero panelTablero;
	
	public VentanaTablero(String titulo, int x, int y, Tablero tablero) {
		setVisible(true);
		setLocation(x,y);
		setSize(850, 700);
		setTitle(titulo);
		panelTablero=new PanelVentanaTablero(tablero);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(panelTablero);
	}
	public PanelVentanaTablero getPanelTablero() {
		return this.panelTablero;
	}
}
