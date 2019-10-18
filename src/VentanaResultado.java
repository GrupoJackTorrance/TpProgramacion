import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class VentanaResultado extends JFrame {
	PanelResultado panelResultado;
	
	public VentanaResultado() {
		this.setBounds(200, 200, 400, 300);
		setSize(400,330);
		setTitle("Resultado Finales");
		this.panelResultado=new PanelResultado();
		getContentPane().add(panelResultado);
		add(panelResultado);
		setResizable(false);
	}
	
	class PanelResultado extends JPanel {

		private static final long serialVersionUID = 1L;
		JButton fin = new JButton("Fin del Juego");
		JLabel resultado= new JLabel("Resultados finales: ");
		JLabel jugador1= new JLabel("");
		JLabel jugador2= new JLabel("");
		JLabel jugador3= new JLabel("");
		JLabel jugador4= new JLabel("");
		
		public PanelResultado() {
			this.add(resultado);
			this.add(fin);
			this.add(jugador1);
			this.add(jugador2);
			this.add(jugador3);
			this.add(jugador4);
			this.setVisible(true);
		}
		
		public void paintComponent(Graphics grafico) {
			super.paintComponent(grafico);
			this.setBounds(0, 0, 500, 500);
			
			fin.setBounds(200, 220, 150, 30);
			fin.setLocation(150,250);
			fin.setBackground(SystemColor.window);
			fin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

			jugador1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			jugador1.setLocation(10,40);
			jugador2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20 ));
			jugador2.setLocation(10,80);
			jugador3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20 ));
			jugador3.setLocation(10,120);
			jugador4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20 ));
			jugador4.setLocation(10,160);
			resultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			resultado.setForeground(new Color(0, 0, 0));
			
			resultado.setLocation(0,10);
			resultado.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			resultado.setForeground(new Color(0, 0, 0));

			//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
			ImageIcon Img = new ImageIcon(getClass().getResource("fondoJuego.jpg")); 
			
			//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
			grafico.drawImage(Img.getImage(), 0, 0, 400, 300, null);
			setOpaque(false);
		}
		
	}
	
	public void resultadosVentana(List<Jugador> jugadores) {
		panelResultado.fin.setVisible(true);
		panelResultado.resultado.setVisible(true);
		panelResultado.jugador1.setText("Puesto 1 "+"  "+jugadores.get(0).getNombre() +"  "+jugadores.get(0).getPuntos()+" ");
		panelResultado.jugador2.setText("Puesto 2 "+"  "+jugadores.get(1).getNombre()+"  "+jugadores.get(1).getPuntos()+" ");
		panelResultado.jugador1.setVisible(true);
		panelResultado.jugador2.setVisible(true);
		if(jugadores.size()>2) {
			panelResultado.jugador3.setText("Puesto 3 "+"  "+jugadores.get(2).getNombre()+"  "+jugadores.get(2).getPuntos()+" ");
			panelResultado.jugador3.setVisible(true);
			if(jugadores.size()>3) {
				panelResultado.jugador4.setText("Puesto 4 "+"  "+jugadores.get(3).getNombre()+"  "+jugadores.get(3).getPuntos()+" ");
				panelResultado.jugador4.setVisible(true);
			}
		}
		
	}
}

