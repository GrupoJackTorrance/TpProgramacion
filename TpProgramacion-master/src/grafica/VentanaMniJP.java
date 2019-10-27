package grafica;

import javax.swing.JFrame;

import logica.MiniJuegoPalabras;

public class VentanaMniJP extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private PanelMniJP panelInicio;
	public VentanaMniJP(String titulo,int x , int y, MiniJuegoPalabras mini) {
		setLocation(x, y);
		setSize(1500,400);
		setTitle("Mini Juego:  Ingresando palabras");
		this.panelInicio=new PanelMniJP(mini);
		getContentPane().add(panelInicio);
		
	}
	
	
	public PanelMniJP getPanel() {
		return this.panelInicio;
	}

}