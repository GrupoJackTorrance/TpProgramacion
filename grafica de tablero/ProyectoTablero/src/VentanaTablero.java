import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaTablero extends JFrame {
	public PanelVentanaTablero miLamina;
	public VentanaTablero(String titulo,int x, int y,Tablero tablero) {
		setVisible(true);
		setLocation(x,y);
		setSize(700, 700);
		setTitle(titulo);
		miLamina=new PanelVentanaTablero(tablero);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(miLamina);

	}

	
}