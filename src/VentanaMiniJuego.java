import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class VentanaMiniJuego extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PanelMiniJuegoAlaSuerte panelInicio;
	public VentanaMiniJuego(String titulo,int x , int y,MiniJuegoAlaSuerte mini) {
		setLocation(x, y);
		setSize(950,400);
		setTitle(titulo);
		this.panelInicio=new PanelMiniJuegoAlaSuerte(mini);
		add(panelInicio);
				
	
	}
	public PanelMiniJuegoAlaSuerte getPanel() {
		return this.panelInicio;
	}

	
}
