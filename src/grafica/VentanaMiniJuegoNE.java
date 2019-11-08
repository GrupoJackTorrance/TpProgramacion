package grafica;

import javax.swing.JFrame;

import logica.MiniJuegoNoExplotes;

public class VentanaMiniJuegoNE extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private PanelMiniJuegoNE panelInicio;
	public VentanaMiniJuegoNE(String titulo,int x , int y, MiniJuegoNoExplotes mini) {
		setLocation(x, y);
		setSize(950,600);
		setTitle("Mini Juego: No Explotes!");
		this.panelInicio=new PanelMiniJuegoNE(mini);
		getContentPane().add(panelInicio);
		
	}
	
	
	public PanelMiniJuegoNE getPanel() {
		return this.panelInicio;
	}

}