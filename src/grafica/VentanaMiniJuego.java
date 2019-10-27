package grafica;


import javax.swing.JFrame;

import logica.MiniJuegoAlaSuerte;


public class VentanaMiniJuego extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private PanelMiniJuegoAlaSuerte panelInicio;
	public VentanaMiniJuego(String titulo,int x , int y, MiniJuegoAlaSuerte mini) {
		setLocation(x, y);
		setSize(950,400);
		setTitle("Mini Juego: A la Suerte");
		this.panelInicio=new PanelMiniJuegoAlaSuerte(mini);
		getContentPane().add(panelInicio);
	}
	
	
	public PanelMiniJuegoAlaSuerte getPanel() {
		return this.panelInicio;
	}

}
