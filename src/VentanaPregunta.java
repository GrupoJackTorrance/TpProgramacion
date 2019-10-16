import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaPregunta extends JFrame{

	private static final long serialVersionUID = 1L;
	PanelPregunta panelPregunta;
	
	public VentanaPregunta() {
		this.setBounds(200, 200, 400, 300);
		setTitle("Ataque");
		this.panelPregunta=new PanelPregunta();
		getContentPane().add(panelPregunta);
		add(panelPregunta);
		setResizable(false);
	}
	
	public int ataque (Jugador jugador) throws InterruptedException {
		int resp = panelPregunta.deseaAtacarVentana(jugador);
		dispose();
		return resp;
	}
}

	class PanelPregunta extends JPanel {

		private static final long serialVersionUID = 1L;
		private int resp = 2;
		JButton ataca = new JButton("Atacar");
		JButton noAtaca = new JButton("No hacer nada");
		JLabel cronometro= new JLabel();
		JLabel pregunta= new JLabel();
		JLabel opcion= new JLabel();
		String mensaje;
		
		public PanelPregunta() {
			this.resp = 2;
			this.setLayout(null);
			this.add(pregunta);
			this.add(ataca);
			this.add(noAtaca);
			this.add(cronometro);
			this.add(opcion);
			Botones escuchador = new Botones();
			ataca.addActionListener(escuchador);
			noAtaca.addActionListener(escuchador);
			this.setVisible(true);
		}
		
		public void paintComponent(Graphics grafico) {
			super.paintComponent(grafico);
			this.setBounds(0, 0, 400, 400);
			
			ataca.setBounds(50, 220, 100, 30);
			ataca.setBackground(SystemColor.window);

			noAtaca.setBounds(200, 220, 150, 30);
			noAtaca.setBackground(SystemColor.window);

			cronometro.setBounds(25, 25, 300, 30);
			cronometro.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20 ));
			cronometro.setForeground(new Color(0, 0, 0));
			
			pregunta.setBounds(15, 70, 350, 50);
			pregunta.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			pregunta.setForeground(new Color(0, 0, 0));
			
			opcion.setBounds(10, 10, 350, 200);
			opcion.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
			opcion.setForeground(new Color(0, 0, 0));

			//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa
			ImageIcon Img = new ImageIcon(getClass().getResource("fondo_ataque.jpg")); 
			
			//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel
			grafico.drawImage(Img.getImage(), 0, 0, 400, 300, null);
			setOpaque(false);
		}
		
		public int deseaAtacarVentana(Jugador jugador) throws InterruptedException {
			
			int cont = 10;
			mensaje = "TIEMPO RESTANTE: ";
			opcion.setVisible(false);
			this.setVisible(true);
			pregunta.setText( jugador.getNombre().toUpperCase() + "\n\r escoja una opción");
			while(cont>=0 && resp==2) {
				Thread.sleep(1000);
				cronometro.setText(mensaje + cont);
				if(cont==3)
					mensaje = "TE QUEDA POCO TIEMPO!  ";
				cont--;
			}
			if(resp == 0) {
				pregunta.setVisible(false);
				cronometro.setVisible(false);
				opcion.setText("Elegiste NO, termino\r\n tu turno");
				opcion.setVisible(true);
				Thread.sleep(1500);
			}
			this.setVisible(false);
			return resp;
		}
		
		class Botones implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				Object click = e.getSource();
				resp = (click == ataca)?1:0;
			}
		}
		
	}

